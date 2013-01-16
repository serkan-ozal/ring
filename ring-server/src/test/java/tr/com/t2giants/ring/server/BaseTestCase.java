package tr.com.t2giants.ring.server;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: sonic
 * Date: 1/15/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
           @ContextConfiguration(locations = {"/applicationContext-test.xml"})
           @TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
           @Transactional
public abstract class BaseTestCase {

}
