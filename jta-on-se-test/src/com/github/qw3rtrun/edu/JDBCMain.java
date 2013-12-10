package com.github.qw3rtrun.edu;
import java.sql.Connection;
import java.sql.SQLException;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import bitronix.tm.resource.jdbc.PoolingDataSource;


public class JDBCMain {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @throws HeuristicRollbackException 
	 * @throws HeuristicMixedException 
	 * @throws RollbackException 
	 * @throws IllegalStateException 
	 * @throws SecurityException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		System.setProperty("java.naming.factory.initial", "bitronix.tm.jndi.BitronixInitialContextFactory");
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		
		PoolingDataSource ds1 = new PoolingDataSource();
		ds1.setUniqueName("jdbc/testDB1");
		ds1.setClassName("org.hsqldb.jdbc.pool.JDBCXADataSource");
		ds1.setMaxPoolSize(3);
		ds1.getDriverProperties().setProperty("url", "jdbc:hsqldb:mem:db1");
		ds1.getDriverProperties().setProperty("user", "sa");
		ds1.getDriverProperties().setProperty("password", "");
		ds1.init();
		 
		PoolingDataSource ds2 = new PoolingDataSource();
		ds2.setUniqueName("jdbc/testDB2");
		ds2.setClassName("org.hsqldb.jdbc.pool.JDBCXADataSource");
		ds2.setMaxPoolSize(3);
		ds2.getDriverProperties().setProperty("url", "jdbc:hsqldb:mem:db2");
		ds2.getDriverProperties().setProperty("user", "sa");
		ds2.getDriverProperties().setProperty("password", "");
		ds2.init();
		
		BitronixTransactionManager userTransaction = TransactionManagerServices.getTransactionManager();
		System.out.println(userTransaction.getTransaction());
		userTransaction.begin();
		Connection c1 = ds1.getConnection();
		Connection c2 = ds2.getConnection();
		c1.createStatement().execute("Create TABLE testt1(id int)");
		c1.createStatement().execute("INSERT INTO testt1 VALUES (1)");
		c2.createStatement().execute("Create TABLE testt2(id int)");
		c2.createStatement().execute("INSERT INTO testt2 VALUES (2)");
		userTransaction.commit();
	}

}
