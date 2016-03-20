package travelpackage;

public enum SeatClass {
	
	first,business,economy;
	
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
