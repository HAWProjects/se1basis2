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
}
