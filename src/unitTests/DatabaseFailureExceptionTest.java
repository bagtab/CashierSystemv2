package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.Controller;
import integration.Inventory;

class DatabaseFailureExceptionTest {
	@Test
	void testInventoryFindItem() {
		Inventory inventory = Inventory.getInventory();
		try{
			inventory.findItem(5);
		}catch(Exception e) {
			fail("correct input, should not throw exception");
		}
		try{
			inventory.findItem(6);
			fail("Should throw DatabaseFailureException");
		}catch(Exception e) {
			assertEquals(e.getMessage(),"database failure in inventory","errormessage didnt match");
		}
	}
	@Test
	void testControllerGenerateQuantifiedItem() {
		Controller ctrl = Controller.getController();
		try{
			ctrl.startNewSale();
			ctrl.scanItem(4);
		}catch(Exception e) {
			fail("correct input, should not throw exception");			
		}
		try {
			ctrl.scanItem(6);
		}catch(Exception e) {
			assertEquals(e.getMessage(),"database failure in inventory","errormessage didnt match");
		}
	}

}
