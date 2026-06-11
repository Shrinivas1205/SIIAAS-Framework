package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "SignupData")
	public Object[][] getSignupData() {
	    Object[][] data = ExcelUtil.getTestData("SignupData");
	    System.out.println("[DataProviders] Total rows loaded: " + data.length);
	    return data;
	}
}