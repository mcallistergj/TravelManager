package travelpackage;

public enum SeatClass {
	
	first,business,economy;
	
	public static SeatClass getSClass(String designator){
		if(designator.equalsIgnoreCase("F"))
			return first;
		if(designator.equalsIgnoreCase("E"))
			return economy;
		return business;
	}
	
	@Override
	public String toString(){
		switch(this){
			case first: return "First";
			case business: return  "Business";
			case economy: return "Economy";
		}
		return "";
		
	}
}
