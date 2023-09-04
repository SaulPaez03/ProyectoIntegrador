package MoneyTracker.DAL;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatabaseConnection {
    public static DatabaseConnection instance;

    public boolean getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }

    private boolean isConnected = false;
    private Connection connection;

    private DatabaseConnection() {

        initializeDataBase();
    }

    public void startConnection() {
        if (this.getIsConnected()) {
            return;
        }
        String url = "jdbc:h2:~/MoneyTracker";
        String username = "admin";
        String password = "12345";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            setConnection(connection);
            setIsConnected(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (!this.isConnected || this.getConnection() == null) {
            return;
        }
        try {
            this.getConnection().close();
            setConnection(null);
            setIsConnected(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static DatabaseConnection getDatabaseConnection() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;

    }

    public int executeQuery(String query) {
        int count = 0;
        try {
            this.startConnection();
            Statement statement = this.getConnection().createStatement();
            statement.execute(query);
            count = statement.getUpdateCount();
            this.closeConnection();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }



    private void initializeDataBase() {
        this.executeQuery(
                "CREATE TABLE IF NOT EXISTS CATEGORIES(ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR (50) NOT NULL)");
        this.executeQuery(
                "CREATE TABLE IF NOT EXISTS TRANSACTIONS(ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,AMOUNT DOUBLE NOT NULL, NOTE VARCHAR (50) NOT NULL, DATE DATE NOT NULL, CATEGORY_ID INT REFERENCES CATEGORIES(ID))");

        try {

            this.startConnection();
            Statement statement= this.getConnection().createStatement();
            statement.execute("SELECT COUNT(*) AS COUNT FROM CATEGORIES");
            ResultSet results = statement.getResultSet();
            results.first();

            int count = results.getInt("COUNT");
            System.out.println("Categories count: " +count
            );
            if (count == 0) {
                String[] basicCategories = new String[] {
                    "House","Food", "Fees", "Clothes", "Healthcare", "Pets"
                };
                for(String category:basicCategories){
                    this.executeQuery(String.format("INSERT INTO CATEGORIES(NAME) VALUES ('%1$s')", category));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;

    }
}
