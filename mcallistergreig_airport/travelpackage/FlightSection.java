package travelpackage;

public class FlightSection {
	
	private Seat[][] seats;
	private SeatClass sClass;
	private String airline,flightID;
	private String colIDs = "ABCDEFGHIJ";
	
	
	FlightSection(String air, String flID, int rows, int cols, SeatClass s){
		this.seats = new Seat[rows][cols];
		initializeSection(seats);
		this.airline = air;
		this.flightID = flID;
		this.sClass = s;
	}
	
	public void initializeSection(Seat[][] seats){
		for(int i = 0 ; i < seats.length; i++){
			for(int j = 0; j < seats[i].length; j++){
				seats[i][j] = new Seat(i,colIDs.charAt(j));
			}
		}
	}
	
	public boolean validSeat(int row, int col){
		return(row < this.seats.length && col < this.seats[0].length);
	}
	
	public SeatClass getSClass(){
		return this.sClass;
	}
	
	public boolean seatBooked(int row, int col){
		return(seats[row][col].getBooked());
	}
	
	public void setBooked(int row, int col){
		seats[row][col].book();
	}
	
	public boolean hasAvailableSeats(){
		for(int i = 0 ; i < seats.length ; i++){
			for(int j = 0 ; j < seats[i].length; j++){
				if(seats[i][j].getBooked())
					return true;
			}
		}
		return false;
	}
	
	public void bookSeat(){
		for(int i = 0 ; i < seats.length ; i++){
			for(int j = 0 ; j < seats[i].length; j++){
				if(!seats[i][j].getBooked())
					seats[i][j].book();
					return;
			}
			
		}
	}
	public String getColIDs(){
		return colIDs;
	}
	
	public int getColNum(char colID){
		return colIDs.indexOf(colID);
	}
	
}
