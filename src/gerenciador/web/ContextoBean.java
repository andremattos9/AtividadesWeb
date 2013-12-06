package gerenciador.web;

import gerenciador.usuario.Usuario;
import gerenciador.usuario.UsuarioRN;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="contextoBean")
@SessionScoped
public class ContextoBean {
	
	private Usuario usuarioLogado = null;
	
	public Usuario getUsuarioLogado () {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String ra = external.getRemoteUser();
		if (this.usuarioLogado == null || !ra.equals(this.usuarioLogado.getRa())){
			if (ra != null) {
				UsuarioRN usuarioRN = new UsuarioRN();
				this.usuarioLogado = usuarioRN.buscarPorRa(ra);
			}
		}
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;
	}
	
}
