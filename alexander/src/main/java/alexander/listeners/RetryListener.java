package alexander.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer{
	
	
	
	private int retryCount = 1;
	private int maxRetryCount =3;
	public boolean retry(ITestResult result) {

	if(retryCount < maxRetryCount) { 
	
		System.out.println("Retry Count:  " + result.getName() +":" +retryCount );
		retryCount++; 
		return true; 
		} 
	return false;
	}
	


} 


