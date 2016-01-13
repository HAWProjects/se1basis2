package se1app.applicationcore.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import se1app.applicationcore.accountcomponent.AccountComponentInterface;
import se1app.applicationcore.accountcomponent.AccountNotCoveredException;
import se1app.applicationcore.accountcomponent.Transaction;
import se1app.applicationcore.customercomponent.Customer;
import se1app.applicationcore.customercomponent.CustomerComponentInterface;
import se1app.applicationcore.customercomponent.CustomerNotFoundException;
import se1app.applicationcore.customercomponent.Reservation;
import se1app.applicationcore.moviecomponent.MovieComponentInterface;
import se1app.applicationcore.moviecomponent.MovieNotFoundException;
import se1app.applicationcore.util.AccountNrType;

@RestController
class ApplicationFacadeController {

    @Autowired
    private CustomerComponentInterface customerComponentInterface;

    @Autowired
    private MovieComponentInterface movieComponentInterface;
    
    @Autowired
    private AccountComponentInterface accountComponent;
    

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
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction addTransaction(@RequestBody Transaction value) throws AccountNotCoveredException {
    	AccountNrType accountNr = new AccountNrType(12345);
    	accountComponent.transfer(accountNr, new AccountNrType(12346), value.getValue());
    	
    	return accountComponent.getTransactions(accountNr).get(accountComponent.getTransactions(accountNr).size()-1);
    }
    
//    @RequestMapping(value = "/transactions/{accountNr}", method = RequestMethod.GET)
//    public List<Transaction> getTransactionsOfAccount(@PathVariable("accountNr") Integer accountNr){
//    	return accountComponent.getTransactions(new AccountNrType(accountNr));
//    }
//    
//    @RequestMapping("/transactions")
//    public List<Transaction> getAllTransactions(){
//    	return accountComponent.getTransactions(new AccountNrType(12345));
//    }
    
    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public List<Transaction> getAllTransactions(){
    	return accountComponent.getTransactions(new AccountNrType(12345));
    }
    
}