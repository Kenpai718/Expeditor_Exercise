import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is an exercise from Expeditor.
 * It is suppose to sort passed in data of people, household, location and age
 * THe output should have each household and number of occupants, followed by:
 * Each First Name, Last Name, Address and Age sorted by Last Name then
 * First Name where the occupant(s) is older than 18
 * 
 * 
 * @author Kenneth Ahrens
 */

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("_________________________________________________________________________");
        System.out.println("Hello world! This is Ken's input data parser exercise for Expeditors.");
        System.out.println("_________________________________________________________________________");

        //Pass file path through args or use the default input.txt file provided
        String fileName = args.length == 0 ? "./src/input.txt" : args[0];

        // File path is passed as parameter. I have it as an input text file
        File file = new File(fileName);

        /*
         * Utilizing built in list stream functions, aggregate operations, and a custom
         * Person class to filter the data as desired and output to console
         */
        List<String> inputLines = readLines(file);
        printFormattedOutput(inputLines);
    }

    /**
     * Preforms stream transformations to format the input data into desired output
     * @param theInputLines
     * @return List<Person> formatted list of data to be parsed
     */
    private static Map<String, List<Person>> printFormattedOutput(List<String> theInputLines) {
        Map<String, List<Person>> householdMap = buildHouseholdMap(theInputLines);
        //loop through the sorted map and show each household and its occupant
        householdMap.entrySet().forEach(household -> {
            System.out.println("\n" + household.getValue().size() + " occupant(s) in \"" + household.getKey() + "\"");

            //format: Each First Name, Last Name, Address and Age sorted by Last Name then First Name where the occupant(s) is older than 18
            household.getValue().stream()
            .filter(Person::isAdult) // must be older than 18 to be listed
            .sorted() //sorts elements of a string according to natural order
            .forEach(System.out::println); //print each occupant in this household per line
        }
        );

        return householdMap;
    }

    /**
     * Helper method, builds a map for a household
     * Key = address, value = list of person occuppants
     * @param theInputLines
     * @return Map<String, List<Person>> mapping 
     */
    private static Map<String, List<Person>> buildHouseholdMap(final List<String> theInputLines) {
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
    private static List<String> readLines(File theFile) throws IOException {
        // try catch in case the input file does not exist
        try (InputStream inputStream = new FileInputStream(theFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            // put lines into a list with a collector
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }
    }
}
