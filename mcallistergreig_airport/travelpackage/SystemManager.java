package travelpackage;

import java.util.*;

public class SystemManager{

	private List<Airport> airports = new ArrayList<Airport>();
	private List<Airline> airlines = new ArrayList<Airline>();
	private boolean valid;
	
	
	public boolean validName(int length, String nameOfNew, String nameOfExisting){
		if(nameOfNew.compareTo(nameOfExisting) == 0){
			System.out.println(nameOfNew + " already exists.");
			return false;
		}
		else if(nameOfNew.length() != length){
			System.out.println(nameOfNew + " is not a valid name.");
			return false;
		}
		return true;
	}
	
	public boolean validDate(int month,int day, int year){
		if(month < 0 || month >12){
			return false;
		}
		else if(day < 0){
			return false;
		}
		else if(month == 2 && day < 0 || day > 28){
			return false;
		}
		else if((month == 4 || month == 6 || month == 9 || month == 11) && day > 30){
			return false;
		} 
		else if(day > 31){
			return false;
		}
		return true;
	}
	
	
	public void createAirport(String name){
		Airport nAirport = new Airport(name);
		if(airports.isEmpty() && validName(3,name," ")){
			airports.add(nAirport);
			return;
			
		}
		else
			for(Airport a: airports){
				if(!validName(3,name,a.getName())){
					return;
				}
			}
			airports.add(nAirport);
	}

	public void createAirline(String name){
		valid = true;
		Airline nAirline = new Airline(name);
		if(airlines.isEmpty()){
			if(name.length() >= 6){
				System.out.println(name + " is not a valid name.");
			}
			else
				airlines.add(nAirline);
		}
		else{
			for(Airline a: airlines){
				if(a.getName().compareTo(name)==0){
					System.out.println(name+" already exists.");
					valid = false;
					break;
				}
				else if(name.length() >=6){
					System.out.println(name + " is not a valid name.");
					valid = false;
					break;
				}
			}
			if(valid)
				airlines.add(nAirline);
		}
		
	}
	
	public void createFlight(String aname, String orig, String dest, int year, int month, int day, String id){
		Flight flight = new Flight(aname, orig, dest, year, month, day, id);
		if(!validDate(month,day,year)){
			System.out.println(month + "/" + day + "/" + year +" is not a valid date.");
			return;
		}
		if(orig.length() != 3 || dest.length() != 3){
			System.out.println(orig + " " + dest + " are not valid.");
			return;
		}
		if(orig.compareTo(dest) == 0){
			System.out.println("Origin and destination airports must be different.");
			return;
		}
		for(Airline a : airlines){
			if(aname.compareToIgnoreCase(a.getName()) == 0){
				a.addFlight(flight);
				return;
			}
		}
		System.out.println(aname + " is not a valid Airline.");
	}
	
	public void createSection(String air, String fID, int rows, int cols, SeatClass s){
		FlightSection section = new FlightSection(air, fID, rows, cols, s);
		if(air.length() >= 6){
			System.out.println(air + " is not a valid Airline.");
			return;
		}
		if(rows < 0 || cols < 0){
			System.out.println("Section must have at least one seat.");
			return;
		}
		if(rows > 100 || cols > 10){
			System.out.println("Section has too many seats");
			return;
		}
		for(Airline a : airlines){
			if(a.getName().compareTo(air) == 0){
				for(Flight f : a.getFlights()){
					if(f.getID().compareTo(fID) == 0){
						f.addSection(section);
						return;
					}
					System.out.println("Flight " + fID + " doesn't exist.");
					return;
				}
			}
		}
	}
	public void bookSeat(String air, String fl, SeatClass s, int row, char col){
		int colNum;
		for(Airline a: airlines){
			if(a.getName().compareTo(air) == 0){
				for(Flight flight : a.getFlights()){
					if(flight.getID().compareTo(fl) == 0){
						for(FlightSection fs : flight.getSections()){
							if(fs.getSClass().equals(s)){
								colNum = fs.getColNum(col);
								if(!fs.validSeat(row, colNum)){
									System.out.println(row+col+" is not a valid seat.");
									return;
								}
								if(fs.seatBooked(row, colNum)){
									System.out.println("Seat "+row+col+ " is already booked");
									return;
								}
								fs.setBooked(row, colNum);
								System.out.println(air+ " Airlines " + "flight "+fl+" seat "+row+col+" in "+ s+" class"+" successfully booked.");
								return;
							}
						}
						System.out.println(s+" is not a valid section.");
						return;
					}
				}
				System.out.println(fl+" is not a valid flight.");
				return;
			}
		}
		System.out.println(air+" is not a valid Airline.");
		return;
	}
	
	public void displaySystemDetails(){
		System.out.println("Airports: ");
		for(Airport a : airports){
			System.out.println("\t"+a.getName());
		}
		System.out.println("Airlines: ");
		for(Airline air: airlines){
			System.out.println(air.getName());
			System.out.println("\tFlights ");
			for(Flight fl : air.getFlights()){
				System.out.println("\t"+fl);
				for(FlightSection fs: fl.getSections()){
					System.out.println("\t\t"+fs.getSClass());
				}
			}
			
		}
	}
	
	public void findAvailableFlights(String orig, String dest){
		System.out.println("Available Flights from "+ orig+ " to "+dest);
		for(Airline al : airlines){
			for(Flight fl:al.getFlights()){
				if(orig.compareTo(fl.getOrig())==0 && dest.compareTo(fl.getDest())==0){
					for(FlightSection fs:fl.getSections()){
						if(fs.hasAvailableSeats()){
							System.out.println("\t"+fl);
							break;
						}
					}
				}
			}
		}
	}
	
}

