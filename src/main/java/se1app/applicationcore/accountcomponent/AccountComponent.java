/**
 * 
 */
package se1app.applicationcore.accountcomponent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se1app.applicationcore.util.AccountNrType;

/**
 * @author Robert
 * @date 29.12.2015
 */
@Component
public class AccountComponent implements AccountComponentInterface {
	
	private AccountRepository accountRepository;
	
	
	@Autowired
	public AccountComponent( AccountRepository accountRepository) {
		this.accountRepository =accountRepository;
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(AccountNrType account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transfer(AccountNrType sourceAccount, AccountNrType targetAccount, int value) {
		// TODO Auto-generated method stub
		
	}

}
