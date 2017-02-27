package com.bhs.util;

public abstract class HelloWorld{

	
	HelloWorld(){
		
	}
    public static void main(String []args){
        
       int[] arr={1,2,3};
		for (int i : arr) {
		    i=i+2;
			System.out.println(i);
		}
		
		String date="01012016";
		date=date.substring(0, 2)+"-"+date.substring(2, 4)+"-"+date.substring(4);
       System.out.println("date with format  "+date);
    }
    
    public void methodNonStatic(){
    	
    	int inLocal=0;
    	methodStatic1();
    	
    }
    
public static void methodStatic1(){
    	
    	int inLocal=0;
    	//methodNonStatic();
    	
    }

abstract void   testAbstract();
    
}


/*# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class Pythonweb(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.google.co.in/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_pythonweb(self):
        driver = self.driver
        driver.get(self.base_url + "/?gfe_rd=cr&ei=fe3cV8K7HuyK8QfCza-QDw")
        driver.find_element_by_id("lst-ib").clear()
        driver.find_element_by_id("lst-ib").send_keys("selenium off")
        driver.find_element_by_css_selector("b").click()
        driver.find_element_by_link_text("Selenium - Web Browser Automation").click()
        driver.find_element_by_id("close").click()
        driver.find_element_by_link_text("Download").click()
        driver.find_element_by_link_text("Documentation").click()
    
    def is_element_present(self, how, what):
        try: self.driver.find_element(by=how, value=what)
        except NoSuchElementException, e: return False
        return True
    
    def is_alert_present(self):
        try: self.driver.switch_to_alert()
        except NoAlertPresentException, e: return False
        return True
    
    def close_alert_and_get_its_text(self):
        try:
            alert = self.driver.switch_to_alert()
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally: self.accept_next_alert = True
    
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)

if __name__ == "__main__":
    unittest.main()
*/