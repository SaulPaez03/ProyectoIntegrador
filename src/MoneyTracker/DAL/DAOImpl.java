package MoneyTracker.DAL;

public abstract class DAOImpl {
    DatabaseConnection databaseConnection;

    public DAOImpl() {
        this.databaseConnection = DatabaseConnection.getDatabaseConnection();
    }
}
