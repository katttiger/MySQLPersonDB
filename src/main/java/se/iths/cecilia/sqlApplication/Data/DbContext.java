package se.iths.cecilia.sqlApplication.Data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbContext {
    private static DbContext instance;
    private Properties properties;

    private DbContext() {
        properties = new Properties();
        try (InputStream input = ClassLoader.getSystemResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(getProperty("url"), getProperty("user"), getProperty("password"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static DbContext getInstance() {
        if (instance == null) {
            instance = new DbContext();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
