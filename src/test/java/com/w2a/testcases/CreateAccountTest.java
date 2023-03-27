package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.zohoHomePage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;
import com.w2a.utilities.DataUtil;

public class CreateAccountTest {

	@Test(dataProviderClass=DataUtil.class, dataProvider="dp") //dependsOnMethods="LoginTest.loginTest"
	public void createAccountTest(Hashtable<String, String> data) {
		zohoHomePage zohohomePage = new zohoHomePage();
		zohohomePage.gotoCRM();
		AccountsPage accountsPage = Page.menu.gotoAccounts();
		CreateAccountPage createAccountPage = accountsPage.createAccount();
		createAccountPage.createAccount(data.get("accountName"));
	}
	
}
