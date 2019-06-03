package unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import controller.Controller;
import integration.Inventory;

public class FailedSearchExceptionTest {
	@Test
	void testInventoryFindItem() {
		Inventory inventory = Inventory.getInventory();
		try{
			inventory.findItem(5);
		}catch(Exception e) {
			fail("correct input, should not throw exception");
		}
		try{
			inventory.findItem(7);
			fail("Should throw FailedSearchException");
		}catch(Exception e) {
			assertEquals(e.getMessage(),"no such itemID exists in the database","errormessage didnt match");
		}
	}
	@Test
	void testControllerGenerateQuantifiedItem() {
		Controller ctrl = Controller.getController();
		ctrl.startNewSale();
		try{
			ctrl.scanItem(4);
		}catch(Exception e) {
			fail("correct input, should not throw exception");			
		}
		try {
			ctrl.scanItem(7);
			fail("Should throw FailedSearchException");
		}catch(Exception e) {
			assertEquals(e.getMessage(),"no such itemID exists in the database","errormessage didnt match");
		}
	}

}
