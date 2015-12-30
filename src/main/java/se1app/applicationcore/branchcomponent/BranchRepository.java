/**
 * 
 */
package se1app.applicationcore.branchcomponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Robert
 * @date 30.12.2015
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer>{
	
	Branch findBybranchNr(int nr);
}
