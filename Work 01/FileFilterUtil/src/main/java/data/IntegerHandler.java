package data;

import filefilterutil.FileHandler;
import filefilterutil.StatisticsCollector;

import java.io.IOException;
public class IntegerHandler implements IDataHandler
{
    private final FileHandler _fileHandler;
    public IntegerHandler(FileHandler fileHandler)
    {
        _fileHandler = fileHandler;
    }

    @Override
    public boolean match(String line)
    {
        return line.matches("-?\\d+");
    }

    // Workaround
    @Override
    public void process(String line) throws IOException
    {
        int tryInt;
        try
        {
            tryInt = Integer.parseInt(line);
            _fileHandler.writeData("integer", line);
            StatisticsCollector.Ints.add(tryInt);
        }
        catch (Exception ex)
        {
            _fileHandler.writeData("string", line);
            StatisticsCollector.Strings.add(line);
        }
    }
}

