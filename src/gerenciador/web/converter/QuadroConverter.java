package gerenciador.web.converter;

import gerenciador.quadro.Quadro;
import gerenciador.quadro.QuadroRN;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Quadro.class)
public class QuadroConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent conponent, String value) {
		if(value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {
				QuadroRN quadroRN = new QuadroRN();
				return quadroRN.carregar(codigo);
			} catch(Exception e) {
				throw new ConverterException("Nao foi possivel encontrar a categoria do codigo " + 
						value + ". " + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Quadro quadro = (Quadro) value;
			return quadro.getId().toString();
		}
		return "";
	}

}
