package filefilterutil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileHandler
{
    private final Path _outputPath;
    private final String _filePrefix;
    private final boolean _appendMode;
    private final Map<String, BufferedWriter> _writers;

    public FileHandler(Options config)
    {
        _outputPath = config.getOutputPath();
        _filePrefix = config.getFilePrefix();
        _appendMode = config.isAppendMode();
        _writers = new HashMap<>();
    }

    public void writeData(String dataType, String data) throws IOException
    {
        BufferedWriter writer = getWriterForType(dataType);
        writer.write(data);
        writer.newLine();
    }

    public void closeAllWriters() throws IOException
    {
        for (BufferedWriter writer : _writers.values())
        {
            if (writer != null)
                writer.close();
        }
    }

    private BufferedWriter getWriterForType(String dataType) throws IOException
    {
        if (!_writers.containsKey(dataType))
        {
            Path filePath = generateFilePath(dataType);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), _appendMode));
            _writers.put(dataType, writer);
        }
        return _writers.get(dataType);
    }

    private Path generateFilePath(String dataType) throws IOException
    {
        String fileName = _filePrefix + dataType + ".txt";
        Path filePath = _outputPath.resolve(fileName);

        if (!Files.exists(_outputPath))
            Files.createDirectories(_outputPath);

        return filePath;
    }
}
