package se1app.applicationcore.accountcomponent;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import se1app.applicationcore.Application;
import se1app.applicationcore.branchcomponent.Branch;
import se1app.applicationcore.branchcomponent.BranchRepository;


/**
 * @author Robert
 * @date 30.12.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class BranchRepositoryTest {
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Before
	public void setUp() throws Exception {
		branchRepository.save(new Branch(1));
		branchRepository.save(new Branch(2));
	}
	
	@Test
	public void testGetBranch() {
		assertEquals(2, branchRepository.findAll().size());
	}
	
}
