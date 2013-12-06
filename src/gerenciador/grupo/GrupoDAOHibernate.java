package gerenciador.grupo;


import gerenciador.quadro.Quadro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class GrupoDAOHibernate implements GrupoDAO {

private Session session;
	
	public void setSession(Session session){
		this.session = session;
	}
	
	public void salvar(Grupo grupo) {
		this.session.saveOrUpdate(grupo);
	}

	public void excluir(Grupo grupo) {
		this.session.delete(grupo);
	}

	public Grupo carregar(Integer id) {
		return (Grupo) this.session.get(Grupo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grupo> listar(Quadro quadro) {
		Criteria criteria = this.session.createCriteria(Grupo.class);
		criteria.add(Restrictions.eq("quadro", quadro));
		return criteria.list();
	}
	
}
