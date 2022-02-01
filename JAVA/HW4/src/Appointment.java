/**
 * Appointment with description, year, month and day.
 *
 */
public class Appointment {
	
	private String description;
	private int year, month, day;
	/**
	 * Construct an Appointment with empty description, year, month and day.
	 */
	public Appointment(String describe, int YYYY, int MM, int DD) {
		description = describe;
		year = YYYY;
		month = MM;
		day = DD;
		
	}
	/**
	 * Override method toString.
	 * @return description, year, month and day
	 */
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public String toString() {
		return description;
	}
	/**
	 * A Boolean method that checks whether the appointment occurs on that day.
	 * Should be override in other subclasses.
	 */
	public boolean occursOn(int year, int month, int day) {
		return true;
	}
}
