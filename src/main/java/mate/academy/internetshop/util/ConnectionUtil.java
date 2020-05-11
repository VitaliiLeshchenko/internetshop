package mate.academy.internetshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ConnectionUtil {
    private static final Logger LOGGER = Logger.getLogger(ConnectionUtil.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cant find mysql Driver");
            throw new RuntimeException("Cant find mysql Driver",e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user","root");
        dbProperties.put("password","1234");
        String url = "jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC";
        try {
            return DriverManager.getConnection(url,dbProperties);
        } catch (SQLException e) {
            LOGGER.error("cannot connect to mysql",e);
            throw new RuntimeException("Cant establish the connection to DB",e);
        }
    }
}
