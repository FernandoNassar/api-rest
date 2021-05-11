package br.com.projeto.apirest.api.exceptions;

public class ErroValidacao {
	
	private String campo;
	private String erro;
	
	public ErroValidacao() {}
	
	public ErroValidacao(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
	
}
