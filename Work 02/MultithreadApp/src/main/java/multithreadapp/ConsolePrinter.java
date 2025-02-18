package multithreadapp;

public class ConsolePrinter
{
    public enum Color
    {
        RED(31),
        GREEN(32),
        WHITE(37);

        private final int code;

        Color(int code)
        {
            this.code = code;
        }

        public int getCode()
        {
            return code;
        }
    }

    public static void clearLine(int row, int col)
    {
        System.out.print(String.format("\033[%d;%dH", row, col));
        System.out.print("\033[2K");
    }

    public static void clear()
    {
        System.out.print("\033[2J\033[H");
    }

    public static void printAt(int row, int col, String text)
    {
        System.out.print(String.format("\033[%d;%dH%s", row, col, text));
    }

    public static void setColor(ConsolePrinter.Color color)
    {
        System.out.print(String.format("\033[%dm", color.getCode()));
    }
}
