package travelpackage;

public abstract class Terminal {

	protected String name;
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public boolean equals(Object o){
		return this.name.compareTo(((Terminal)o).name) == 0;
	}
	
}
