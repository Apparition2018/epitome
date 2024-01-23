package jar.truelicense.extra.factory;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * RuntimeUtils
 *
 * @author java
 * @since 2024/1/18 17:20
 */
public final class RuntimeUtils {
    private RuntimeUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    public static String getWindowsSerial(String command) throws IOException {
        String serial = "";
        Process process = Runtime.getRuntime().exec(command);
        try (Scanner scanner = new Scanner(process.getInputStream())) {
            if (scanner.hasNext()) {
                scanner.next();
            }
            if (scanner.hasNext()) {
                serial = scanner.next().trim();
            }
        } finally {
            process.getOutputStream().close();
        }
        return serial;
    }

    public static String getLinuxSerial(String... shell) throws IOException {
        String serial = "";
        Process process = Runtime.getRuntime().exec(shell);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = reader.readLine().trim();
            if (StringUtils.isNotBlank(line)) {
                serial = line;
            }
        } finally {
            process.getOutputStream().close();
        }
        return serial;
    }
}
