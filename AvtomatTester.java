package grading;

import mathcomp.oletsky.hierarchyanalysis.SaatiAnalyzer;
import mathcomp.oletsky.mathhelper.VectMatr;

import java.util.ArrayList;

public class AvtomatTester {
    public static void main(String[] args) {
        ArrayList <Activity> grades = new ArrayList<>();
        grades.add(new Activity(60,60, true));
        grades.add(new Activity(30,20, false));
        grades.add(new Activity(10,10, false));

        double maxGrade = GradeService.getMaxGrade(grades);
        System.out.printf("Max grade: %5.2f\n",maxGrade);
        double currentMax = GradeService.countCurrentMax(grades);
        System.out.printf("Max current grade: %5.2f\n",currentMax);
        double currentGrade = GradeService.countCurrentGrade(grades);
        System.out.printf("Current grade: %5.2f\n",currentGrade);
        double remainedMax = GradeService.countRemainder(grades);
        System.out.printf("Remained max: %5.2f\n",remainedMax);
        double karma=1.;
        double tau=2.;
        double kappa=0.28;

        System.out.println("-------  Calculations:  -------------");
        double ratio=currentGrade/currentMax;
        System.out.printf("Ratio is: %5.2f\n",ratio);
        System.out.printf("Karma is: %5.2f\n",karma);
        Activity test = grades.get(1);
        if (test.isCompleted()) {
            double testRatio = test.getCurrentGrade() / test.getMaxGrade();
            System.out.printf("Test ratio is: %5.2f\n", testRatio);
        }
        double estimatedRemainder = remainedMax*ratio;
        System.out.printf("Estimated remainder is: %5.2f\n",estimatedRemainder);
        int[][] prefsKarma = {
                {-1}
        };

        double[] karmaWs = SaatiAnalyzer.calculateSaatiVectorByIntPreferences(
                prefsKarma,
                tau
        );
        System.out.println("Karma Saati weights:");
        VectMatr.defaultOutputVector(karmaWs);
        double weightedCoeff = karmaWs[0]*ratio+
                karmaWs[1]*karma;
        System.out.printf("Weighted coeff is: %5.2f\n",weightedCoeff);
        double prognoz = currentGrade+kappa*weightedCoeff*remainedMax;
        System.out.printf("Prognoz is: %5.2f\n",prognoz);

        double desired = 71.;
        double kappaRec = GradeService.getProperReduce(
            desired,
            currentGrade,
            remainedMax
        );
        System.out.printf("Recommendation for %4.0f  is %8.4f",
                desired,
                kappaRec);


    }
}
