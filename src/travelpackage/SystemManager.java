package travelpackage;

import java.util.*;
import java.io.*;

public class SystemManager{

	private List<Airport> airports = new ArrayList<Airport>();
	private List<Airline> airlines = new ArrayList<Airline>();
	private List<Flight> flights = new ArrayList<Flight>();
	private List<FlightSection> sections = new ArrayList<FlightSection>();
	
	public void readFile(Scanner fin){
		List<String> infoArray = new ArrayList<String>();
		String input = "";
		while(fin.hasNextLine()){
			input = input.concat(fin.nextLine());
		}
		String ap = "";
		int index = 0;
		char c = input.charAt(index);
		while(!Character.toString(c).equals("]")){
			c = input.charAt(index);
			ap = ap.concat(Character.toString(c));
			index++;
		}
		do{
        	int rCount = 0;
        	String aLine = "";
        	while(rCount < 3){
        		c = input.charAt(index);
        		aLine = aLine.concat(Character.toString(c));
        		if(Character.toString(c).equals("]")){
        			rCount++;
        		}
        		index++;
        	}
        	infoArray.add(aLine);
        }while(index < input.length()-1);
		String[] aports = ap.split("\\W");
		addAirports(aports);
		processReadIn(infoArray);

		
	}
	
	public ArrayList<String> scrubData(String[] toScrub){
		ArrayList<String> scrubbed = new ArrayList<String>();
		for(String str : toScrub){
			if(!str.equals("") && !str.equals(null)){
				scrubbed.add(str);
			}
		}
		return scrubbed;
	}
	
	public void processReadIn(List<String> info){
			for(String s : info){
				String[] toScrub = s.split("\\W");
				ArrayList<String> scrubbed = scrubData(toScrub);
				inputResults(scrubbed);
			}
	}
	
	public void writeToFile(String outFile, String output){	
		
		try{
			PrintWriter writer = new PrintWriter(new File(outFile));
			writer.print(output);
			writer.close();
			System.out.println("Information successfully written to " + outFile);
		} catch(FileNotFoundException e){
			System.out.println("Could not write to file");
		}
		
	}
	
	public String processOutput(){
		String output = "";
		output = output.concat("[");
		for(int i = 0; i < airports.size(); i++){
			if(!(i == airports.size()-1))
				output = output.concat(airports.get(i).getName() + ", ");
			else
				output = output.concat(airports.get(i).getName() + "]");
		}
		output = output.concat("{");
		for(Airline al : airlines){
			output = output.concat(al.getName()+"[");
			for(Flight fl : flights){
				if(fl.getAirline().equals(al.getName())){
					output = output.concat(fl.generateFlightOutput());
					output = output.concat("[");
					for(int i = 0; i < sections.size(); i++){
						if(sections.get(i).getID().equals(fl.getID())&&
								i == sections.size()-1){
							output = output.concat(sections.get(i).generateSectionOutput() +
									"]]}");
						}	
						else if(sections.get(i).getID().equals(fl.getID())&& 
								!(sections.get(i+1).getID().equals(fl.getID()))){
							output = output.concat(sections.get(i).generateSectionOutput() +
									"]");
						}
						else if(sections.get(i).getID().equals(fl.getID())&&
								sections.get(i+1).getID().equals(fl.getID())){
							output = output.concat(sections.get(i).generateSectionOutput() +
									",");
						}
					}
				}
			}
			output = output.concat("], ");
			
		}
		StringBuilder finalizeString = new StringBuilder(output);
		finalizeString.deleteCharAt(output.length()-1);
		finalizeString.deleteCharAt(output.length()-2);
		finalizeString.deleteCharAt(output.length()-3);
		output = finalizeString.toString();
		return output;
	}
	
	public void addAirports(String[] airps){
		for(String s : airps){
			if(!s.equals("") && !s.equals(null)){
				createAirport(s);
			}
		}
	}
	
	public void inputResults(ArrayList<String> cleanData){
		int index = 1,year,month,day, hours, minutes, price, rows;
		String orig, dest, classDesignator, layout, flID, airline;
		Date date;
		SeatClass sClass;
		airline = cleanData.get(0);
		createAirline(airline);
		while(index < cleanData.size()){
			flID = cleanData.get(index);
			index++;
			year = Integer.parseInt(cleanData.get(index));
			index++;
			month = Integer.parseInt(cleanData.get(index));
			index++;
			day = Integer.parseInt(cleanData.get(index));
			index++;
			hours = Integer.parseInt(cleanData.get(index));
			index++;
			minutes = Integer.parseInt(cleanData.get(index));
			index++;
			date = new Date(month, day, year, hours, minutes);
			orig = cleanData.get(index);
			index++;
			dest = cleanData.get(index);
			index++;
			createFlight(airline, orig, dest, date, flID);
			while(index < cleanData.size()&&checkForClass(index, cleanData, cleanData.get(index))){
				classDesignator = cleanData.get(index);
				sClass = SeatClass.getSClass(classDesignator);
				index++;
				price = Integer.parseInt(cleanData.get(index));
				index++;
				layout = cleanData.get(index);
				index++;
				rows = Integer.parseInt(cleanData.get(index));
				index++;
				createSection(airline, flID, price, layout, rows, sClass);
			}
			
			
		}
	}
	
	public boolean checkForClass(int index, ArrayList<String> data, String toCheck){
		return (data.get(index).equalsIgnoreCase("F")||data.get(index).equalsIgnoreCase("E")||data.get(index).equalsIgnoreCase("B"));
	}
	
	public void changeSectionPrice(String flight, String sClass, int newPrice){
		
		for(FlightSection fls : sections){
			if(fls.getID().equalsIgnoreCase(flight)&&fls.getSClass().toString().equalsIgnoreCase(sClass))
				fls.setPrice(newPrice);
		}
	}
	
	public boolean validAirport(String name){
		if(name.length() != 3){
			System.out.println(name + " is not a valid Airport name.");
			return false;
		}
		else if(airports.contains(new Airport(name))){
			System.out.println(name + " already exists.");
				return false;
		}
		return true;
	}
	
	
	public void createAirport(String name){
		Airport nAirport = new Airport(name);
		if(!validAirport(name))
			return;
		if(airports.isEmpty()){
			airports.add(nAirport);
			return;
		}
		else{
			airports.add(nAirport);
			return;
		}
	}

	public void createAirline(String name){
		Airline nAirline = new Airline(name);
		if(name.length() >= 6){
			System.out.println(name + " is not a valid name. Must be less than 6 characters.");
			return;
		}
		if(airlines.isEmpty()){
			airlines.add(nAirline);
			return;
		}
		if(airlines.contains(new Airline(name))){
			System.out.println(name+" already exists.");
			return;
		}
		airlines.add(nAirline);
	}
	
	public void createFlight(String aname, String orig, String dest, Date date, String id){
		Flight flight = new Flight(aname, orig, dest, date, id);
		if(!date.validDate()){
			System.out.println(date +" is not a valid date.");
			return;
		}
		if(orig.length() !=3 || dest.length() != 3){
			System.out.println(orig + " " + dest + " are not valid.");
			return;
		}
		if(orig.compareTo(dest) == 0){
			System.out.println("Origin and destination points must be different.");
			return;
		}
		if(airlines.contains(new Airline(aname))){
			flights.add(flight);
			return;
		}
		System.out.println(aname + " is not valid.");
	}
	
	public void createSection(String air, String fID, int price, String size, int rows, SeatClass s){
		FlightSection section = new FlightSection(air, fID, price ,size, rows, s);
		if(air.length() >= 6){
			System.out.println(air + " is not a valid Airline.");
			return;
		}
		if(rows < 0){
			System.out.println("Section must have at least one seat.");
			return;
		}
		if(rows > 100){
			System.out.println("Section has too many seats");
			return;
		}
		for(Airline a : airlines){
			if(a.getName().compareTo(air) == 0){
				for(Flight f : flights){
					if(f.getID().compareTo(fID) == 0){
						addSection(section);
						return;
					}
				}
			}
		}
		System.out.println("Flight " + fID + " doesn't exist.");
	}
	
	public void addSection(FlightSection section){
		if(sections.isEmpty())
			sections.add(section);
		else{		
			for(FlightSection s : sections){
				if(section.equals(s)){
					System.out.println("This section already has a " + section.getSClass() + " class.");
					return;
				}
			}
			sections.add(section);
		}
	}
	
	
	public void bookSeat(String air, String fl, SeatClass s, int row, char col){
		int colNum;
		for(Airline a: airlines){
			if(a.getName().compareTo(air) == 0){
				for(Flight flight : flights){
					if(flight.getID().compareTo(fl) == 0){
						for(FlightSection fs : sections){
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
			for(Flight fl : flights){
				if(fl.getAirline().equals(air.getName())){
					System.out.println("\t"+fl);
					for(FlightSection fs: sections){
						if(fs.getID().equals(fl.getID())){
							System.out.println("\t\t"+fs);
						}
					}
				}
			}
			
		}
	}
	
	public void findAvailableFlights(String orig, String dest, Date date){
		System.out.println("Available Flights from "+ orig+ " to "+dest);
		for(Flight fl: flights){
			if(orig.equalsIgnoreCase(fl.getOrig()) && dest.equalsIgnoreCase(fl.getDest()) && date.equals(fl.getDate())){
				for(FlightSection fs:sections){
					if(fs.getID().equalsIgnoreCase(fl.getID()) && fs.hasAvailableSeats()){
						System.out.println("\t"+fl);
						System.out.println("\t"+fs.getSClass() + " class, Price: " + fs.getPrice());
					}
				}
			}
		}
	}
	
	public void bookPreference(String flID, String pref, String sClass){
		SeatClass seatClass;
		if(sClass.equalsIgnoreCase("business")){
			seatClass = SeatClass.business;
		}
		else if(sClass.equalsIgnoreCase("economy")){
			seatClass = SeatClass.economy;
		}
		else if(sClass.equalsIgnoreCase("first")){
			seatClass = SeatClass.first;
		}
		else{
			System.out.println(sClass + " is not a valid Seat Class");
			return;
		}
		for(FlightSection fs : sections){
			if(flID.equalsIgnoreCase(fs.getID()) && seatClass.equals(fs.getSClass())){
				Seat[][] seats = fs.getSeats();
				for(int row = 0 ; row < seats.length ; row++){
					for(int col = 0; col < seats[row].length ; col++){
						if(seats[row][col].isPreference(pref) && !seats[row][col].getBooked()){
							seats[row][col].book();
							System.out.println(pref + " seat" + seats[row][col] + " in " + seatClass + " class booked!");
							return;
						}
					}
				}
				System.out.println("There are no " + pref + " seats available in " + 
								seatClass + " class on this flight.");
				System.out.println("We will broaden our search to any seat in " + seatClass + " class.");
				fs.bookAnyInClass();
			}
		}
	}
	
	public void menu(){
		int choice;
		Scanner kb = new Scanner(System.in);
		System.out.println("1.) Initialize airport system.");
		System.out.println("2.) Change seat prices in a flight section.");
		System.out.println("3.) Look up flights with available seats.");
		System.out.println("4.) Change pricing of given seat class.");
		System.out.println("5.) Book a specific seat on a given flight.");
		System.out.println("6.) Book a seat given a seating preference.");
		System.out.println("7.) Display Airport System Details.");
		System.out.println("8.) Store Airport System Details to file.");
		System.out.println("9.) Exit the system.");
		do{
			System.out.println("Please choose desired option:");
			choice = kb.nextInt();
			switch(choice){
				case 1: 
					System.out.println("Please enter input file:");
					String input = kb.next();
					try{
						Scanner fin = new Scanner( new File(input));
						readFile(fin);
						System.out.println("Airport System initialized.");
					} catch (FileNotFoundException e){
						System.out.println("Your input file could not be found!");
						break;
					}
					break;
				case 2:
					System.out.println("Please enter flight number of section price to change:");
					String flight = kb.next();
					System.out.println("Please enter section class to price change:");
					String sClass = kb.next();
					System.out.println("Please enter desired new price:");
					int nPrice = kb.nextInt();
					changeSectionPrice(flight,sClass, nPrice);
					break;
				case 3:
					System.out.println("Please enter originating airport:");
					String orig = kb.next();
					System.out.println("Please enter desired destination");
					String dest = kb.next();
					System.out.println("Please enter date and time of departure ex; MM/DD/YYYY,15:30");
					String stringDate = kb.next();
					String[] rawDate = stringDate.split("\\W");
					ArrayList<String> clean = scrubData(rawDate);
					try{
						int month = Integer.parseInt(clean.get(0));
						int day = Integer.parseInt(clean.get(1));
						int year = Integer.parseInt(clean.get(2));
						int hour = Integer.parseInt(clean.get(3));
						int min = Integer.parseInt(clean.get(4));
						Date date = new Date(month, day, year, hour, min);
						if(date.validDate()){
							findAvailableFlights(orig,dest, date);
						}
					}catch (InputMismatchException e){
						System.out.println("Invalid date entry.");
					}
					break;
				case 4:
					System.out.println("Not functional yet");
					break;
				case 5:
					System.out.println("Not functional yet");
					break;
				case 6:
					System.out.println("Please enter desired class:");
					String seatClass = kb.next();
					System.out.println("Please enter seating preference:");
					String pref = kb.next();
					System.out.println("Please enter desired flight number:");
					String flightID = kb.next();
					bookPreference(flightID,pref,seatClass);
					break;
				case 7:
					displaySystemDetails();
					break;
				case 8:
					System.out.println("Please enter file to write to:");
					String outFile = kb.next();
					String output = processOutput();
					writeToFile(outFile, output);
					break;
			}	
		}while(choice != 9);
		System.out.println("Goodbye");
	}
	
}

