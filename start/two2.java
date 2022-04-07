package start;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class two2 {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);

		driver.get("https://www.tripadvisor.co.kr/Airlines");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated
		}
		
		ArrayList<String> nameList = new ArrayList<>();
		for (int i = 11; i < 12; i++) {
			nameList.add(driver.findElement(By.xpath(
					"/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div/div[" + i + "]/div/div[1]/div[1]/a[1]/div"))
					.getAttribute("data-name"));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}
			if (i == 11) {
				driver.findElement(
						By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div/div[12]/div/div/span[2]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated

				}
				System.out.println(nameList);
				i = 2;
				continue;
			}
		}
	}
}