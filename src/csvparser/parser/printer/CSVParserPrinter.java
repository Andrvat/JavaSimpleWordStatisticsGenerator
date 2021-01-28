package csvparser.parser.printer;

import lombok.Builder;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

@Builder
public class CSVParserPrinter implements Printable {
    private final Map<String, Integer> wordFrequenciesStatistics;
    private final Integer wordsNumber;
    private final String outputFileName;
    private final String delimiter;

    public void printAll() throws FileNotFoundException {
        PrintWriter printer = new PrintWriter(outputFileName);
        for (Map.Entry<String, Integer> pair : wordFrequenciesStatistics.entrySet()) {
            String word = pair.getKey();
            String absoluteWordFrequency = pair.getValue().toString();
            String wordFrequency = String.format("%.4f", (double) pair.getValue() / wordsNumber);
            printer.println(word + delimiter + absoluteWordFrequency + delimiter + wordFrequency);
        }
        printer.close();
    }

}
