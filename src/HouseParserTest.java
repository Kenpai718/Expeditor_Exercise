import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * basing my junit tests off manually parsing of data and checking if the automated parse is the same
 * 
 * Note: the JUnit tests are unfinished because for some reason the file path for the tests are not being accepted :(
 * 
 */
public class HouseParserTest {

    HouseParser myParser;

    @BeforeAll
    public void setUp() {
        // File path is passed as parameter. I have it as an input text file
        File file = new File("./src/input.txt");
        this.myParser = new HouseParser(file);
    }

    @Test
    public void testOccupationSize() {
        /**
         * 4 occupant(s) in "123 main st, seattle, wa"
         * smith ian, 123 main st, seattle, wa, 18
         * smith alice, 123 main st, seattle, wa, 45
         * smith dave, 123 main st, seattle, wa, 43
         */
        assertEquals(this.myParser.getHouseMap().get("123 main st, seattle, wa"), 4);
    }

}
