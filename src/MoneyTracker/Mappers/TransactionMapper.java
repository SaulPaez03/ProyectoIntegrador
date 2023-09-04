package MoneyTracker.Mappers;

import MoneyTracker.DAL.CategoryDAOImpl;
import MoneyTracker.DTOs.TransactionDTO;
import MoneyTracker.Entities.Category;
import MoneyTracker.Entities.Transaction;

import java.util.NoSuchElementException;

public class TransactionMapper {

    public static TransactionDTO toDto(Transaction trn) {
        Category category = new CategoryDAOImpl().getById(trn.getCategoryId());
        String categoryName = category==null?"No category": category.getName();
        return new TransactionDTO(trn.getId(), trn.getNote(), trn.getAmount(), categoryName, trn.getDate());
    }

    public static Transaction toEntity(TransactionDTO dto) {
        if (dto == null) {
            return null;
        }

        Integer id;
        try {
            CategoryDAOImpl categoryDao = new CategoryDAOImpl();
            Category category =  categoryDao.findByName(dto.categoryName);
            id = category==null?null:category.getId();
        } catch (NoSuchElementException e) {
            id= null;
        }


        return new Transaction(dto.id,dto.amount, dto.note, id);
    }
}
