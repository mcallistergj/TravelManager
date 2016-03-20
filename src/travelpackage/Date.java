package travelpackage;

public class Date {

	private int month,day,year,hour,minute;
	
	public Date(int mon, int d, int yr, int hr, int min){
		this.month = mon;
		this.day = d;
		this.year = yr;
		this.hour = hr;
		this.minute = min;
	}
	
	public String genDateOutput(){
		
		return Integer.toString(this.year) + ", " + Integer.toString(this.month) + 
				", " + Integer.toString(this.day) + ", " + 
				Integer.toString(this.hour) + ", " + Integer.toString(this.minute);
	}
	
	public boolean validDate(){
		if(this.month < 0 || this.month >12){
			return false;
		}
		else if(this.day < 0){
			return false;
		}
		else if(this.month == 2 && this.day < 0 || this.day > 28){
			return false;
		}
		else if((this.month == 4 ||this. month == 6 || this.month == 9 || this.month == 11) && this.day > 30){
			return false;
		} 
		else if(this.day > 31){
			return false;
		}
		else if(this.hour < 0 || this.hour > 24 || this.minute < 0 || this.minute > 59)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return(Integer.toString(this.month) + "/" + Integer.toString(day) + "/" + Integer.toString(year) +
				" " + Integer.toString(hour) + ":" + Integer.toString(minute));
	}
	
	@Override
	public boolean equals(Object o){
		Date dateToCheck = (Date)o;
		return(this.year == dateToCheck.year && this.month == dateToCheck.month &&
				this.day == dateToCheck.day && this.hour == dateToCheck.hour && this.minute == dateToCheck.minute);
	}
	
}
