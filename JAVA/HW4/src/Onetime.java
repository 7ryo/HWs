
public class Onetime extends Appointment {
	public Onetime(String describe, int YYYY, int MM, int DD) {
		super(describe, YYYY, MM, DD);
		
	}
	
	public boolean occursOn(int year, int month, int day) {
		if(year==this.getYear() && month==this.getMonth() && day==this.getDay()) {
			return true;
		}
		else return false;
	}

}
