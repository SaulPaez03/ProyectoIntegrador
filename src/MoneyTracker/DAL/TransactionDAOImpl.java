package MoneyTracker.DAL;

import MoneyTracker.Entities.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class TransactionDAOImpl extends DAOImpl implements TransactionDAO {
    public TransactionDAOImpl() {
        super();
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new LinkedList<>();
        try {
            this.databaseConnection.startConnection();
            Statement statement = this.databaseConnection.getConnection().createStatement();
            statement.execute("SELECT * FROM TRANSACTIONS");
            ResultSet results = statement.getResultSet();


            while (results.next()) {
                transactions.add(
                        new Transaction(results.getInt("ID"), results.getDouble("AMOUNT"), results.getString("NOTE"),
                                        results.getInt("CATEGORY_ID"), results.getDate("DATE").toLocalDate()));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.closeConnection();
        }
        return transactions;
    }

    @Override
    public Transaction getById(Integer id) {
        Transaction Transaction = null;

        if (id == null) return null;
        try {
            this.databaseConnection.startConnection();
            Statement statement = this.databaseConnection.getConnection().createStatement();
            statement.execute("SELECT * FROM TransactionS WHERE id = " + id);
            ResultSet result = statement.getResultSet();
            System.out.println(result);
            System.out.println(result.next());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.databaseConnection.closeConnection();
        }

        return Transaction;


    }


    @Override
    public int update(Transaction object) {
        return 0;
    }

    @Override
    public boolean insert(Transaction object) {
        String sql = String.format(Locale.US, "INSERT INTO TransactionS(AMOUNT,NOTE,DATE,CATEGORY_ID) VALUES (%1$f,'%2$s','%3$s',%4$s)",
                                   object.getAmount(), object.getNote(), object.getDate().toString(), object.getCategoryId());
        int count = this.databaseConnection.executeQuery(sql);
        System.out.println(count + " row(s) inserted");
        return count > 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    public List<Transaction> getAllByCategoryId(int categoryID){
        List<Transaction> transactions = new LinkedList<>();
        try {
            this.databaseConnection.startConnection();
            Statement statement = this.databaseConnection.getConnection().createStatement();
            String sql = String.format("SELECT * FROM TRANSACTIONS WHERE CATEGORY_ID = %1$s", categoryID);
            statement.execute(sql);
            ResultSet results = statement.getResultSet();


            while (results.next()) {
                transactions.add(
                        new Transaction(results.getInt("ID"), results.getDouble("AMOUNT"), results.getString("NOTE"),
                                        results.getInt("CATEGORY_ID"), results.getDate("DATE").toLocalDate()));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.databaseConnection.closeConnection();
        }
        return transactions;
    }
}
