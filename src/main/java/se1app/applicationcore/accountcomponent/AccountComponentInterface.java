/**
 * 
 */
package se1app.applicationcore.accountcomponent;

import java.util.List;

import se1app.applicationcore.util.AccountNrType;

/**
 * @author Robert
 * @date 29.12.2015
 */
public interface AccountComponentInterface {
	
	
	/**
	 * Liefert eine Liste aller Accounts
	 * @return Liste aller Accounts
	 */
	List<Account> getAllAccounts();
	
	/**
	 * liefert einen bestimmten Account
	 * @param account
	 * @return
	 */
	Account getAccount(AccountNrType account);
	
	
	/**
	 * loescht einen Account
	 * @param accountNr die AccountNummer des zu loeschenden Accounts
	 */
	void deleteAccount(AccountNrType accountNr);
	
	/**
	 * ueberweist einen Betrag von einem Konto auf ein anderes
	 * @param sourceAccount das Konto von dem abgebucht wird
	 * @param targetAccount das Konto auf das ueberwiesen wird
	 * @param value der Betrag (darf nicht negativ sein)
	 * @throws AccountNotCoveredException 
	 */
	void transfer(AccountNrType sourceAccount,AccountNrType targetAccount, int value) throws AccountNotCoveredException;

}
