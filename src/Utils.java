import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static String readFileAsString(String filePath){
        StringBuilder output = new StringBuilder();

        try(Scanner scanner = new Scanner(new File(filePath))){

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
        ArrayList<ElectionResult> results = new ArrayList<>();
        String[] rows = data.split("\n");
            for (int a = 1; a < rows.length; a++) {
                String[] valuesArray = rows[a].split(",");
                //votes_dem,votes_gop,total_votes,per_dem,per_gop,
                //diff,per_point_diff,state_abbr,county_name,combined_fips
                double votes_dem = Double.parseDouble(valuesArray[1]);
                double votes_gop = Double.parseDouble(valuesArray[2]);
                double total_votes = Double.parseDouble(valuesArray[3]);
                double per_dem = Double.parseDouble(valuesArray[4]);
                double per_gop = Double.parseDouble(valuesArray[5]);
                //.replaceAll("\"", "") ???
                //String diffString = valuesArray[6].replaceAll(",", "");
                int index = valuesArray[6].indexOf("\"");
                double diff = Double.parseDouble(valuesArray[6].substring(index + 1, valuesArray[6].length() - 2));
                double per_point_diff = Double.parseDouble(valuesArray[7].replaceAll("%", ""));
                String state_abbr = valuesArray[8];
                String county_name = valuesArray[9];
                double combined_fips = Double.parseDouble(valuesArray[10]);
            ElectionResult electionResultObj = new ElectionResult(votes_dem,votes_gop,total_votes,
                    per_dem,per_gop,diff,per_point_diff,combined_fips,state_abbr,county_name);
            results.add(electionResultObj);
        }
        return results;
    }

}
