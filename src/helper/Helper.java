package helper;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;

public class Helper {
	public static final int X=0, Y=1;

	public static int[] pos(int... pos) {
		return pos;
	}
	
	public static int[] pos(String s) {
		s = s.toLowerCase();
		char col = s.charAt(0);
		return new int[] {col-'a', Integer.parseInt(s.substring(1)) - 1};
	}
	
	public static final ColoredPrinter console = new ColoredPrinter.Builder(1, false)
            .foreground(FColor.WHITE).background(BColor.BLUE)
            .build();
}