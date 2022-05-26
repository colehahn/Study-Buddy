package org.studybuddy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.FileWriter;

import com.opencsv.*;

// Code that reads assignment data to a csv:
// https://www.geeksforgeeks.org/reading-csv-file-java-using-opencsv/
// https://www.tutorialspoint.com/how-to-write-data-to-csv-file-in-java#:~:
// text=How%20to%20write%20data%20to%20.csv%20file%20in,the%20writeAll%20%28%29%20method.%20...
// %206%20Example.%20

/**
 * A utility class capable of adding/ removing an assignment in our app.
 */
public class AssignmentManager {

    /**
     * we use HashMap to store the assignment's name with its AssignmentClass
     */
    private HashMap<String, AssignmentClass> assignmentMap;

    public AssignmentManager() {
        assignmentMap = new HashMap<>();
    }

    /**
     * adds a new assignment into the map and saves it to a csv.
     *
     * @param name             name of the assignment.
     * @param description      description of the assignment.
     * @param estimateToFinish estimate time to finish this assignment in format "hh:mm:ss"
     * @param duedate          due date of this assignment in format "MM/dd/YYYY"
     */
    public void addAssignment(String name, String description, String estimateToFinish, String duedate) throws Exception {
        AssignmentClass assignment = new AssignmentClass(name, description, estimateToFinish, duedate);
        CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\dejen\\Desktop\\Study-Buddy\\output.csv"));
//        String data[] = {assignment.getName(), assignment.getDescription(), assignment.getEstimateToFinish(),
//                            assignment.getEstimateToFinish(), assignment.getDuedate().toString()};
        String line1[] = {"id", "name", "salary", "start_date", "dept"};
        writer.writeNext(line1);
        writer.flush();
        assignmentMap.put(name, assignment);
    }

    /**
     * Read a specific assignment from the csv file
     * @param assignmentClass
     * @throws FileNotFoundException
     */
    public void readAssignment(AssignmentClass assignmentClass) throws FileNotFoundException {
        // Instantiating the CSVReader class
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\dejen\\Desktop\\Study-Buddy\\output.csv"));
        // Reading the contents of the csv file
        StringBuffer buffer = new StringBuffer();
        String line[];
        // Getting the iterator object for this reader
        Iterator it = reader.iterator();
        while (it.hasNext()) {
            line = (String[]) it.next();
            System.out.println(Arrays.toString(line));
            System.out.println(" ");
        }
    }

    /**
     * removes an assignment out of our app.
     * @param name name of the assignment needs to remove.
     */
    public void deleteAssignment(String name) {
        assignmentMap.remove(name);
    }

}
