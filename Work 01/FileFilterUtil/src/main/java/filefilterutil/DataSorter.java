package filefilterutil;

import data.FloatHandler;
import data.IDataHandler;
import data.IntegerHandler;
import data.StringHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DataSorter
{

    private final List<IDataHandler> _handlers;

    public DataSorter(FileHandler fileHandler)
    {
        // StringHandler must be on the last place
        // Now it's place we should check if implements more dataTypes
        _handlers = List.of(
                // e.g. new PositiveIntegerHandler(fileHandler),
                // new SmileHandler(fileHandler) with :),
                new IntegerHandler(fileHandler),
                new FloatHandler(fileHandler),
                new StringHandler(fileHandler)
        );
    }

    // This method may be done with String delimiter for flexibility. Now it just read every line
    public void processFiles(List<Path> inputFiles)
    {
        for (Path filePath : inputFiles)
        {
            try (BufferedReader reader = Files.newBufferedReader(filePath))
            {
                String line;
                while ((line = reader.readLine()) != null)
                    processLine(line);
            }
            catch (IOException e)
            {
                System.out.println("Error reading file: " + filePath + " - " + e.getMessage());
            }
        }
    }

    public void processLine(String line)
    {
        for(IDataHandler handler : _handlers)
        {
            if(handler.match(line))
            {
                try
                {
                    handler.process(line);
                }
                catch(IOException e)
                {
                    System.out.println("Error processing line: " + line + " - " + e.getMessage());
                }
                break;
            }
        }
    }
}
