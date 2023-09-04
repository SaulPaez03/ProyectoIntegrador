package MoneyTracker.Controllers;

import MoneyTracker.DAL.CategoryDAOImpl;
import MoneyTracker.DTOs.CategoryDTO;
import MoneyTracker.Entities.Category;
import MoneyTracker.Mappers.CategoryMapper;

import java.util.List;

public class CategoriesController {
    private final CategoryDAOImpl categoryDAO;

    public CategoriesController() {
        this.categoryDAO = new CategoryDAOImpl();
    }

    public List<CategoryDTO> getAllCategories(){

        return this.categoryDAO.getAll().stream().map(CategoryMapper::toDto).toList();
    }

    public boolean addCategory(String name){
        return  this.categoryDAO.insert(new Category(name, 1));
    }
    public int deleteCategory(int id){
        return this.categoryDAO.delete(id);
    }
}
