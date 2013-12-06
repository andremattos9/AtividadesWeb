package gerenciador.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class IntegerConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) throws ConverterException {  
        return 1;
     } 

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) throws ConverterException {  
		  
        String resultado ="";  
         if (obj != null) {  
            resultado = obj.toString();  
                   }  
        return resultado;  
        }

}
