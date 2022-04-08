package start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class two {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) throws IOException {
		// �װ��� �̸� ���� �ҷ�����
		BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\501-10\\Desktop\\Airlines\\nameList.csv"));
		String lineOne;
		ArrayList<String> nameList = new ArrayList<>();
		while ((lineOne = read.readLine()) != null) {
			String[] cutLine = lineOne.split(",");
			nameList.add(lineOne);
		}

		// ���� �̸� ���� �ҷ�����
		BufferedReader reader = new BufferedReader(
				new FileReader("C:\\Users\\501-10\\Desktop\\Airlines\\countryName.csv"));
		String lineTwo;
		ArrayList<String> countryName = new ArrayList<String>(); // ��ü ������ �̸��� ��� ����Ʈ
		while ((lineTwo = reader.readLine()) != null) {
			countryName.add(lineTwo);
		}
		
		ArrayList<String> unknownCountry = new ArrayList<String>();
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
			// �˻�â Ŭ��
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/div/div/div/span/input"))
					.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// �װ��� �Է�
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/div/div/div/span/input"))
					.sendKeys(nameList.get(i));
			try {
				Thread.sleep(2000);
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
			String airlineName = driver
					.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div/h1")).getText();
			System.out.println(cnt + ". �װ��� �̸� : " + airlineName);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// �����׸� ����
			String scoreOneName = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[5]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]")).getText();
			System.out.println(scoreOneName);
			String scoreOneScore = driver.findElement(By.xpath(
					"/html/body/div[2]/div/div/div[5]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]"))
					.getAttribute("class");
			System.out.println(scoreOneName + scoreOneScore);

			System.out.println("-----------------------------------");

			// ���������� �̵�
			driver.navigate().back();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			cnt++;
		}
	}
}