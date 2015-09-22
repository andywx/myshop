package cn.edu.tju.myshop.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import cn.edu.tju.myshop.data.RankRepository;
import cn.edu.tju.myshop.model.Rank;

@FacesConverter(value = "rankConverter", forClass = cn.edu.tju.myshop.model.Rank.class)
public class RankConverter implements Converter {

    @Inject
    private RankRepository rankRepository;
    
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		byte rankId;
		try {
			rankId = Byte.parseByte(value);
		} catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Not a rank object", 
                    "Not a rank object"));
		}
		System.out.println(rankRepository.findById(rankId).getName());
		return rankRepository.findById(rankId);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Rank) {
			Rank rank = (Rank) value;
			return "" + rank.getId();
		}
		return "";
	}

}
