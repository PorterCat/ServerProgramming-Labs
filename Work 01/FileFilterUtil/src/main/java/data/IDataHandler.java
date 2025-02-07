package data;

import filefilterutil.FileHandler;

import java.io.IOException;

public interface IDataHandler
{
    boolean match(String line);
    void process(String line) throws IOException;
}
