package br.com.liraquality.teste;
import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import br.com.liraquality.core.PaginaBase;
import br.com.liraquality.core.PaginaInicial;
import br.com.liraquality.sistemax.SistemaX;



public class TesteGoogle extends PaginaBase {
	SistemaX sistemaX = new SistemaX();
	ArrayList<String> dados1 = new ArrayList();
	ArrayList<String> dados2 = new ArrayList();
	
	@Before
	public void setUp() throws Exception {
		sistemaX.setCadastro();
		dados1= sistemaX.getDiretor();
		dados2= sistemaX.getFilmes();
			
	}
	
	
	@After
	public void tearDown() throws Exception {
		 killDriver();
	}

	@Test
	public void test() throws AWTException {
		String diretor;
		String diretores[] = new String[5];
		String filme;
		String filmes[] = new String[5];
				
		navegateTo("https://www.google.com.br");
		for (int i = 0; i < dados1.size(); i++)
	      {
			 diretor = dados1.get(i).toString();
			 diretores = diretor.split(";");
			 filme = dados2.get(i).toString();
			 filmes = filme.split(";");
			 findElement(By.name("q")).sendKeys(diretores[0]+" "+filmes[0]);
			 findElement(By.name("q")).sendKeys(Keys.ENTER);
			 waitPageLoad1();
			System.out.println(findElement(By.id("resultStats")).getText());
			 findElement(By.name("q")).clear();
			
	      } 
		
		 
	}
	
	
	
	

	@Override
	public ExpectedCondition<Boolean> getExpectedCondition() {
		// Validar Paginas 
		return null;
	}

}
