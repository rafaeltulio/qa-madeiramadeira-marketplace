package com.madeiramadeira.objects;

import java.sql.Connection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.madeiramadeira.uteis.Utils;
import com.madeiramadeira.uteis.dbconnection;

public class DashboardB2wPage {

	/**
	 * INSTÂNCIA PRIVADA DO WEBDRIVER QUE VIRA DA SUITE DE TESTES
	 */

	private WebDriver driver;
	private WebDriverWait wait;

	/**
	 * 
	 * CONTRUTOR ADICIONAR A INSTÂNCIA DO WEBDRIVER
	 */

	private String entrega;
	private String reclamacao;
	private String contato;
	private String notaAvaliacao;
	private String qtdAvaliacao;
	private String indicadorAvaliacao;
	
	/**
	 * DEFINIÇÃO ÚNICA DOS LOCATORS
	 * @throws InterruptedException 
	 */
	
	public DashboardB2wPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Integer.parseInt(Utils.getPropertyValue("timeout.global.web")));
	}


	public void preencherLogin(String login) {
		WebElement formLogin= driver.findElement(By.name("loginname"));
		formLogin.clear();
		formLogin.sendKeys(login);
		formLogin.sendKeys(Keys.TAB);
		System.out.println("Login Preenchido: "+login);
	}

	public void preencherSenha(String senha) {
		WebElement formSenha= driver.findElement(By.id("loginpwd"));
		formSenha.clear();
		formSenha.sendKeys(senha);
		formSenha.sendKeys(Keys.TAB);
		System.out.println("Senha Peenchida: "+senha);
	}

	public void clicarButtonEntrar() throws InterruptedException {
		WebElement buttonEntrar = driver.findElement(By.cssSelector(".btn.btn-primary.col-sm-12.ng-binding"));
		buttonEntrar.click();
		System.out.println("Botão Entrar");
		Thread.sleep(1000);
		System.out.println(driver.getCurrentUrl());
	}


	public void getDadosB2w() throws InterruptedException {
		Thread.sleep(4000);
		// PERFORMACE DAS ENTREGAS
		WebElement getEntrega = driver.findElement(By.cssSelector("#telaClientesSAC #contact-rate .stats-title > span"));
		entrega = getEntrega.getText();
		
		// RECLAMAÇÕES NOS ÚLTIMOS 7 DIAS
		WebElement getReclamacao = driver.findElement(By.cssSelector("#telaClientesSAC #complaint-rate .stats-title > span"));
		reclamacao = getReclamacao.getText();
		
		// CONTATO EM 7 DIAS
		WebElement getContato = driver.findElement(By.cssSelector("#telaClientesSAC div:nth-child(4) .stats-title > span"));
		contato = getContato.getText();
		
		// NOTA AVALIAÇÃO NOS ÚLTIMOS 30 DIAS
		WebElement getNotaAvaliacao = driver.findElement(By.cssSelector("#telaClientesSAC #dash-avaliacao .stats-title > span"));
		notaAvaliacao = getNotaAvaliacao.getText();
		
		WebElement getQtdAvaliacao = driver.findElement(By.cssSelector("#telaClientesSAC #dash-avaliacao .stats-title > div"));
		qtdAvaliacao = getQtdAvaliacao.getText(); // PEGA O TEXTO COMPLETO QUE TEM OS DADOS
		int firstSpace = qtdAvaliacao.indexOf(" "); // PEGA A POSIÇÃO DO PRIMEIRO ESPAÇO DO TEXTO DA QUANTIDADE
		
		indicadorAvaliacao = qtdAvaliacao.substring(firstSpace, qtdAvaliacao.length()); //PEGA O TEXTO DA QUANTIDADE 
		qtdAvaliacao = qtdAvaliacao.substring(0, firstSpace); // PEGA O VALOR DA QUANTIDADE 
		
		String indicadorQntAvaliacao = "Quantidade de " + indicadorAvaliacao; // SETA O INDICE DE QUALIDADE
		String indicadorNotaAvaliacao = "Nota de " + indicadorAvaliacao; // SETA O INDICE PARA SALVAR NO BANCO Q NOTA DE AVALIAÇÃO 
		
		System.out.println(indicadorQntAvaliacao +": "+qtdAvaliacao);
		System.out.println(indicadorNotaAvaliacao +": "+getNotaAvaliacao.getText());
		System.out.println(getEntrega.getText());
		System.out.println(getReclamacao.getText());
		System.out.println(getContato.getText());
	}
	
	public Boolean saveDadosB2w() {
		Connection con = dbconnection.get();
		String query = "INSERT INTO marketplace_b2w_desempenho (created_at, indicador, valor) "
				+ "VALUES (NOW(), 'Performance das Entregas',"+Float.parseFloat(entrega.substring(0, entrega.length() - 1))+"),"
				+ "(NOW(), 'Reclamação em 7 dias' ,"+Float.parseFloat(reclamacao.substring(0, reclamacao.length() - 1))+"),"
				+ "(NOW(), 'Contato em 7 dias' ,"+Float.parseFloat(contato.substring(0, contato.length() - 1))+"),"
				+ "(NOW(), 'Nota de " + indicadorAvaliacao +"',"+Float.parseFloat(notaAvaliacao)+"),"
				+ "(NOW(), 'Quantidade de " + indicadorAvaliacao +"',"+Float.parseFloat(qtdAvaliacao)+")"
			;
		System.out.println(query);

		try {
			con.createStatement().execute(query);
			con.close();
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		}
		
	}

}
