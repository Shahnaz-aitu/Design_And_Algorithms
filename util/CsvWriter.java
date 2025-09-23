package util;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter implements AutoCloseable {
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < values.length - 1) sb.append(",");
        }
        sb.append("\n");
        writer.write(sb.toString());
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
