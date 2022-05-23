import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.studybuddy.AssignmentClass;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class AssignmentClassTest {
    String assignemnt1 = "Homework 1";
    String description = "Homework 1: Architecture and Design";
    Time estimateTime = new Time(20);
    Date dueDate = new Date(01,01,2022);
    @Test
    public void testCreateAnAssignment() {
        AssignmentClass assignment01 = new AssignmentClass(assignemnt1, description, estimateTime, dueDate);
        Assertions.assertEquals(assignment01.getName(), assignemnt1);
    }
}
