package com.github.qw3rtrun.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

public class LiquiTest {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws LiquibaseException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException, LiquibaseException {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		Connection connection = DriverManager.getConnection(
			"jdbc:hsqldb:mem:db1","sa","");
		Liquibase lb = new Liquibase("lb/test.xml", new FileSystemResourceAccessor(), new JdbcConnection(connection));
		lb.update(null);
	}

}
