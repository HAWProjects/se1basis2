/**
 * 
 */
package se1app.applicationcore.accountcomponent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se1app.applicationcore.util.AccountNrType;


/**
 * @author Robert
 * @date 29.12.2015
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	Account findByAccountNr(AccountNrType accountnr);
	
	
	//TODO: die Query muss noch getestet werden. Ich zweifel noch an der Korrektheit..
	//Liefert eine Liste von Accounts die weniger als den angegebnen Betrag haben
	@Query(value = "SELECT * FROM ACCOUNT WHERE ACCOUNT.ACCOUNTVALUE == VALUE", nativeQuery = true)
	List<Account> findAccountByValue(@Param("VALUE") int value);
}
