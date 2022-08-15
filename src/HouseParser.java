import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Parses input data like
 * "Dave","Smith","123 main st.","seattle","wa","43"
 * "Alice","Smith","123 Main St.","Seattle","WA","45"
 * 
 * and formats it to be:
 * Each household and number of occupants, followed by:
 * Each First Name, Last Name, Address and Age sorted by Last Name then First
 * Name where the occupant(s) is older than 18
 *
 * Utilizes Stream API to make it easier to work with maps and lists
 * @author Kenneth Ahrens
 */

public class HouseParser {

    /* map that stores the address and people */
    private static Map<String, List<Person>> myHouseMap;

    /**
     * 
     * @param theFile
     */
    public HouseParser(final File theFile) {
        try {
            List<String> inputLines = readLines(theFile);
            myHouseMap = buildHouseholdMap(inputLines);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Preforms stream transformations to format the input data into desired output
     * and output it to the console
     * 
     * @param theInputLines
     * @return List<Person> formatted list of data to be parsed
     */
    public Map<String, List<Person>> printFormattedOutput() {
        // loop through the sorted map and show each household and its occupant
        myHouseMap.entrySet().forEach(household -> {
            System.out.println("\n" + household.getValue().size() + " occupant(s) in \"" + household.getKey() + "\"");

            // format: Each First Name, Last Name, Address and Age sorted by Last Name then
            // First Name where the occupant(s) is older than 18
            household.getValue().stream()
                    .filter(Person::isAdult) // must be older than 18 to be listed
                    .sorted() // sorts elements of a string according to natural order
                    .forEach(System.out::println); // print each occupant in this household per line
        });

        return myHouseMap;
    }

    /**
     * Helper method, builds a map for a household
     * Key = address, value = list of person occuppants
     * 
     * @param theInputLines
     * @return Map<String, List<Person>> mapping
     */
    private Map<String, List<Person>> buildHouseholdMap(final List<String> theInputLines) {
        return (Map<String, List<Person>>) theInputLines.stream()
                .map(Person::new) // transform the string into a person object
                .collect(Collectors.groupingBy(Person::getAddress)); // group the map by address of a person
    }

    /**
     * Helper method to read lines from an input file and store in a list
     * 
     * @param theFile
     * @return
     * @throws IOException
     */
    private List<String> readLines(File theFile) throws IOException {
        // try catch in case the input file does not exist
        try (InputStream inputStream = new FileInputStream(theFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            // put lines into a list with a collector
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 
     * @return house map
     */
    public Map<String, List<Person>> getHouseMap() {
        return myHouseMap;
    }
}
