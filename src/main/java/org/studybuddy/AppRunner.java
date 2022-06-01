/**
 * A wrapper for the app's starting point, because App.java extends Application,
 * which was not acceptable for one of the Maven plugins to run correctly
 **/

package org.studybuddy;

public class AppRunner {

    public static void main (String[] args) {
        App.main(args);
    }

}
