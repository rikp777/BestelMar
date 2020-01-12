package Data.Context.MySQLContext.Helpers;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class SQLConnector {
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bestelmar";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public SQLConnector(){
    }

    public Jdbi jdbi(){
        Jdbi jdbi = Jdbi.create(DATABASE_URL, USERNAME, PASSWORD);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }
}
