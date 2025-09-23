package algo;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private final FileWriter writer;
    private boolean headerWritten = false;

    public CsvWriter(String file) throws IOException {
        writer = new FileWriter(file, false); // перезаписать файл
    }

    public void writeHeader(String... headers) throws IOException {
        if (!headerWritten) {
            writer.write(String.join(",", headers) + "\n");
            headerWritten = true;
        }
    }

    public void writeRow(Object... values) throws IOException {
        for (int i = 0; i < values.length; i++) {
            writer.write(values[i].toString());
            if (i < values.length - 1) writer.write(",");
        }
        writer.write("\n");
    }

    public void close() throws IOException {
        writer.close();
    }
}
