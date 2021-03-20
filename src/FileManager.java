import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static ArrayList<WineInstance> read(File file) throws Exception {
        try {
            ArrayList<Double> wineAtrbs;
            ArrayList<WineInstance> wineInstances = new ArrayList<WineInstance>();
            BufferedReader br = new BufferedReader(new FileReader(file)); //Take file input, read with buffered reader

            //Parse data into usable tokens
            String line;
            br.readLine(); //Skip header line
            int index = -1;
            while ((line = br.readLine()) != null) { //While there is another line, read it
                index++;
                wineAtrbs = new ArrayList<Double>();
                Scanner sc = new Scanner(line); //Feed each line into scanner
                while (sc.hasNext()) { //While each line contains another value
                    String token = sc.next();
                    Double db = Double.parseDouble(token);
                    wineAtrbs.add(db);
                }
                sc.close();
//            System.out.println(wineAtrbs.toString());
                wineInstances.add(new WineInstance(wineAtrbs, index));
            }
            br.close();
            return wineInstances;
        }catch(Exception e){
            System.err.println("Error: Problem reading a dataset");
            e.printStackTrace();
            return null;
        }
    }
}
