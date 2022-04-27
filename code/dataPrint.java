package start;

// ��ȣ, �װ���, ����, �� ����, �����, ������1~5 �Ѥ�> csv

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

public class dataPrint {
	static ArrayList<String> inspection = new ArrayList<String>();
	static ArrayList<String> normal9 = new ArrayList<String>();
	static ArrayList<String> normal8 = new ArrayList<String>();
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void FileWrite() throws IOException {

	}

	public static void main(String[] args) throws IOException {
		Crawling();
		// FileWrite();
	}

	public static void Crawling() throws IOException {
		File file1 = new File("C:\\Users\\501-10\\Desktop\\data\\Airlines\\country_score5_1.csv");
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1, true));

		// �װ��� �̸� ���� �ҷ�����
		BufferedReader read = new BufferedReader(
				new FileReader("C:\\Users\\501-10\\Desktop\\data\\Airlines\\nameList.csv"));
		String lineOne;
		ArrayList<String> nameList = new ArrayList<>();
		while ((lineOne = read.readLine()) != null) {
			nameList.add(lineOne);
		}

		// ���� �̸� ���� �ҷ�����
		BufferedReader reader = new BufferedReader(
				new FileReader("C:\\Users\\501-10\\Desktop\\data\\Airlines\\countryName.csv"));
		String lineTwo;
		ArrayList<String> countryName = new ArrayList<String>(); // ��ü ������ �̸��� ��� ����Ʈ
		while ((lineTwo = reader.readLine()) != null) {
			countryName.add(lineTwo);
		}

		String[] checkList = { "Legroom", "Seat comfort", "In-flight entertainment (WiFi, TV, movies)",
				"Onboard Experience", "Customer service", "Value for money", "Cleanliness", "Check-in and boarding",
				"Food and beverage" };

		ArrayList<String> unknownCountry = new ArrayList<String>();
		String numOfCheckList;
		int cnt = 500;

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

		for (int i = 500; i < nameList.size(); i++) {
			// �˻�â Ŭ��
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/div/div/div/span/input"))
					.click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// �װ��� �Է�
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/div/div/div/span/input"))
					.sendKeys(nameList.get(i-1));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// ������ Ŭ��
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/span/span")).click();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// �װ��� �̸�
			String airlineName = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div/h1"))
					.getText();
			System.out.println(cnt + ". �װ��� �̸� : " + airlineName);
			bw1.write(String.valueOf(cnt));
			bw1.write(",");
			bw1.write(airlineName);
			bw1.write(",");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// ����
			try {
				String country = driver
						.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div/div[1]/span/span"))
						.getText();
				country = country.toLowerCase();
				
				// �� ���� ���� ����Ʈ�� ���������� ���� �ؽ�Ʈ�� ��
				int checkInputed = 1;
				for (int j = 0; j < countryName.size(); j++) {
					if (country.contains(countryName.get(j))) { // ������ ���� �̸� ����
						System.out.println("���� : " + countryName.get(j));
						bw1.write(countryName.get(j));
						bw1.write(",");
						checkInputed = 0;
						break;
					} 
				}
				// checkInputed == 0 �̸� �ش� �װ����� �Ҽ� ������ �����ϰ� 0�� �ƴ϶�� �Ҽӱ����� ���� �װ���� �ν��Ͽ� �̸� ����
				if (checkInputed != 0){
					System.out.println("���� : " + " ");
					bw1.write(" ");
					bw1.write(",");
				}
				
				Thread.sleep(300);
			} catch (Exception e) {
				System.out.println(" ");
				bw1.write(" ");
				bw1.write(",");
				// TODO Auto-generated
			}

			// ������
			try {
				String totalScore = driver
						.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[2]/div/span[1]"))
						.getAttribute("class");
				String[] splitTotalScore = totalScore.split("_"); // ui_bubble_rating bubble_30 ���� 30�� ����
				String onlyScore = splitTotalScore[splitTotalScore.length - 1];
				System.out.println("�� ���� : " + onlyScore);
				bw1.write(onlyScore);
				bw1.write(",");
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated
				System.out.println(" ");
			}

			// �����
			try {
				String reviewsCnt = driver
						.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[2]/div/span[2]")).getText();
				String[] splitReviewsCnt = reviewsCnt.split(" ");
				String onlyReviewsCnt = splitReviewsCnt[0];
				System.out.println("����� : " + onlyReviewsCnt);
				bw1.write(onlyReviewsCnt.replace(",", ""));
				bw1.write(",");
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println(" ");
				// TODO Auto-generated
			}

			// ������ ����
			try {
				for (int l = 1; l <= 5; l++) {
					String[] scoreTwo = new String[5];
					scoreTwo[l - 1] = driver.findElement(By.xpath(
							"/html/body/div[2]/div/div/div[5]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[1]/ul/li["
									+ l + "]/span[2]"))
							.getText();
					System.out.println("������ " + l + " : " + scoreTwo[l - 1]);
					bw1.write(scoreTwo[l - 1].replace(",", ""));
					bw1.write(",");
				}
				Thread.sleep(300);
			} catch (InterruptedException e) {
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