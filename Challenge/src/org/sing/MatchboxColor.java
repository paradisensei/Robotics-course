package org.sing;

public enum MatchboxColor {
	
	NO(0),
	BLACK(1),
	BLUE(2),
	GREEN(3),
	YELLOW(4),
	RED(5),
	WHITE(6),
	BROWN(7);
	
	private int code;
	
	private MatchboxColor(int code) {
		this.code = code;
	}
	
}
