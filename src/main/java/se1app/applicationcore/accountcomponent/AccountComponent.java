/**
 * 
 */
package se1app.applicationcore.accountcomponent;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se1app.applicationcore.branchcomponent.BranchComponentInterface;
import se1app.applicationcore.branchcomponent.BranchNotFoundException;
import se1app.applicationcore.util.AccountNrType;

/**
 * @author Robert
 * @date 29.12.2015
 */
@Component
public class AccountComponent implements AccountComponentInterface {
	
	private AccountRepository accountRepository;
	private BranchComponentInterface branchComponent;
	
	@Autowired
	public AccountComponent(AccountRepository accountRepository , BranchComponentInterface branchComponent) {
		this.accountRepository = accountRepository;
		this.branchComponent = branchComponent;
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Account getAccount(AccountNrType account) {
		return accountRepository.findByAccountNr(account);
	}
	
	@Override
	public List<Transaction> getTransactions(AccountNrType account) {
		return accountRepository.findByAccountNr(account).getTransactions();
	}

	@Override
	public void transfer(AccountNrType sourceAccount, AccountNrType targetAccount, int value) throws AccountNotCoveredException {
		Account sourceAcc = accountRepository.findByAccountNr(sourceAccount);
		Account targetAcc = accountRepository.findByAccountNr(targetAccount);
//		if(sourceAcc.getAccountValue() < value){
//			throw new AccountNotCoveredException(sourceAccount);
//		}
//		sourceAcc.buche(-value);
//		targetAcc.buche(value);
		
//		accountRepository.save(sourceAcc);
//		accountRepository.save(targetAcc);

		//TODO:transaktion hochzählen
//		try {
//			int branchNr = 1; // normalerweise aus Account ableiten
//			branchComponent.increaseTransferStatistics(branchNr);
//		} catch(BranchNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void deleteAccount(AccountNrType accountNr) {
		accountRepository.delete(accountRepository.findByAccountNr(accountNr));
	}
	
	public BranchComponentInterface getBranchComponent(){
		return branchComponent;
	}
	
	public List<Transaction> getAllTransactions(){
		List<Transaction> result = new LinkedList<>();
		for(Account allAccou :accountRepository.findAll()){
			result.addAll(allAccou.getTransactions());
		}
		return result;
	}



}
