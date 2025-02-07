package data;

import filefilterutil.FileHandler;

import java.io.IOException;

public class StringHandler implements IDataHandler
{
    private final FileHandler _fileHandler;
    public StringHandler(FileHandler fileHandler)
    {
        _fileHandler = fileHandler;
    }

    @Override
    public boolean match(String line)
    {
        return true;
    }

    @Override
    public void process(String line) throws IOException
    {
        _fileHandler.writeData("string", line);
    }
}
