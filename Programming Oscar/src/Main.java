import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        int[] Files     = {1,2,3,4,5,6,7,8,9,10};   //Enter Students roll'no which will load their File. 
        int Strings     = 0;                        //Enter No of Students Which want to give their input now. 
        String testFile = "test";                   //Enter test file name which contains meaningful words.
        int Tokens      = 100;                      //Enter min word count.
        int Marks       = 20;                       //Enter Total Marks for the test.

        Oscar code = new Oscar(Files, Strings, Tokens, Marks, testFile);
        
        int[] rollNo = Files;
        rollNo = IntStream.concat(Arrays.stream(rollNo), Arrays.stream(code.StringrollNo)).toArray();
        
        
        for (int i=0 ; i< rollNo.length ; i++ ){
            code.tokenizer(rollNo[i]);
            code.words(rollNo[i]);
            code.score(rollNo[i]);
            code.Grade(rollNo[i]);
        
        }

        System.out.println(code);

        System.out.println(code.winner(rollNo));
        
    }
}
