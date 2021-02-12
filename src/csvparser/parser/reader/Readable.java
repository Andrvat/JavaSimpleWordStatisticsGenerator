package csvparser.parser.reader;

import java.io.IOException;

public interface Readable {
    String readNextWord() throws IOException;
}
