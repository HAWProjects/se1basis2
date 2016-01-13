package se1app.applicationcore.accountcomponent;


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Proxy(lazy=false)
public class Transaction {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
//	@Basic(fetch=FetchType.LAZY)
	private Account account;
	
	private Integer value;
	
	public Transaction() {
	}
	
	public Transaction(Integer value) {
		this.value = value;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Transaction other = (Transaction) obj;
		if(account == null) {
			if(other.account != null)
				return false;
		}
		else if(!account.equals(other.account))
			return false;
		if(value == null) {
			if(other.value != null)
				return false;
		}
		else if(!value.equals(other.value))
			return false;
		return true;
	}
	
}
