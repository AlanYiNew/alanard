package alanard.enums;

public enum Errors {
	success(0),error(-1);
	public final int code;
	
	Errors(int code) {
		this.code = code;
	}
}
