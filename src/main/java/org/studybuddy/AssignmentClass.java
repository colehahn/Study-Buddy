package org.studybuddy;
import java.util.Date;
import java.sql.Time;

/**
 * This represents one Assignment in our app.
 */

public class AssignmentClass {

    /**
     * name of the assignment
     */
    private String name;

    /**
     * Assignment description
     */
    private String description;

    /**
     * Estimate time to finish this assignment.
     */
    private Time estimateInMinute;

    /**
     * Time has been spent on this assignment.
     */
    private Time timeSpent;

    /**
     * Assignment due date.
     */
    private Date duedate;

    /**
     * Create a new assignment containing the provided data.
     * @param name   name of this assignment.
     * @param description assignment description
     * @param estimateInMinute estimate time to finish this assignment.
     * @param duedate assignment due date
     */
    public AssignmentClass (String name, String description, Time estimateInMinute, Date duedate) {
        this.name = name;
        this.description = description;
        this.estimateInMinute = estimateInMinute;
        this.duedate = duedate;
        this.timeSpent = new Time(0);
    }

    /**
     * returns the name of this assignment
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the description of this assignment
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return estimate time to finish this assignment
     */
    public String getEstimateInMinute() {
        return estimateInMinute.toString();
    }

    /**
     *
     * @return time has been spent on this assignment
     */
    public String getTimeSpent() {
        return timeSpent.toString();
    }

    /**
     *
     * @return due date of this assignment
     */
    public Date getDuedate() {
        return duedate;
    }

    /**
     * update time has been spent on this assignment.
     * @param time amount of extra time has been spent on this assignment.
     */
    public void updateTimeSpent(Time time) {
        this.timeSpent = new Time (timeSpent.getTime() + time.getTime());
    }

    /**
     * changes due date for this assignment
     * @param newDue new due Date for this assignment.
     */
    public void changeDueDate(Date newDue) {
        this.duedate = newDue;
    }
}
