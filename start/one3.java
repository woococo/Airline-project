package start;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class one3 {
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
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated
		}

		// 원본 창 설정, 윈도우 창 하나로 고정
		String originalWindow = driver.getWindowHandle();
		assert driver.getWindowHandles().size() == 1;

		// 리뷰 칸에 마우스 우클릭 후 새탭으로 열기
		WebElement review = driver
				.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div/div[2]/div/div[2]/a[2]"));
		Actions actionProvider = new Actions(driver);
		// Perform context-click action on the element
		actionProvider.contextClick(review).build().perform();
		review.sendKeys(Keys.CONTROL + "\n");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated
		}
		
		// 새 탭으로 이동
		driver.switchTo().newWindow(WindowType.TAB);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated
		}

		review.sendKeys(Keys.CONTROL + "w");
		// 새 탭 닫기
		
		
		
		
		// 원본 창을 전환
		driver.switchTo().window(originalWindow);
	}
}