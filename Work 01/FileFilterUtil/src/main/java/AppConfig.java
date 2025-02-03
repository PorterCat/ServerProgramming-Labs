import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AppConfig
{
    public enum StatsType { SHORT, FULL }

    private Path outputPath = Paths.get("");
    private String filePrefix = "";
    private boolean appendMode = false;
    private StatsType statsType = StatsType.SHORT;
    private final List<Path> inputFiles = new ArrayList<>();

    // Геттеры и сеттеры
    public Path getOutputPath() { return outputPath; }
    public void setOutputPath(Path outputPath) { this.outputPath = outputPath; }
    public String getFilePrefix() { return filePrefix; }
    public void setFilePrefix(String filePrefix) { this.filePrefix = filePrefix; }
    public boolean isAppendMode() { return appendMode; }
    public void setAppendMode(boolean appendMode) { this.appendMode = appendMode; }
    public StatsType getStatsType() { return statsType; }
    public void setStatsType(StatsType statsType) { this.statsType = statsType; }
    public List<Path> getInputFiles() { return inputFiles; }
}