package com.madeiramadeira.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.madeiramadeira.uteis.Utils;

/**
 * PÁGINA QUE DISPONIBILIZA OS SERVIÇOS JÁ EXISTENTES NA HOMEPAGE DO SITE
 * 
 */

public class HomePage {

	/**
	 * INSTÂNCIA PRIVADA DO WEBDRIVER QUE VIRA DA SUITE DE TESTES
	 */

	private WebDriver driver;
	private WebDriverWait wait;
	
	/**
	 * DEFINIÇÃO DOS LOCATORS UTILIZADOS NA PÁGINA
	 */
	
	static By buttonLogin = By.cssSelector("#loginForm > form > div.form-actions > button.green.pull-right");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Integer.parseInt(Utils.getPropertyValue("timeout.global.web")));
	}

	public void urlCnova() {
		driver.navigate().to("https://lojista.ehub.com.br/adminseller/login.jsp?st=MADEIRAMADEIRA");	
		System.out.println("Acessando URL: https://lojista.ehub.com.br/adminseller/login.jsp?st=MADEIRAMADEIRA");
	}
	
	public void urlB2w() {
		driver.get(Utils.getPropertyValue("url.inicial.web"));
		System.out.println("Acessando URL: "+Utils.getPropertyValue("url.inicial.web"));
	}
	
	public DashboardPage preencherLogin() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(buttonLogin));
		WebElement inputUser = driver.findElement(By.name("j_username"));
		WebElement inputPassword = driver.findElement(By.name("j_password"));
		WebElement buttonLogar = driver.findElement(By.cssSelector(("#loginForm > form > div.form-actions > button.green.pull-right")));
		
		inputUser.clear();
		inputUser.sendKeys("madeira.madeira");
		inputPassword.clear();
		inputPassword.sendKeys("Madeira2017*");

		System.out.println("Preencher Login: madeira.madeira | Madeira2017");
		buttonLogar.click();
		
		return new DashboardPage();
	}

	public DashboardB2wPage acessPortal() throws InterruptedException {
		
		Thread.sleep(1000);
		WebElement acessoPortal = driver.findElement(By.cssSelector("#access.dropdown a.dropdown-toggle.btn-login"));
		acessoPortal.click();
		System.out.println("Acessar Portal");
		
		return new DashboardB2wPage(driver);
	}

}
