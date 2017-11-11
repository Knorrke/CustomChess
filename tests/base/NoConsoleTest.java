package base;

import static helper.Helper.console;
import static org.mockito.Mockito.mock;

import org.junit.BeforeClass;

import com.diogonunes.jcdp.color.ColoredPrinter;

public class NoConsoleTest {

	@BeforeClass
    public static void setUpBaseClass() {
       console = mock(ColoredPrinter.class);
	}
}
