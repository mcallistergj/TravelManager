package travelpackage;
import java.util.*;

public class Airline {
	
		private String name;
		private ArrayList<Flight> flights;
		
		Airline(String name){
			this.name = name;
			this.flights = new ArrayList<Flight>();
		}
		
		public String getName(){
			return this.name;
		}
		
		public void addFlight(Flight flight){
			flights.add(flight);
		}
		public ArrayList<Flight> getFlights(){
			return flights;
		}
		
		
		@Override
		public boolean equals(Object obj){
			return (this.name.compareTo(((Airline)obj).name)==0);
		}
		
}
