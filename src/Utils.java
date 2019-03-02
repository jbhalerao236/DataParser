import java.io.File;
import java.io.IOException;
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

}
