package com.yjm.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * ���ݿ������ӹ�����
 * 
 * @author lxm
 * @date 2014-3-8 12:30:58
 */
public class DBManage {

	private String driver;//��������
	private String url;//����URL
	private String user;//�����û���
	private String password;//��������
	private Connection con;// ��������
	private Statement stmt;// ����STMT
	private PreparedStatement ps;
	private ResultSet rs;// ��������
	
	private static DBManage slef = null;
	
	static String confPath = null;
	static{
		String path = System.getProperty("user.dir"); 
		// ͨ��File��ʽ��ȡconf.properties  
        confPath = path.concat(File.separator).concat("jdbc.properties"); 
	}

	//���캯����Ĭ�ϼӲ������ļ�Ϊjdbc.driver
	private DBManage() {
		this(confPath);
	}

	public static synchronized DBManage getInstance() {
		if (slef == null) {
			slef = new DBManage();
		}
		return slef;
	}

	//�вι��캯��������·��Conf���÷���loadProperties���м��أ���setConn��������
	public DBManage(String conf) {
		loadProperties(conf);
	}
	
	//load properties
	private void loadProperties(String conf) {
		Properties props = new Properties();
		try {
			System.out.println("LXM=======DBUtil=====loadProperties===conf:"+conf);
			props.load(new FileInputStream(conf));//���������ļ�·��Conf���������ļ�
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		//�������ļ���ȡ����Ӧ�Ĳ��������������
		this.driver = props.getProperty("driver");
		this.url = props.getProperty("url");
		this.user = props.getProperty("username");
		this.password = props.getProperty("password");
	}

	public Connection getConnect() {
		try {
			Class.forName(driver);
			this.con = DriverManager.getConnection(url, user, password);
		}
		catch (ClassNotFoundException classnotfoundexception) {
			classnotfoundexception.printStackTrace();
			System.err.println("db: " + classnotfoundexception.getMessage());
		}
		catch (SQLException sqlexception) {
			System.err.println("db.getconn(): " + sqlexception.getMessage());
		}
		return con;
	}

	// ִ��Sql
	public int executeSql(String sql, List<Object> list) {
		con = getConnect();
		try {
			ps = con.prepareStatement(sql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ps.setObject(i + 1, list.get(i));
				}
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return -1;
	}

	public int executeSql(String sql) {
		con = getConnect();
		try {
			stmt = con.createStatement();
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return -1;
	}

	// ��ѯ
	public ResultSet executeQuery(String sql, List<Object> list) {
		con = getConnect();
		try {
			ps = con.prepareStatement(sql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ps.setObject(i + 1, list.get(i));
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;
	}

	public ResultSet executeQuery(String sql) {
		con = getConnect();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	// �ر��ͷ���Դ
	public void closeAll() {
		if (ps != null) {
			try {
				ps.close();
				ps = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
