package csvparser.application.runner;

import java.io.IOException;

public interface ParameterizedRunnable {
    void parametrizedRun(String inputFilename, String outputFilename) throws IOException;
}
