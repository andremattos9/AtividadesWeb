package gerenciador.web;

import gerenciador.usuario.Usuario;
import gerenciador.usuario.UsuarioRN;

import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {

	private Usuario	    usuario	= new Usuario();
	private String	       confirmarSenha;
	private List<Usuario>	lista;
	private String	       destinoSalvar;

	public String novo() {
			this.destinoSalvar = "usuarioSucesso";
			this.usuario = new Usuario();
			this.usuario.setAtivo(true);
			return "usuario";
	}

	public String editar() {
		this.confirmarSenha = this.usuario.getSenha();
		this.destinoSalvar = "/restrito/principal";
		return "/publico/usuario";	
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage("A senha n�o foi confirmada corretamente!");
			context.addMessage(null, facesMessage);
			return null;
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		String ra = this.usuario.getRa();
		
		if (usuarioRN.buscarPorRa(ra) != null) {
			FacesMessage facesMessage = new FacesMessage("O RA ja exite!");
			context.addMessage(null, facesMessage);
			return null;
		}
		
		usuarioRN.salvar(this.usuario);

		return this.destinoSalvar;
	}

	public String excluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		return null;
	}

	public String ativar() {
		if (this.usuario.isAtivo())
			this.usuario.setAtivo(false);
		else
			this.usuario.setAtivo(true);

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return null;
	}

	public List<Usuario> getLista() {
		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}
		
	public String atribuiPermissao(Usuario usuario, String permissao) {
		this.usuario = usuario;
		Set<String> permissoes = this.usuario.getPermissao();
		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
		} else {
			permissoes.add(permissao);
		}
		return null;
	}
	

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
}