/**
 * 
 */
package se1app.applicationcore.customercomponent;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import se1app.applicationcore.Application;
import se1app.applicationcore.accountcomponent.Account;
import se1app.applicationcore.accountcomponent.AccountComponent;
import se1app.applicationcore.accountcomponent.AccountComponentInterface;
import se1app.applicationcore.accountcomponent.AccountNotCoveredException;
import se1app.applicationcore.accountcomponent.AccountRepository;
import se1app.applicationcore.branchcomponent.Branch;
import se1app.applicationcore.branchcomponent.BranchComponent;
import se1app.applicationcore.branchcomponent.BranchRepository;
import se1app.applicationcore.util.AccountNrType;

/**
 * @author Robert
 * @date 30.12.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class AccountComponentTest {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	private AccountComponentInterface accountComponent;
	
	AccountNrType accNr;
	Account account2;
	
	/**
	 * TODO
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		accNr = new AccountNrType(234675);
		
		Account account1 = new Account(new AccountNrType(234674));
		account2 = new Account(accNr);
		Account account3 = new Account(new AccountNrType(234676));
		
		account2.setAccountValue(50);
		
		accountRepository.save(account1);
		accountRepository.save(account2);
		accountRepository.save(account3);
		
		branchRepository.save(new Branch(1));
		
		accountComponent = new AccountComponent(accountRepository, new BranchComponent(branchRepository));
	}
	
	
	@Test
	public void testIncreaseTransferstatistics() throws AccountNotCoveredException{
		AccountNrType accNrTwo = new AccountNrType(234676);
		
		accountComponent.transfer(accNr, accNrTwo, 25);
		accountComponent.transfer(accNr, accNrTwo, 5);
		accountComponent.transfer(accNr, accNrTwo, 5);
		accountComponent.transfer(accNrTwo,accNr , 5);
		
		Account accTwo = accountRepository.findByAccountNr(accNrTwo);
		
		assertEquals(accTwo.getAccountValue(), 30);
		
		assertEquals(4, branchRepository.findBybranchNr(1).getNumberOfTransfers());
	}
	
}
