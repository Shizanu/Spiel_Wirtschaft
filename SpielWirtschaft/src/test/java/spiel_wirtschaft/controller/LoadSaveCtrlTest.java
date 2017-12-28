package spiel_wirtschaft.controller;

import org.junit.Assert;
import org.junit.Test;

import spiel_wirtschaft.controller.LoadSaveCtrl;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.test.AbstractTestBase;

public class LoadSaveCtrlTest extends AbstractTestBase {

	private LoadSaveCtrl loadSaveCtrl = new LoadSaveCtrl();

	@Test
	public void testSaveLoad() {
		StadtBE testCity = new StadtBE();
		// testCity.setName("AnneTown");
		loadSaveCtrl.save(testCity);
		StadtBE cityAfterLoad = loadSaveCtrl.load();
		Assert.assertEquals(testCity, cityAfterLoad);

	}
}
