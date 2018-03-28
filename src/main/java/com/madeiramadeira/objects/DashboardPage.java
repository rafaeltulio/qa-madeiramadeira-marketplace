package com.madeiramadeira.objects;

import java.sql.Connection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.madeiramadeira.uteis.dbconnection;

public class DashboardPage {
	
	/**
	 * Instância privada do webDriver que vira da suite de teste
	 */

	private static WebDriver driver;
	private static WebDriverWait wait;
	/**
	 * Construtor que ira adicionar a Instância do WebDriver para utilizacao dos
	 * metodos
	 */
	
	/**
	 * Definição única dos locators utilizados na página
	 * @throws InterruptedException 
	 */

	private String[][] arrayCampos = new String[4][4];
	static By logo = By.cssSelector(".brand");
	
	public DashboardPage(){
		
	}
	
	
	public void clicarMenuInicio() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(logo));
//		WebElement menuInicio = driver.findElement(By.id("idashboard"));
//		menuInicio.click();
//		System.out.println("Clicado Menu Início");
	}

	public void getValores() throws InterruptedException {
		Thread.sleep(5000);
		WebElement desempenho = driver.findElement(By.cssSelector("#sla-management-indicators > div.sla-label-color.label-text-align.sla-performance-header > h4 > span"));
		Connection con = dbconnection.get();
		System.out.println("\n");
		String query = "INSERT INTO marketplace_cnova_desempenho (data_criacao, desempenho, indicador, limite, sete_dias, trinta_dias) VALUES ";
		
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				WebElement campo = driver.findElement(By.cssSelector("#sla-table>tbody tr:nth-child("+i+") td:nth-child("+j+")>*"));
				arrayCampos[i-1][j-1] = campo.getText();
				
				if (j == 1) {
					query += "(NOW(), '"+desempenho.getText()+"' ,'"+arrayCampos[i-1][j-1]+"',";
				}
				else if (j < 4) {
					query += "'"+arrayCampos[i-1][j-1]+"',";
					System.out.print(arrayCampos[i-1][j-1] +"| ");
				}else {
					query += "'"+arrayCampos[i-1][j-1]+"')";
					System.out.print(arrayCampos[i-1][j-1]);
				}
			}
			
			if (i < 4) {
				query += ",";
			}else {
				query += ";";
			}
			//Script com insert no banco de dados utilizando o variavel com o array arrayCampos[i-1]
		}
		
		try {
			con.createStatement().execute(query);
			con.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		
		}
		
		System.out.println(query);
	}

}

