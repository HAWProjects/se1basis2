/**
 * 
 */
package se1app.applicationcore.accountcomponent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import se1app.applicationcore.util.AccountNrType;

/**
 * @author Robert
 * @date 29.12.2015
 */
@Entity
public class Account {
	
	// Technische ID der Entität für DB/Repository
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> transactions;
	
	private int accountValue; 
	
	private AccountNrType accountNr;
	

	
	/**
	 * leerer Constructor für hypernate
	 * Constructor
	 */
	public Account(){}
	
	/**
	 * Constructor
	 * @param accountNr
	 */
	public Account(AccountNrType accountNr){
		this.accountNr = accountNr;
		accountValue = 0;
		transactions = new ArrayList<>();
	}
	
	
	/**
	 * TODO
	 * @return
	 */
	public AccountNrType getAccountNr(){
		return accountNr;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public int getAccountValue(){
		return accountValue;
	}
	
	/**
	 * TODO
	 * @param newValue
	 */
	public void setAccountValue(int newValue){
		accountValue = newValue;
	}
	
	/**
	 * TODO
	 * @param value
	 */
	public void addToAcountValue(int value){
		accountValue += value;
		transactions.add(new Transaction(value));
	}
	
	/**
	 * TODO
	 * @param value
	 */
	public void subFromAcountValue(int value){
		accountValue -= value;
		transactions.add(new Transaction(-value));
	}
	
	/**
	 * TODO
	 * @return
	 */
	public List<Transaction> getTransactions(){
		return transactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNr == null) ? 0 : accountNr.hashCode());
		result = prime * result + accountValue;
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
		Account other = (Account) obj;
		if(accountNr == null) {
			if(other.accountNr != null)
				return false;
		}
		else if(!accountNr.equals(other.accountNr))
			return false;
		if(accountValue != other.accountValue)
			return false;
		return true;
	}
	
	
}
