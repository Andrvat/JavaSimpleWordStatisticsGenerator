package csvparser.parser.reader;

import java.io.IOException;

public interface Readable {
    void readAll() throws IOException;

    void readNextWord();
}
