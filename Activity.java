package grading;

public class Activity {
    double maxGrade;
    double currentGrade;
    boolean completed;

    public Activity(double maxGrade,
                    double currentGrade,
                    boolean completed) {
        this.maxGrade = maxGrade;
        this.currentGrade = currentGrade;
        this.completed = completed;
    }

    public double getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(double maxGrade) {
        this.maxGrade = maxGrade;
    }

    public double getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(double currentGrade) {
        this.currentGrade = currentGrade;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
