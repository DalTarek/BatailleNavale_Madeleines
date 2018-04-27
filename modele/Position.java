package modele;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public Position(Position p){
		this.x=p.getX();
		this.y=p.getY();
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int nx){
		this.x=nx;
	}
	public void setY(int ny){
		this.y=ny;
	}
	
	public boolean same(Position p){
		if((this.getX()==p.getX())&&(this.getY()==p.getY())){
			return true;
		}
		return false;
	}
}
