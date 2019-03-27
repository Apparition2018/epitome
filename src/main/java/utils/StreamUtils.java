package utils;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class StreamUtils {

    // InputStream → File
    public static void inputStreamToFile(InputStream is, File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            byte[] buf = new byte[8192];
            int len;
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
    }

}
