/**
 * 
 */
package se1app.applicationcore.accountcomponent;

import se1app.applicationcore.util.AccountNrType;

/**
 * @author Robert
 * @date 29.12.2015
 */
public class AccountNotCoveredException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1721177120867932816L;
	
	
	public AccountNotCoveredException(AccountNrType account){
		super("Account: "+account+ " has not enough Money!");
	}

}
