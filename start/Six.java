package start;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Six {
   public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
   public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

   public static void main(String[] args) throws IOException {
      FileInputStream input = new FileInputStream("C:\\Users\\501-10\\Desktop\\Airlines\\nameList.csv");
     byte[] content = input.readAllBytes();
      
     input.read(content);
     String allLine = new String(content);
     
     String[] nameList = allLine.split(",");
      
      ArrayList<String> under9CheckListAirLine = new ArrayList<String>();
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
      
      // 나라 이름 파일 불러오기
      ArrayList<String> countryName = new ArrayList<String>();         // 전체 국가의 이름이 담긴 리스트
      ArrayList<String> unknownCountry = new ArrayList<String>();      // 국가 정보가 없는 항공사 저장
      ArrayList<String> airLineCountry = new ArrayList<String>();      // 항공사의 국가를 저장
      
      BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\501-10\\Desktop\\Airlines\\countryName.csv"));
      String line;
      while ((line = reader.readLine()) != null) {
         countryName.add(line);
      }
      
      for (int i = 0; i < nameList.length; i++) {
         String airlineName = null; // 항공사의 이름을 저장해둠 두고 두고 쓸 꺼임 
         
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
               .sendKeys(nameList[i]);
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            // TODO Auto-generated
         }

         // 돋보기 클릭
         driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div/div/div/span/span")).click();
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            // TODO Auto-generated
         }

         // 항공사 이름
         try {
            airlineName = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div/h1"))
                  .getText();
            System.out.println(airlineName);
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            // TODO Auto-generated
         }
         
         // 나라
         try {
            String country = driver
                  .findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div/div[1]/span/span"))
                  .getText();
            country = country.toLowerCase();               // 국가 이름 부분 전부 소문자화
//            System.out.println(country);
            
            // 전 세계 국가 리스트와 웹페이지의 나라 텍스트를 비교
            for (int j = 0; j < countryName.size(); j++) {
               if (country.contains(countryName.get(j))) {      // 있으면 국가 이름 저장
                  airLineCountry.add(countryName.get(j));
               } else {                              // 없으면 없는 항공사의 이름 저장 & 항공사의 국가 리스트에 없다고 저장
                  airLineCountry.add("none");
                  unknownCountry.add(airlineName);
               }
            }
            
            Thread.sleep(1000);
         } catch (Exception e) {
            System.out.println(" ");
            // TODO Auto-generated
         }
         
         // 총평점
         try {
            String totalScore = driver
                  .findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[2]/div/span[1]"))
                  .getAttribute("class");
            
            String[] splitTotalScore = totalScore.split("_");      // ui_bubble_rating bubble_30 에서 30만 저장
            String onlyScore = splitTotalScore[splitTotalScore.length - 1];
            
            System.out.println("총 평점 " + onlyScore);
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            // TODO Auto-generated
         }
         
         // 리뷰수
         try {
            String reviewsCnt = driver
                  .findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[2]/div/span[2]")).getText();
            
            String[] splitReviewCnt = reviewsCnt.split(" ");
            String onlyReviewCnt = splitReviewCnt[0];
            
            Thread.sleep(1000);
            System.out.println("리뷰 수 " + onlyReviewCnt);
         } catch (InterruptedException e) {
            // TODO Auto-generated
         }
         
         // 세부항목의 수 파악
         int countOfCheckList = 0;
         numOfCheckList = driver.findElement(By.xpath(
               "/html/body/div[2]/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[2]"))
               .getText();
         System.out.println(checkList.length);
         for (int j = 0; j < checkList.length; j++) { // 평가 항목의 수를 파악
            if (numOfCheckList.contains(checkList[j])) {
//               System.out.println(checkList[j]);
               countOfCheckList++;
            }
         }
         
//         System.out.println(countOfCheckList);
         if (countOfCheckList < 9) {
            under9CheckListAirLine.add(nameList[i]);
            System.out.println("세부사항 부족 항공사 : " + nameList[i]);
         }

         try {
            Thread.sleep(2000);
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
            
            String[] splitScoreOne = scoreOne[k - 1].split("_");    // 세부사항 만족도에서 점수만 출력
            String onlyDetailScore = splitScoreOne[splitScoreOne.length - 1]; 
            System.out.println(onlyDetailScore);
            
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
         try {
            Thread.sleep(3000);
         } catch (InterruptedException e) {
            // TODO Auto-generated
         }
      }
   }
}