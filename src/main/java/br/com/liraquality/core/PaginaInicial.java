package br.com.liraquality.core;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaginaInicial extends PaginaBase {

	
	
	public PaginaInicial(PaginaBase paginaBase) {
		super(paginaBase);
	}

	
	
	@Override
	public ExpectedCondition<Boolean> getExpectedCondition() {
		return ExpectedConditions.and(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By
						.id("formMenu")),
				ExpectedConditions.presenceOfElementLocated(By
						.id("frameContent")));
	}

	
}
