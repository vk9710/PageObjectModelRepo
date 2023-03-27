package com.w2a.pages;

import org.openqa.selenium.By;

import com.w2a.base.Page;

public class HomePage extends Page{
	
	public void goToFreeSignUp() throws InterruptedException {
		click("FreeSignUp_XPATH");
	}
	
	public LoginPage gotoSignIn() {
		click("SignInBtn_CSS");
		return new LoginPage();
		
	}
	
	public void goToSupport() {
		
	}
	
	public void zohoHome() {
		
	}
}
