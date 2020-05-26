package plans.informal;

public class GradeService {
    static double calculateEstimatedGrade(double totalMax,
                                          double currentMax,
                                          double currentGrade) {
        double proportion = totalMax/currentMax;
        return currentGrade*proportion;
    }

    static double calculateRemainder(
            int tCurr,
            int[] controlIndexes,
            double[] maxGrades
    ) {
        double controlMax=0.;
        for (int ind:controlIndexes) {
            if (tCurr<ind) controlMax+=maxGrades[ind];
        }
        return controlMax;
    }
}
