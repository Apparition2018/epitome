package org.ljh.dependencies.group.jakarta.activation;

import jakarta.activation.MimetypesFileTypeMap;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.ljh.commons.Demo.*;

/**
 * MimetypesFileTypeMap
 *
 * @author ljh
 * @since 2021/4/25 17:38
 */
public class MimetypesFileTypeMapTests {

    @Test
    public void test() {
        p(new MimetypesFileTypeMap().getContentType(new File(XIAO_XIN_PNG)));           // image/png
        p(new MimetypesFileTypeMap().getContentType(new File(JDBC_PROPS_FILENAME)));    // application/octet-stream
    }
}
