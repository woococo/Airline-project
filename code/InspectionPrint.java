package start;

// inspection 세부항목 9개를 뽑아보자

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InspectionPrint {
	static ArrayList<String> inspection = new ArrayList<String>();
	static ArrayList<String> normal9 = new ArrayList<String>();
	static ArrayList<String> normal8 = new ArrayList<String>();
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) throws IOException {
		Crawling();
		// FileWrite();
	}

	public static void Crawling() throws IOException {
		File file1 = new File("C:\\Users\\501-10\\Desktop\\data\\Airlines\\inspection9_9.csv");
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1, true));
		
		//inspection 파일 불러오기
		BufferedReader read1 = new BufferedReader(
				new FileReader("C:\\Users\\501-10\\Desktop\\data\\Airlines\\inspectionCnt.csv"));
		String readtxt1;
		ArrayList<String> nameList = new ArrayList<>();
		while ((readtxt1 = read1.readLine()) != null) {
			nameList.add(readtxt1);
		}

		String[] checkList = { "Legroom", "Seat comfort", "In-flight entertainment (WiFi, TV, movies)",
				"Onboard Experience", "Customer service", "Value for money", "Cleanliness", "Check-in and boarding",
				"Food and beverage" };

		ArrayList<String> unknownCountry = new ArrayList<String>();
		String numOfCheckList;
		int cnt = 121;

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

		for (int i = 120; i < 127; i++) {
			// 검색창 클릭
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/div/div/div/span/input"))
					.click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// 항공사 입력
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/div/div/div/span/input"))
					.sendKeys(nameList.get(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// 돋보기 클릭
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/span/span")).click();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// 항공사 이름
			String airlineName = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div/h1"))
					.getText();
			System.out.println(cnt + ". 항공사 이름 : " + airlineName);
			bw1.write(String.valueOf(cnt));
			bw1.write(",");
			bw1.write(airlineName);
			bw1.write(",");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// 세부항목의 수 파악
			int countOfCheckList = 0;
			numOfCheckList = driver.findElement(By.xpath(
					"/html/body/div[2]/div/div/div[5]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[3]"))
					.getText();
			// System.out.println(checkList.length);
			for (int j = 0; j < checkList.length; j++) { // 평가 항목의 수를 파악
				if (numOfCheckList.contains(checkList[j])) {
					// System.out.println(checkList[j]);
					countOfCheckList++;
				}
			}
			if (countOfCheckList < 9) {
				inspection.add(nameList.get(i));
			}

			String score99;
			// 세부항목 수집
			try {
				for (int k = 1; k <= countOfCheckList; k++) {
					score99 = driver.findElement(By.xpath(
							"/html/body/div[2]/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[3]/div[" + k + "]/span[2]/span"))
							.getAttribute("class");
					String[] splitScoreOne = score99.split("_"); // 세부사항 만족도에서 점수만 출력
					String onlyDetailScore = splitScoreOne[splitScoreOne.length - 1];
					System.out.println("세부항목 " + k + " : " + onlyDetailScore);
					bw1.write(onlyDetailScore);
					bw1.write(",");
				}
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println(" ");
				// TODO Auto-generated
			}

			System.out.println("-----------------------------------");

			driver.navigate().back();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			cnt++;
			bw1.newLine();

		}
		bw1.close();

	}
}