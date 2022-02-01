
public class Daily extends Appointment {
	public Daily(String describe, int YYYY, int MM, int DD) {
		super(describe, YYYY, MM, DD);
	}
	
	public boolean occursOn(int year, int month, int day) {
		if(year>this.getYear() || (year==this.getYear() && (month>this.getMonth() || (month==this.getMonth() && day>=this.getDay())))) {
			return true;
		}
		else return false;
		
	}

}
