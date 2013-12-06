package gerenciador.web;

import gerenciador.atividade.Atividade;
import gerenciador.atividade.AtividadeRN;
import gerenciador.exception.UtilException;
import gerenciador.grupo.Grupo;
import gerenciador.grupo.GrupoRN;
import gerenciador.quadro.Quadro;
import gerenciador.util.ContextoUtil;
import gerenciador.util.RelatorioUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.StreamedContent;

@ManagedBean(name = "atividadeBean")
@ViewScoped
public class AtividadeBean {
	
	private Atividade selecionada = new Atividade();
	private List<Atividade> lista = null;
	private List<SelectItem> gruposSelect;
	private StreamedContent arquivoRetorno;
	private int tipoRelatorio;
	private int modeloRelatorio;
	private Integer totalApoveitamento = 0;
	private String nomeRelatorioJasper;
	
	public Integer getTotalApoveitamento() {
		AtividadeRN atividadeRN = new AtividadeRN();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		
		List<Atividade> atividades = new ArrayList<>();
		atividades = atividadeRN.listar(contextoBean.getUsuarioLogado());
		
		Integer total = 0;
				
		for (Atividade atividade : atividades) {
			total = total + atividade.getHorasAceitas();
		}
		totalApoveitamento = total;
		
		return totalApoveitamento;
	}

	public void setTotalApoveitamento(Integer totalApoveitamento) {
		this.totalApoveitamento = totalApoveitamento;
	}

	public void setGruposSelect(List<SelectItem> gruposSelect) {
		this.gruposSelect = gruposSelect;
	}

	public void salvar() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.selecionada.setUsuario(contextoBean.getUsuarioLogado());
		
		this.selecionada.setHorasAceitas(this.calculaHoras());
		
		AtividadeRN atividadeRN = new AtividadeRN();
		atividadeRN.salvar(this.selecionada);
		this.selecionada = new Atividade();
		this.lista = null;
	}
	
	public void excluir() {
		AtividadeRN atividadeRN = new AtividadeRN();
		atividadeRN.excluir(this.selecionada);
		this.selecionada = new Atividade();
		this.lista = null;
	}
	
	public List<Atividade> getLista() {
		if(this.lista == null) {
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			
			AtividadeRN atividadeRN = new AtividadeRN();
			this.lista = atividadeRN.listar(contextoBean.getUsuarioLogado());
		}
		return this.lista;
	}
	
	public Integer calculaHoras() {
		
		Integer grupoSelecionado = this.selecionada.getGrupo().getId();
		Integer aproveitamento = null;
		
		switch (grupoSelecionado){
		case 1:
			aproveitamento = 4;			
			break;
		case 2:
			aproveitamento = this.selecionada.getHorasRequisitadas();
			break;
		case 3:
			aproveitamento = 10;
			break;
		case 4:
			aproveitamento = 10;
			break;
		case 5:
			aproveitamento = this.selecionada.getHorasRequisitadas() / 2;
			break;
		case 6:
			aproveitamento = this.selecionada.getHorasRequisitadas();
			break;
		case 7:
			aproveitamento = 2;
			break;
		case 8:
			aproveitamento = 60;
			break;
		case 9:
			aproveitamento = 60;
			break;
		case 10:
			aproveitamento = 80;
			break;
		case 11:
			aproveitamento = 10;
			break;
		case 12:
			aproveitamento = 5;
			break;
		case 13:
			aproveitamento = 5;
			break;
		case 14:
			aproveitamento = 5;
			break;
		case 15:
			aproveitamento = this.selecionada.getHorasRequisitadas() / 5 * 2;
			break;
		case 16:
			aproveitamento = this.selecionada.getHorasRequisitadas() / 4 * 2;
			break;
		case 17:
			aproveitamento = this.selecionada.getHorasRequisitadas() / 5 * 2;
			break;
		case 18:
			aproveitamento = this.selecionada.getHorasRequisitadas();
			break;
		default:
			break;
		}
		
		Integer cargaMaxima = this.selecionada.getQuadro().getCargaMax();
		AtividadeRN atividadeRN = new AtividadeRN();
		Integer totalHorasQuadro = atividadeRN.calcularHorasQuadro(this.selecionada.getUsuario(), 
				this.selecionada.getQuadro());
		
		if(totalHorasQuadro < cargaMaxima){
			if(totalHorasQuadro + aproveitamento <= cargaMaxima){
				
			} else {
				aproveitamento = cargaMaxima - totalHorasQuadro;
			}
		} else if(totalHorasQuadro >= cargaMaxima) {
			aproveitamento = 0;		
		}
		
		return aproveitamento;
	}
	
	public void atualizaGrupos() {
		this.gruposSelect = null;
	}
	
	public List<SelectItem> getGruposSelect() {
		if(gruposSelect == null) {
			Quadro quadro = this.selecionada.getQuadro();
			this.gruposSelect = new ArrayList<SelectItem>();
			
			if(quadro != null) {
				GrupoRN grupoRN = new GrupoRN();
				List<Grupo> grupos = grupoRN.listar(quadro);
				this.montaDadosSelect(this.gruposSelect, grupos, "");
			}
		}
		return gruposSelect;
	}

	private void montaDadosSelect(List<SelectItem> select,
			List<Grupo> grupos, String prefixo) {
		SelectItem item = null;
		if(grupos != null) {
			for(Grupo grupo : grupos) {
				item = new SelectItem(grupo, grupo.getNome());
				item.setEscape(false);
				select.add(item);
			}
		}
	}
	
	public Atividade getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Atividade selecionada) {
		this.selecionada = selecionada;
	}

	public void setLista(List<Atividade> lista) {
		this.lista = lista;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public StreamedContent getArquivoRetorno() {
		FacesContext context = FacesContext.getCurrentInstance();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		String usuario = contextoBean.getUsuarioLogado().getNome();
		String nomeRelatorioSaida;
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap<String, Object> parametrosRelatorio = new HashMap();
		String codigoAtividade = this.selecionada.getAtividade().toString();
		String codigoUsuario = contextoBean.getUsuarioLogado().getCodigo().toString();
		nomeRelatorioSaida = "ERRO";
		
		switch (modeloRelatorio) {
		case 1:
			parametrosRelatorio.put("codigoAtividade", codigoAtividade);
			nomeRelatorioSaida =  "RAC_" + usuario;
			break;
		case 2:
			parametrosRelatorio.put("codigoUsuario", codigoUsuario);
			nomeRelatorioSaida = usuario + "_atividades";
			break;
		}
			
		try {
			this.arquivoRetorno = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper, nomeRelatorioSaida, this.tipoRelatorio);
		} catch(UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		return this.arquivoRetorno;
	}

	public void setArquivoRetorno(StreamedContent arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public String getNomeRelatorioJasper() {
		return nomeRelatorioJasper;
	}

	public void setNomeRelatorioJasper(String nomeRelatorioJasper) {
		this.nomeRelatorioJasper = nomeRelatorioJasper;
	}

	public int getModeloRelatorio() {
		return modeloRelatorio;
	}

	public void setModeloRelatorio(int modeloRelatorio) {
		this.modeloRelatorio = modeloRelatorio;
	}
	

}
