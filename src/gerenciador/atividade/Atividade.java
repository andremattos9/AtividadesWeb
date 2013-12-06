package gerenciador.atividade;



import gerenciador.grupo.Grupo;
import gerenciador.quadro.Quadro;
import gerenciador.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Atividade implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3443586373411312207L;

	@Id
	@GeneratedValue
	@Column(name="cod_atividade")
	private Integer atividade;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "cod_usuario", nullable = false)
	private Usuario usuario;
	
	@Column(name = "titulo_atividade")
	private String tituloAtividade;
	
	@OneToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name = "quadro_id", nullable = true)
	@ForeignKey(name="fk_atividade_quadro")
	private Quadro quadro;
	
	@OneToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name = "grupo_id", nullable = true)
	@ForeignKey(name="fk_atividade_grupo")
	private Grupo grupo;
	
	@Column(name="horas_requisitadas")
	private Integer horasRequisitadas;
	
	@Column(name ="horas_aceitas")
	private Integer horasAceitas;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_requisicao")
	private Date dataRequisicao;
	
	private String comprovacao;

	public Integer getAtividade() {
		return atividade;
	}

	public void setAtividade(Integer atividade) {
		this.atividade = atividade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTituloAtividade() {
		return tituloAtividade;
	}

	public void setTituloAtividade(String tituloAtividade) {
		this.tituloAtividade = tituloAtividade;
	}

	public Quadro getQuadro() {
		return quadro;
	}

	public void setQuadro(Quadro quadro) {
		this.quadro = quadro;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Integer getHorasRequisitadas() {
		return horasRequisitadas;
	}

	public void setHorasRequisitadas(Integer horasRequisitadas) {
		this.horasRequisitadas = horasRequisitadas;
	}

	public Integer getHorasAceitas() {
		return horasAceitas;
	}

	public void setHorasAceitas(Integer horasAceitas) {
		this.horasAceitas = horasAceitas;
	}

	public Date getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public String getComprovacao() {
		return comprovacao;
	}

	public void setComprovacao(String comprovacao) {
		this.comprovacao = comprovacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((atividade == null) ? 0 : atividade.hashCode());
		result = prime * result
				+ ((comprovacao == null) ? 0 : comprovacao.hashCode());
		result = prime * result
				+ ((dataRequisicao == null) ? 0 : dataRequisicao.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result
				+ ((horasAceitas == null) ? 0 : horasAceitas.hashCode());
		result = prime
				* result
				+ ((horasRequisitadas == null) ? 0 : horasRequisitadas
						.hashCode());
		result = prime * result + ((quadro == null) ? 0 : quadro.hashCode());
		result = prime * result
				+ ((tituloAtividade == null) ? 0 : tituloAtividade.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (atividade == null) {
			if (other.atividade != null)
				return false;
		} else if (!atividade.equals(other.atividade))
			return false;
		if (comprovacao == null) {
			if (other.comprovacao != null)
				return false;
		} else if (!comprovacao.equals(other.comprovacao))
			return false;
		if (dataRequisicao == null) {
			if (other.dataRequisicao != null)
				return false;
		} else if (!dataRequisicao.equals(other.dataRequisicao))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (horasAceitas == null) {
			if (other.horasAceitas != null)
				return false;
		} else if (!horasAceitas.equals(other.horasAceitas))
			return false;
		if (horasRequisitadas == null) {
			if (other.horasRequisitadas != null)
				return false;
		} else if (!horasRequisitadas.equals(other.horasRequisitadas))
			return false;
		if (quadro == null) {
			if (other.quadro != null)
				return false;
		} else if (!quadro.equals(other.quadro))
			return false;
		if (tituloAtividade == null) {
			if (other.tituloAtividade != null)
				return false;
		} else if (!tituloAtividade.equals(other.tituloAtividade))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}