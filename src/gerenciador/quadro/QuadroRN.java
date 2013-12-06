package gerenciador.quadro;

import java.util.List;

import gerenciador.util.DAOFactory;

public class QuadroRN {

	private QuadroDAO quadroDAO;
	
	public QuadroRN() {
		this.quadroDAO = DAOFactory.criarQuadroDAO();
	}
	
	public List<Quadro> listar(){
		return this.quadroDAO.listar();
	}
	
	public void salvar(Quadro quadro) {
		this.quadroDAO.salvar(quadro);
	}
	
	public void excluir(Quadro quadro) {
		this.quadroDAO.excluir(quadro);
	}
	
	public Quadro carregar(Integer id){
		return this.quadroDAO.carregar(id);
	}
	
}
