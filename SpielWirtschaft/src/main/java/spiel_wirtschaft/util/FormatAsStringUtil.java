package spiel_wirtschaft.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Utility class to format stuff as String to display in the UI.
 *
 */
public class FormatAsStringUtil {

	private static final DecimalFormat DECIMAL_FORMAT_2_DECIMALS = new DecimalFormat("#0.##");

	private FormatAsStringUtil() {
		super();
		// util class not instantiable!
	}

	public static String formatWith2DecimalPlaces(BigDecimal bigDecimal) {
		return DECIMAL_FORMAT_2_DECIMALS.format(bigDecimal);
	}
}
