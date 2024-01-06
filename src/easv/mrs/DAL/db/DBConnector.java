package easv.mrs.DAL.db;

// JDBC driver imports
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

// Project imports
import easv.mrs.util.MRSException;

// Java imports
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;


public class DBConnector {

    private static DBConnector INSTANCE = null;
    private static final String PROP_FILE = "config/config.settings";
    private SQLServerDataSource dataSource;

    /**
     * 
     * @throws MRSException
     */
    private DBConnector() throws MRSException {

        try {
            Properties databaseProperties = new Properties();
            databaseProperties.load(new FileInputStream(new File(PROP_FILE)));

            dataSource = new SQLServerDataSource();
            dataSource.setServerName(databaseProperties.getProperty("Server"));
            dataSource.setDatabaseName(databaseProperties.getProperty("Database"));
            dataSource.setUser(databaseProperties.getProperty("User"));
            dataSource.setPassword(databaseProperties.getProperty("Password"));
            dataSource.setPortNumber(1433);
            dataSource.setTrustServerCertificate(true);
        }
        catch (IOException ex)
        {
            // fixme: optionally log to file, db etc.
            throw new MRSException("Could not connect to database.", ex);
        }
    }

    /**
     *
     * @return
     * @throws MRSException
     */
    public static DBConnector getInstance() throws MRSException {
        if (INSTANCE != null)
            return INSTANCE;
        else
            return INSTANCE = new DBConnector();
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    /* Testing purposes...

    public static void main(String[] args) throws SQLException, IOException {

        MyDatabaseConnector databaseConnector = new MyDatabaseConnector();

        try (Connection connection = databaseConnector.getConnection()) {
            System.out.println("Is it open? " + !connection.isClosed());
        } //Connection gets closed here
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */


}
