package travelManager;
import java.util.*;
public class Airport {

	private String name;
	
	private Airport(String name){
		this.name = name;
	}
	
	public ArrayList<Airport> makeAirport(ArrayList<Airport> airports, String name){
		if(name.length() == 3)
			airports.add(new Airport(name));
		else{
			System.out.println("Invalid airport name. Must be 3 letters in length");
		}
		return airports;
	}
	
	public String getName(){
		return this.name;
	}
	
}
