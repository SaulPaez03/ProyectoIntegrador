package MoneyTracker.DAL;

public abstract class DAOImpl {
    final DatabaseConnection databaseConnection;

    public DAOImpl() {
        this.databaseConnection = DatabaseConnection.getDatabaseConnection();
    }
}
