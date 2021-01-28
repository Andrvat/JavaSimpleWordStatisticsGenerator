package csvparser.application.runner;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public interface Parseable {
    void parseCommandLineArguments(String[] args) throws ParseException;

    Options getCommandLineArgsOptions();

    String getInputFilename();

    String getOutputFilename();
}
