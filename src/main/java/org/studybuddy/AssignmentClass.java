package org.studybuddy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private Time estimateToFinish;

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
     * @param estimateToFinish estimate time to finish this assignment.
     * @param duedate assignment due date
     */
    public AssignmentClass (String name, String description, String estimateToFinish, String duedate) throws Exception {
        this.name = name;
        this.description = description;
        this.timeSpent = Time.valueOf("00:00:00");
        this.estimateToFinish = Time.valueOf(estimateToFinish);
        Calendar targetDate = Calendar.getInstance();
        SimpleDateFormat targetDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            targetDateFormat.setLenient(false);
            this.duedate = targetDateFormat.parse(duedate);
            targetDate.setTime(this.duedate);
        } catch (ParseException e) {
            throw new Exception("Invalid date is provided, please check input date");
        }
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
    public String getEstimateToFinish() {
        return estimateToFinish.toString();
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
     * @param time amount of extra time has been spent on this assignment
     * Time in format "hh:mm:ss"
     */
    public void updateTimeSpent(String time) {
        ArrayList<String> timestampsList = new ArrayList<String>();
        timestampsList.add(time);
        timestampsList.add(this.timeSpent.toString());

        long tm = 0;
        for (String tmp : timestampsList){
            String[] arr = tmp.split(":");
            tm += Integer.parseInt(arr[2]);
            tm += 60 * Integer.parseInt(arr[1]);
            tm += 3600 * Integer.parseInt(arr[0]);
        }

        long hh = tm / 3600;
        tm %= 3600;
        long mm = tm / 60;
        tm %= 60;
        long ss = tm;

        String updatedTime = hh +
                ":" +
                mm +
                ":" +
                ss;
        this.timeSpent = Time.valueOf(updatedTime);
    }

    /**
     * changes due date for this assignment
     * @param newDue new due Date for this assignment.
     * Date in format "MM/dd/YYY"
     */
    public void changeDueDate(String newDue) throws Exception {
        Calendar targetDate = Calendar.getInstance();
        SimpleDateFormat targetDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date newDueDate;
        try {
            targetDateFormat.setLenient(false);
            newDueDate = targetDateFormat.parse(newDue);
            targetDate.setTime(newDueDate);
        } catch (ParseException e) {
            throw new Exception("Invalid date is provided, please check input date");
        }
        this.duedate = newDueDate;
    }
}
