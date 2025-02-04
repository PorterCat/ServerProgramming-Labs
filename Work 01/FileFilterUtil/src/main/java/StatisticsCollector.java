public class StatisticsCollector
{
    private int intCount = 0;
    private int floatCount = 0;
    private int stringCount = 0;

    private Integer intMin = null;
    private Integer intMax = null;
    private Double floatMin = null;
    private Double floatMax = null;

    public void addInteger(int value)
    {
        intCount++;
        if (intMin == null || value < intMin) intMin = value;
        if (intMax == null || value > intMax) intMax = value;
    }

    public void addFloat(double value)
    {
        floatCount++;
        if (floatMin == null || value < floatMin) floatMin = value;
        if (floatMax == null || value > floatMax) floatMax = value;
    }

    public void addString(String value)
    {
        stringCount++;
    }

    public void printStatistics()
    {
        System.out.println("Integer stats:");
        System.out.println("Count: " + intCount);
        if (intMin != null && intMax != null)
        {
            System.out.println("Min: " + intMin);
            System.out.println("Max: " + intMax);
        }

        System.out.println("Float stats:");
        System.out.println("Count: " + floatCount);
        if (floatMin != null && floatMax != null)
        {
            System.out.println("Min: " + floatMin);
            System.out.println("Max: " + floatMax);
        }

        System.out.println("String stats:");
        System.out.println("Count: " + stringCount);
    }
}
