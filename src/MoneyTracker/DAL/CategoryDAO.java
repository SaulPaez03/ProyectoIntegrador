package MoneyTracker.DAL;

import MoneyTracker.Entities.Category;

public interface CategoryDAO extends CRUD<Category> {
    Category getById(Integer id);
}
