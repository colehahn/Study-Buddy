package org.studybuddy;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AssignmentManager {

    // we use HashMap to store the assignment's name with its AssignmentClass
    private HashMap<String, AssignmentClass> assignmentMap;

    public AssignmentManager() {
        assignmentMap = new HashMap<>();
    }

    // adds a new assignment into the map.
    public void addAssignment(String name, String description, Time estimateInMinute, Date duedate) {
        AssignmentClass assignment = new AssignmentClass(name, description, estimateInMinute, duedate);
        assignmentMap.put(name, assignment);
    }

    // deletes an assignment
    public void deleteAssignment(String name) {
        assignmentMap.remove(name);
    }

}
