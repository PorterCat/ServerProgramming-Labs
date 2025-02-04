import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DataProcessor {
    private final FileHandler fileHandler;
    private final StatisticsCollector statsCollector;

    public DataProcessor(FileHandler fileHandler, StatisticsCollector statsCollector)
    {
        this.fileHandler = fileHandler;
        this.statsCollector = statsCollector;
    }

    public void processFiles(List<Path> inputFiles)
    {
        for (Path filePath : inputFiles)
        {
            try (BufferedReader reader = Files.newBufferedReader(filePath))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    processLine(line);
                }
            }
            catch (IOException e)
            {
                System.out.println("Error reading file: " + filePath + " - " + e.getMessage());
            }
        }
    }

    private void processLine(String line)
    {
        if (line.matches("-?\\d+"))
        {
            try
            {
                fileHandler.writeData("integers", line);
                statsCollector.addInteger(Integer.parseInt(line));
            }
            catch (IOException e)
            {
                System.out.println("Error writing integer: " + e.getMessage());
            }
        }
        else if (line.matches("-?\\d*\\.\\d+"))
        {
            try
            {
                fileHandler.writeData("floats", line);
                statsCollector.addFloat(Float.parseFloat(line));
            }
            catch (IOException e)
            {
                System.out.println("Error writing float: " + e.getMessage());
            }
        }
        else
        {
            try
            {
                fileHandler.writeData("strings", line);
                statsCollector.addString(line);
            }
            catch (IOException e)
            {
                System.out.println("Error writing string: " + e.getMessage());
            }
        }
    }
}
