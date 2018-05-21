package spiel_wirtschaft.controller;

import org.junit.Assert;
import org.junit.Test;

import spiel_wirtschaft.model.GebaeudeEnum;
import spiel_wirtschaft.model.KartenKoordinatenBE;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.test.AbstractTestBase;
import spiel_wirtschaft.test.TestObjectFactory;

public class LoadSaveCtrlTest extends AbstractTestBase {

	private LoadSaveCtrl loadSaveCtrl = new LoadSaveCtrl();

	@Test
	public void testSaveLoad() {
		SpielBE testSpiel = TestObjectFactory.createBasicSpiel();
		KartenKoordinatenBE kartenkoordinaten = new KartenKoordinatenBE();
		kartenkoordinaten.init(1, 1);
		StadtBE neueStadt = new StadtBE(10, "Neustadt", kartenkoordinaten);
		neueStadt.addGebaeude(GebaeudeEnum.MARKTPLATZ);
		testSpiel.getSpielkarte().getStaedte().add(neueStadt);
		loadSaveCtrl.save(testSpiel);
		SpielBE geladenesSpiel = loadSaveCtrl.load();
		Assert.assertEquals(testSpiel, geladenesSpiel);
	}
}
