package travelpackage;

public class FlightSection extends Section{
	
	private Seat[][] seats;
	private String airline,flightID, layout;
	private String colIDs = "ABCDEFGHIJ";
	private int price, rows;
	private SeatClass sClass;

	
	
	FlightSection(String air, String flID, int price, String size, int rows, SeatClass s){
		initializeSection(size, rows);
		this.airline = air;
		this.flightID = flID;
		this.sClass = s;
		this.price = price;
		this.layout = size;
		this.rows = rows;
	}
	
	public String generateSectionOutput(){
		String classDesignator;
		if(this.sClass.equals(SeatClass.economy))
			classDesignator = "E";
		else if(this.sClass.equals(SeatClass.first))
			classDesignator = "F";
		else
			classDesignator = "B";
		return classDesignator + ":" + this.price + ":" +
					this.layout + ":" + Integer.toString(this.rows);
	}
	
	public void initializeSection(String size, int rows){
		if(size.equalsIgnoreCase("S")){
			this.seats = new Seat[rows][3];
			setupSmallSection(this.seats);
			return;
		}
		else if(size.equalsIgnoreCase("M")){
			this.seats = new Seat[rows][4];
			setupMedSection(this.seats);
			return;
		}
		else if(size.equalsIgnoreCase("W")){
			this.seats = new Seat[rows][10];
			setupLargeSection(this.seats);
		}

	}
	
	public void initializeSeats(Seat[][] seats){
		for(int i = 0 ; i < seats.length; i++){
			for(int j = 0; j < seats[i].length; j++){
				seats[i][j] = new Seat(i,colIDs.charAt(j));
			}
		}
	}
	
	public void setupSmallSection(Seat[][] seats){
		initializeSeats(seats);
		for(int row = 0; row < seats.length; row++){
			seats[row][0].setAisle(true);
			seats[row][0].setWindow(true);
			seats[row][1].setAisle(true);
			seats[row][2].setWindow(true);
		}
	}
	
	public void setupMedSection(Seat[][] seats){
		initializeSeats(seats);
		for(int row = 0; row < seats.length; row++){
			seats[row][0].setWindow(true);
			seats[row][1].setAisle(true);
			seats[row][2].setAisle(true);
			seats[row][3].setWindow(true);
		}
	}
	
	public void setupLargeSection(Seat[][] seats){
		for(int row = 0; row < seats.length; row++){
			seats[row][0].setWindow(true);
			seats[row][2].setAisle(true);
			seats[row][3].setAisle(true);
			seats[row][6].setAisle(true);
			seats[row][7].setAisle(true);
			seats[row][9].setWindow(true);
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
	
	public String getID(){
		return this.flightID;
	}
	
	public String getAirline(){
		return this.airline;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public Seat[][] getSeats(){
		return this.seats;
	}
	
	public void bookAnyInClass(){
		for(int row = 0; row < this.seats.length; row++){
			for(int col = 0; col < this.seats[row].length; col++){
				if(!this.seats[row][col].getBooked()){
					this.seats[row][col].book();
					System.out.println("Seat " + this.seats[row][col] + 
							" in " + this.sClass + " class succesfully booked.");
					return;
				}
			}
		}
		System.out.println("We are sorry, there are no seats availble in " +
								this.sClass + " class.");
	}
	
	@Override
	public String toString(){
		return (this.sClass + "\tPrice: " + this.price + "\t\t" + this.layout + ": "+this.rows );
	}
	
	@Override
	public boolean equals(Object o){
		return this.flightID.equals(((FlightSection)o).flightID) && 
				this.airline.equals(((FlightSection)o).airline) &&
				this.sClass.equals(((FlightSection)o).sClass);
	}
	
}
