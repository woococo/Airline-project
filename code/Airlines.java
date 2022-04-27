package start;

// 잘 돌아가는 원본

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Airlines {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) throws IOException {
		// 항공사 이름 파일 불러오기
		BufferedReader read = new BufferedReader(
				new FileReader("C:\\Users\\501-10\\Desktop\\data\\Airlines\\nameList.csv"));
		String lineOne;
		ArrayList<String> nameList = new ArrayList<>();
		while ((lineOne = read.readLine()) != null) {
			String[] cutLine = lineOne.split(",");
			nameList.add(lineOne);
		}

		// 국가 이름 파일 불러오기
		BufferedReader reader = new BufferedReader(
				new FileReader("C:\\Users\\501-10\\Desktop\\data\\Airlines\\countryName.csv"));
		String lineTwo;
		ArrayList<String> countryName = new ArrayList<String>(); // 전체 국가의 이름이 담긴 리스트
		while ((lineTwo = reader.readLine()) != null) {
			countryName.add(lineTwo);
		}

		ArrayList<String> inspection = new ArrayList<String>();
		String[] checkList = { "Legroom", "Seat comfort", "In-flight entertainment (WiFi, TV, movies)",
				"Onboard Experience", "Customer service", "Value for money", "Cleanliness", "Check-in and boarding",
				"Food and beverage" };

		ArrayList<String> unknownCountry = new ArrayList<String>();
		String numOfCheckList;
		int cnt = 1;

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

		for (int i = 0; i < nameList.size(); i++) {
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
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// 항공사 이름
			String airlineName = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div/h1"))
					.getText();
			System.out.println(cnt + ". 항공사 이름 : " + airlineName);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// 나라
			try {
				String country = driver
						.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div/div[1]/span/span"))
						.getText();
				country = country.toLowerCase();
				// 전 세계 국가 리스트와 웹페이지의 나라 텍스트를 비교
				for (int j = 0; j < countryName.size(); j++) {
					if (country.contains(countryName.get(j))) { // 있으면 국가 이름 저장
						System.out.println("국가 : " + countryName.get(j));
					} else {
						unknownCountry.add(airlineName);
					}
				}
				Thread.sleep(300);
			} catch (Exception e) {
				System.out.println(" ");
				// TODO Auto-generated
			}

			// 총평점
			try {
				String totalScore = driver
						.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[2]/div/span[1]"))
						.getAttribute("class");
				String[] splitTotalScore = totalScore.split("_"); // ui_bubble_rating bubble_30 에서 30만 저장
				String onlyScore = splitTotalScore[splitTotalScore.length - 1];
				System.out.println("총 평점 : " + onlyScore);
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated
				System.out.println(" ");
			}

			// 리뷰수
			try {
				String reviewsCnt = driver
						.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[2]/div/span[2]")).getText();
				String[] splitReviewsCnt = reviewsCnt.split(" ");
				String onlyReviewsCnt = splitReviewsCnt[0];
				System.out.println("리뷰수 : " + onlyReviewsCnt);
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println(" ");
				// TODO Auto-generated
			}

			// 세부항목의 수 파악
			int countOfCheckList = 0;
			numOfCheckList = driver.findElement(By.xpath(
					"/html/body/div[2]/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]"))
					.getText();
			// System.out.println(checkList.length);
			for (int j = 0; j < checkList.length; j++) { // 평가 항목의 수를 파악
				if (numOfCheckList.contains(checkList[j])) {
					// System.out.println(checkList[j]);
					countOfCheckList++;
				}
			}
			
			// System.out.println(countOfCheckList);
			if (countOfCheckList < 9) {
				inspection.add(nameList.get(i));
			}

			// 세부항목 수집
			try {
				for (int k = 1; k <= countOfCheckList; k++) {
					String[] scoreOne = new String[countOfCheckList];
					scoreOne[k - 1] = driver.findElement(By.xpath(
							"/html/body/div[2]/div/div/div[5]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]/div["
									+ k + "]/span[2]/span"))
							.getAttribute("class");
					String[] splitScoreOne = scoreOne[k - 1].split("_"); // 세부사항 만족도에서 점수만 출력
					String onlyDetailScore = splitScoreOne[splitScoreOne.length - 1];
					System.out.println("세부항목 " + k + " : " + onlyDetailScore);
				}
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println(" ");
				// TODO Auto-generated
			}

			// 만족도 수집
			try {
				for (int l = 1; l <= 5; l++) {
					String[] scoreTwo = new String[5];
					scoreTwo[l - 1] = driver.findElement(By.xpath(
							"/html/body/div[2]/div/div/div[5]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[1]/ul/li["
									+ l + "]/span[2]"))
							.getText();
					System.out.println("만족도 " + l + " : " + scoreTwo[l - 1]);
				}
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			System.out.println("-----------------------------------");

			// 전페이지로 이동
			driver.navigate().back();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			cnt++;
		}
	}
}