package filefilterutil;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
public class OptionsParser
{
    public Options parse(String[] args) throws IllegalArgumentException
    {
        Options config = new Options();
        List<String> inputFiles = new ArrayList<>();

        for(int i = 0; i < args.length; ++i)
        {
            if (args[i].startsWith("-"))
            {
                switch (args[i])
                {
                    case "-o":
                        checkHasNext(args, i, "Missing value for -o option");
                        config.setOutputPath(Path.of(args[++i]));
                        break;

                    case "-p":
                        checkHasNext(args, i, "Missing value for -p option");
                        config.setFilePrefix(args[++i]);
                        break;

                    case "-a":
                        config.setAppendMode(true);
                        break;

                    case "-s":
                        if (config.getStatsType() != Options.StatsType.NONE)
                            throw new IllegalArgumentException("Conflicting options: -s and -f");
                        config.setStatsType(Options.StatsType.SHORT);
                        break;

                    case "-f":
                        if (config.getStatsType() != Options.StatsType.NONE)
                            throw new IllegalArgumentException("Conflicting options: -f and -s");
                        config.setStatsType(Options.StatsType.FULL);
                        break;

                    default:
                        throw new IllegalArgumentException("Unknown option: " + args[i]);
                }
            }
            else
            {
                inputFiles.add(args[i]);
            }
        }

        if (inputFiles.isEmpty())
        {
            throw new IllegalArgumentException("No input files specified");
        }

        inputFiles.forEach(f -> config.getInputFiles().add(Path.of(f)));
        return config;
    }

    private void checkHasNext(String[] args, int index, String message)
    {
        if (index + 1 >= args.length) {
            throw new IllegalArgumentException(message);
        }
    }
}
