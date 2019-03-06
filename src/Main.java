import java.util.ArrayList;

/***
 * Main class for data parser
 * Author: Jessica Bhalerao
 */

public class Main {
    public static void main(String[] args) {
        //Test of utils
        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        ArrayList<ElectionResult> results = Utils.parse2016ElectionResults(data);
        System.out.println(results.get(results.size() - 1).toString());
    }
}
