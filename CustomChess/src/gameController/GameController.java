package gameController;

import java.util.ArrayList;

import player.PlayerColor;

public class GameController {

	private ArrayList<PlayerColor> turnOrder = new ArrayList<>();

	public GameController(PlayerColor startingPlayer){
		turnOrder.add(startingPlayer);
		turnOrder.add(startingPlayer.getOppositColor());
	}
	
	public PlayerColor getCurrentPlayer() {
		return turnOrder.get(0);
	}
	
	public PlayerColor nextPlayer(){
		turnOrder.add(turnOrder.remove(0));
		return getCurrentPlayer();
	}
}
