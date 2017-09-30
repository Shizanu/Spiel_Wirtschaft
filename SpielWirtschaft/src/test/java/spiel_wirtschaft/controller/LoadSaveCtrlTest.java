package spiel_wirtschaft.controller;

import org.junit.Assert;
import org.junit.Test;

import spiel_wirtschaft.model.City;
import spiel_wirtschaft.test.AbstractTestBase;

public class LoadSaveCtrlTest extends AbstractTestBase {

	private LoadSaveCtrl loadSaveCtrl = new LoadSaveCtrl();

	@Test
	public void testSaveLoad() {
		City testCity = new City();
		testCity.setName("AnneTown");
		loadSaveCtrl.save(testCity);
		City cityAfterLoad = loadSaveCtrl.load();

		Assert.assertEquals(testCity.getName(), cityAfterLoad.getName());
	}
}
