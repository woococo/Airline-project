package start;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class two {
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
      
      // 항공사 선택
      driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div/div[2]/div/div[1]/div[1]/a[1]/div")).click();
      try {
         Thread.sleep(3000);
      } catch (InterruptedException e) {
         // TODO Auto-generated
      }
      
      for (int i = 1; i <= 9; i++) {
         String[] scoreOne = new String[9];
         scoreOne[i -1] = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[5]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]/div[" + i + "]/span[2]/span")).getAttribute("class");
         System.out.println(scoreOne[i -1]);
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            // TODO Auto-generated
         }
      }
      
      for (int i = 1; i <= 6; i++) {
         String[] scoreTwo = new String[6];
         scoreTwo[i -1] = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[5]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[1]/ul/li[" + i + "]/span[2]")).getText();
         System.out.println(scoreTwo[i -1]);
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            // TODO Auto-generated
         }
      }
      
      driver.navigate().back();
   }

}