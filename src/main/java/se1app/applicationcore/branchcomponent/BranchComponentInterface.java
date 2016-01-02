/**
 * 
 */
package se1app.applicationcore.branchcomponent;


/**
 * @author Robert
 * @date 30.12.2015
 */
public interface BranchComponentInterface {
	
	/**
	 * erhoeht den Counter fuer die in der Filiale getaetigten ueberweisungen
	 * @throws BranchNotFoundException 
	 */
	void increaseTransferStatistics(int branchNr) throws BranchNotFoundException;
	
	
	/**
	 * liefert die Anzahl der Transactions fuer die Filiale zurueck
	 * @param branchNr die Nr der Filiale
	 * @return Anzahl der Transactions
	 * @throws BranchNotFoundException
	 */
	int getTransactionCountOfBranch(int branchNr) throws BranchNotFoundException;
}
