package TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.liraquality.teste.TesteAPI;
import br.com.liraquality.teste.TesteGoogle;
 
 
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
		TesteAPI.class,
		TesteGoogle.class,
})
public class SuiteTest {
	
}