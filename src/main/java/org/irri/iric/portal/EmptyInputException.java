package org.irri.iric.portal;

public class EmptyInputException extends Exception {

	String msg;
	
	public EmptyInputException(String msg) {
		this.msg = msg;
	} 
	public String toString() {
		return (msg);
	}
}
