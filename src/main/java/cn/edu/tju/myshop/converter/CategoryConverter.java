package cn.edu.tju.myshop.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import cn.edu.tju.myshop.data.CategoryRepository;
import cn.edu.tju.myshop.model.Category;

@FacesConverter(value = "categoryConverter", forClass = cn.edu.tju.myshop.model.Category.class)
public class CategoryConverter implements Converter {

    @Inject
    private CategoryRepository categoryRepository;
    
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		int categoryId;
		try {
			categoryId = Integer.parseInt(value);
		} catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Not a category object", 
                    "Not a category object"));
		}
		return categoryRepository.findById(categoryId);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Category) {
			Category category = (Category) value;
			return "" + category.getId();
		}
		return "";
	}

}
