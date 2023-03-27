package com.w2a.base;
//
//import org.openqa.selenium.By;
//
import com.w2a.pages.crm.accounts.AccountsPage;

public class TopMenu {

	public void gotoLeads() {
		
	}

	public void gotoContacts() {

	}

	public AccountsPage gotoAccounts() {
		//Page.driver.findElement(By.cssSelector("#Visible_Accounts")).click();
		Page page = new Page();
		page.click("AccountsTab_CSS");
		return new AccountsPage();
	}

	public void gotoDeals() {

	}
}
