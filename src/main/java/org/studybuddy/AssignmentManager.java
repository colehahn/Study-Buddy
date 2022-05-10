package org.studybuddy;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
     * adds a new assignment into the map.
     * @param name name of the assignment.
     * @param description description of the assignment.
     * @param estimateInMinute estimate time to finish this assignment.
     * @param duedate due date of this assignment.
     */
    public void addAssignment(String name, String description, Time estimateInMinute, Date duedate) {
        AssignmentClass assignment = new AssignmentClass(name, description, estimateInMinute, duedate);
        assignmentMap.put(name, assignment);
    }

    /**
     * removes an assignment out of our app.
     * @param name name of the assignment needs to remove.
     */
    public void deleteAssignment(String name) {
        assignmentMap.remove(name);
    }

}
