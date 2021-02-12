package csvparser.parser.reader;

import lombok.Builder;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@Builder
public class CSVParserReader implements Readable {
    private final BufferedReader reader;
    private final Queue<String> readerWordQueue;

    @Override
    public String readNextWord() throws IOException {
        if (readerWordQueue.isEmpty()) {
            String nextLine = reader.readLine();
            if (nextLine == null) {
                reader.close();
                return null;
            }
            String[] allWordsInLine = nextLine.split(" ");
            readerWordQueue.addAll(Arrays.asList(allWordsInLine));
        }
        return readerWordQueue.remove();
    }
}
