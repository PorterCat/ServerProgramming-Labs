import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileHandler
{
    private final Path outputPath;
    private final String filePrefix;
    private final boolean appendMode;
    private final Map<String, BufferedWriter> writers;

    public FileHandler(AppConfig config)
    {
        this.outputPath = config.getOutputPath();
        this.filePrefix = config.getFilePrefix();
        this.appendMode = config.isAppendMode();
        this.writers = new HashMap<>();
    }

    public void writeData(String dataType, String data) throws IOException
    {
        BufferedWriter writer = getWriterForType(dataType);
        writer.write(data);
        writer.newLine();
    }

    public void closeAllWriters() throws IOException
    {
        for (BufferedWriter writer : writers.values())
        {
            if (writer != null)
                writer.close();
        }
    }

    private BufferedWriter getWriterForType(String dataType) throws IOException
    {
        if (!writers.containsKey(dataType))
        {
            Path filePath = generateFilePath(dataType);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), appendMode));
            writers.put(dataType, writer);
        }
        return writers.get(dataType);
    }

    private Path generateFilePath(String dataType) throws IOException
    {
        String fileName = filePrefix + dataType + ".txt";
        Path filePath = outputPath.resolve(fileName);

        if (!Files.exists(outputPath))
            Files.createDirectories(outputPath);

        return filePath;
    }
}
