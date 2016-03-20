package travelpackage;

public class Seat {
	
	private char colID;
	private int row;
	private boolean booked;
	
	public Seat(int row, char colID){
		this.booked = false;
		this.colID = colID;
		this.row = row;
	}
	
	public void book(){
		this.booked = true;
	}
	
	public boolean getBooked(){
		return this.booked;
	}
}
