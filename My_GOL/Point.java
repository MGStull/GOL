
public class Point{
int x,y;
	Point(int X,int Y){
		this.setX(X);
		this.setY(Y);
	}
	
	
	boolean equals(Point other){
		if(other.getX()==this.getX() && other.getY() == this.getY()){
			return true;
		}
		return false;
	}
	
	Point add(Point other){
		return new Point((this.getX()+other.getX()),(this.getY()+other.getY()));
	}
	
	
	void print(){System.out.println("Point: \n  X = "+this.getX()+ "\n  Y = "+ this.getY());}
	
	int getX(){return this.x;}
	int getY(){return this.y;}
	
	void setX(int n){this.x=n;}
	void setY(int n){this.y=n;}
	
}
