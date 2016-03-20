package travelpackage;
import java.util.*;


public class Flight {
	
	private String airlineName,origin,destination,flID;
	private int year,month,day;
	private ArrayList<FlightSection> sections;
	
	public Flight(String aname, String orig, String dest, int year, int month, int day, String id){
		this.airlineName = aname;
		this.origin = orig;
		this.destination = dest;
		this.year = year;
		this.month = month;
		this.day = day;
		this.flID = id;
		this.sections = new ArrayList<FlightSection>(3);
	}
	
	public void addSection(FlightSection section){
		if(sections.isEmpty())
			sections.add(section);
		else{		
			for(FlightSection s : sections){
				if(section.getSClass().equals(s.getSClass())){
					System.out.println("This section already has a " + section.getSClass() + " class.");
					return;
				}
			}
			sections.add(section);
		}
	}
	
	public String getID(){
		return this.flID;
	}
	
	public ArrayList<FlightSection> getSections(){
		return this.sections;
	}
	
	public String getOrig(){
		return this.origin;
	}
	
	public String getDest(){
		return this.destination;
	}
	
	@Override 
	public String toString(){
		return ("Flight num: "+flID+",\tOriginating Airport: "+origin+",\tDestination Airport: "+destination+",\tDate: "
				+month+"/"+day+"/"+year);
	}
}
