package com.norwegian.pageobjects;

import com.norwegian.basepageplatform.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * @author anna
 */
public class ShipListImpl extends BasePage {
    private static final By SHIPS_LIST = By.xpath("//*[@resource-id='com.ncl.nclcruiseinfo:id/shipNameLabel']");

    public ShipListImpl(AndroidDriver driver) {
        super(driver);
    }

    private List<String> getListOfTitles() {
        List<String> titles = new ArrayList();
        List<WebElement> cards = getElementsByLocator(SHIPS_LIST);
        for (int i = 0; i < cards.size(); i++) {
            titles.add(cards.get(i).getText());
        }
        return titles;
    }

    public List<String> getListOfShipNames() {
        List<String> shipNames = new ArrayList();
        List<String> titles = getListOfTitles();
        for (int i = 0; i < titles.size(); i++) {
            String[] words = titles.get(i).split(" ");
            for (String word : words) {
                if (!word.equals("Norwegian")) {
                    shipNames.add(word);
                }
            }
        }
        return shipNames;
    }

    public boolean areShipNamesSortedAscending() {
        List<String> names = getListOfShipNames();
        for (int i = 0; i < names.size() - 1; i++) {
            if (names.get(i).compareTo(names.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}
