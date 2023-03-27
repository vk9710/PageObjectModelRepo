package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.zohoHomePage;
import com.w2a.utilities.DataUtil;

public class LoginTest extends BaseTest{

	@Test(dataProviderClass=DataUtil.class, dataProvider = "dp")
	public void loginTest(Hashtable<String, String> data) throws InterruptedException {
		HomePage home = new HomePage();
		LoginPage loginPage = home.gotoSignIn();
		loginPage.doLogin(data.get("username"), data.get("password"));
	}
	
}
