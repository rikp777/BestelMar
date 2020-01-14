package Data.Context.SQLContext.Helpers;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLConnector {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bestelmar" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Amsterdam";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private final static Logger LOGGER = Logger.getLogger(SQLConnector.class.getName());
    private Connection conn;
    private Properties properties;

    private Properties getProperties(){
        if(properties == null){
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    public void open() {
        if(conn == null){
            try{
                Class.forName(DATABASE_DRIVER);
                conn = DriverManager.getConnection(DATABASE_URL, getProperties());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public PreparedStatement getPreparedStatement(String stmt){
        try {
            return this.conn.prepareStatement(stmt);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    public ResultSet executeQuery(PreparedStatement stmt){
        try {
            return stmt.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
    public int executeUpdate(PreparedStatement stmt){
        try{
            return stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return 0;
    }

    public void close(){
        if(conn != null){
            try {
                this.conn.close();
                this.conn = null;
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.log(Level.WARNING, e.getMessage());
            }
        }
    }
}
