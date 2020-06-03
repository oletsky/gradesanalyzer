package grading;

import java.util.ArrayList;

public class GradeService {

    public static double getMaxGrade(ArrayList<Activity> activities) {
        double s=0.;
        for (Activity a:activities) {
            s+=a.getMaxGrade();
        }
        return s;
    }

    public static double countGrade(ArrayList<Activity> activities,
                                    boolean maxGr,
                                    boolean completed) {
        double s=0.;
        for (Activity a:activities) {
            if (a.isCompleted()==completed) {
                s += maxGr ? a.getMaxGrade() : a.currentGrade;
            }
        }
        return s;
    }

    public static double countCurrentMax(ArrayList<Activity> activities) {
        return countGrade(activities, true, true);
    }

    public static double countCurrentGrade(ArrayList<Activity> activities) {
        return countGrade(activities, false, true);
    }

    public static double countRemainder(ArrayList<Activity> activities) {
        return countGrade(activities, true, false);
    }

    public static double getProperReduce(
            double desired,
            double current,
            double remain
    ) {
        return (desired-current)/remain;
    }
}
