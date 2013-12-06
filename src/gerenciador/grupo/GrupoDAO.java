package gerenciador.grupo;


import gerenciador.quadro.Quadro;

import java.util.List;

public interface GrupoDAO {
	
	public void salvar(Grupo grupo);
	public void excluir(Grupo grupo);
	public Grupo carregar(Integer id);
	public List<Grupo> listar(Quadro quadro);
	

}
