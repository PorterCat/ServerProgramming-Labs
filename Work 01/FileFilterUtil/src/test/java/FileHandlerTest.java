import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FileHandlerTest
{
    AppConfig config;
    private FileHandler fileHandler;
    private Path tempDir;

    @BeforeEach
    public void setUp() throws IOException
    {
        tempDir = Files.createTempDirectory("testOutput");

        config = new AppConfig();
        config.setOutputPath(tempDir).setFilePrefix("test_").setAppendMode(false);

        fileHandler = new FileHandler(config);
    }

    @AfterEach
    public void tearDown() throws IOException
    {
        fileHandler.closeAllWriters();
        Files.walk(tempDir)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void testWriteDataCreatesCorrectFiles() throws IOException
    {
        String data1 = "10\n";
        String data2 = "test_string\n";

        fileHandler.writeData("integers", data1);
        fileHandler.writeData("strings", data2);

        Path integersFile = tempDir.resolve("test_integers.txt");
        Path stringsFile = tempDir.resolve("test_strings.txt");

        assertTrue(Files.exists(integersFile), "Files for integers was created!");
        assertTrue(Files.exists(stringsFile), "Files for strings was created!");

        //assertEquals(data1, Files.readAllLines(integersFile).get(0));
        //assertEquals(data2, Files.readAllLines(stringsFile).get(0));
    }
}
