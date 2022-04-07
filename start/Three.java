package start;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Three {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) {

		ArrayList<String> inspection = new ArrayList<String>();
		String[] checkList = { "Legroom", "Seat comfort", "In-flight entertainment (WiFi, TV, movies)",
				"Onboard Experience", "Customer service", "Value for money", "Cleanliness", "Check-in and boarding",
				"Food and beverage" };

		String numOfCheckList;
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		ArrayList<String> nameList = new ArrayList<>();

		driver.get("https://www.tripadvisor.co.kr/Airlines");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated
		}

		for (int i = 0; i < nameList.size(); i++) {
			// 검색창 클릭
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/div/div/div/span/input"))
					.click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// 항공사 입력
			driver.findElement(By.xpath("/html/body/div/div/div/header/div[1]/div[2]/span/input"))
					.sendKeys(nameList.get(i));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// 돋보기 클릭
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/span/span")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			int countOfCheckList = 0;
			numOfCheckList = driver.findElement(By.xpath(
					"/html/body/div[2]/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]"))
					.getText();
			System.out.println(checkList.length);
			for (int j = 0; j < checkList.length; j++) { // 평가 항목의 수를 파악
				if (numOfCheckList.contains(checkList[j])) {
					System.out.println(checkList[j]);
					countOfCheckList++;
				}
			}
			System.out.println(countOfCheckList);
			if (countOfCheckList < 9) {
				inspection.add(nameList.get(i));
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// 세부항목 수집
			for (int k = 1; k <= countOfCheckList; k++) {
				String[] scoreOne = new String[countOfCheckList];
				scoreOne[k - 1] = driver.findElement(By.xpath(
						"/html/body/div[2]/div/div/div[5]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]/div["
								+ k + "]/span[2]/span"))
						.getAttribute("class");
				System.out.println(scoreOne[k - 1]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated
				}
			}

			// 만족도 수집
			for (int l = 1; l <= 5; l++) {
				String[] scoreTwo = new String[5];
				scoreTwo[l - 1] = driver.findElement(By.xpath(
						"/html/body/div[2]/div/div/div[5]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[1]/ul/li["
								+ l + "]/span[2]"))
						.getText();
				System.out.println(scoreTwo[l - 1]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated
				}
			}

			// 전페이지로 이동
			driver.navigate().back();
		}
	}
}