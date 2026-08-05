package jar.apache.commons.cli;

import org.apache.commons.cli.*;

/**
 * <a href="https://commons.apache.org/proper/commons-cli/">Commons CLI</a>
 *
 * @author ljh
 * @since 2023/7/28 10:23
 */
public class CliDemo {

    public static void main(String[] args) throws ParseException {
        // 1. Creating the Options
        Options options = new Options();
        options.addOption("t", false, "display current time");

        // 2. Parsing the command line arguments
        CommandLineParser parser = new DefaultParser();
        String[] arguments = {"-t"};
        CommandLine cmd = parser.parse(options, arguments);
        if (cmd.hasOption("t")) {
            System.out.println("print the date and time");
        } else {
            System.out.println("print the date");
        }
    }
}
