package com.github.qw3rtrun.edu;

import javax.transaction.TransactionManager;

import org.eclipse.persistence.transaction.JTATransactionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitronixTransactionController extends JTATransactionController {

    private static Logger logger = LoggerFactory.getLogger(BitronixTransactionController.class);

    public BitronixTransactionController() {
        super();
    }

    public BitronixTransactionController(TransactionManager transactionManager) {
        super(transactionManager);
    }

    /* (non-Javadoc)
     * @see org.eclipse.persistence.transaction.JTATransactionController#acquireTransactionManager()
     */
    @Override
    protected TransactionManager acquireTransactionManager() throws Exception {
        logger.debug("ENTERING: acquireTransactionManager");
        
        TransactionManager mgr = super.acquireTransactionManager();
        if(mgr == null) {
            try {
                // Bitronix binds to java:comp/UserTransaction within Tomcat.
                mgr = (TransactionManager) jndiLookup("java:comp/UserTransaction"); 
                
                setTransactionManager(mgr);
            }
            catch(Exception e) {
               logger.error(e.getMessage()); 
            }
        }
        
        logger.debug("EXITING: acquireTransactionManager");
        return mgr;
    } 

}
