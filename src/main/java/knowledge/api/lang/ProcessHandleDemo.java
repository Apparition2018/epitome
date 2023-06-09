package knowledge.api.lang;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.ZoneId;

/**
 * ProcessHandle
 * <p><a href="https://openjdk.org/jeps/102">JDK9 JEP 102: Process API Updates</a>
 *
 * @author ljh
 * @since 2023/2/7 11:14
 */
public class ProcessHandleDemo {

    public static void main(String[] args) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");
        final String np = "Not Present";
        Process process = processBuilder.start();

        ProcessHandle.Info info = process.info();
        System.out.printf("Process ID : %s%n", process.pid());
        System.out.printf("Command name : %s%n", info.command().orElse(np));
        System.out.printf("Command line : %s%n", info.commandLine().orElse(np));

        System.out.printf("Start time: %s%n",
                info.startInstant().map(i -> i.atZone(ZoneId.systemDefault())
                        .toLocalDateTime().toString()).orElse(np));

        System.out.printf("Arguments : %s%n", info.arguments().map(a -> String.join(StringUtils.SPACE, a)).orElse(np));
        System.out.printf("User : %s%n", info.user().orElse(np));
    }
}
