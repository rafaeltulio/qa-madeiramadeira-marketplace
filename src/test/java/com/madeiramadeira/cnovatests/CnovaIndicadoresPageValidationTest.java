package com.madeiramadeira.cnovatests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.madeiramadeira.objects.DashboardPage;
import com.madeiramadeira.objects.HomePage;

public class CnovaIndicadoresPageValidationTest {
	
	HomePage homePage;
	DashboardPage dashboardPage = new DashboardPage();
	
	@Before
	public void before() throws Exception {
//		
//		homePage = new HomePage();
//		homePage.urlCnova();
	}
	
	
	@Test
	public void testAcessarDashboard() throws InterruptedException{
		
		dashboardPage = homePage.preencherLogin();
		dashboardPage.clicarMenuInicio();
		dashboardPage.getValores();
	} 
	
	@After
	public void after() throws Exception {

	}

}
