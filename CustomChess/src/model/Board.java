package model;

public class Board {

	private Field[][] fields;
	
	public Board(int x, int y){
		fields = new Field[x][y];
	}

	public Field[][] getFields() {
		return fields;
	}
}
