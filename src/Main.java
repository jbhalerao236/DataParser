import java.util.ArrayList;

/***
 * Main class for data parser
 * Author: Jessica Bhalerao
 */

public class Main {
    public static void main(String[] args) {
        //Test of utils
        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        String data1 = Utils.readFileAsString("data/Education.csv");
        String data2 = Utils.readFileAsString("data/Unemployment.csv");

        ArrayList<String> results = Utils.Election2016GOPData(data);
        System.out.println(results.toString());

    }
}
