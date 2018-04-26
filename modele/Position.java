package modele;

import java.lang.Comparable;

public class Position implements Comparable {
	private int x;
	private int y;
	
	public Position(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) 
			return false;

		if (this.getClass() != o.getClass())
			return false;

		Position p = (Position) o;

		return (this.x == p.getX() && this.y == p.getY());
	}

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
}
