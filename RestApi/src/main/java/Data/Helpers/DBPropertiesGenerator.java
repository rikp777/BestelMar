package Data.Helpers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBPropertiesGenerator {
    private static FileOutputStream outputStream;
    private static final Logger LOGGER = Logger.getLogger(DBPropertiesGenerator.class.getName());

    public static void main(String[] args){
        try{
            Properties prop = new Properties();
            outputStream = new FileOutputStream("db.prop");

            prop.setProperty("jdbc.drivers", "org.sqlite.JDBC");
            prop.setProperty("jdbc.url", "jdbc:sqlite:bestelMar.db");
            prop.setProperty("jdbc.username", "");
            prop.setProperty("jdbc.password", "");

            prop.store(outputStream, null);
        } catch (IOException io){
            LOGGER.warning(io.getMessage());
        } finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LOGGER.warning(e.getMessage());
                }
            }
        }
    }
}
