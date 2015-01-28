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
 * 数据库里连接管理类
 * 
 * @author lxm
 * @date 2014-3-8 12:30:58
 */
public class DBManage {

	private String driver;//定义驱动
	private String url;//定义URL
	private String user;//定义用户名
	private String password;//定义密码
	private Connection con;// 定义连接
	private Statement stmt;// 定义STMT
	private PreparedStatement ps;
	private ResultSet rs;// 定义结果集
	
	private static DBManage slef = null;
	
	static String confPath = null;
	static{
		String path = System.getProperty("user.dir"); 
		// 通过File方式获取conf.properties  
        confPath = path.concat(File.separator).concat("jdbc.properties"); 
	}

	//构造函数，默认加裁配置文件为jdbc.driver
	private DBManage() {
		this(confPath);
	}

	public static synchronized DBManage getInstance() {
		if (slef == null) {
			slef = new DBManage();
		}
		return slef;
	}

	//有参构造函数，传入路径Conf并用方法loadProperties进行加载，用setConn进行设置
	public DBManage(String conf) {
		loadProperties(conf);
	}
	
	//load properties
	private void loadProperties(String conf) {
		Properties props = new Properties();
		try {
			System.out.println("LXM=======DBUtil=====loadProperties===conf:"+conf);
			props.load(new FileInputStream(conf));//根据配置文件路径Conf加载配置文件
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		//从配置文件中取得相应的参数并设置类变量
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

	// 执行Sql
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

	// 查询
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

	// 关闭释放资源
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
