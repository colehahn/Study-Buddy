package org.studybuddy;

import java.io.*;
import java.util.*;

import com.opencsv.*;

// Code that reads assignment data to a csv:
// https://www.geeksforgeeks.org/reading-csv-file-java-using-opencsv/
// https://www.tutorialspoint.com/how-to-write-data-to-csv-file-in-java#:~:
// text=How%20to%20write%20data%20to%20.csv%20file%20in,the%20writeAll%20%28%29%20method.%20...
// %206%20Example.%20

import java.sql.Time;
import java.util.*;

/**
 * A utility class capable of adding/ removing an assignment in our app.
 */
//@Data
//@EqualsAndHashCode
public class AssignmentManager implements Iterable<AssignmentClass> {

    /**
     * we use HashMap to store the assignment's name with its AssignmentClass
     */
    private HashMap<String, AssignmentClass> assignmentMap;

    public AssignmentManager() {
        // Read every assignment from csv file
        assignmentMap = new HashMap<>();
        // Read in all assignments from csv,
        // and then populate the map.
        readAssignments();
    }

    /**
     * adds a new assignment into the map and saves it to a csv.
     *
     * @param name             name of the assignment.
     * @param description      description of the assignment.
     * @param estimateToFinish estimate time to finish this assignment in format "hh:mm:ss"
     * @param duedate          due date of this assignment in format "MM/dd/YYYY"
     */
    public void addAssignment(String name, String description, String estimateToFinish, String duedate) {
        AssignmentClass assignment = new AssignmentClass(name, description, estimateToFinish, duedate);
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter("output.csv"));
            String data[] = {assignment.getName(), assignment.getDescription(), assignment.getEstimateToFinish(),
                                assignment.getEstimateToFinish(), assignment.getDuedate().toString()};
            writer.writeNext(data);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assignmentMap.put(name, assignment);
    }

    /**
     * Read all the assignments from the csv file in.
     * @throws FileNotFoundException
     */
    public void readAssignments() {
        // Instantiating the CSVReader class
        CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("output.csv")));
        // Reading the contents of the csv file
        StringBuffer buffer = new StringBuffer();
        String line[];
        // Getting the iterator object for this reader
        Iterator it = reader.iterator();
        // Getting the iterator object for this reader
        // read in each line from the csv file, construct an assignment,
        // and add it to the map.
        while (it.hasNext()) {
            line = (String[]) it.next();
            System.out.println(Arrays.toString(line));
            assignmentMap.put(line[0], new AssignmentClass(line[0], line[1], line[2], line[4]));
        }
    }

    public void addAssignment(AssignmentClass assignment) {
        assignmentMap.put(assignment.getName(), assignment);
    }

    /**
     * removes an assignment out of our app.
     * @param name name of the assignment needs to remove.
     */
    public void deleteAssignment(String name) {
        assignmentMap.remove(name);
    }

    public boolean containsAssignment(String name) {
        return assignmentMap.containsKey(name);
    }

    @Override
    public Iterator<AssignmentClass> iterator() {
        ArrayList<AssignmentClass> array =  new ArrayList<>(assignmentMap.values());
        Collections.sort(array, new SortByDate());
        Iterator<AssignmentClass> iter =  array.iterator();
        return iter;
    }
    /**
     * Read a specific assignment from the csv file
     * @param assignmentClass
     * @throws FileNotFoundException
     */
    public void readAssignment(AssignmentClass assignmentClass) throws Exception {
        // Instantiating the CSVReader class
        CSVReader reader = new CSVReader(new FileReader("output.csv"));
        // Reading the contents of the csv file
        StringBuffer buffer = new StringBuffer();
        String line[];
        // Getting the iterator object for this reader
        // and print out the assignment with the same
        // name as the passed in assignment.
        Iterator it = reader.iterator();
        while (it.hasNext()) {
            line = (String[]) it.next();
            if (assignmentClass.getName().equals(line[0])) {
                System.out.println(Arrays.toString(line));
            }
        }
    }

}


class SortByDate implements Comparator<AssignmentClass> {
    public int compare(AssignmentClass a, AssignmentClass b) {
        if (a.getDuedate().before(b.getDuedate())) {
            return -1;
        } else {
            return 1;
        }
    }
}