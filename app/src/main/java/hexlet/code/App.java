package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.util.concurrent.Callable;

@Command(name = "genDiff", mixinStandardHelpOptions = true, version = "version",
        description = "Compares two configuration files and shows a difference.")
class GenDiff implements Callable<Integer> {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested = false;

    @Option(names = {"-V", "--version"}, description = "Print version information and exit.")
    boolean version;

    @Override
    public Integer call() throws Exception {
        return null;
    }
}

public class App {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new GenDiff()).execute(args);
        System.exit(exitCode);
    }
}
