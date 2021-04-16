package com.tcs.tep.gech.cs03.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public abstract class DBConnectionImpl implements DBConnection{

	Connection conn;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public DBConnectionImpl() throws UnsupportedEncodingException, ClassNotFoundException, SQLException {
		String rootPath = null;
		rootPath = getPath();
//		System.out.println("root path :"+ rootPath );
		rootPath = rootPath + "src/main/webapp/WEB-INF/";
//		System.out.println("root path :"+ rootPath );
		rootPath = rootPath + "MyDB";
		System.out.println("Root Path" + rootPath);
		Class.forName("org.h2.Driver");
		this.conn = DriverManager.getConnection("jdbc:h2:" + rootPath + ";create=true", "", "");
		System.out.println(conn);
		return;
	}

	private String getPath() throws UnsupportedEncodingException {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
//        System.out.println("Full Path : " + fullPath);
		fullPath = fullPath.replace("target/classes/", "");
		System.out.println("Full Path : " + fullPath);
		return fullPath;
	}

}
