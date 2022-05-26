import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.studybuddy.AssignmentClass;

import java.sql.Time;
import java.util.*;

public class AssignmentClassTest {
    Calendar currTime = Calendar.getInstance();
    String assignemntA = "Homework 1";
    String description = "Homework 1: Architecture and Design";
    String estimateToFinish = "01:30:00";
    String dueDate = "06/06/2022";
    String dueDate2 = "05/05/2021";
    @Test
    public void testAssignmentName() throws Exception {
        AssignmentClass assignmentA = new AssignmentClass(assignemntA, description, estimateToFinish, dueDate);
        Assertions.assertEquals(assignmentA.getName(), assignemntA);
    }

    @Test
    public void testAssignmentDescription() throws Exception {
        AssignmentClass assignmentA = new AssignmentClass(assignemntA, description, estimateToFinish, dueDate);
        Assertions.assertEquals(assignmentA.getDescription(), description);
    }

    @Test
    public void testBeforeDue() throws Exception {
        AssignmentClass assignmentA = new AssignmentClass(assignemntA, description, estimateToFinish, dueDate);
        Calendar currTime = Calendar.getInstance();
        Assertions.assertTrue(assignmentA.getDuedate().after(currTime.getTime()));
        System.out.println(assignmentA.getTimeSpent());
    }

    @Test
    public void testPastDue() throws Exception {
        AssignmentClass assignmentB = new AssignmentClass(assignemntA, description, estimateToFinish, dueDate2);
        Calendar currTime = Calendar.getInstance();
        Assertions.assertTrue(assignmentB.getDuedate().before(currTime.getTime()));
    }


}
