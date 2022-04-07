package start;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class one2 {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) {
		ArrayList<String> inspection = new ArrayList<String>();
		String[] checkList = { "Legroom", "Seat comfort", "In-flight entertainment (WiFi, TV, movies)",
				"Onboard Experience", "Customer service", "Value for money", "Cleanliness", "Check-in and boarding",
				"Food and beverage" };

		String numOfCheckList;

		// TODO Auto-generated method stub
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);

		driver.get("https://www.tripadvisor.com/Airlines");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated
		}

		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div/div[12]/div/div/div/span[5]"))
				.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated

		}

	}
}