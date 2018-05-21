package spiel_wirtschaft.controller;

import org.junit.Assert;
import org.junit.Test;

import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.test.AbstractTestBase;
import spiel_wirtschaft.test.TestObjectFactory;

public class LoadSaveCtrlTest extends AbstractTestBase {

	private LoadSaveCtrl loadSaveCtrl = new LoadSaveCtrl();

	@Test
	public void testSaveLoad() {
		SpielBE testSpiel = TestObjectFactory.createBasicSpiel();
		loadSaveCtrl.save(testSpiel);
		SpielBE geladenesSpiel = loadSaveCtrl.load();
		Assert.assertEquals(testSpiel, geladenesSpiel);
	}
}
