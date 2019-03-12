public class Employment2016 {
    private int totalLaborForce;
    private int employedLaborForce;
    private int unemployedLaborForce;
    private double unemployedPercent;

    public Employment2016(double laborForce, double percentUnemployed, double numEmployed, double numUnemployed) {
        this.totalLaborForce = (int)laborForce;
        this.employedLaborForce = (int)numEmployed;
        this.unemployedLaborForce = (int)numUnemployed;
        this.unemployedPercent = percentUnemployed;
    }

    public int getTotalLaborForce() {
        return totalLaborForce;
    }

    public void setTotalLaborForce(int totalLaborForce) {
        this.totalLaborForce = totalLaborForce;
    }

    public void setEmployedLaborForce(int employedLaborForce) {
        this.employedLaborForce = employedLaborForce;
    }

    public void setUnemployedLaborForce(int unemployedLaborForce) {
        this.unemployedLaborForce = unemployedLaborForce;
    }

    public void setUnemployedPercent(double unemployedPercent) {
        this.unemployedPercent = unemployedPercent;
    }

    public int getEmployedLaborForce() {
        return employedLaborForce;
    }

    public int getUnemployedLaborForce() {
        return unemployedLaborForce;
    }

    public double getUnemployedPercent() {
        return unemployedPercent;
    }
}
