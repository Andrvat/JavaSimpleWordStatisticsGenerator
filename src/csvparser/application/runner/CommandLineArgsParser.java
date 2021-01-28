package csvparser.application.runner;

import org.apache.commons.cli.*;

public class CommandLineArgsParser implements Parseable {
    private String inputFilename;
    private String outputFilename;
    private final Options parserOptions;

    CommandLineArgsParser() {
        parserOptions = new Options();
        parserOptions.addOption(createNewOption("i", "input", true, "input csv file name"));
        parserOptions.addOption(createNewOption("o", "output", true, "output csv file name"));
    }

    private Option createNewOption(String opt, String longOpt, Boolean hasArg, String description) {
        Option option = new Option(opt, longOpt, hasArg, description);
        option.setRequired(true);
        return option;
    }

    @Override
    public void parseAll(String[] args) throws ParseException {
        CommandLineParser parser = new BasicParser();
        CommandLine commandLine = parser.parse(parserOptions, args);

        inputFilename = commandLine.getOptionValue("i");
        outputFilename = commandLine.getOptionValue("o");
    }

    public String getInputFilename() {
        return inputFilename;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public Options getParserOptions() {
        return parserOptions;
    }
}
