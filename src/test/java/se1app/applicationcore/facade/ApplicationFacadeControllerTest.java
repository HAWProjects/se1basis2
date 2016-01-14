package se1app.applicationcore.facade;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.http.HttpStatus;
import se1app.applicationcore.Application;
import se1app.applicationcore.accountcomponent.Account;
import se1app.applicationcore.accountcomponent.AccountComponent;
import se1app.applicationcore.accountcomponent.AccountComponentInterface;
import se1app.applicationcore.accountcomponent.AccountNotCoveredException;
import se1app.applicationcore.accountcomponent.AccountRepository;
import se1app.applicationcore.accountcomponent.Transaction;
import se1app.applicationcore.branchcomponent.Branch;
import se1app.applicationcore.branchcomponent.BranchComponent;
import se1app.applicationcore.branchcomponent.BranchRepository;
import se1app.applicationcore.customercomponent.Customer;
import se1app.applicationcore.customercomponent.CustomerRepository;
import se1app.applicationcore.customercomponent.Reservation;
import se1app.applicationcore.moviecomponent.Movie;
import se1app.applicationcore.util.AccountNrType;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
@ActiveProfiles("test")
public class ApplicationFacadeControllerTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	BranchComponent branchComponent;

	private Customer mickey;
	private Customer minnie;
	private Customer pluto;

	private Account account1;
	private Account account2;
	private Account account3;
	
	@Autowired
	private AccountComponentInterface accountComponent;

	@Before
	public void setUp() throws AccountNotCoveredException {

		customerRepository.deleteAll();
		accountRepository.deleteAll();
		branchRepository.deleteAll();
		

		mickey = new Customer("Mickey Mouse");
		minnie = new Customer("Minnie Mouse");
		pluto = new Customer("Pluto");
		customerRepository.save(Arrays.asList(mickey, minnie, pluto));

		account1 = new Account(new AccountNrType(12345));
		account1.buche(200);
		account2 = new Account(new AccountNrType(12346));
		account3 = new Account(new AccountNrType(12347));
		accountRepository.save(Arrays.asList(account1, account2, account3));
		
		Branch branch = new Branch(1);
		branchRepository.save(branch);
		
		branchComponent = new BranchComponent(branchRepository);
		
		accountComponent = new AccountComponent(accountRepository, branchComponent);

	}

	@Test
	public void canFetchMickey() {
		Integer mickeyId = mickey.getId();

		when().get("/customers/{id}", mickeyId).then().statusCode(HttpStatus.OK.value())
				.body("name", is("Mickey Mouse")).body("id", is(mickeyId));
	}
	


	@Test
	public void testTransactionsAll() throws AccountNotCoveredException {
		
		accountComponent.transfer(account1.getAccountNr(), account2.getAccountNr(), 20);
//		accountRepository.save(account1);
		
		when().get("/transactions").

		then().statusCode(HttpStatus.OK.value()).body("id", hasItem(1));

	}
	

	@Test
	public void canFetchAll() {
		when().get("/customers").then().statusCode(HttpStatus.OK.value()).body("name",
				hasItems("Mickey Mouse", "Minnie Mouse", "Pluto"));
	}

	@Test
	public void canDeletePluto() {
		Integer plutoId = pluto.getId();

		when().delete("/customers/{id}", plutoId).then().statusCode(HttpStatus.NO_CONTENT.value());
	}

	@Test
	public void canSaveDonald() {
		Customer donald = new Customer("Donald Duck");

		given().contentType("application/json").body(donald).expect().statusCode(HttpStatus.CREATED.value()).when()
				.post("/customers");
	}

	@Test
	public void testTransfer() {
		Transaction trasnaction = new Transaction(10);

		given().contentType("application/json").
			body(trasnaction).
		expect().
			statusCode(HttpStatus.CREATED.value()).
		when()
				.post("/transactions");
	}

	@Test
	public void canAddReservation() {
		Integer mickeyId = mickey.getId();

		when().get("/movies/007").then().statusCode(HttpStatus.NOT_FOUND.value());

		Movie movie007 = new Movie("007");
		Reservation movieReservation007 = new Reservation(movie007);
		given().contentType("application/json").body(movieReservation007).expect()
				.statusCode(HttpStatus.CREATED.value()).when().post("/customers/{id}/reservations", mickeyId);

		when().get("/movies/007").then().statusCode(HttpStatus.OK.value()).body(equalTo("1"));
	}
}