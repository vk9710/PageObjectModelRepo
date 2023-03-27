package com.w2a.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.base.Page;

public class LoginPage extends Page {

	public zohoHomePage doLogin(String userName, String password) throws InterruptedException {

		type("LoginId_CSS", userName);
		click("nextBtn_XPATH");
		type("Password_XPATH", password);
		click("SignIn1Btn_XPATH");
		return new zohoHomePage();
	}
}
