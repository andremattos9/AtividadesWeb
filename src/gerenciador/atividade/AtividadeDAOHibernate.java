package gerenciador.atividade;

import gerenciador.quadro.Quadro;
import gerenciador.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class AtividadeDAOHibernate implements AtividadeDAO {

	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	public void salvar(Atividade atividade) {
		this.session.saveOrUpdate(atividade);

	}

	@Override
	public void excluir(Atividade atividade) {
		this.session.delete(atividade);
	}

	@Override
	public Atividade carregar(Integer codigo) {
		return (Atividade) this.session.get(Atividade.class, codigo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Atividade> listar(Usuario usuario) {
		Criteria criteria = this.session.createCriteria(Atividade.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public Integer calcularHorasQuadro(Usuario usuario, Quadro quadro) {
		Criteria criteria = this.session.createCriteria(Atividade.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.add(Restrictions.eq("quadro", quadro));
		List<Atividade> atividades = new ArrayList<>();
		atividades = criteria.list();
		
		Integer totalHorasQuadro = 0;
		
		for(Atividade atividade : atividades){
			totalHorasQuadro = totalHorasQuadro + atividade.getHorasAceitas();
		}
		
		return totalHorasQuadro;
	}



}
