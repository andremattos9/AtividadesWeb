package gerenciador.grupo;

import gerenciador.quadro.Quadro;
import gerenciador.util.DAOFactory;

import java.util.List;

public class GrupoRN {

	private GrupoDAO grupoDAO;
	
	public GrupoRN() {
		this.grupoDAO = DAOFactory.criarGrupoDAO();
	}
	
	public List<Grupo> listar(Quadro quadro){
		return this.grupoDAO.listar(quadro);
	}
	
	public void salvar(Grupo grupo) {
		this.grupoDAO.salvar(grupo);
	}
	
	public void excluir(Grupo grupo) {
		this.grupoDAO.excluir(grupo);
	}
	
	public Grupo carregar(Integer id){
		return this.grupoDAO.carregar(id);
	}
	
}
