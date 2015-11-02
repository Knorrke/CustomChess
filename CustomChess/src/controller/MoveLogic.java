package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import controller.moveLogicAdapter.MoveLogicAdapterInterface;
import model.pieces.Piece;
import player.PlayerColor;

public class MoveLogic implements MoveLogicInterface {
	
	private final String rule;
	private final Piece piece;
	private final static int X = 0, Y=1;
	private HashMap<Character, MoveLogicAdapterInterface> adapterList;
	
	
	public MoveLogic(Piece piece, String rule){
		this.rule = rule;
		this.piece = piece;
		adapterList = new HashMap<>();
	}
	
	/**
	 * Checks if the piece of this controller can move to the specified position
	 * @param newPos Array containing x and y position, where piece should move to
	 * @return true if move is according to the rule, false otherwise
	 */
	public boolean moveCorrect(int[] newPos){
		if(newPos.length!=2){
			throw new IllegalArgumentException("Position not two-dimensional: " + Arrays.toString(newPos));
		}else{
		
			String[] rules = rule.split("\\|"); //Split total rule at "or" into single segments
	    	for(int i=0; i<rules.length; i++){
	    		if(moveAccordingToSingleRule(rules[i], newPos)){
	    			return true; //If the move matches one of the rules then its correct
	    		}
	    	}
	    	return false; //if no correct rule was found previously then its wrong
		}
    }
	
	// überprüft, ob der Zug der bestimmten Zugregel der Form "X-Unterschied,Y-Unterschied,Sonderbedingung" entspricht
    /**
     * Checks if the move to specified position matches the rule.
     * @param rule a single rule of type "x-Difference, y-Difference, special conditions"
     * @param newPos position to move to as {x,y} array
     * @return true if the move matches the specified rule
     */
	private boolean moveAccordingToSingleRule(String rule, int[] newPos){
//    	
//    	//farbe^3 XOR verknüpfung um von 1 zu 2 und 2 zu 1 zu ändern. Fragt ab, ob eine eigene Figur bereits auf dem Feld steht
//    	if(feld.istGegnerAufFeld(farbe^3, neuX, neuY)) return false; 
//
//		if(feld.istUnschlagbar(neuX,neuY)) return false;
//		
		return allowedMovement(rule,newPos) && matchingSpecialConditions(rule,newPos);
	}
	
    /**
     * Checks, if the movement is allowed, ignoring special conditions
     * @param rule rule specifying allowed movement
     * @param newPos position to move to as {x,y} array
     * @return true, if movement is allowed, false otherwise
     */
	private boolean allowedMovement(String rule, int[] newPos) {
		int[] pos = piece.getPosition();
    	
		//if Piece is black, swap + and - (because black pieces move in the other direction)
    	if(piece.getColor()==PlayerColor.BLACK){
    		rule = swapChars(rule,'+','-');
    	}
    	
    	String[] regelTeil = rule.split(","); //Split at ','
    	if(allowEverywhere(regelTeil)); // piece can move everywhere 
    	else if(regelTeil[0].equals("n") || regelTeil[1].equals("n")){
    		if(allowDiagonal(regelTeil)){ //piece can move diagonal
    			if(Math.abs(newPos[0]-pos[0]) != Math.abs(newPos[1]-pos[1])) return false;
    		}else{
    			int i = regelTeil[0].equals("n") ? 1 : 0;
    			if(!correctDistance(regelTeil[i],newPos[i],pos[i])){
    				return false;
    			}
    		}
    	}else{ //If rule specifies correct distance directly
	    	if(!correctDistance(regelTeil[X],newPos[X],pos[X]) 
	    	 || !correctDistance(regelTeil[Y],newPos[Y],pos[Y])){
	    		return false;
	    	}
	    }
    	
    	return true;
	}

	/**
	 * Checks if the move matches the special conditions of the rule
	 * @param rule Single rule of type "x-difference, y-difference, specialconditions"
	 * @param newPos position to move to
	 * @return true, if move matches special conditions, false otherwise
	 */
    private boolean matchingSpecialConditions(String rule, int[] newPos) {
    	String[] ruleparts = rule.split(",");
    	if(ruleparts.length > 2){ //if there are special conditions
    		for(char condition : ruleparts[2].toCharArray()){ //for each special condition
    			MoveLogicAdapterInterface mla = adapterList.get(condition);
    			if(mla != null && !mla.isMatchingSpecialCondition(rule, newPos)){
    				return false;
        		}
    		}
    	}
    	return true;

//    	if(regelTeil.length > 2){
//    		for(int i=0; i<regelTeil[2].length();i++){
//	    		switch(regelTeil[2].charAt(i)){
//	    		case 'X': //Nur schlagen
//	    			if(!feld.istGegnerAufFeld(farbe, neuX, neuY)) return false;
//	    			break;
//	    		case 'M': //Nur bewegen (Move)
//	    			if(feld.istGegnerAufFeld(farbe, neuX, neuY)) return false;
//	    			break;
//	    		case '!': //Nur wenn Feld nicht bedroht ist
//	    			if(feld.istBedroht(farbe, neuX,neuY)) return false;
//	    			break;
//	    		case 'F': //Nur wenn der Weg frei ist
//	    			if(posX-neuX == 0){ //Wenn die Figur sich gerade nach oben bewegt, dann einfach nur die y-Richtung durchlaufen
//	    				
//	    				boolean next = true; // Braucht man, weil man auf ein Feld eines Gegners ziehen kann, aber nicht weiter
//	    				
//	    				//Differenz durchlaufen, mit Signum die Richtung bestimmen.
//	    				for(int dif=0; (-1)*Math.abs(posY-neuY) <= dif && dif <= Math.abs(posY-neuY); dif = dif + (int)(Math.signum(neuY-posY))){
//		    				if(dif==0)continue; //das eigene Feld nicht prüfen...
//	    					if(!next) return false;
//		    				if(feld.istGegnerAufFeld(farbe^3, posX, posY+dif)) return false; //wenn auf dem Feld eine eigene Figur steht
//		    				if(feld.istGegnerAufFeld(farbe, posX, posY+dif)) next = false; //wenn auf dem Feld eine gegnerische Figur steht, dann nicht dahinter
//	    				}
//	    			}else{ //Wenn die Figur sich nicht senkrecht bewegt, dann eine Gerade aufstellen, und alle Felder darauf durchlaufen.
//		    			double m = (double)(posY-neuY)/(posX-neuX); //Steigung der Geraden
//		    			double t = posY - (double)(posY-neuY)/(posX-neuX) * posX; //Y-Achsenabschnitt
//		    		
//		    			if(Math.abs(posX-neuX)>Math.abs(posY-neuY)){ //Wenn der X-Unterschied größer ist, dann diesen durchlaufen
//		    				boolean next = true;
//		    				
//		    				//X-Differenz durchlaufen, mit Signum die Richtung bestimmen.
//		    				for(int dif=0; (-1)*Math.abs(posX-neuX) <= dif && dif <= Math.abs(posX-neuX); dif = dif + (int)(Math.signum(neuX-posX))){
//		    					if(dif==0)continue; //das eigene Feld nicht prüfen...
//		    					if(!next) return false;
//			    				int y = (int) (m*(posX+dif) +t +0.5); //Y-Wert berechnen, +0.5 für richtige Rundung
//			    				if(feld.istGegnerAufFeld(farbe^3, posX+dif, y)) return false; //Wenn auf dem Feld eine eigene Figur steht
//			    				if(feld.istGegnerAufFeld(farbe, posX+dif, y)) next = false; // Wenn auf dem Feld eine gegnerische Figur steht, dann nicht dahinter
//			    			}
//		    			}else{
//		    				boolean next = true;
//		    				
//		    				//Y-Differenz durchlaufen, mit Signum die Richtung bestimmen.
//		    				for(int dif=0; (-1)*Math.abs(posY-neuY) <= dif && dif <= Math.abs(posY-neuY); dif = dif + (int)(Math.signum(neuY-posY))){
//		    					if(dif==0)continue; //das eigene Feld nicht prüfen...
//		    					if(!next) return false;
//		    					int x = (int)(((posY+dif)-t)/m +0.5); //X-Wert berechnen, +0.5 für richtige Rundung
//			    				if(feld.istGegnerAufFeld(farbe^3, x, posY+dif)) return false; //Wenn auf dem Feld eine eigene Figur steht
//			    				if(feld.istGegnerAufFeld(farbe, x, posY+dif)) next = false; // Wenn auf dem Feld eine gegnerische Figur steht, dann nicht dahinter
//		    				}
//		    			}
//	    			}
//	    			break;
//	    		case 'B': //Nur wenn noch nicht bewegt
//	    			if(bewegt) return false;
//	    			break;
//	    			
//	    		case '2': case '3': case '4': //zwei- /drei- /vier-mal ziehen
//	    			break;
//	    		case 'S': //MUSS über eine Figur springen => Gegenteil von F
//	    			if(zugEntsprichtRegel(regelTeil[0]+","+regelTeil[1]+",F", neuX, neuY)) return false;
//	    			break;
//	    		case '#': //Nur, wenn er selbst bedroht wird
//	    			if(!feld.istBedroht(farbe, posX, posY)) return false;
//	    			break;
//	    		case '*': //Nur, wenn er selbst Nicht bedroht wird;
//	    			if(feld.istBedroht(farbe, posX, posY)) return false;
//	    			break;
//	    		default:
//	    			System.err.println("Unbekannte Zugregel " + regelTeil[3].charAt(0));
//	    			break;
//	    		}
//    		}
//    	}
	}

	
	/**
	 * Checks for one rule part, if the position is in correct Distance
	 * @param rulepart
	 * @param newPos
	 * @param pos
	 * @return
	 */
	private boolean correctDistance(String rulepart, int newPos, int pos) {
		if(rulepart.startsWith("+")){
			if(newPos-pos != Integer.parseInt(rulepart.substring(1))){
				return false;
			}
		}else if(rulepart.startsWith("-")){
			if(pos-newPos != Integer.parseInt(rulepart.substring(1))){
				return false;
			}
		}else{
			if(Math.abs(newPos-pos) != Integer.parseInt(rulepart)){
				return false;
			}
		}
		
		return true;
	}

	/**
     * Swaps all Occurences of Character a and Character b
     * @param source
     * @param a
     * @param b
     * @return
     */
	private String swapChars(String source, char a, char b) {
		StringBuilder sb = new StringBuilder();
		for (char c : source.toCharArray()) {
		  if (c == a) sb.append(b);
		  else if (c == b) sb.append(a);
		  else sb.append(c);
		}
		return sb.toString();
	}
	
	/**
	 * Checks if the rule is of form "n,m"
	 * @param ruleparts
	 * @return
	 */
	private boolean allowEverywhere(String[] ruleparts){
		if(ruleparts.length < 2){
			throw new IllegalArgumentException("Wrong rule");
		}
		return ruleparts[0].equals("n") && ruleparts[1].equals("m");
	}
	
	/**
	 * Checks if the rule is of form "n,n"
	 * @param ruleparts
	 * @return
	 */
	private boolean allowDiagonal(String[] ruleparts) {
		if(ruleparts.length < 2){
			throw new IllegalArgumentException("Wrong rule");
		}
		return ruleparts[0].equals(ruleparts[1]);
	}
	
	/**
	 * Adds a MoveLogicAdapter for the specified condition in rules
	 * @param condition
	 * @param mla
	 */
	public void addMoveLogicAdapter(char condition, MoveLogicAdapterInterface mla){
		adapterList.put(condition, mla);
	}
}
