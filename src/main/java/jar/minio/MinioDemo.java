package jar.minio;

import io.minio.*;
import l.demo.Demo;
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
 * @author ljh
 * @see <a href="https://min.io/docs/minio/linux/developers/java/minio-java.html">Java Quickstart Guide</a>
 * @see <a href="https://min.io/docs/minio/linux/developers/java/API.html">Java Client API Reference</a>
 * @see <a href="https://blog.csdn.net/weixin_42170236/article/details/109356921">MinIO 工具类</a>
 * @since 2021/4/25 16:28
 */
public class MinioDemo extends Demo {

    private MinioClient minioClient;
    private static final String BUCKET_NAME = "demo";

    private final File file = new File(XIAO_XIN_PNG);

    private String objectName;

    @BeforeEach
    public void init() throws Exception {
        minioClient = MinioClient.builder().
            endpoint(String.format("http://%s:9000", MY_SERVER_IP))
            .credentials("minio", "minio123")
            .build();

        // 这里不能使用 File.separator，否则 window 系统不能创建文件夹
        objectName = String.format("images/%s/%s", DateFormatUtils.format(new Date(), "yyyy/MMdd"), file.getName());

        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
        }
    }

    @Test
    public void putObject() throws Exception {
        try (FileInputStream fis = new FileInputStream(file)) {
            ObjectWriteResponse response = minioClient.putObject(
                PutObjectArgs.builder().bucket(BUCKET_NAME)
                    .object(objectName)
                    .stream(fis, fis.available(), -1)
                    .contentType(new MimetypesFileTypeMap().getContentType(file))
                    .build()
            );
            p("etag = " + response.etag());
            p("versionId = " + response.versionId());
            p("bucket = " + response.bucket());
            p("region = " + response.region());
            p("object = " + response.object());
            p("headers =\n" + response.headers().toString());
        }
    }

    @Test
    public void statObject() throws Exception {
        p(minioClient.statObject(StatObjectArgs.builder().bucket(BUCKET_NAME).object(objectName).build()));
    }
}
