
public class Monthly extends Appointment {
	public Monthly(String describe, int YYYY, int MM, int DD) {
		super(describe, YYYY, MM, DD);
	}
	
	public boolean occursOn(int year, int month, int day) {
		if(day==this.getDay() && (year>this.getYear() || (year==this.getYear() && month>=this.getMonth()))) {
			return true;
		}
		else return false;
		
	}

}
