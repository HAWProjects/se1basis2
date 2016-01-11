package se1app.applicationcore.accountcomponent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByAccount(Account account);
}
