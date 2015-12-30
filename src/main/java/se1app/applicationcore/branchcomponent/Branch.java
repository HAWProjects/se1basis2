/**
 * 
 */
package se1app.applicationcore.branchcomponent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Robert
 * @date 30.12.2015
 */
@Entity
public class Branch {

    @Id
    @GeneratedValue
    private Integer id;
    
    private int branchNr;
    
    private int numberOfTransfers;
    
    
    public Branch(){}
    
    /**
     * Constructor
     * @param branchNr
     */
    public Branch(int branchNr){
    	this.branchNr = branchNr;
    	this.numberOfTransfers = 0;
    }
    
    /**
     * TODO
     * @return
     */
    public int getBranchNr(){
    	return branchNr;
    }
    
    /**
     * TODO
     * @return
     */
    public int getNumberOfTransfers(){
    	return numberOfTransfers;
    }
    
    /**
     * TODO
     */
    public void increaseNumberOfTransfers(){
    	numberOfTransfers++;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + branchNr;
		result = prime * result + numberOfTransfers;
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
		Branch other = (Branch) obj;
		if(branchNr != other.branchNr)
			return false;
		if(numberOfTransfers != other.numberOfTransfers)
			return false;
		return true;
	}
    
    
}
