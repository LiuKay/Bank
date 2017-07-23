package kay.com.utils;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {

	private static DataSource dataSource=null;
	
	static{
		try {
			//读取DBCP配置文件
			InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("kay/com/dbcp.properties");
			Properties properties = new Properties();		
			properties.load(inputStream);
			dataSource=BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	//获取连接
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	//关闭连接
    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
