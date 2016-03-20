package travelpackage;

public class Seat {
	
	private char colID;
	private int row;
	private boolean booked, window, aisle;
	
	public Seat(int row, char colID){
		this.booked = false;
		this.colID = colID;
		this.row = row;
	}
	
	public boolean isPreference(String pref){
		if(pref.equalsIgnoreCase("window"))
			return this.window;
		return this.aisle;
	}
	
	public void book(){
		this.booked = true;
	}
	
	public boolean getBooked(){
		return this.booked;
	}
	
	
	public void setAisle(boolean isAisle){
		this.aisle = isAisle;
	}
	
	public void setWindow(boolean isWindow){
		this.window = isWindow;
	}
	
	@Override
	public String toString(){
		return(Integer.toString(this.row + 1) + this.colID);
	}
}
