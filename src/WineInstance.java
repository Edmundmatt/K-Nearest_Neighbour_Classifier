import java.util.ArrayList;
import java.util.List;

public class WineInstance {
    private ArrayList<Double> wineAtrbs;
    private int index;
    private double testSetClassEst;

    public WineInstance(ArrayList<Double> wineAtrbs, int index) {
        this.wineAtrbs = wineAtrbs;
        this.index = index;
    }

    public ArrayList<Double> getWineAtrbs() {
        return this.wineAtrbs;
    }

    public double getWineClass() {
        return this.wineAtrbs.get(wineAtrbs.size() - 1);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getTestSetClassEst() {
        return testSetClassEst;
    }

    public void setTestSetClassEst(double testSetClassEst) {
        this.testSetClassEst = testSetClassEst;
    }
}
