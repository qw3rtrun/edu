package com.github.qw3rtrun.edu;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import bitronix.tm.resource.jdbc.PoolingDataSource;


public class JpaMain {

	/**
	 * @param args
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @throws HeuristicRollbackException 
	 * @throws HeuristicMixedException 
	 * @throws RollbackException 
	 * @throws IllegalStateException 
	 * @throws SecurityException 
	 */
	public static void main(String[] args) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		System.setProperty("java.naming.factory.initial", "bitronix.tm.jndi.BitronixInitialContextFactory");
		
		PoolingDataSource ds1 = new PoolingDataSource();
		ds1.setUniqueName("jdbc/testDB1");
		ds1.setClassName("org.hsqldb.jdbc.pool.JDBCXADataSource");
		ds1.setMaxPoolSize(3);
		ds1.getDriverProperties().setProperty("url", "jdbc:hsqldb:mem:db1");
		ds1.getDriverProperties().setProperty("user", "sa");
		ds1.getDriverProperties().setProperty("password", "");
		ds1.init();
		
		BitronixTransactionManager userTransaction = TransactionManagerServices.getTransactionManager();
		userTransaction.begin();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jta-on-se-test");
		EntityManager em = emf.createEntityManager();
		
//		userTransaction.commit();
		
//		BitronixTransactionManager userTransaction = TransactionManagerServices.getTransactionManager();
//		userTransaction.begin();
		
		MyEntity entity = new MyEntity();
		entity.setId(1l);
		em.persist(entity);
		em.close();
		
//		userTransaction.commit();
		
//		userTransaction.begin();
		em = emf.createEntityManager();
		System.out.println(em.find(MyEntity.class, 1l));
		System.out.println(em.find(MyEntity.class, 2l));
	}

}
