package start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Four {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) throws IOException{
		BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\501-10\\Desktop\\Airlines\\nameList.csv"));
		String line;
		ArrayList<String> nameList = new ArrayList<>();
		while ((line = read.readLine()) != null) {
			String[] cutLine = line.split(",");
			nameList.add(line);
			}
		
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
			String airlineName = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div/h1")).getText();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}
			System.out.println(airlineName);
			
			// ���� 
			try {
				String country = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div/div[1]/span/span")).getText();
				System.out.println(country);
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(" ");
				// TODO Auto-generated
			}
			
			// �����׸��� �� �ľ�
			int countOfCheckList = 0;
			numOfCheckList = driver.findElement(By.xpath(
					"/html/body/div[2]/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]"))
					.getText();
			System.out.println(checkList.length);
			for (int j = 0; j < checkList.length; j++) { // �� �׸��� ���� �ľ�
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
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}

			// �����׸� ����
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

			// ������ ����
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

			// ���������� �̵�
			driver.navigate().back();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated
			}
		}
	}
}