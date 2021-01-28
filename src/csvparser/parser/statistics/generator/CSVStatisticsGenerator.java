package csvparser.parser.statistics.generator;

import lombok.Builder;

import java.util.*;
import java.util.stream.Collectors;

@Builder
public class CSVStatisticsGenerator {
    private final ArrayList<String> dataFromReader;

    public Map<String, Integer> generateWordFrequenciesStatistics() {
        Map<String, Integer> statistics = new LinkedHashMap<>();
        ArrayList<String> allWords = getAllWords();
        for (String word : allWords) {
            statistics.put(word, calculateCurrentWordAbsoluteFrequency(statistics, word));
        }

        return getSortedStatisticsMapByValue(statistics);
    }

    private ArrayList<String> getAllWords() {
        ArrayList<String> allWords = new ArrayList<>();
        for (String dataLine : dataFromReader) {
            String[] allWordsInLine = dataLine.split(" ");
            allWords.addAll(Arrays.asList(allWordsInLine));
        }

        return allWords;
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
        return getAllWords().size();
    }
}
