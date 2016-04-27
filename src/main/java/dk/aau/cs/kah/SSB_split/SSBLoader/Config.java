package dk.aau.cs.kah.SSB_split.SSBLoader;

public class Config {
	private static int linesFromLineorder = 10000;
	private static String lineorder = "../../ssb-dbgen/lineorder.tbl";
	private static String customer = "../../ssb-dbgen/customer.tbl";
	private static String part = "../../ssb-dbgen/part.tbl";
	private static String supplier = "../../ssb-dbgen/supplier.tbl";
	private static String date = "../../ssb-dbgen/date.tbl";
	private static String output = "";
	
	public static String getLineorder() {
		return lineorder;
	}

	public static String getCustomer() {
		return customer;
	}

	public static String getPart() {
		return part;
	}

	public static String getSupplier() {
		return supplier;
	}

	public static String getDate() {
		return date;
	}

	public static void setLineorder(String lineorder) {
		Config.lineorder = lineorder;
	}

	public static void setCustomer(String customer) {
		Config.customer = customer;
	}

	public static void setPart(String part) {
		Config.part = part;
	}

	public static void setSupplier(String supplier) {
		Config.supplier = supplier;
	}

	public static void setDate(String date) {
		Config.date = date;
	}

	public static int getLinesFromLineorder() {
		return linesFromLineorder;
	}

	public static void setLinesFromLineorder(int linesFromLineorder) {
		Config.linesFromLineorder = linesFromLineorder;
	}

	public static String getOutput() {
		return output;
	}

	public static void setOutput(String output) {
		Config.output = output;
	}
	
	
}
