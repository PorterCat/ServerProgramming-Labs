package filefilterutil;
import java.io.IOException;

public class Program
{
    public static void main(String[] args)
    {
        OptionsParser parser = new OptionsParser();
        Options config = new Options();

        try
        {
            config = parser.parse(args);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        try
        {
            FileHandler fileHandler = new FileHandler(config);

            DataSorter dataSorter = new DataSorter(fileHandler);
            dataSorter.processFiles(config.getInputFiles());

            if(config.getStatsType() == Options.StatsType.SHORT)
                StatisticsCollector.printShortStatistics();
            else if(config.getStatsType() == Options.StatsType.FULL)
                StatisticsCollector.printFullStatistics();

            fileHandler.closeAllWriters();
        }
        catch (IOException e)
        {
            System.out.println("Error initializing file handler: " + e.getMessage());
            System.exit(1);
        }
    }
}
