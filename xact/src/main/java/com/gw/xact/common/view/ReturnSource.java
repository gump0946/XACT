package com.gw.xact.common.view;

public enum ReturnSource {
	NONE(-1), SUCCESS(0), SELF(3), BOEING(5), UNKOWN(9);

	private int value;

	private ReturnSource(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}
}
