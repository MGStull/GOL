import java.awt.Color;
import java.util.*;

public class Enviornment {
	
	public static void main(String[] args){
		Plane home = new Plane(10,10);
		Enviornment test = new Enviornment(10,home);
		home.print();
		
		Point notInbounds = new Point(1,-3);
		System.out.println("False = " + home.inBounds(notInbounds));
		
		
		test.incrementTime();
		home.print();
		
		System.out.println("Simulation Complete");
	}
	
	Plane myPlane;
	LinkedList<cell> cellsAtWork;
	int time = 0;
	
	
	Enviornment(int cells, Plane plane){
		cellsAtWork = new LinkedList<>();
		ArrayList<Point> pointsAtWork = new ArrayList<>();
		this.setPlane(plane);
		
		for(int i=0;i<cells;i++)
		{
			cellsAtWork.add(new cell(this.getPlane().randEmptyPoint()));
			pointsAtWork.add(cellsAtWork.get(i).getPosition());
		}
		this.getPlane().updateCells(pointsAtWork);
		
	}
	
	void incrementTime(){
		for(cell A: cellsAtWork){
			A.incrementTime(this.getPlane());
		}
		
	}
	
	
	

	public void bottleNeck()
	{
		
	}
	
	void setPlane(Plane n) {this.myPlane=n;}
	Plane getPlane() {return this.myPlane;}
	
}
