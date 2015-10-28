package file;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileLoader {
	private BufferedReader reader;
	private String spiel;

	public FileLoader(){
		spiel = "Standard";
	}
	public FileLoader(String spiel){
		this.spiel = spiel;
	}
	
	public String dateiLesen(String dateiname){
		String gesamt = "";
		try{
			reader = new BufferedReader(new FileReader(dateiname));
			String zeile = "";
			
			while(true){
				zeile = reader.readLine();
				if(zeile == null) break;
				gesamt = gesamt + zeile;
			}
		}catch(IOException e){
			System.err.println("Fehler beim Lesen der Datei!" + dateiname);
		}finally{
			try{ reader.close();} catch(Exception ignore){}
		}
		return gesamt;
	}
}