
public class Producer extends cell {
	
	//@ Producer is a cell type that provides a source of food for the enviornment
	//		this is initialized the same way as a normal cell, but has overwriten functions
	//		that describe and implement its special characteristics. Such as eating algae.
	Producer(cell parent1, cell parent2) {
		super(parent1, parent2);
		
	}
	Producer(Point position){
		super(position);
	}

}
