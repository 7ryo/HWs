import java.util.Scanner;

public class TimeCalculator{
    public static void main(String[] args) {  
        
        Boolean state = true;

        while(state == true){           
        System.out.print("Please enter the first time: ");
        Scanner scan1 = new Scanner(System.in);
        int initial = scan1.nextInt(); //initial time
        System.out.print("Please enter the second time: ");
        Scanner scan2 = new Scanner(System.in);
        int ending = scan2.nextInt(); //final time

        int initialh = initial/100;
        int initialm = initial%100;
        int endingh = ending/100;
        int endingm = ending%100; //seperate hour and minute

        if(initial>2359 || ending>2359 || initialm>59 || endingm>59){
            System.out.println("Invalid Input! The input should be military format (i.e. 0850, 1710), please enter your input again.");
            state = true;
        }
        else{
            int innum = initialh*60+initialm; 
            int finum = endingh*60+endingm; //change hours to minutes
            //calculate 
            int time = 0;
            if(innum>finum){
                time = (finum+24*60)-innum;
            }
            else{
                time = finum-innum;
            }
            int hour = time/60;
            int minute = time%60;
            System.out.println("The duration between these two times is " + hour + " hours " + minute + " minutes");
            break;
        }}      
    }
}