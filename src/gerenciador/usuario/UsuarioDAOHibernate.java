package gerenciador.usuario;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.hibernate.Query;
import org.hibernate.Session;

@RequestScoped
public class UsuarioDAOHibernate implements UsuarioDAO {

	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public void salvar(Usuario usuario) {
		this.session.save(usuario);
	}

	public void atualizar(Usuario usuario) {
		if (usuario.getPermissao() == null || usuario.getPermissao().size() == 0) { 
			Usuario usuarioPermissao = this.carregar(usuario.getCodigo()); 
			usuario.setPermissao(usuarioPermissao.getPermissao()); 
			this.session.evict(usuarioPermissao); 
		}
		this.session.update(usuario);
	}

	public void excluir(Usuario usuario) {
		this.session.delete(usuario);
	}

	public Usuario carregar(Integer codigo) {
		return (Usuario) this.session.get(Usuario.class, codigo);
	}

	public Usuario buscarPorRa(String ra) {
		String hql = "Select u from Usuario u where u.ra = :ra";
		Query consulta = session.createQuery(hql);
		consulta.setString("ra", ra);
		
		return (Usuario) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listar() {
		return this.session.createCriteria(Usuario.class).list();
	}

}
