package gerenciador.atividade;

import gerenciador.quadro.Quadro;
import gerenciador.usuario.Usuario;

import java.util.List;


public interface AtividadeDAO {
	
	public void salvar(Atividade atividade);
	public void excluir(Atividade atividade);
	public Atividade carregar(Integer codigo);
	public List<Atividade> listar(Usuario usuario);
	public Integer calcularHorasQuadro(Usuario usuario, Quadro quadro);
}
