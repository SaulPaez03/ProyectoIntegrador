package MoneyTracker.Mappers;

import MoneyTracker.DTOs.CategoryDTO;
import MoneyTracker.Entities.Category;

public class CategoryMapper {
    public static CategoryDTO toDto(Category cat){
        return new CategoryDTO(cat.getName(), cat.getId());
    };

    public static Category toEntity(CategoryDTO dto){
        return  new Category(dto.name, dto.id);
    }

}
