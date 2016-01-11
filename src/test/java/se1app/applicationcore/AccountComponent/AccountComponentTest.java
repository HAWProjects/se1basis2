package se1app.applicationcore.AccountComponent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

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
	
	AccountNrType accNrAccount2;
	Account account2;
	Account account1;
	Account account3;
	
	/**
	 * TODO
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		accNrAccount2 = new AccountNrType(234675);
		
		account1 = new Account(new AccountNrType(234674));
		account2 = new Account(accNrAccount2);
		account3 = new Account(new AccountNrType(234676));
		
		account2.setAccountValue(50);
		
		accountRepository.save(account1);
		accountRepository.save(account2);
		accountRepository.save(account3);
		
		branchRepository.save(new Branch(1));
		branchRepository.save(new Branch(2));
		
		accountComponent = new AccountComponent(accountRepository, new BranchComponent(branchRepository));
	}
	
	@Test
	public void testGetAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		assertThat(accounts).hasSize(3);
	}
	
	@Test
	public void testGetSomeAccount() {
		Account acc = accountRepository.findByAccountNr(accNrAccount2);
		assertEquals(acc, account2);
	}
	
	@Test
	public void testDeleteAccount() {
		accountRepository.delete(account2);
		List<Account> accounts = accountRepository.findAll();
		assertThat(accounts).hasSize(2);
	}
	
	@Test(expected = AccountNotCoveredException.class)
	public void testAccountNotCoveredException() throws AccountNotCoveredException {
		AccountNrType accNrTwo = new AccountNrType(234676);
		
		accountComponent.transfer(accNrAccount2, accNrTwo, 125);
	}
	
	@Test
	public void testTransfer() throws AccountNotCoveredException {
		AccountNrType accNrThree = new AccountNrType(234676);
		
		accountComponent.transfer(accNrAccount2, accNrThree, 25);
		
		Account accOne = accountRepository.findByAccountNr(accNrAccount2);
		Account accTwo = accountRepository.findByAccountNr(accNrThree);
		
		assertEquals(accOne.getAccountValue(), accTwo.getAccountValue());
		assertEquals(accTwo.getAccountValue(), 25);
	}
	
	@Test
	public void testIncreaseTransferStats() throws AccountNotCoveredException{
		//account2 hat 50 startKapital
		accountComponent.transfer(account2.getAccountNr(),account1.getAccountNr() , 25);
		accountComponent.transfer(account2.getAccountNr(),account1.getAccountNr() , 5);
		accountComponent.transfer(account2.getAccountNr(),account1.getAccountNr() , 5);
		
		assertEquals(account2.getAccountValue(), 15);
		accountComponent.transfer(account1.getAccountNr(),account2.getAccountNr() , 5);
		assertEquals(account2.getAccountValue(), 20);
		assertEquals(account1.getAccountValue(), 30);
		
		assertEquals(4, account2.getTransactions().size());
		assertEquals(4, account1.getTransactions().size());
		
		accountComponent.transfer(account1.getAccountNr(), account3.getAccountNr(), 12);
		assertEquals(account3.getAccountValue(), 12);
		assertEquals(account1.getAccountValue(), 18);
		
		assertEquals(4, account2.getTransactions().size());
		assertEquals(5, account1.getTransactions().size());
		assertEquals(1, account3.getTransactions().size());
		
		assertEquals(5, branchRepository.findBybranchNr(1).getNumberOfTransfers());
	}
	
}
