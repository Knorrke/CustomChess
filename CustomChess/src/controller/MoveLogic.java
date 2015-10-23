package controller;

import model.pieces.PieceInterface;
import player.PlayerColor;

public class MoveLogic implements MoveLogicInterface {
	
	private final String rule;
	private final PieceInterface piece;
	
	public MoveLogic(PieceInterface piece, String rule){
		this.rule = rule;
		this.piece = piece;
	}
	
	public boolean moveCorrect(int neuX, int neuY){
    	String[] rules = rule.split("\\|"); //Regeln in die einzelnen "Oder"-Teile aufteilen
    	for(int i=0; i<rules.length; i++){
    		if(zugEntsprichtRegel(rules[i], neuX, neuY)) return true; //Für jeden Teil prüfen, ob der Zug dieser Regel entspricht. Wenn eine gefunden wurde true zurückgeben
    	}
    	return false; //Wenn alle durchgearbeitet wurden und keine gefunden wurde, dann false zurückgeben
    }
	
	// überprüft, ob der Zug der bestimmten Zugregel der Form "X-Unterschied,Y-Unterschied,Sonderbedingung" entspricht
    private boolean zugEntsprichtRegel(String rule, int neuX, int neuY){
    	int[] pos = piece.getPosition();
    	int posX = pos[0];
    	int posY = pos[1];
    	
    	//if Piece is black, swap + and - (because black pieces move in the other direction)
    	if(piece.getColor()==PlayerColor.BLACK){
    		rule = swapChars(rule,'+','-');
    		rule = rule.replace('+','ä'); //Ringtausch von Plus und Minus, mit Temporärem ä...
    		rule = rule.replace('-','+');
    		rule = rule.replace('ä','-');
    	}
    	String[] regelTeil = rule.split(","); //Split at ','
    	if(regelTeil[0].equals("n") && regelTeil[1].equals("m")); // "n,m" bedeutet auf jedes beliebige Feld
    	else if(rule.contains("n")){ //Falls die Regel "beliebig viele Felder in eine Richtung" enthält
    		if(regelTeil[0].equals(regelTeil[1])){ //Falls die Regel "n,n" ist -> diagonale Bewegung
    			if(Math.abs(neuX-posX) != Math.abs(neuY-posY)) return false;
    		}else if(regelTeil[0].equals("n")){ //Falls der erste Teil beliebig, der zweite Teil aber vorgegeben ist
    			if(regelTeil[1].startsWith("+")){
    				if(neuY - posY != Integer.parseInt(regelTeil[1].substring(1))) return false;
    			}else if(regelTeil[1].startsWith("-")){
    				if(posY - neuY != Integer.parseInt(regelTeil[1].substring(1))) return false;
    			}else{
    				if(Math.abs(neuY-posY) != Integer.parseInt(regelTeil[1])) return false;
    			}
    		}else{ //Falls der zweite Teil beliebig, aber der erste Teil vorgegeben ist
    			if(regelTeil[0].startsWith("+")){
    				if(neuX - posX != Integer.parseInt(regelTeil[0].substring(1))) return false;
    			}else if(regelTeil[0].startsWith("-")){
    				if(posX - neuX != Integer.parseInt(regelTeil[0].substring(1))) return false;
    			}else{
    				if(Math.abs(neuX-posX) != Integer.parseInt(regelTeil[0])) return false;
    			}
    		}
    	}else{ //Wenn die Regel direkt die Anzahl der Felder angibt
	    	if(regelTeil[0].startsWith("+")){
	    		//Falls die x-Richtung mit + beginnt, dann nur nach rechts erlauben (neuX>posX)
	    		if(neuX - posX != Integer.parseInt(regelTeil[0].substring(1))) return false;
	    	}else if(regelTeil[0].startsWith("-")){ 
	    		//Falls die x-Richtung mit - beginnt, dann nur nach links erlauben(posX>neuX)
	    		if(posX - neuX != Integer.parseInt(regelTeil[0].substring(1))) return false;
	    	}else{
	    		//Sonst in beide Richtungen erlauben
	    		if(Math.abs(neuX-posX) != Integer.parseInt(regelTeil[0])) return false;
	    	}
	    	
	    	if(regelTeil[1].startsWith("+")){
	    		//Falls die y-Richtung mit + beginnt, dann nur nach rechts erlauben (neuY>posY)
	    		if(neuY-posY != Integer.parseInt(regelTeil[1].substring(1))) return false;
	    	}else if(regelTeil[1].startsWith("-")){
	    		//Falls die y-Richtung mit - beginnt, dann nur nach links erlauben(posY>neuY)
	    		if(posY-neuY != Integer.parseInt(regelTeil[1].substring(1))) return false;
	    	}else{
	    		if(Math.abs(neuY-posY) != Integer.parseInt(regelTeil[1])) return false;
	    	}
    	}
//    	
//    	//farbe^3 XOR verknüpfung um von 1 zu 2 und 2 zu 1 zu ändern. Fragt ab, ob eine eigene Figur bereits auf dem Feld steht
//    	if(feld.istGegnerAufFeld(farbe^3, neuX, neuY)) return false; 
//
//		if(feld.istUnschlagbar(neuX,neuY)) return false;
//		
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
    	
    	//Falls nichts von dem oberen falsch war, dann ist alles korrekt
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
}
