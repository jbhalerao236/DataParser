import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;

public class Utils {
    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static ArrayList<ElectionResult> parse2016ElectionResults(String data) {
        ArrayList<ElectionResult> result = new ArrayList<>();
        String[] rows = data.split("\n");
        String[] linesArray;

        for (int i = 1; i < rows.length; i++) {
            int indexOfFirstQuote = rows[i].indexOf("\"");
            if(indexOfFirstQuote != -1) {
                int indexOfSecondQuote = rows[i].indexOf("\"", indexOfFirstQuote + 1);
                String originalString = rows[i].substring(indexOfFirstQuote, indexOfSecondQuote + 1);
                String newString = originalString.replaceAll("\"", "").replaceAll(",", "");
                rows[i] = rows[i].replace(originalString, newString);
            }



            linesArray = rows[i].split(",");


            double votes_dem = Double.parseDouble(linesArray[1]);
            double votes_gop = Double.parseDouble(linesArray[2]);
            double total_votes = Double.parseDouble(linesArray[3]);
            double per_dem = Double.parseDouble(linesArray[4]);
            double per_gop = Double.parseDouble(linesArray[5]);
            System.out.println(linesArray[6]);
            //String diffString = rows[6].replaceAll(",", "").replaceAll("\"", ""); //replaceAll(",",linesArray[6]);
            //System.out.println(diffString);
            double diff = Double.parseDouble(linesArray[6]);
            //String per_diff_String = linesArray[7].replaceAll("%", ""); //replaceAll("%",linesArray[7]);
            double per_point_diff = Double.parseDouble(linesArray[7].replaceAll("%", ""));
            String state_abbr = linesArray[8];
            String county_name = linesArray[9];
            double combined_fips = Double.parseDouble(linesArray[10]);
            ElectionResult electionResultObject = new ElectionResult(votes_dem, votes_gop, total_votes, per_dem, per_gop, diff, per_point_diff, state_abbr, county_name, combined_fips);
            result.add(electionResultObject);


        }


        return result;
    }
}