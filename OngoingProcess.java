package plans.informal;

import mathcomp.oletsky.hierarchyanalysis.SaatiAnalyzer;
import mathcomp.oletsky.mathhelper.VectMatr;

public class OngoingProcess {
    public static void main(String[] args) {
        double maxTestGrade = 30.;
        double maxIspitGrade = 10.;
        double[] maxGrades = {10., 10., 10., 10., 10., 10.,
                maxTestGrade, maxIspitGrade};
        
        int[] controlIndexes={6, 7};
        int tCurr=-1;
        double controlMax = GradeService.calculateRemainder(
                tCurr,
                controlIndexes,
                maxGrades
        );

        int kolSteps = maxGrades.length;
        double maxTotalGrade = VectMatr.calculateSumOfComponents(maxGrades);
        System.out.println("Maximum total grade is " + maxTotalGrade);
        System.out.println("Maximum control grade is "+controlMax);
        double kappa=0.5;

        //Karma preferences
        double tauKarma=1.2;
        int[][] prefKarma={
                {1}
        };
        double[] karmaVs=
                SaatiAnalyzer.calculateSaatiVectorByIntPreferences(
                        prefKarma,
                        tauKarma
                        );
        System.out.println("Karma coefficients:");
        VectMatr.defaultOutputVector(karmaVs);


        //CriteriaComparisons
        double tau = 1.05;
        int[][] compar = {
                {0, 0, 0, 0, 0, -1, -1},
                {0, 0, 0, 0, -1, -1},
                {0, 0, 0, -1, -1},
                {0, 0, -1, -1},
                {0, -1, -1},
                {0, 0},
                {0}
        };
        double[] critWs = SaatiAnalyzer.calculateSaatiVectorByIntPreferences(
                compar,
                tau
        );
        System.out.println("Criteria weights:");
        VectMatr.defaultOutputVector(critWs);
        System.out.println("Accumulating process:");


        //Main process
        double accumulatedGrade = 0.;
        double[] currentGrades = {10., 10., 10., 10., 10., 10., 20., 10.};
        double[] currentKarmas = {10., 10., 10., 10., 10., 10., 0., 0.};

        double[] accumulatedGrades = new double[kolSteps];
        double accumulatedMax = 0.;
        double[] accumulatedMaxes = new double[kolSteps];
        double[] averages = new double[kolSteps];
        double[] prognoz = new double[kolSteps];


        double accumulatedKarma =0.;
        double[] averageKarmas=new double[kolSteps];


        System.out.println(" --------------------------------------------------------------");
        System.out.println("|No|  Current  |  Accumul  | Avg |Karma|AvgKarm| Mult |Prognoz |");
        System.out.println(" --------------------------------------------------------------");

        for (int t = 0; t < kolSteps; t++) {
            accumulatedGrade += currentGrades[t];
            accumulatedGrades[t] = accumulatedGrade;
            accumulatedMax += maxGrades[t];
            accumulatedMaxes[t] = accumulatedMax;
            averages[t]=accumulatedGrades[t]/accumulatedMaxes[t];
            double currentRemainder = GradeService.calculateRemainder(
                    t,
                    controlIndexes,
                    maxGrades
            );

            accumulatedKarma+=currentKarmas[t];
            averageKarmas[t]=t<6?accumulatedKarma/(10.*(t+1.)):
                    averageKarmas[t-1];

            double mult=averageKarmas[t]*karmaVs[0]+
                    kappa*karmaVs[1];
            prognoz[t]=accumulatedGrades[t]+averages[t]*
                                       mult*currentRemainder;

            //Printing process:
            System.out.printf
                    ("|%2s|%5.1f/%5.1f|%5.1f/%5.1f|%4.3f|%5.2f|%7.2f|%6.2f|%7.2f|\n",
                    t,
                    currentGrades[t],
                    maxGrades[t],
                    accumulatedGrades[t],
                    accumulatedMaxes[t],
                    averages[t],
                    currentKarmas[t],
                    averageKarmas[t],
                    mult,
                    prognoz[t]
            );

        }


    }
}

class StepState {

}
