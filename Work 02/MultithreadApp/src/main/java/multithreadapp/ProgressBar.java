package multithreadapp;

import java.awt.*;

public class ProgressBar
{
    public static int MaxValue = 20;
    private int _x = 0;
    private int _y = 0;
    private int _capacity = 0;

    public ProgressBar(int x, int y)
    {
        _x = x;
        _y = y;
    }

    public ProgressBar increase() {_capacity++; return this; }
    public ProgressBar visualise()
    {
        String bar = "[";
        for(int i = 0; i < MaxValue; ++i)
        {
            bar += (i < _capacity) ? "=" : "_";
        }
        bar += String.format("] %d/%d", _capacity, MaxValue);
        ConsolePrinter.printAt(_y, _x, bar);

        return this;
    }

    public ProgressBar complete(String text)
    {
        ConsolePrinter.setColor(ConsolePrinter.Color.GREEN);
        ConsolePrinter.printAt(_y, _x, text);
        ConsolePrinter.setColor(ConsolePrinter.Color.WHITE);

        return this;
    }

    public ProgressBar clear()
    {
        ConsolePrinter.clearLine(_y, _x);
        return this;
    }
}
