package csvparser.application.runner;

import org.apache.commons.cli.*;

public class CommandLineArgsParser implements Parseable {
    private String inputFilename;
    private String outputFilename;

    @Override
    public void parseCommandLineArguments(String[] args) throws ParseException {
        CommandLineParser parser = new BasicParser();
        Options parserOptions = getCommandLineArgsOptions();
        CommandLine commandLine = parser.parse(parserOptions, args);
        inputFilename = commandLine.getOptionValue("i");
        outputFilename = commandLine.getOptionValue("o");
    }

    @Override
    public Options getCommandLineArgsOptions() {
        Options parserOptions = new Options();
        parserOptions.addOption(getOption("i", "input", true, "input csv file name"));
        parserOptions.addOption(getOption("o", "output", true, "output csv file name"));

        return parserOptions;
    }

    private Option getOption(String opt, String longOpt, Boolean hasArg, String description) {
        Option option = new Option(opt, longOpt, hasArg, description);
        option.setRequired(true);
        return option;
    }

    @Override
    public String getInputFilename() {
        return inputFilename;
    }

    @Override
    public String getOutputFilename() {
        return outputFilename;
    }
}
