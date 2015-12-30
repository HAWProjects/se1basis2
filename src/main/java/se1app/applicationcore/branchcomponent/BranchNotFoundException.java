/**
 * 
 */
package se1app.applicationcore.branchcomponent;


/**
 * @author Robert
 * @date 30.12.2015
 */
public class BranchNotFoundException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8114263804411939026L;
	
	public BranchNotFoundException(int branchNr){
		super("Filiale "+branchNr+ " nicht gefunden");
	}

}
