import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest
{
    private final OptionsParser parser = new OptionsParser();

    @Test
    void validArguments()
    {
        String[] args = {"test01.txt", "test02.txt", "-o", "output", "-p", "pre_", "-a", "-f"};
        AppConfig config = parser.parse(args);

        assertEquals("output", config.getOutputPath().toString());
        assertEquals("pre_", config.getFilePrefix());
        assertTrue(config.isAppendMode());
        assertEquals(AppConfig.StatsType.FULL, config.getStatsType());
        assertEquals(2, config.getInputFiles().size());
    }

    @Test
    void missingOptionValue()
    {
        String[] args1 = {"-o"};
        assertThrows(IllegalArgumentException.class, () -> parser.parse(args1),
                "Missing value for -o option");

        String[] args2 = {"-p"};
        assertThrows(IllegalArgumentException.class, () -> parser.parse(args2),
                "Missing value for -p option");
    }

    @Test
    void conflictingStatsOptions()
    {
        String[] args = {"-s", "-f", "file.txt"};
        assertThrows(IllegalArgumentException.class, () -> parser.parse(args),
                "Conflicting options: -s and -f");
    }

    @Test
    void noInputFiles()
    {
        String[] args = {"-o", "output"};
        assertThrows(IllegalArgumentException.class, () -> parser.parse(args),
                "No input files specified");
    }

    @Test
    void unknownOption()
    {
        String[] args = {"file.txt", "-x"};
        assertThrows(IllegalArgumentException.class, () -> parser.parse(args),
                "Unknown option: -x");
    }

    @Test
    void defaultValues()
    {
        String[] args = {"file.txt"};
        AppConfig config = parser.parse(args);

        assertEquals("", config.getOutputPath().toString());
        assertEquals("", config.getFilePrefix());
        assertFalse(config.isAppendMode());
        assertEquals(AppConfig.StatsType.NONE, config.getStatsType());
        assertEquals(1, config.getInputFiles().size());
    }

    @Test
    void mixedOrder()
    {
        String[] args = {"file1.txt", "-a", "file2.txt", "-p", "test_"};
        AppConfig config = parser.parse(args);

        assertTrue(config.isAppendMode());
        assertEquals("test_", config.getFilePrefix());
        assertEquals(2, config.getInputFiles().size());
    }
}
