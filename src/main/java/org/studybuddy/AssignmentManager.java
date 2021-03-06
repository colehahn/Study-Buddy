package org.studybuddy;

//import lombok.EqualsAndHashCode;

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
        assignmentMap = new HashMap<>();
    }

    /**
     * adds a new assignment into the map.
     * @param name name of the assignment.
     * @param description description of the assignment.
     * @param estimateToFinish estimate time to finish this assignment in format "hh:mm:ss"
     * @param duedate due date of this assignment in format "MM/dd/YYYY"
     */
    public void addAssignment(String name, String description, String estimateToFinish, String duedate) {
        AssignmentClass assignment = new AssignmentClass(name, description, estimateToFinish, duedate);
        assignmentMap.put(name, assignment);
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