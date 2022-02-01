import java.util.Scanner;


public class GradeAnalyzerTester {
    public static void main(String[] args){
       System.out.print("Please enter the grades and use Q to exit:");
       Scanner in= new Scanner(System.in);
       String a =in.nextLine();
       GradeAnalyzer grade =new GradeAnalyzer();
       String[] k = a.split(", ");

       for(String x : k){
           if(x.equals("Q") || x.equals("q")){
               grade.analyzerGrades();
               System.out.println(grade.toString());
               break;
           }
           grade.addGrade(Double.parseDouble(x));
       }
    }
}
