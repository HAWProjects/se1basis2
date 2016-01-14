/**
 * 
 */
package se1app.applicationcore.accountcomponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se1app.applicationcore.util.AccountNrType;


/**
 * @author Robert
 * @date 29.12.2015
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	Account findByAccountNr(AccountNrType accountnr);
	
}
