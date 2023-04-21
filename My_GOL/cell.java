import java.util.ArrayList;
import java.util.Random;

//@cell is the building block or base model for different cells.
//@Params
//	Alleles defines the genetics of the cells, can be varied by species
//  pressPref defines the species enviornmental range in terms of pressure.
//	speed defines how fast the species speed range is.
//  health defines the health the current cell is at.
//  metab defines the speed at which this cell burns calories.

public class cell {
	
	Point position;
	double pressPref,tempPref,speed,health,calories,metab;
	ArrayList<Allele> Alleles;
	Random rand = new Random();
		
	cell(Point a){
		this.setAlleles(randomAlleles(3,2,5));
		pressPref =Alleles.get(0).geneReader(Alleles.get(0).getGenes().get(0));
		pressPref+=Alleles.get(0).geneReader(Alleles.get(0).getGenes().get(1));
		tempPref = Alleles.get(1).geneReader(Alleles.get(1).getGenes().get(0));
		tempPref +=Alleles.get(1).geneReader(Alleles.get(1).getGenes().get(1));
		speed    = Alleles.get(2).geneReader(Alleles.get(2).getGenes().get(0));
		speed    +=Alleles.get(2).geneReader(Alleles.get(2).getGenes().get(1));
		this.setCalories(20);
		this.setHealth(10);
		this.setPosition(a);
		metab();
		this.print();		
	}
	cell(cell parent1, cell parent2){
		this.setHealth(10);
		this.setCalories(20);
		this.setAlleles(parent1.reproduce(parent2));
		metab();
	}
	
	public ArrayList<Allele> reproduce(cell other){
		ArrayList<Allele> child = new ArrayList<Allele>();
		for(int i=0;i>this.getAlleles().size();i++){
			if(rand.nextBoolean()){
				child.add(this.getAlleles().get(i));
			}
			else{
				child.add(other.getAlleles().get(i));
			}
			
		}
		return child;
	}
	
	//@randomAlleles is a function used to generate random alleles for cells.
	//@Params numAlleles numGenes numPairs 
	//	numAllels represents the number of allels for this cell
	//	numGenes represents the number of genes for this cell
	//  numPairs represents the number of wanted pairs per Allele
	public ArrayList<Allele> randomAlleles(int numAlleles,int numGenes,int numPairs){
		ArrayList<Allele> child = new ArrayList<Allele>();
		for(int i=0;i<numAlleles;i++){
			child.add(randGenes(numGenes,numPairs));
		}
		return child;
	}
	
	//@incrementTime(Plane plane) is a function that models the way cells operate in
	//	 relation to time in their enviornment, forcing adaptive change.
	// plane is the plane object on which the cell.this operates on. 
	
	public void incrementTime(Plane plane){
		this.setCalories(this.calories-this.metab);
		this.setHealth(this.getHealth()-possibleDamage());
		
		boolean moved = false;
		Point pointCont = this.possiblePoint();
		
		for(int i=0;i<this.speed;i++){
			pointCont = possiblePoint();
			if(plane.inBounds(pointCont)&& !plane.get(pointCont).getHasCell())
				moved = true;
			else {
				pointCont = this.position;
			}
			pointCont.print();
		}
		
		plane.get(pointCont).setHasCell(true);
		if(pointCont != this.position)
			plane.get(this.getPosition()).setHasCell(false);
		this.setPosition(pointCont);
		
	}
	
	//- means its gaining health
	double possibleDamage(){
		double damage = -10;
		if(this.getCalories()<0)
			damage += this.calories;
		else if(this.getCalories()<-5)
			damage += this.calories*1.25;
		return damage;
		
	}
	
	//@possiblePoint() is a function that defines the distance a cell can travel
	//   This is based on genetice diposition. Which is random.
	public Point possiblePoint(){
		int moveX=0,moveY=0;
		moveX = rand.nextInt(-moveX,moveX);
		moveY = rand.nextInt(-moveY,moveY);
		
		Point moveP = new Point(moveX,moveY);
		return this.position.add(moveP);
	}
	
	//@metab() is a relatively exponential punishing mechanisms for those with higher
	//   speed.
	public void metab(){
		this.metab = Math.sqrt(this.speed*this.speed) +this.speed;
	}
	
	public Allele randGenes(int numGenes,int numPairs){
		ArrayList<NTCombination[]> genes = new ArrayList<NTCombination[]>();
		for(int i=0;i<numGenes;i++){
			genes.add(new NTCombination[5]);
			for(int j=0;j<numPairs;j++){
				switch (rand.nextInt(0,4)){
					case(0):
						genes.get(i)[j] = new NTCombination("A");
						break;
					case(1):
						genes.get(i)[j] = new NTCombination("T");
						break;
					case(2):
						genes.get(i)[j] = new NTCombination("C");
						break;
					case(3):
						genes.get(i)[j] = new NTCombination("G");
						break;
				}
			}
		}
		return new Allele(genes);
	}

	
	
	void print(){System.out.println("Cell: \n Pressure Pref:" +this.pressPref+"\n Temp Pref:" + tempPref + "\n Speed: "+ this.speed + "\n Metab: " + this.metab );}
	
	
	ArrayList<Allele> getAlleles(){return this.Alleles;}
	Point getPosition(){return this.position;}
	double getPresPref(){return this.pressPref;}
	double getTempPref(){return this.tempPref;}
	double getSpeed(){return this.speed;}
	double getHealth(){return this.health;}
	double getMetab(){return this.metab;}
	double getCalories(){return this.calories;}
	
	void setAlleles(ArrayList<Allele> a){this.Alleles = a;}
	void setPosition(Point n){this.position=n;}
	void setPresPref(double n){this.pressPref=n;}
	void setTempPref(double n){this.tempPref=n;}
	void setSpeed(double n) {this.speed=n;}
	void setHealth(double n) {this.health=n;}
	void setMetab(int n) {this.metab=n;}
	void setCalories(double d){this.calories = d;}
	
}
