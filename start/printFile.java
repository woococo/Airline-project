package start;

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

public class printFile {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	static ArrayList<Integer> counter = new ArrayList<Integer>();
	static ArrayList<String> nameList = new ArrayList<>();
	static ArrayList<String> countryList = new ArrayList<String>();
	static ArrayList<Integer> totalScoreList = new ArrayList<Integer>();
	static ArrayList<Integer> reviewsList = new ArrayList<Integer>();

	static ArrayList<Integer> scoreOneArr = new ArrayList<Integer>();
	static ArrayList<Integer> scoreTwoArr = new ArrayList<Integer>();
	static ArrayList<Integer> scoreThreeArr = new ArrayList<Integer>();
	static ArrayList<Integer> scoreFourArr = new ArrayList<Integer>();
	static ArrayList<Integer> scoreFiveArr = new ArrayList<Integer>();
	static ArrayList<Integer> scoreSixArr = new ArrayList<Integer>();
	static ArrayList<Integer> scoreSevenArr = new ArrayList<Integer>();
	static ArrayList<Integer> scoreEightArr = new ArrayList<Integer>();
	static ArrayList<Integer> scoreNineArr = new ArrayList<Integer>();

	static ArrayList<Integer> excellent = new ArrayList<Integer>();
	static ArrayList<Integer> veryGood = new ArrayList<Integer>();
	static ArrayList<Integer> average = new ArrayList<Integer>();
	static ArrayList<Integer> poor = new ArrayList<Integer>();
	static ArrayList<Integer> terrible = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		Crawling();
		FileWrite();
	}

	public static void FileWrite() throws IOException {
		File file = new File("C:\\Users\\501-10\\Desktop\\data\\Airlines\\two.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write("�װ���" + "," + "����" + "," + "�� ����" + "," + "���� ��" + "," + "�����׸�1" + "," + "�����׸�2" + "," + "�����׸�3" + ","
				+ "�����׸�4" + "," + "�����׸�5" + "," + "�����׸�6" + "," + "�����׸�7" + "," + "�����׸�8" + "," + "�����׸�9" + "," + "������1"
				+ "," + "������2" + "," + "������3" + "," + "������4" + "," + "������5");

		for (int i = 0; i < 622; i++) {
			bw.write((i + 1) + ",");
			bw.write(nameList.get(i) + ",");
			bw.write(countryList.get(i) + ",");
			bw.write(totalScoreList.get(i) + ",");
			bw.write(reviewsList.get(i) + ",");
			
			bw.write(scoreOneArr.get(i) + ",");
			bw.write(scoreTwoArr.get(i) + ",");
			bw.write(scoreThreeArr.get(i) + ",");
			bw.write(scoreFourArr.get(i) + ",");
			bw.write(scoreFiveArr.get(i) + ",");
			bw.write(scoreSixArr.get(i) + ",");
			bw.write(scoreSevenArr.get(i) + ",");
			bw.write(scoreEightArr.get(i) + ",");
			bw.write(scoreNineArr.get(i) + ",");

			bw.write(excellent.get(i) + ",");
			bw.write(veryGood.get(i) + ",");
			bw.write(average.get(i) + ",");
			bw.write(poor.get(i) + ",");
			bw.write(terrible.get(i) + ",");

			bw.newLine();
		}
		bw.close();
	}

	public static void Crawling() throws IOException {
		// �װ��� �̸� ���� �ҷ�����
		BufferedReader read = new BufferedReader(
				new FileReader("C:\\Users\\501-10\\Desktop\\data\\Airlines\\nameList.csv"));
		String lineOne;
		ArrayList<String> nameList = new ArrayList<>();
		while ((lineOne = read.readLine()) != null) {
			String[] cutLine = lineOne.split(",");
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

		// Ȩ�������� �̵�
		driver.get("https://www.tripadvisor.com/Airlines");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated
		}

		for (int i = 0; i < nameList.size(); i++) {
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
					.sendKeys(nameList.get(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// ������ Ŭ��
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/span/span")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// �װ��� �̸�
			String airlineName = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div/h1"))
					.getText();
			System.out.println(cnt + ". �װ��� �̸� : " + airlineName);
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
				for (int j = 0; j < countryName.size(); j++) {
					if (country.contains(countryName.get(j))) { // ������ ���� �̸� ����
						System.out.println("���� : " + countryName.get(j));
						countryList.add(countryName.get(j));
					} else {
						unknownCountry.add(airlineName);
						countryList.add(" ");
					}
				}
				Thread.sleep(300);
			} catch (Exception e) {
				System.out.println(" ");
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
				totalScoreList.add(Integer.parseInt(onlyScore));
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
				reviewsList.add(Integer.parseInt(onlyReviewsCnt.replace(",", "")));
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println(" ");
				// TODO Auto-generated
			}

			// �����׸��� �� �ľ�
			int countOfCheckList = 0;
			try {
				numOfCheckList = driver.findElement(By.xpath(
						"/html/body/div[2]/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]"))
						.getText();
				// System.out.println(checkList.length);
				for (int j = 0; j < checkList.length; j++) { // �� �׸��� ���� �ľ�
					if (numOfCheckList.contains(checkList[j])) {
						// System.out.println(checkList[j]);
						countOfCheckList++;
					}
				}
				// System.out.println(countOfCheckList);
				if (countOfCheckList < 9) {
					inspection.add(nameList.get(i));
				}
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// �����׸� ����
			try {
				for (int k = 1; k <= countOfCheckList; k++) {
					String[] scoreOne = new String[countOfCheckList];
					scoreOne[k - 1] = driver.findElement(By.xpath(
							"/html/body/div[2]/div/div/div[5]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]/div["
									+ k + "]/span[2]/span"))
							.getAttribute("class");
					String[] splitScoreOne = scoreOne[k - 1].split("_"); // ���λ��� ���������� ������ ���
					String onlyDetailScore = splitScoreOne[splitScoreOne.length - 1];
					System.out.println("�����׸� " + k + " : " + onlyDetailScore);
					Thread.sleep(300);
				}
			} catch (InterruptedException e) {
				System.out.println(" ");
				// TODO Auto-generated
			}

			// ������ ����
			String[] scoreTwo = new String[5];
			try {
				for (int l = 1; l <= 5; l++) {
					scoreTwo[l - 1] = driver.findElement(By.xpath(
							"/html/body/div[2]/div/div/div[5]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[1]/ul/li["
									+ l + "]/span[2]"))
							.getText();
					System.out.println("������ " + l + " : " + scoreTwo[l - 1]);
				}
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}
			
			excellent.add(Integer.parseInt(scoreTwo[0].replace(",", "")));
			veryGood.add(Integer.parseInt(scoreTwo[1].replace(",", "")));
			average.add(Integer.parseInt(scoreTwo[2].replace(",", "")));
			poor.add(Integer.parseInt(scoreTwo[3].replace(",", "")));
			terrible.add(Integer.parseInt(scoreTwo[4].replace(",", "")));

			System.out.println("-----------------------------------");

			// ���������� �̵�
			try {
				driver.navigate().back();
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			cnt++;
		}
	}
}