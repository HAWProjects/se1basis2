package se1app.applicationcore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import se1app.applicationcore.accountcomponent.Account;
import se1app.applicationcore.accountcomponent.AccountRepository;
import se1app.applicationcore.branchcomponent.Branch;
import se1app.applicationcore.branchcomponent.BranchRepository;
import se1app.applicationcore.customercomponent.Customer;
import se1app.applicationcore.customercomponent.CustomerRepository;
import se1app.applicationcore.customercomponent.Reservation;
import se1app.applicationcore.moviecomponent.Movie;
import se1app.applicationcore.util.AccountNrType;

import java.util.Arrays;

@SpringBootApplication // das Folgende in dieser Zeile hinzufügen, um die Authentifizierung zu deaktivieren: (exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class Application {

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository , AccountRepository accountRepository, BranchRepository branchRepository) {
        return args -> {
            Customer mickey = new Customer("Mueller");
            Customer minnie = new Customer("Meier");
            Customer pluto = new Customer("Schulze");
            customerRepository.save(Arrays.asList(mickey, minnie, pluto));
            
            Account accOne = new Account(new AccountNrType(12484));
            Account accTwo = new Account(new AccountNrType(12485));
            Account accThree = new Account(new AccountNrType(12486));
            accOne.addToAcountValue(100);
            accTwo.addToAcountValue(200);
            accountRepository.save(Arrays.asList(accOne,accTwo,accThree));
            
            Branch branchOne = new Branch(1);
            Branch branchTwo = new Branch(2);
            
            branchRepository.save(Arrays.asList(branchOne,branchTwo));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
