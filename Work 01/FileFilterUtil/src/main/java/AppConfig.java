import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AppConfig
{
    public enum StatsType { NONE, SHORT, FULL }

    private Path outputPath = Paths.get("");
    private String filePrefix = "";
    private boolean appendMode = false;
    private StatsType statsType = StatsType.NONE;
    private final List<Path> inputFiles = new ArrayList<>();

    public Path getOutputPath() { return outputPath; }
    public AppConfig setOutputPath(Path outputPath) { this.outputPath = outputPath; return this;}
    public String getFilePrefix() { return filePrefix; }
    public AppConfig setFilePrefix(String filePrefix) { this.filePrefix = filePrefix; return this;}
    public boolean isAppendMode() { return appendMode; }
    public AppConfig setAppendMode(boolean appendMode) { this.appendMode = appendMode; return this;}
    public StatsType getStatsType() { return statsType; }
    public AppConfig setStatsType(StatsType statsType) { this.statsType = statsType; return this;}
    public List<Path> getInputFiles() { return inputFiles; }
}