package jar.minio;

import io.minio.*;
import io.minio.errors.*;
import l.demo.Demo;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Minio
 * <pre>
 * <a href="http://docs.minio.org.cn/docs/master/java-client-quickstart-guide">Java Client 快速入门指南</a>
 * <a href="http://docs.minio.org.cn/docs/master/java-client-api-reference">Java Client API 参考文档</a>
 * <a href="https://blog.csdn.net/weixin_42170236/article/details/109356921">MinIO 工具类</a>
 * </pre>
 *
 * @author ljh
 * @since 2021/4/25 16:28
 */
public class MinioDemo extends Demo {

    private static MinioClient minioClient;
    private static final String BUCKET = "demo";

    public MinioDemo() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient = MinioClient.builder().
                endpoint("http://localhost:9000")
                .credentials("minio", "minio123")
                .build();

        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET).build());
        }
    }

    public static void main(String[] args) {
        File file = new File(XIAO_XIN_IMG);
        // 这里不能使用 File.separator，否则 window 系统不能创建文件夹
        String date = DateFormatUtils.format(new Date(), "yyyy/MMdd");
        String fileName = "images/" + date + "/蜡笔小新." + FilenameUtils.getExtension(file.getName());
        String contentType = new MimetypesFileTypeMap().getContentType(file);
        try {
            minioClient.statObject(StatObjectArgs.builder().bucket(BUCKET).object(fileName).build());
            try (FileInputStream fis = new FileInputStream(file)) {
                ObjectWriteResponse response = minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(BUCKET)
                                .object(fileName)
                                .stream(fis, fis.available(), -1)
                                .contentType(contentType)
                                .build()
                );
                p("etag = " + response.etag());
                p("versionId = " + response.versionId());
                p("headers = " + response.headers().toString());
                p("bucket = " + response.bucket());
                p("region = " + response.region());
                p("object = " + response.object());
            }
        } catch (Exception e) {
            p(e.getMessage());
        }
    }
}
