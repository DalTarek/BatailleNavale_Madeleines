package modele;

import java.lang.Comparable;

public class Position implements Comparable {
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
	
	/**
	 * Return true si deux position sont egales
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) 
			return false;

		if (this.getClass() != o.getClass())
			return false;

		Position p = (Position) o;

		return (this.x == p.getX() && this.y == p.getY());
	}
	/**
	 * Méthode pour comparer deux position 
	 * return 0,1,-1 en fonction des position
	 * 			
	 */
	public int compareTo(Object o) {
		if (o == null || this.getClass() != o.getClass())
			return 1;

		Position p = (Position) o;

		if (this.x == p.getX() && this.y == p.getY())
			return 0;
		
		if (this.y == p.getY()) {
			if (this.x > p.getX()) {
				return 1;
			} else {
				return -1;
			}
		} else {
			if (this.y > p.getY()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
	/**
	 * méthode de toString
	 */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
