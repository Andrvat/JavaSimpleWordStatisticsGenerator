package csvparser.application.runner;

import org.apache.commons.cli.ParseException;

public interface Parseable {
    void parseAll(String[] args) throws ParseException;
}
