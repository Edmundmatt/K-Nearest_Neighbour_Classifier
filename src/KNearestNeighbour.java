import java.io.File;
import java.util.*;

public class KNearestNeighbour {
    private static int KNumber = 7;

    public static void main(String args[])throws Exception{
        //Take in an input of the two file sets - File.Manager.read() handles this and returns a list of usable
        //WineInstances
        ArrayList<WineInstance> trainingSet = FileManager.read(new File("C:\\Users\\Matthew\\IdeaProjects\\COMP307Assignment1Part1\\part1Data\\wine-training"));
        ArrayList<WineInstance> testSet = FileManager.read(new File("C:\\Users\\Matthew\\IdeaProjects\\COMP307Assignment1Part1\\part1Data\\wine-test"));
        //Calculate the distance between WineInstances
        //1st Method: Euclidian - one value for distance

        //2nd Method: Get distance between test input and all other in training set
        for(WineInstance testWI : testSet){
            //Create a map of all distances from each testWI to each trainingWI ordered by euclDist
            SortedMap<Double, WineInstance> distances = new TreeMap<>();
            for(WineInstance trainWI : trainingSet) {
                double euclDist = calcEuclidianDist(testWI, trainWI);
                distances.put(euclDist, trainWI);
//                System.out.println(euclDist);
            }
            //Set testWI classification according to the mode classification of K closest neighbours
            int index = 0;
            List<Integer> classification = new ArrayList<>();
            for(Map.Entry<Double, WineInstance> entry : distances.entrySet()){
                index++;
                WineInstance wI = entry.getValue();
                classification.add((int)wI.getWineClass());
                if(index == KNumber) break;
            }
            //Find mode
            testWI.setTestSetClassEst(getMode(classification));
            System.out.println("Estimated: " + testWI.getTestSetClassEst() + '\n' +
            "Actual " + testWI.getWineClass() + '\n');
        }
        //Calculate the percentage of correct estimations
        double perc = 0;
        for(WineInstance wI : testSet){
            if(wI.getTestSetClassEst() == wI.getWineClass()){
                perc = perc + 1;
            }
        }
        perc = (perc/testSet.size()) * 100;
        System.out.println("Correct: " + perc + "%");

    }

    public static double calcEuclidianDist(WineInstance a, WineInstance b){
        //dist(A,B) = sqrt((xi - yi)^2/range))
        double euclDist = 0;
        for(int i = 0; i < a.getWineAtrbs().size(); i++){
            //Note to self: Ask tutor about the range, supposed to divide by the range to avoid weighting
            euclDist += (Math.pow((a.getWineAtrbs().get(i) - b.getWineAtrbs().get(i)), 2));
        }
        euclDist = Math.sqrt(euclDist);
        return euclDist;
    }

    public static List<WineInstance> getNearestNeighbours(){

        return null;
    }

    public static int getMode(List<Integer> list) {
        int mode = list.get(0);
        int maxCount = 0;
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == value) count++;
                if (count > maxCount) {
                    mode = value;
                    maxCount = count;
                }
            }
        }
        if (maxCount > 1) {
            return mode;
        }
        return 0;
    }

}



