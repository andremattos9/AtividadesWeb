package gerenciador.atividade;

import gerenciador.quadro.Quadro;
import gerenciador.usuario.Usuario;
import gerenciador.util.DAOFactory;

import java.util.Date;
import java.util.List;


public class AtividadeRN {
	
	private AtividadeDAO atividadeDAO;
	
	public AtividadeRN() {
		this.atividadeDAO = DAOFactory.criaAtividadeDAO();
	}
	
	public List<Atividade> listar(Usuario usuario){
		return this.atividadeDAO.listar(usuario);
	}
	
	public void salvar(Atividade atividade) {
		atividade.setDataRequisicao(new Date());
		this.atividadeDAO.salvar(atividade);
	}
	
	public void excluir(Atividade atividade) {
		this.atividadeDAO.excluir(atividade);
	}
	
	public Integer calcularHorasQuadro(Usuario usuario, Quadro quadro) {
		return this.atividadeDAO.calcularHorasQuadro(usuario, quadro);
	}
	
}
