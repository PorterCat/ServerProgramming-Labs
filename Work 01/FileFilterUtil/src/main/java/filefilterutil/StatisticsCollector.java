package filefilterutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Stupid collector. Need to refactor with IDataHadnler support.
public class StatisticsCollector
{
    public static List<Integer> Ints = new ArrayList<>();
    public static List<Float> Floats = new ArrayList<>();
    public static List<String> Strings = new ArrayList<>();

    public static void printShortStatistics()
    {
        System.out.println("Integer count: " + Ints.size());
        System.out.println("Float count: " + Floats.size());
        System.out.println("Strings count: " + Strings.size());
    }

    public static void printFullStatistics()
    {
        if(Ints.size() > 0)
        {
            System.out.println("Integer stats:");
            System.out.println("Count: " + Ints.size());
            if (Collections.min(Ints) != null)
                System.out.println("Min: " + Collections.min(Ints));
            if (Collections.max(Ints) != null)
                System.out.println("Max: " + Collections.max(Ints));
            System.out.println("Avg:" + Ints.stream().mapToInt(Integer::intValue).average().orElse(0.0));
        }

        if(Floats.size() > 0)
        {
            System.out.println("\nFloat stats:");
            System.out.println("Count: " + Floats.size());
            if (Collections.min(Ints) != null)
                System.out.println("Min: " + Collections.min(Ints));
            if (Collections.max(Ints) != null)
                System.out.println("Max: " + Collections.max(Ints));
            System.out.println("Avg:" + Floats.stream().mapToDouble(Float::floatValue).average().orElse(0.0));
        }

        if(Strings.size() > 0)
        {
            System.out.println("\nString stats:");
            System.out.println("Count: " + Strings.size());
            String longestString = Strings.stream()
                    .max((s1, s2) -> Integer.compare(s1.length(), s2.length()))
                    .orElse("");
            System.out.println("Longest string:" + longestString + " [" + longestString.length() + "]");
        }
    }
}
