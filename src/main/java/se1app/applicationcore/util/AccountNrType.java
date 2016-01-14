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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNr;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		AccountNrType other = (AccountNrType) obj;
		if(accountNr != other.accountNr)
			return false;
		return true;
	}
	
	
	
}
