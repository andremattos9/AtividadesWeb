package gerenciador.web;

import gerenciador.quadro.Quadro;
import gerenciador.quadro.QuadroRN;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name="quadroBean")
@ViewScoped
public class QuadroBean {
	
	private List<SelectItem> quadrosSelect;

	public List<SelectItem> getQuadrosSelect() {
		if(this.quadrosSelect == null) {
			this.quadrosSelect = new ArrayList<SelectItem>();
			QuadroRN quadroRN = new QuadroRN();
			List<Quadro> quadros = quadroRN.listar();
			this.montaDadosSelect(this.quadrosSelect, quadros, "");
		}
		
		return quadrosSelect;
	}

	private void montaDadosSelect(List<SelectItem> select,
			List<Quadro> quadros, String prefixo) {
		SelectItem item = null;
		if(quadros != null) {
			for(Quadro quadro : quadros) {
				item = new SelectItem(quadro, quadro.getNome());
				item.setEscape(false);
				select.add(item);
			}
		}
		
	}
	
	
	
}
