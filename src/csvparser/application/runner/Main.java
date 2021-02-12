package csvparser.application.runner;

import csvparser.parser.printer.CSVParserPrinter;
import csvparser.parser.reader.CSVParserReader;
import csvparser.parser.statistics.generator.CSVStatisticsGenerator;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CommandLineArgsParser commandLineArgsParser = new CommandLineArgsParser();
        try {
            commandLineArgsParser.parseAll(args);

            StatisticsGeneratorApp statisticsGenerator = new StatisticsGeneratorApp();
            statisticsGenerator.parametrizedRun(commandLineArgsParser.getInputFilename(),
                    commandLineArgsParser.getOutputFilename());
        } catch (ParseException exception) {
            HelpFormatter formatter = new HelpFormatter();
            exception.printStackTrace(System.out);
            System.out.println(exception.getMessage());
            formatter.printHelp("ApplicationRunner", commandLineArgsParser.getParserOptions());
        } catch (IOException exception) {
            System.out.println(exception.getLocalizedMessage());
            exception.printStackTrace(System.out);
        }
    }
}
