package gerenciador.quadro;

import java.util.List;

import org.hibernate.Session;

public class QuadroDAOHibernate implements QuadroDAO{

	private Session session;
	
	public void setSession(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Quadro quadro) {
		this.session.saveOrUpdate(quadro);
	}

	@Override
	public void excluir(Quadro quadro) {
		this.session.delete(quadro);
	}

	@Override
	public Quadro carregar(Integer id) {
		return (Quadro) this.session.get(Quadro.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quadro> listar() {
		return this.session.createCriteria(Quadro.class).list();
	
	}
}