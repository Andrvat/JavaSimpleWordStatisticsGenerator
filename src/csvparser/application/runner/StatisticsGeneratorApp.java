package csvparser.application.runner;

import csvparser.parser.printer.CSVParserPrinter;
import csvparser.parser.reader.CSVParserReader;
import csvparser.parser.statistics.generator.CSVStatisticsGenerator;

import java.io.IOException;
import java.util.Map;

public final class StatisticsGeneratorApp implements ParameterizedRunnable {

    @Override
    public void parametrizedRun(String inputFilename, String outputFilename) throws IOException {
        CSVParserReader reader = CSVParserReader.builder()
                .inputFilename(inputFilename)
                .build();
        reader.readAll();

        CSVStatisticsGenerator statisticsGenerator = CSVStatisticsGenerator.builder()
                .dataFromReader(reader.getAllData())
                .build();

        Map<String, Integer> wordFrequenciesStatistics = statisticsGenerator.generateWordFrequenciesStatistics();
        Integer wordsNumber = statisticsGenerator.getWordsNumber();

        CSVParserPrinter printer = CSVParserPrinter.builder()
                .wordFrequenciesStatistics(wordFrequenciesStatistics)
                .wordsNumber(wordsNumber)
                .outputFileName(outputFilename)
                .delimiter(";")
                .build();

        printer.printAll();
    }
}
