package travelpackage;
import java.util.*;


public class Flight {
	
	private String airlineName,origin,destination,flID;
	private Date date;
	
	public Flight(String aname, String orig, String dest, Date date, String id){
		this.airlineName = aname;
		this.origin = orig;
		this.destination = dest;
		this.date = date;
		this.flID = id;
	}
	
	public String getID(){
		return this.flID;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	
	public String getOrig(){
		return this.origin;
	}
	
	public String getDest(){
		return this.destination;
	}
	
	public String getAirline(){
		return this.airlineName;
	}
	
	public String generateFlightOutput(){
		return this.flID + "|" + this.date.genDateOutput() + "|" + this.origin +
				"|" + this.destination;
	}
	
	@Override 
	public String toString(){
		return ("Flight num: "+flID+"\tOriginating Airport: "+origin+"\tDestination Airport: "+destination+
				"\tDate: " + date);
	}
}
