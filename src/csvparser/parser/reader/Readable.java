package csvparser.parser.reader;

import java.io.IOException;
import java.util.ArrayList;

public interface Readable {
    void readAll() throws IOException;

    ArrayList<String> getAllData();
}
