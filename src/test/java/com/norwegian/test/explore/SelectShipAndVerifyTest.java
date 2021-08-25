package com.norwegian.test.explore;

import com.norwegian.basetestplatform.BaseTest;

import com.norwegian.pageobjects.ShipDetailsImpl;
import com.norwegian.pageobjects.ShipListImpl;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * @author anna
 */
public class SelectShipAndVerifyTest extends BaseTest {

    private ShipListImpl ships;
    private ShipDetailsImpl shipFacts;


    @BeforeClass
    public void setUp() {
        ships = new ShipListImpl((AndroidDriver) driver);
        shipFacts = new ShipDetailsImpl((AndroidDriver) driver);

    }

    @Test
    public void selectShipAndDetailsTest() {
        assertTrue(ships.areShipNamesSortedAscending());
    }

    @AfterMethod
    public void afterMethodSetUp() {
        System.out.println("Some tear down func after called method goes here...");
    }
}