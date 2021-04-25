package jar;

import io.minio.*;
import l.demo.Demo;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

/**
 * Minio
 * 
 * MinIO 工具类：https://blog.csdn.net/weixin_42170236/article/details/109356921
 *
 * @author ljh
 * created on 2021/4/25 16:28
 */
public class MinioDemo extends Demo {

    private MinioClient minioClient;
    private String bucket;

    @BeforeEach
    public void init() throws Exception {
        minioClient = MinioClient.builder().
                endpoint("http://localhost:9000")
                .credentials("minio", "minio123")
                .build();
        bucket = "demo";

        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    @Test
    public void upload() throws Exception {
        File file = new File(NOHARA_SINNOSUKE);
        String date = DateFormatUtils.format(new Date(), "yyyy" + File.separator + "MMdd");
        String fileName = "images" + File.separator + date + File.separator + "蜡笔小新." + FilenameUtils.getExtension(file.getName());
        String contentType = new MimetypesFileTypeMap().getContentType(file);
        try (FileInputStream fis = new FileInputStream(file)) {
            ObjectWriteResponse response = minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(fileName)
                            .stream(fis, fis.available(), -1)
                            .contentType(contentType)
                            .build()
            );
            p(response.etag());
        }

    }
}
