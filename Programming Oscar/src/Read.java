import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Read {

    public static String File (String filePath){

        StringBuilder content = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = read.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String String (String file){

        return File("test cases/"+file+".txt");
    }

    public static String String (int rollNo){

        return File("test cases/"+rollNo+".txt");
    }   


}
