package csvparser.parser.printer;

import lombok.Builder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Builder
public class CSVParserPrinter implements Printable {
    private final Map<String, Integer> wordFrequenciesStatistics;
    private final Integer wordsNumber;
    private final String outputFileName;
    private final String delimiter;

    public void printAll() throws IOException {
        PrintWriter printer = new PrintWriter(outputFileName);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> pair : wordFrequenciesStatistics.entrySet()) {
            String word = pair.getKey();
            String absoluteWordFrequency = pair.getValue().toString();
            String wordFrequency = calculateWordFrequency(pair.getValue());

            stringBuilder.setLength(0);
            stringBuilder.append(word)
                    .append(delimiter)
                    .append(absoluteWordFrequency)
                    .append(delimiter)
                    .append(wordFrequency);
            printer.println(stringBuilder.toString());
        }
        printer.close();
    }

    private String calculateWordFrequency(Integer absoluteFrequency) {
        return String.format("%.4f", (double) absoluteFrequency / wordsNumber);
    }

}
