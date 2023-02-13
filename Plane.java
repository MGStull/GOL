import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Plane {
//pressure, temperature 
InfMod[][] plane;
Color[][] visual;

	Plane(int[][] Pressure,int[][] Temp,int[][] Food, Point[] hasCell ){
		Point currPoint;
		plane = new InfMod[Pressure.length][Pressure[0].length];
		for(int i=0;i<Pressure.length;i++){
			for(int j=0;j<Pressure[i].length;j++){
				currPoint = new Point(i,j);
				for(Point cellP : hasCell) {
					if(currPoint.equals(cellP)){
						plane[i][j] = new InfMod(Food[i][j],Temp[i][j],Pressure[i][j],true);
					}
				}
				plane[i][j] = new InfMod(Food[i][j],Temp[i][j],Pressure[i][j],false);
			}
		}
	}
	Plane(int Height, int Width){
		Random rand = new Random();
		plane = new InfMod[Height][Width];
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				plane[i][j] = new InfMod(rand.nextInt(0-i, 20-i),i);
			}
		}
	}

	Point randEmptyPoint()
	{
		Random rand = new Random();
		boolean openLoca = false;
		int Xval=0, Yval=0;
		Point key;
		while(!openLoca){
			Xval = rand.nextInt(0, plane[0].length);
			Yval = rand.nextInt(0,plane.length);
			if(!this.get(Xval, Yval).hasCell) {
				openLoca = !openLoca;
			}
		}
		return new Point(Xval,Yval);
	}
	
	
	void print(){
		for(InfMod[] y : plane)	{
			for(InfMod x : y){
				if(x.hasCell){
					System.out.print("-");
				}
				else{
					System.out.print(" ");
				}	
			}
			System.out.println();
		}
	}
	
	void clear(boolean CellClear,boolean TempClear,boolean PressClear,boolean FoodClear)
	{
		if(CellClear) {
			for(InfMod[] a: plane) {
				for(InfMod b : a) {
					b.setHasCell(false);
				}
			}
		}
		if(TempClear) {
			for(InfMod[] a: plane) {
				for(InfMod b : a) {
					b.setTemp(0);
				}
			}
		}
		if(PressClear) {
			for(InfMod[] a: plane) {
				for(InfMod b : a) {
					b.setPressure(0);
				}
			}
		}
		if(FoodClear) {
			for(InfMod[] a: plane) {
				for(InfMod b : a) {
					b.setSunlight(0);
				}
			}
		}
		
	}
	void updateCells(ArrayList<Point> cells){
		this.clear(true, false, false, false);;
		for(Point a: cells){
			get(a).setHasCell(true);
		}
	}
	
	//@inBounds if the point is within the bounds of the plane will return true
	//Point is the given point to be search for on this plane
	public boolean inBounds(Point key) {
		if((key.getX() > 0 && key.getX() < this.plane[0].length) && (key.getY() > 0 && key.getY() < this.plane.length)){return true;}
		return false;			
	}
	
	public InfMod get(Point given){return plane[given.getX()][given.getY()];}
	public Color getColor(Point given){return visual[given.getX()][given.getY()];}
	public InfMod get(int x,int y){return plane[x][y];}
	public Color getColor(int x, int y){return visual[x][y];}
	public Color[][] getVisual(){return this.visual;}
	public InfMod[][] getPlane(){return this.plane;}
	

	public class InfMod{
		
		boolean hasSunlight;
		int sunlight,temp,pressure;
		boolean hasCell;
		
		InfMod(int Sunlight,int Temp,int Pressure,boolean HasCell){
			this.setSunlight(Sunlight);
			this.setPressure(Pressure);
			this.setTemp(Temp);
			this.setHasCell(HasCell);
		}
		InfMod(int Temp,int Pressure){
			this.setSunlight(0);
			this.setPressure(Pressure);
			this.setTemp(Temp);
			this.setHasCell(false);
		}
		
		public String toString(){
			String cont = "Temp:" + this.temp + " Pressure:" + this.pressure + " Sunlight:" + this.sunlight + " Has Cell:" + this.hasCell; 
			return cont;
		}
		
		
		int getSunlight(){return this.sunlight;}
		int getTemp(){return this.temp;}
		int getPressure(){return this.pressure;}
		boolean getHasCell() {return this.hasCell;}
		
		void setSunlight(int n){this.sunlight=n;}
		void setTemp(int n){this.temp=n;}
		void setPressure(int n){this.pressure=n;}
		void setHasCell(boolean n) {this.hasCell=n;}
		
		
	}


}

