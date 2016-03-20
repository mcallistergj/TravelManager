package travelpackage;

public abstract class Section {

	protected Seat[][] seats;
	protected SeatClass sClass;
	protected String colIDs = "ABCDEFGHIJ";
	protected int price;
	
	

	
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
				if(!seats[i][j].getBooked())
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
	
	public void setPrice(int nPrice){
		this.price = nPrice;
	}
	
	public String getColIDs(){
		return colIDs;
	}
	
	public int getColNum(char colID){
		return colIDs.indexOf(colID);
	}
	
}
	
