import java.util.List; 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver; 
 

public class testSlashdot{
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		  
		driver = new FirefoxDriver();
		baseUrl = "https://slashdot.org/";
		driver.manage().window().maximize(); 
		driver.get(baseUrl); // Browse to http://slashdot.org/
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();

	}

	@Test
	public void testArticles() throws Exception {
		//driver.get(baseUrl); // Browse to http://slashdot.org/
		
		List<WebElement> articles = driver.findElements(By.className("story-title"));//locate articles
		
		int numberofArticles = articles.size();
		System.out.println("the number of Articles is  "+numberofArticles);// Print how many articles are on the page
		 
		System.out.println();	 
		
	 	
		//Print a list of unique (different) icons used on article titles and how many times was it used 
		List<WebElement> articlesTitleIcons = driver.findElements(By.cssSelector("img[width='64'][height='64']"));//locate icons used on article titles
		List<WebElement> usedTimes = driver.findElements(By.className("comment-bubble"));//how many times was it used 

		// Iterate through the list of icons used on article titles and print	 
		for(int i=0;i<articlesTitleIcons.size();i++){
			System.out.print("Articles Title:" + articlesTitleIcons.get(i).getAttribute("title")+"  ");
			System.out.println("Used Times:" + usedTimes.get(i).getText()+"  ");
		}	 
		}
	 
	 
	@Test
	public void testCommentsNumber() throws Exception {
		
		List<WebElement> numbers = driver.findElements(By.cssSelector("span[style='background: #333; color:#fff; font-weight:bold; font-size:.85em']"));
		 
		int totalNumber=0;
		for(int i=0; i<numbers.size();i++){
			String numberText=numbers.get(i).getText();
			System.out.println(numberText); 
			int number= Integer.parseInt(numberText);
			
			  totalNumber+=number;			 
		}
		
		System.out.println(totalNumber);
		
		
		
	} 	
	
		@Test
		public void testVotes() throws Exception {
		    //navigate to https://slashdot.org/polls
	 		driver.findElement(By.linkText("Polls")).click();
	 		
	 		//Vote for some random option on the daily poll 
	  
	 		List<WebElement> Radios = driver.findElements(By.cssSelector("input[type='radio']"));
	 		int voteNumber= Radios.size();
	 		int I=(int)(Math.random()*voteNumber+1);
	 				 
 	 		String ItemTitle= Radios.get(I).getText();	 
 	 		System.out.println(ItemTitle.isEmpty());
 	 		System.out.println("vote:" +I);
 	 		 
 	 		 
	 		Radios.get(I).click();	 		
	 		
	 		WebElement voteButton=driver.findElement(By.cssSelector("button[class='btn-polls'][type='submit']"));
	 		voteButton.click();	 		
	 		 
	 		List<WebElement> pollBars = driver.findElements(By.className("poll-bar-label"));
	 		List<WebElement> pollTexts = driver.findElements(By.className("poll-bar-text"));
	 		 
	 	   // return the number of people that have voted for that same option
	 		
////	 		for(WebElement pollBar :pollBars){
//	 		     String pollBarText=pollBars.get(I).getText();
////	 			if(ItemTitle.equals(pollBarText)break;
////	 		}
	 		 
	 		System.out.println("the number of people vote " +I+" "+pollBars.get(I).getText()+pollTexts.get(I).getText()); 				
	 	}
		
		
}