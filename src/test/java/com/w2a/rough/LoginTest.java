package com.w2a.rough;

import com.w2a.base.Page;
import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.zohoHomePage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;

public class LoginTest {

	public static void main(String[] args) throws InterruptedException {

		HomePage home = new HomePage();
		LoginPage loginPage = home.gotoSignIn();
		zohoHomePage zohohomePage = loginPage.doLogin("vijayanandkumar7@gmail.com", "^YU&*Ii87uy6");
		zohohomePage.gotoCRM();
		AccountsPage accountsPage= Page.menu.gotoAccounts();
		CreateAccountPage createAccountPage = accountsPage.createAccount();
		createAccountPage.createAccount("Savings");

	}

}
