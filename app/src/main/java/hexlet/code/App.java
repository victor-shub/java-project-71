package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
import java.io.File;

@Command(name = "genDiff", mixinStandardHelpOptions = true, version = "version",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private File file1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private File file2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]", paramLabel = "format")
    String format;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested = false;

    @Option(names = {"-V", "--version"}, description = "Print version information and exit.")
    boolean version;

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(file1, file2));
        return null;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
