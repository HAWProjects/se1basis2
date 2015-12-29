/**
 * 
 */
package se1app.applicationcore.util;


import java.io.Serializable;

/**
 * @author Robert
 * @date 29.12.2015
 */
public class AccountNrType implements Serializable {
	
	private static final String NUMBER = "[0-9]{5,7}";
	private int accountNr;
	
	public AccountNrType(int accountNr) {
		if(!isValidAccountNr(accountNr)) {
			throw new IllegalArgumentException("Not a valid AccountNumber");
		}
		this.accountNr = accountNr;
	}
	
	private boolean isValidAccountNr(int accountNr) {
		Integer i = new Integer(accountNr);
		return i.toString().matches(NUMBER);
	}
	
	public int getAccountNr(){
		return accountNr;
	}
	
}
