package MoneyTracker.Mappers;

import MoneyTracker.DTOs.CategoryDTO;
import MoneyTracker.Entities.Category;

public class CategoryMapper {
    public static CategoryDTO toDto(Category cat){
        return new CategoryDTO(cat.getName(), cat.getId());
    }

// --Commented out by Inspection START (3/9/2023 9:45 p. m.):
//    public static Category toEntity(CategoryDTO dto){
//        return  new Category(dto.name, dto.id);
//    }
// --Commented out by Inspection STOP (3/9/2023 9:45 p. m.)

}
