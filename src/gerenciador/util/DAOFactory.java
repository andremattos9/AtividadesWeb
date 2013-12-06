package gerenciador.util;

import gerenciador.atividade.AtividadeDAO;
import gerenciador.atividade.AtividadeDAOHibernate;
import gerenciador.grupo.GrupoDAO;
import gerenciador.grupo.GrupoDAOHibernate;
import gerenciador.quadro.QuadroDAO;
import gerenciador.quadro.QuadroDAOHibernate;
import gerenciador.usuario.UsuarioDAO;
import gerenciador.usuario.UsuarioDAOHibernate;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO; 
	}
	
	public static AtividadeDAO criaAtividadeDAO() {
		AtividadeDAOHibernate atividadeDAO = new AtividadeDAOHibernate();
		atividadeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return atividadeDAO;
	}
	
	public static QuadroDAO criarQuadroDAO() {
		QuadroDAOHibernate quadroDAO = new QuadroDAOHibernate();
		quadroDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return quadroDAO;
	}
	
	public static GrupoDAO criarGrupoDAO() {
		GrupoDAOHibernate grupoDAO = new GrupoDAOHibernate();
		grupoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return grupoDAO;
	}

}
