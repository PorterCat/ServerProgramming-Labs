package data;

import filefilterutil.FileHandler;
import filefilterutil.StatisticsCollector;

import java.io.IOException;

public class FloatHandler implements IDataHandler
{
    private final FileHandler _fileHandler;
    public FloatHandler(FileHandler fileHandler)
    {
        _fileHandler = fileHandler;
    }

    @Override
    public boolean match(String line)
    {
        return line.matches("-?\\d*\\.\\d+");
    }

    // Workaround
    @Override
    public void process(String line) throws IOException
    {
        float tryFloat;
        try
        {
            tryFloat = Float.parseFloat(line);
            _fileHandler.writeData("float", line);
            StatisticsCollector.Floats.add(tryFloat);
        }
        catch (Exception ex)
        {
            _fileHandler.writeData("string", line);
            StatisticsCollector.Strings.add(line);
        }
    }
}
