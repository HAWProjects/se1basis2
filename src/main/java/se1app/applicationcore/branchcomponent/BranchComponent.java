/**
 * 
 */
package se1app.applicationcore.branchcomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robert
 * @date 30.12.2015
 */
@Component
public class BranchComponent implements BranchComponentInterface{

	private BranchRepository branchRepository;
	
	@Autowired
	public BranchComponent(BranchRepository branchRepository){
		this.branchRepository = branchRepository;
	}
	
	@Override
	public void increaseTransferStatistics(int branchNr) throws BranchNotFoundException {
		Branch branch = branchRepository.findBybranchNr(branchNr);
		if(branch == null){
			throw new BranchNotFoundException(branchNr);
		}
		branch.increaseNumberOfTransfers();
		branchRepository.save(branch);
	}

	@Override
	public int getTransactionCountOfBranch(int branchNr) throws BranchNotFoundException {
		Branch branch = branchRepository.findBybranchNr(branchNr);
		if(branch == null){
			throw new BranchNotFoundException(branchNr);
		}
		return branch.getNumberOfTransfers();
	}
	
	

}
