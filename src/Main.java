import static org.junit.Assert.assertEquals;

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

import org.junit.Test;

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

    /*map that stores the address and people */
    private static HouseParser myParser;

    public static void main(String[] args) throws Exception {
        System.out.println("_________________________________________________________________________");
        System.out.println("Hello world! This is Ken's input data parser exercise for Expeditors.");
        System.out.println("_________________________________________________________________________");

        // Pass file path through args or use the default input.txt file provided
        String fileName = args.length == 0 ? "./src/input.txt" : args[0];

        // File path is passed as parameter. I have it as an input text file
        File file = new File(fileName);

        /*
         * Utilizing built in list stream functions, aggregate operations, and a custom
         * Person class to filter the data as desired and output to console
         */
        myParser = new HouseParser(file);
        myParser.printFormattedOutput();
    }

}
