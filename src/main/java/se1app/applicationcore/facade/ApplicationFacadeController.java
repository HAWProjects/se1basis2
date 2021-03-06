package se1app.applicationcore.facade;

import org.hibernate.annotations.Formula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se1app.applicationcore.accountcomponent.AccountComponentInterface;
import se1app.applicationcore.accountcomponent.AccountNotCoveredException;
import se1app.applicationcore.branchcomponent.Branch;
import se1app.applicationcore.branchcomponent.BranchComponentInterface;
import se1app.applicationcore.branchcomponent.BranchNotFoundException;
import se1app.applicationcore.customercomponent.Customer;
import se1app.applicationcore.customercomponent.CustomerComponentInterface;
import se1app.applicationcore.customercomponent.CustomerNotFoundException;
import se1app.applicationcore.customercomponent.Reservation;
import se1app.applicationcore.moviecomponent.MovieComponentInterface;
import se1app.applicationcore.moviecomponent.MovieNotFoundException;
import se1app.applicationcore.util.AccountNrType;

import java.util.List;

@RestController
class ApplicationFacadeController {

    @Autowired
    private CustomerComponentInterface customerComponentInterface;

    @Autowired
    private MovieComponentInterface movieComponentInterface;
    
    @Autowired
    private AccountComponentInterface accountComponent;
    
    @Autowired
    private BranchComponentInterface branchComponent;

    @RequestMapping("/customers")
    public List<Customer> getAllCustomers()
    {
        return customerComponentInterface.getAllCustomers();
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable("id") Integer id) {
        return customerComponentInterface.getCustomer(id);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") Integer id) {
        customerComponentInterface.deleteCustomer(id);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        customerComponentInterface.addCustomer(customer);
        return customer;
    }

    @RequestMapping(value = "/customers/{id}/reservations", method = RequestMethod.POST)
    public ResponseEntity<?> addReservation(@PathVariable("id") Integer customerId, @RequestBody Reservation reservation) {
        try {
            customerComponentInterface.addReservation(customerId, reservation);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(CustomerNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/movies/{title}", method = RequestMethod.GET)
    public ResponseEntity<?> getNumberOfReservations(@PathVariable("title") String title) {
        try {
            int numberOfReservations = movieComponentInterface.getNumberOfReservations(title);
            return new ResponseEntity<Integer>(numberOfReservations, HttpStatus.OK);
        }
        catch(MovieNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    //________________ Praktikum 4 ___________________
    
    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> doTransaction(@PathVariable("value") int value) throws AccountNotCoveredException, BranchNotFoundException {
    	accountComponent.transfer(new AccountNrType(12484), new AccountNrType(12485), value);
    	int bNr = 1; // die branchnr normalerweise aus der AccounttypNr ableiten
    	int count = branchComponent.getTransactionCountOfBranch(bNr);
    	return new ResponseEntity<>(count, HttpStatus.OK);
    }
    
    
//    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<?> doTransactionF(@RequestBody("value") int value) throws AccountNotCoveredException, BranchNotFoundException {
//    	accountComponent.transfer(new AccountNrType(12484), new AccountNrType(12485), value);
//    	int bNr = 1; // die branchnr normalerweise aus der AccounttypNr ableiten
//    	int count = branchComponent.getTransactionCountOfBranch(bNr);
//    	return new ResponseEntity<>(count, HttpStatus.OK);
//    }
 
    
    
    @RequestMapping(value = "/transactions/{branchNr}", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactionCount(@PathVariable("branchNr") String branchNr) throws BranchNotFoundException{
    	int bNr = Integer.parseInt(branchNr);
    	int count = branchComponent.getTransactionCountOfBranch(bNr);
    	return new ResponseEntity<>(count, HttpStatus.OK);
    }
}