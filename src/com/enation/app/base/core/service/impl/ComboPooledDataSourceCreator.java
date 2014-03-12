package com.enation.app.base.core.service.impl;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.enation.app.base.core.service.IDataSourceCreator;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ComboPooledDataSourceCreator implements IDataSourceCreator {
	private DataSource dataSource;
	public DataSource createDataSource(String driver,String url,String username,String password) {

		try {
			ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource)dataSource;

			comboPooledDataSource.setUser(username);
			comboPooledDataSource.setPassword(password);
			comboPooledDataSource.setJdbcUrl(url);
			comboPooledDataSource.setDriverClass(driver);

			return comboPooledDataSource;
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static void main(String args[]) throws SQLException{
		IDataSourceCreator creator = new ComboPooledDataSourceCreator();
		DataSource dataSource = creator.createDataSource("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:XE","javashop", "javashop");
		java.sql.Connection con =  dataSource.getConnection();
		Statement st = con.createStatement();
		st.execute("delete from test");
	}


	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
