public class Student {
    
    public String content;
    public int tokens;
    public int rollNo;
    public double score;
    public int words;
    public String grade;

    public Student (int rollNo ,int tokens, String content,int words, double score, String grade){
        this.tokens = tokens;
        this.rollNo = rollNo;
        this.content = content;
        this.score = score;
        this.words = words;
        this.grade = grade;
    }

    @Override
    public String toString() {
    return "Student{rollNo=" + rollNo
    + ", tokens=" + tokens
    + ", words=" + words
    + ", score=" + String.format("%.2f", score)
    + ", grade=" + grade + "}";
    }
}
