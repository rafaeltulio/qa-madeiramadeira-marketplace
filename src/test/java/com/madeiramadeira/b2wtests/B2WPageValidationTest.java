package com.madeiramadeira.b2wtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.madeiramadeira.objects.DashboardB2wPage;
import com.madeiramadeira.objects.HomePage;
import com.madeiramadeira.uteis.Utils;

public class B2WPageValidationTest {
	
	private WebDriver driver;
	HomePage homePage;
	DashboardB2wPage dashboardB2w;
	
	@Before
	public void before() throws Exception {
		
		driver = Utils.selectBrowser(Utils.getPropertyValue("browser"));
		homePage = new HomePage(driver);
	}
	
	@Test
	public void testAcessarDashboard() throws InterruptedException{
		
		homePage.urlB2w();
		dashboardB2w = homePage.acessPortal();
		dashboardB2w.preencherLogin("B2W_PARCEIRO_MADEIRAMADEIRA");
		dashboardB2w.preencherSenha("bkn8190");
		dashboardB2w.clicarButtonEntrar();
		dashboardB2w.getDadosB2w();
//		dashboardB2w.saveDadosB2w();
	}
	
	@After
	public void after() throws Exception {
		
		driver.quit();
		
	}

}
  