package start;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class one {
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
		for ( int j = 0; j < 63; j++) {
			if (j == 0) {
				continue;
			} else {
				// 뒤로 가기 후 밑의 항공사 선택
				for (int index = 10; index < 12; index++) {

					// 항공사 선택
					String airLine = driver.findElement(By.xpath(
							"/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div/div[" + index + "]/div/div[1]/div[1]/a[1]/div"))
							.getText();
					driver.findElement(By.xpath(
							"/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div/div[" + index + "]/div/div[1]/div[1]/a[1]/div"))
							.click();

					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated
					}

					// 세부항목의 수 파악
					int countOfCheckList = 0;
					numOfCheckList = driver.findElement(By.xpath(
							"/html/body/div[2]/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]"))
							.getText();
					System.out.println(checkList.length);
					for (int i = 0; i < checkList.length; i++) { // 평가 항목의 수를 파악
						if (numOfCheckList.contains(checkList[i])) {
							System.out.println(checkList[i]);
							countOfCheckList++;
						}
					}
					System.out.println(countOfCheckList);
					if (countOfCheckList < 9) {
						inspection.add(airLine);
					}

					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated
					}

					// 세부항목 수집
					for (int i = 1; i <= countOfCheckList; i++) {
						String[] scoreOne = new String[countOfCheckList];
						scoreOne[i - 1] = driver.findElement(By.xpath(
								"/html/body/div[2]/div/div/div[5]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]/div["
										+ i + "]/span[2]/span"))
								.getAttribute("class");
						System.out.println(scoreOne[i - 1]);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated
						}
					}
					
					// 만족도 수집
					for (int i = 1; i <= 5; i++) {
						String[] scoreTwo = new String[5];
						scoreTwo[i - 1] = driver.findElement(By.xpath(
								"/html/body/div[2]/div/div/div[5]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[1]/ul/li["
										+ i + "]/span[2]"))
								.getText();
						System.out.println(scoreTwo[i - 1]);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated
						}
					}
					
					// 전페이지로 이동
					driver.navigate().back();
					
					// 다음으로 이동
					if (index == 11) {
						driver.findElement(
								By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div/div[12]/div/div/span[2]")).click();
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated
							
						}
						index = 2;
						continue;
					}
				}
			}
		}
		
	}
}