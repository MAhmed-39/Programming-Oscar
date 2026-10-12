import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Oscar {

    int tokens;
    String testFile;
    int[] files;
    int totalMarks;
    int Strings;
    int[] StringrollNo;
    String Stringscontent;
    int[] totalrollno;
    HashMap <Integer , Student> Student = new HashMap<>();
    
    public Oscar (int[] files , int tokens , int totalMarks , String testFile){
        this.tokens = tokens;
        this.testFile = testFile;
        this.files = files;
        this.totalMarks = totalMarks;
        
        for (int i=0 ; i < files.length ; i++){
            Student student = new Student( files[i], this.tokens, Read.String(files[i]),0, 0,null);
            Student.put(files[i] , student);
        }
    }
    
    public Oscar (int[] files , int Strings , int tokens , int totalMarks , String testFile){
        this.tokens = tokens;
        this.testFile = testFile;
        this.files = files;
        this.totalMarks = totalMarks;
        this.Strings = Strings;
        
        Scanner sc = new Scanner(System.in);
        
        for (int i=0 ; i < files.length ; i++){
            Student student = new Student( files[i], this.tokens, Read.String(files[i]),0, 0,null);
            Student.put(files[i] , student);
        }
        
        int rollNo = 0;
        int[] StringrollNo = new int[Strings];
        for (int i = 0; i < Strings; i++) {
            while (true) {
                System.out.print("Enter Student roll no : ");
                try{
                    rollNo = Integer.parseInt(sc.nextLine().trim().replaceAll("[^0-9]", ""));
                }
                catch (NumberFormatException e){
                    System.out.println("Enter a valid roll number");
                    continue;
                }
                if (rollNo == 0) {
                    System.out.println("Roll no can not be zero ");
                    continue;
                }
                if (Student.containsKey(rollNo)) {
                    System.out.println("Duplicate roll number");
                    continue;
                }
                else{
                    break;
                }
            }
            System.out.print("Enter your Definition : ");
            String content = "";
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String terminater = "done";
                String[] temp = Utility.stringtoArray(line.toLowerCase());
                int count = Utility.filter(temp, terminater);
                content += line + "\n";
                if (count >= 1) {
                    break;
                }
            }
            
            content = content.replaceAll("(?i)\\bdone\\b", "").trim();
            
            Student student = new Student(rollNo, tokens,content, 0, 0, null);
            Student.put(rollNo, student);
            StringrollNo[i] = rollNo;
        }
        this.StringrollNo = StringrollNo;
        sc.close();
    }
    
    
    public void tokenizer (int rollNo){
        
        String[] words = Utility.stringtoArray(Student.get(rollNo).content);

        if (words == null) return;
        
        if (words.length <= this.tokens) {
            Student.get(rollNo).tokens -= words.length;
        }
        else{
            String[] temp1 = new String[tokens];
            for (int i=0 ; i<this.tokens ; i++){
                temp1[i] = words [i];
            }
            Student.get(rollNo).content = String.join(" ", temp1);
            Student.get(rollNo).tokens -= temp1.length;
        }
    }
    
    public void words (int rollNo){
        
        String temp1 = Read.String(testFile);
        if (temp1 == null) {
            System.out.println("The test file is empty or not found");
            return;
        }
        String[] temp2 = Utility.stringtoArray(temp1.toLowerCase());
        String[] test = Utility.distinct(temp2);
        // String[] test = temp2;
        // test = Arrays.stream(test).distinct().toArray(String[]::new);
        
        
        String temp3 = Student.get(rollNo).content;
        if (temp3 == null) {
            System.out.println("Student "+rollNo+" file is empty or not found");
            return;
        }
        String[] Temp = new String[tokens];
        if (Utility.stringtoArray(temp3).length > this.tokens) {
            for (int i=0 ; i<this.tokens ; i++){
                Temp[i] = Utility.stringtoArray(temp3)[i];
            }
        }
        temp3 = Arrays.stream(Temp).collect(Collectors.joining(" "));
        String[] temp4= Utility.stringtoArray(temp3.toLowerCase());
        String[] stu = Utility.distinct(temp4);
        // String[] stu = temp4;
        // stu = Arrays.stream(stu).distinct().toArray(String[]::new);
        
        
        int count = Utility.count(stu,test );
        // int count = (int) Arrays.stream(stu).filter(word -> Arrays.asList(test).contains(word)).count();
        
        Student.get(rollNo).words = count;
    }
    
    public void score (int rollNo){

        String temp = Read.String(testFile);
        if (temp  == null) {
            System.out.println("Test file is empty or not found");
        }
        int words = Utility.stringtoArray(Read.String(testFile)).length;
        if (words == 0) {
            System.out.println("student "+rollNo+" file is empty or not found");
            return;
        }
        double avg = (double) this.totalMarks / words;
        Student.get(rollNo).score = Student.get(rollNo).words*avg;
    }

    public void Grade (int rollNo){
        double A = (this.totalMarks / 100.0)*85;
        double B = (this.totalMarks / 100.0)*75;
        double C = (this.totalMarks / 100.0)*60;
        double D = (this.totalMarks / 100.0)*50;

        if (Student.get(rollNo).score>=A) {
            Student.get(rollNo).grade = "A";
        }
        else if (Student.get(rollNo).score>=B) {
            Student.get(rollNo).grade = "B";
        }
        else if (Student.get(rollNo).score>=C) {
            Student.get(rollNo).grade = "C";
        }
        else if (Student.get(rollNo).score>=D) {
            Student.get(rollNo).grade = "D";
        }
        else {
            Student.get(rollNo).grade = "F";
        }
    }

   public String winner(int[] rollNo) {

    double highScore = -1;
    for (int i = 0; i < rollNo.length; i++) {
        Student s = Student.get(rollNo[i]);
        if (s == null) continue;               

        if (s.score > highScore) {
            highScore = s.score;
        }
    }
    if (highScore < 0) return "No students";

    int winner = 0;
    int leastUsed = Integer.MAX_VALUE;
    int tied = 0;                              

    for (int i = 0; i < rollNo.length; i++) {
        Student s = Student.get(rollNo[i]);
        if (s == null) continue;

        if (s.score == highScore) {
            int used = this.tokens - s.tokens;   

            if (used < leastUsed) {
                leastUsed = used;
                winner = s.rollNo;
                tied = 1;
            }
            else if (used == leastUsed) {
                tied++;
            }
        }
    }

    if (tied > 1) {
        return "Tie: " + tied + " students have the same score and the same tokens used";
    }
    return "Student " + winner + " is the Winner!";
}

    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Oscar{testFile=").append(testFile)
    .append(", tokenLimit=").append(tokens)
    .append(", totalMarks=").append(totalMarks)
    .append(", students=").append(Student.size())
    .append("}");

    for (Map.Entry<Integer, Student> e : new TreeMap<>(Student).entrySet()) {
        sb.append("\n  ").append(e.getValue());
    }
    return sb.toString();
    }
}



