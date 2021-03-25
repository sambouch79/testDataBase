package baseDonneeTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DDBTest {
	static WebDriver driver;
	public static void ouvrirPage() {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();


		
		//acceder a la page web
		driver.get("https://www.net-entreprises.fr/inscription-a-net-entreprises");
		String pageName = driver.getTitle();
		System.out.println(pageName);
	}
	
	
	
	
	

	public static void main(String[] args) throws SQLException {
		
		String host="localhost";
		String port= "3306";
		Connection con=DriverManager.getConnection("jdbc:mysql://"+ host +":"+ port +"/Demo", "root", "Samira1979+");
		Statement a=con.createStatement();
		ResultSet rs= a.executeQuery("select * from MaTable ")	;
		
		//ouvrirPage();
		
		while(rs.next()) {
		String siret1=rs.getString("Siret");
		String nom1=rs.getString("nom");
		String prenom1 =rs.getString("prenom");
		String mail1 = rs.getString("Mail");
		String tel1 = rs.getString("telephone");
		ouvrirPage();	
			driver.findElement(By.xpath("//a[@id='cn-accept-cookie']")).click();
			driver.findElement(By.xpath("//input[@id='txtSiret']")).sendKeys(siret1);
			driver.findElement(By.xpath("//input[@id='txtNom']")).sendKeys(nom1);
			driver.findElement(By.xpath("//input[@id='txtPrenom']")).sendKeys(prenom1);
			driver.findElement(By.xpath("//input[@id='txtTelephone']")).sendKeys(tel1);
			// driver.findElement(By.xpath("//input[@id='TelephonePortable']")).sendKeys("");
			driver.findElement(By.xpath("//input[@id='txtMel']")).sendKeys(mail1);
			driver.findElement(By.xpath("//input[@id='txtConfirmationMel']")).sendKeys(mail1);

			driver.findElement(By.xpath("//*[@id='validButtonInscription']")).click();

			System.out.println(driver.findElement(By.xpath("//*[@id=\'coordonneesADForm\']/div[3]/div/div")).getText());

		}
		
	} 

}
