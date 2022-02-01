import java.util.*;

public class AppointmentTester {
	
	/**
	 *Give the user the option to add new onetime/daily/monthly appointments.
	 * @return newAppointment
	 */
	public static Appointment addAppointment(int year, int month, int day, String type, String description) {
		description = description.substring(1);
		if(type.equals("O") || type.equals("o")) {
			Appointment newAppointment = new Onetime(description, year, month, day);	
			return newAppointment;				
		}
		else if(type.equals("D") || type.equals("d")) {
			Appointment newAppointment = new Daily(description, year, month, day);	
			return newAppointment;
		}
		else if(type.equals("M") || type.equals("m")) {
			Appointment newAppointment = new Monthly(description, year, month, day);	
			return newAppointment;		
		}
		else return null;
	}
	
	/**
	 * Have the user enter the date and show all appointments occurred on that day.
	 * Also show whether the appointment is onetime, daily or monthly.
	 */
	public static void displayAppointment(int year, int month, int day, ArrayList<Appointment> appointment) {
		System.out.println("Year: " + year + "\nMonth: " + month + "\nDay: " + day);
		int cases = 1;
		boolean event = true;
		for(int i=0;i<appointment.size();i++) {
			if(appointment.get(i) instanceof Onetime) {
				if(appointment.get(i).occursOn(year, month, day)) {
					System.out.println("Descreption" + cases + ": " + appointment.get(i).toString());
					cases++;
					event = true;
				}
			}
			else if(appointment.get(i) instanceof Daily) {
				if(appointment.get(i).occursOn(year, month, day)) {
					System.out.println("Descreption" + cases + ": " + appointment.get(i).toString());
					cases++;
					event = true;
				}			
			}
			else if(appointment.get(i) instanceof Monthly) {
				if(appointment.get(i).occursOn(year, month, day)) {
					System.out.println("Descreption" + cases + ": " + appointment.get(i).toString());
					cases++;
					event = true;
				}				
			}
			else event = false;		
		}
		if(event==false) System.out.println("No event on this day.");
	}
	
	/**
	 *Have the user input the starting date and ending date, and would show all of the appointments is the period of time.
	 */
	public static void displayAppointment(int startyear, int startmonth, int startday, int endyear, int endmonth, int endday, ArrayList<Appointment> appointment) {
		int startsum = startyear*10000 + startmonth*100 + startday;
		int endsum = endyear*10000 + endmonth*100 + endday;
		boolean event = true;
		if(startsum>endsum){
			System.out.println("Error!");
		}
		else{
			System.out.println("==========");
			for(int i=0;i<appointment.size();i++) {				
				int appointmentsum = appointment.get(i).getYear()*10000 + appointment.get(i).getMonth()*100 + appointment.get(i).getDay();
				if(appointment.get(i) instanceof Onetime){
					if(appointmentsum>=startsum && appointmentsum<=endsum){
						System.out.print("Year: " + appointment.get(i).getYear() + "\nMonth: " + appointment.get(i).getMonth() + "\nDay: " + appointment.get(i).getDay() + "\nDescription: " + appointment.get(i).toString());
						event = true;
					}
				}
				else if(appointment.get(i) instanceof Daily){
					if(appointmentsum<=endsum){
						System.out.print("Year: " + appointment.get(i).getYear() + "\nMonth: " + appointment.get(i).getMonth() + "\nDay: " + appointment.get(i).getDay() + "\nDescription: " + appointment.get(i).toString() + "(Daily)");
						event = true;
					}
				}
				else if(appointment.get(i) instanceof Monthly){
					appointmentsum = appointmentsum + (endyear-appointment.get(i).getYear())*10000 + (endmonth-appointment.get(i).getMonth())*100;
					if(appointmentsum>=startsum && appointmentsum<=endsum){
						System.out.print("Year: " + appointment.get(i).getYear() + "\nMonth: " + appointment.get(i).getMonth() + "\nDay: " + appointment.get(i).getDay() + "\nDescription: " + appointment.get(i).toString() + "(Monthly)");
						event = true;
					}
				}
				else event = false;
				if(event==false) System.out.print("No event during this period of time.");
	
				System.out.println("\n==========");
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Appointment> appointment = new ArrayList<>();
		String option = " ";
		
		while(!option.equals("Q")) {
			
			System.out.println("A. Add a new appointment\nB. Display one day's appointment\nC. Display a period's appointments\nQ. Quit\n(Type A/B/C/Q and press Enter.)");
			option = sc.nextLine();
			if(option.equals("A") || option.equals("a")) {
				System.out.println("Please enter appointment data in format\"YYYY MM DD type description\", seperate with blank.\nAppointment type: O for one-time, D for daily, M for monthly");
				appointment.add(addAppointment(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.next(), sc.nextLine()));			
			}
			else if(option.equals("B") || option.equals("b")) {
				System.out.println("Please enter the date you want to check. Format\"YYYY MM DD\", seperate with blank.");
				displayAppointment(sc.nextInt(), sc.nextInt(), sc.nextInt(), appointment);
				sc.nextLine();
				
			}
			else if(option.equals("C") || option.equals("c")) {
				System.out.println("Please enter the starting and ending date you want to check. Format\"YYYY MM DD YYYY MM DD\", seperate with blank.");
				displayAppointment(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), appointment);
				sc.nextLine();
			}
			else if(option.equals("Q") || option.equals("q")) break;
			
		}
		sc.close();
	}

}
