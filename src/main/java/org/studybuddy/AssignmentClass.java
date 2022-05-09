package org.studybuddy;
import java.util.Date;
import java.sql.Time;

public class AssignmentClass {
    private String name;
    private String description;
    private Time estimateInMinute;
    private Time timeSpent;
    private Date duedate;

    // Construction and Initialization
    public AssignmentClass (String name, String description, Time estimateInMinute, Date duedate) {
        this.name = name;
        this.description = description;
        this.estimateInMinute = estimateInMinute;
        this.duedate = duedate;
        this.timeSpent = new Time(0);
    }

    // returns the name of this assignment
    public String getName() {
        return name;
    }

    // returns the description of this assignment
    public String getDescription() {
        return description;
    }

    // returns estimate time for this assignment
    public String getEstimateInMinute() {
        return estimateInMinute.toString();
    }

    // returns time has spent on this assignment
    public String getTimeSpent() {
        return timeSpent.toString();
    }

    // returns due date of this assignment
    public Date getDuedate() {
        return duedate;
    }

    // updates time spent on this assignment
    public void updateTimeSpent(Time time) {
        this.timeSpent = new Time (timeSpent.getTime() + time.getTime());
    }

    // changes due date for this assignment
    public void changeDueDate(Date newDue) {
        this.duedate = newDue;
    }
}
