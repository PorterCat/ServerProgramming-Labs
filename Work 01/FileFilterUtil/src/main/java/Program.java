import java.io.IOException;

public class Program
{
    public static void main(String[] args)
    {
        OptionsParser parser = new OptionsParser();
        AppConfig config = new AppConfig();

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
            StatisticsCollector statsCollector = new StatisticsCollector();

            DataProcessor dataProcessor = new DataProcessor(fileHandler, statsCollector);
            dataProcessor.processFiles(config.getInputFiles());

            if(config.getStatsType() == AppConfig.StatsType.SHORT)
                statsCollector.printShortStatistics();
            else if(config.getStatsType() == AppConfig.StatsType.FULL)
                statsCollector.printFullStatistics();

            fileHandler.closeAllWriters();
        }
        catch (IOException e)
        {
            System.out.println("Error initializing file handler: " + e.getMessage());
            System.exit(1);
        }
    }
}
