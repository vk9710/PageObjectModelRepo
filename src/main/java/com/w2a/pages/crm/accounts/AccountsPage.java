package com.w2a.pages.crm.accounts;

import org.openqa.selenium.By;

import com.w2a.base.Page;

public class AccountsPage extends Page {

	public CreateAccountPage createAccount() {
		click("CreateAccountBtn_CSS");
		return new CreateAccountPage();
	}

	public void importAccount() {

	}

}
