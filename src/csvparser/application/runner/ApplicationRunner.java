package csvparser.application.runner;

import csvparser.parser.printer.CSVParserPrinter;
import csvparser.parser.printer.Printable;
import csvparser.parser.reader.CSVParserReader;
import csvparser.parser.reader.Readable;
import csvparser.parser.statistics.generator.CSVStatisticsGenerator;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Map;

public class ApplicationRunner {
    public static void main(String[] args) {
        Parseable commandLineArgsParser = new CommandLineArgsParser();
        try {
            runApplication(args, commandLineArgsParser);
        } catch (ParseException exception) {
            HelpFormatter formatter = new HelpFormatter();
            exception.printStackTrace(System.out);
            System.out.println(exception.getMessage());
            formatter.printHelp("ApplicationRunner", commandLineArgsParser.getCommandLineArgsOptions());
        } catch (IOException exception) {
            System.out.println(exception.getLocalizedMessage());
            exception.printStackTrace(System.out);
        }
    }

    private static void runApplication(String[] args, Parseable commandLineArgsParser) throws IOException, ParseException {
        commandLineArgsParser.parseCommandLineArguments(args);
        startDataProcessing(commandLineArgsParser.getInputFilename(), commandLineArgsParser.getOutputFilename());
    }

    private static void startDataProcessing(String inputFilename, String outputFilename) throws IOException {
        Readable reader = CSVParserReader.builder()
                .inputFilename(inputFilename)
                .build();
        reader.readAll();

        CSVStatisticsGenerator statisticsGenerator = CSVStatisticsGenerator.builder()
                .dataFromReader(reader.getAllData())
                .build();

        Map<String, Integer> wordFrequenciesStatistics = statisticsGenerator.generateWordFrequenciesStatistics();
        Integer wordsNumber = statisticsGenerator.getWordsNumber();

        Printable printer = CSVParserPrinter.builder()
                .wordFrequenciesStatistics(wordFrequenciesStatistics)
                .wordsNumber(wordsNumber)
                .outputFileName(outputFilename)
                .delimiter(";")
                .build();

        printer.printAll();
    }
}
