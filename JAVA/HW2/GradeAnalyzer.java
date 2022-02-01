import java.util.Scanner;

public class GradeAnalyzer {
    
    int count=0;
    int sum, average;
    double deiviation, GPA;
    private int [] array= new int[11];
    
    int[] limit ={98,92,90,88,82,80,78,72,70,60,0};

    public GradeAnalyzer(){
        
    }
    
    public boolean isValidGade (double aGrade){
        if(aGrade < 0 || aGrade > 100){
            return false;
        }else{
            return true;
        }
    }

    public void addGrade(double aGrade){
        if(isValidGade(aGrade)){
            count++;
            sum+= aGrade;
            deiviation+= aGrade*aGrade;

     
            for(int i =0;i<=limit.length;i++){
                if(aGrade>=limit[i]){
                    array[i]++;
                    break;
                }
            }
        }
    }


    public void analyzerGrades() {
        average = sum/count;
        deiviation=Math.sqrt((deiviation-count*average*average)/(count-1));
        GPA=(array[0]*4.3+array[1]*4+array[2]*3.7+array[3]*3.3+array[4]*3+array[5]*2.7+array[6]*2.3+array[7]*2+array[8]*1.7+array[9]*1+array[10]*0)/count;
    }

    public String toString(){
        String a =
        "You entered " + count + " valid grades from 0 to 100. Invalid grades are ignored!" +
        "\nThe average = " + average + " with a standard deviation = " + Math.round(deiviation) +
        "\nThe grade distribution is: \n" + 
        "A+ = " + array[0] + "\n"+ 
        "A = " + array[1] + "\n"+ 
        "A- = "+ array[2]+ "\n" + 
        "B+ = "+ array[3] + "\n" + 
        "B = "+ array[4]+ "\n"+
        "B- = "+ array[5]+ "\n"+
        "C+ = "+ array[6]+ "\n"+
        "C = "+ array[7]+ "\n"+
        "C- = "+ array[8]+ "\n"+
        "D = "+ array[9]+ "\n"+
        "F = "+ array[10]+
        "\nThe GPA is "+(double)Math.round(GPA*100)/100;
        return a;
    }
}    