package csvparser.parser.statistics.generator;

import csvparser.parser.reader.CSVParserReader;
import lombok.Builder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Builder
public class CSVStatisticsGenerator {
    private final CSVParserReader parserReader;
    private Integer wordsNumber;

    public Map<String, Integer> generateWordFrequenciesStatistics() throws IOException {
        Map<String, Integer> statistics = new LinkedHashMap<>();
        String currentWord = parserReader.readNextWord();
        while (currentWord != null) {
            wordsNumber++;
            statistics.put(currentWord, calculateCurrentWordAbsoluteFrequency(statistics, currentWord));
            currentWord = parserReader.readNextWord();
        }

        return getSortedStatisticsMapByValue(statistics);
    }

    private Integer calculateCurrentWordAbsoluteFrequency(Map<String, Integer> statistics, String word) {
        return (statistics.containsKey(word)) ? statistics.get(word) + 1 : 1;
    }

    private Map<String, Integer> getSortedStatisticsMapByValue(Map<String, Integer> statistics) {
        return statistics.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
    }

    public Integer getWordsNumber() {
        return wordsNumber;
    }
}
