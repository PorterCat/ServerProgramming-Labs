import filefilterutil.Options;
import filefilterutil.DataSorter;
import filefilterutil.FileHandler;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileHandlerTest
{
    private Options _options;
    private FileHandler _fileHandler;
    private DataSorter _dataSorter;
    private Path _tempDir;

    @BeforeEach
    public void setUp() throws IOException
    {
        _tempDir = Files.createTempDirectory("testOutput");

        _options = new Options();
        _options.setOutputPath(_tempDir).setFilePrefix("test_").setAppendMode(false);

        _fileHandler = new FileHandler(_options);
        _dataSorter = new DataSorter(_fileHandler);
    }

    @AfterEach
    public void tearDown() throws IOException
    {
        _fileHandler.closeAllWriters();
        Files.walk(_tempDir)
                .map(Path::toFile)
                .forEach(File::delete);
        Files.deleteIfExists(_tempDir);
    }

    @Test
    public void testWriteDataCreatesCorrectFiles() throws IOException
    {
        String data1 = "10";
        String data2 = "test_string";

        _fileHandler.writeData("integer", data1);
        _fileHandler.writeData("string", data2);
        _fileHandler.closeAllWriters();

        Path integersFile = _tempDir.resolve("test_integer.txt");
        Path stringsFile = _tempDir.resolve("test_string.txt");

        assertTrue(Files.exists(integersFile));
        assertTrue(Files.exists(stringsFile));

        assertEquals(data1, Files.readAllLines(integersFile).getFirst());
        assertEquals(data2, Files.readAllLines(stringsFile).getFirst());
    }
}
