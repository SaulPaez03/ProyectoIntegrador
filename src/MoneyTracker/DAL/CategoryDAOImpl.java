package MoneyTracker.DAL;

import MoneyTracker.Entities.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAOImpl extends DAOImpl implements CategoryDAO {
    public CategoryDAOImpl() {
        super();
    }

    @Override
    public Collection<Category> getAll() {
        try {
            this.databaseConnection.startConnection();
            Statement statement = this.databaseConnection.getConnection().createStatement();
            statement.execute("SELECT * FROM CATEGORIES");
            ResultSet results = statement.getResultSet();
            boolean isValidPosition = results.first();
            List<Category> categories = new LinkedList<>() {};
            while (isValidPosition) {
                String name = results.getString("name");
                int id = results.getInt("id");
                categories.add(new Category(name, id));
                isValidPosition = results.next();
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>() {};
        } finally {
            this.databaseConnection.closeConnection();
        }


    }

    @Override
    public Category getById(Integer id) {
        try {
            if (id == null) {
                return null;
            }
            this.databaseConnection.startConnection();
            Statement statement = this.databaseConnection.getConnection().createStatement();
            String sql = "SELECT * FROM CATEGORIES WHERE id = " + id;
            statement.execute(sql);

            ResultSet results = statement.getResultSet();
            boolean firsExists = results.first();
            if (!firsExists) return null;
            String categoryName = results.getString("name");
            return new Category(categoryName, id);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            this.databaseConnection.closeConnection();
        }
    }

    @Override
    public int update(Category object) {

        return this.databaseConnection.executeQuery(
                String.format("UPDATE CATEGORIES NAME = %1$s WHERE id = %2$s", object.getName(), object.getId()));

    }

    @Override
    public boolean insert(Category object) {
        return this.databaseConnection.executeQuery(
                String.format("INSERT INTO CATEGORIES(name) VALUES ('%1$s')", object.getName())) > 0;
    }

    @Override
    public int delete(Integer id) {
        return this.databaseConnection.executeQuery(String.format("DELETE FROM CATEGORIES WHERE ID = %1$s", id));
    }

    public Category findByName(String name) {
        this.databaseConnection.startConnection();
        Category category = null;
        try {
            Statement statement = this.databaseConnection.getConnection().createStatement();
            String sql =String.format("SELECT * FROM CATEGORIES WHERE NAME = '%s' ", name);
            statement.execute(sql);
            ResultSet results = statement.getResultSet();
            boolean hasData = results.next();
            category = !hasData?null: new Category(results.getString("NAME"), results.getInt("ID"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.databaseConnection.closeConnection();
        }
        return category;
    }
}
