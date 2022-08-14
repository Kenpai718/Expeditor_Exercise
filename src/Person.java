/**
 * Info on a person such as name, age, address
 * 
 * @author Kenneth Ahrens
 */
public class Person implements Comparable<Person>{
    private final String myFirstName;
    private final String myLastName;
    private final int myAge;
    private final String myAddress;

    
    /**
     * 
     * @param theLine
     */
    public Person(final String theLine) {
        //expected that the fields of the string are in order
        //0 = first name, 1 = last name, 2 = street, 3 = city, 4 = state, 5 = age

        //split the fields by quotation marks
        String[] fields = theLine.split("\",\"");

        myFirstName = cleanString(fields[0]);
        myLastName = cleanString(fields[1]);
        myAddress= cleanString(fields[2]) + ", " + cleanString(fields[3]) + ", " + cleanString(fields[4]);
        myAge = Integer.parseInt(cleanString(fields[5]));

    }

    /**
     * Cleans the string, removes all unnecessary characters and makes it 
     * lowercase for consistency
     * 
     * @param str
     * @return formatted string
     */
    private String cleanString(final String str) {
        return str
        .replaceAll("\"", "")
        .replaceAll(",", "")
        .replaceAll("\\.", "")
        .trim()
        .toLowerCase();
    }

    /**
     * Return true if age is 18 or over
     * @return boolean 
     */
    public boolean isAdult() {
        return myAge >= 18;
    }

    public String toString() {
        return myLastName + " " + myFirstName + ", " + myAddress + ", " + myAge;
    }

    /**
     * Return first name
     * @return String
     */
    public String getFirstName() {
        return myFirstName;
    }

    /**
     * Return last name
     * @return String
     */
    public String getLastName() {
        return myLastName;
    }

    /**
     * Return age
     * @return int
     */
    public int getAge() {
        return myAge;
    }

    /**
     * Return address
     * @return String
     */
    public String getAddress() {
        return myAddress;
    }

    @Override
    public int compareTo(final Person person) {
        //equal if same first and last name
        return person.getFirstName().equals(getFirstName()) 
            && person.getLastName().equals(getLastName())
            && person.getAddress().equals(getAddress())
            && person.getAge() == getAge()
            ? 1 : -1;
    }
}
