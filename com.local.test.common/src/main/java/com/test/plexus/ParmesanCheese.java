package com.test.plexus;

public class ParmesanCheese implements Cheese {
	public void slice(int slices) {
		throw new UnsupportedOperationException("No can do");
	}

	public String getAroma() {
		return "strong";
	}
}