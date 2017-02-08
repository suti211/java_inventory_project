package inventory;

public class CDProduct extends Product {
	
	private int numOfTracks;
	
	public int getNumOfTracks() {
		return numOfTracks;
	}

	public CDProduct(String name, int price, int numOfTracks){
		this.name = name;
		this.price = price;
		this.numOfTracks = numOfTracks;
		
	}
}
