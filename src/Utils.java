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
    public static ArrayList<Education2016> parseEducationData(String data) {
        ArrayList<Education2016> results = new ArrayList<>();
        String[] rows = data.split("\n");
        ArrayList<String> rowsList = new ArrayList<>();

        for (String s : rows) {
            rowsList.add(s);
        }

        ArrayList<String> labels = format(rowsList.get(4));

        for (int i = 5; i < labels.size(); i++) {
            ArrayList<String> vals = format(rowsList.get(i));
            double noHighSchool = Double.parseDouble(vals.get(labels.indexOf("Percent of adults with less than a high school diploma 2012-2016")));
            double onlyHighSchool = Double.parseDouble(vals.get(labels.indexOf("Percent of adults with a high school diploma only 2012-2016")));
            double someCollege = Double.parseDouble(vals.get(labels.indexOf("Percent of adults completing some college or associate's degree 2012-2016")));
            double bachelors = Double.parseDouble(vals.get(labels.indexOf("Percent of adults with a bachelor's degree or higher 2012-2016")));
            Education2016 educObj = new Education2016(noHighSchool, onlyHighSchool, someCollege, bachelors);
            results.add(educObj);
        }
        return results;    }

    public static ArrayList<Employment2016> parseEmploymentData(String data) {
        ArrayList<Employment2016> results = new ArrayList<>();
        String[] rows = data.split("\n");
        ArrayList<String> rowsList = new ArrayList<>();

        for (String s : rows) {
            rowsList.add(s);
        }

        ArrayList<String> labels = format(rowsList.get(7));

        for (int i = 8; i < labels.size(); i++) {
            ArrayList<String> vals = format(rowsList.get(i));
            double laborForce = Double.parseDouble(vals.get(labels.indexOf("Civilian_labor_force_2016")));
            double percentUnemployed = Double.parseDouble(vals.get(labels.indexOf("Unemployment_rate_2016")));
            double numEmployed = Double.parseDouble(vals.get(labels.indexOf("Employed_2016")));
            double numUnemployed = Double.parseDouble(vals.get(labels.indexOf("Unemployed_2016")));
            Employment2016 employObj = new Employment2016(laborForce, percentUnemployed, numEmployed, numUnemployed);
            results.add(employObj);
        }
        return results;    }
    private static ArrayList<String> format(String s) {
        s.replace("\"", "");
        String[] arr = s.split(",", -1);
        ArrayList<String> valList = new ArrayList<>();
        for (String s1 : arr) {
            valList.add(s);
        }
        for (int i = 0; i < valList.size(); i++) {
            valList.get(i).trim();
            if(valList.get(i).isEmpty()){
                valList.set(i, "0");
            }
        }
        return valList;
    }
}