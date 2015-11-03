package player;

public enum PlayerColor {
	WHITE, BLACK;
	
	public PlayerColor getOppositColor(){
		return (this==WHITE) ? BLACK : WHITE;
	}
}
