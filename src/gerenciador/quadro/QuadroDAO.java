package gerenciador.quadro;

import java.util.List;

public interface QuadroDAO {
	
	public void salvar(Quadro quadro);
	public void excluir(Quadro quadro);
	public Quadro carregar(Integer id);
	public List<Quadro> listar();

}
