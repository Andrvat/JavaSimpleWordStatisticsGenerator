package csvparser.parser.reader;

import lombok.Builder;

import java.io.*;
import java.util.ArrayList;

@Builder
public class CSVParserReader implements Readable {
    private final String inputFilename;
    private ArrayList<String> data;

    @Override
    public void readAll() throws IOException {
        if (!isFileAvailable(inputFilename)) {
            throw new FileNotFoundException("CSV Parser Reader: file doesn't available");
        }

        BufferedReader reader = new BufferedReader(new FileReader(inputFilename));

        data = new ArrayList<>();

        String token;
        while ((token = reader.readLine()) != null) {
            data.add(token);
        }
        reader.close();
    }

    private boolean isFileAvailable(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    @Override
    public ArrayList<String> getAllData() {
        return data;
    }
}
