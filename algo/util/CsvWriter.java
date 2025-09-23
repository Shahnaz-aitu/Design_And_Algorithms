package algo.util;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter implements AutoCloseable {
    private final FileWriter writer;

    public CsvWriter(String filename) {
        try {
            this.writer = new FileWriter(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeRow(Object... values) {
        try {
            for (int i = 0; i < values.length; i++) {
                writer.write(values[i].toString());
                if (i < values.length - 1) {
                    writer.write(",");
                }
            }
            writer.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
