package application;

public class Usuario {
	private String login;
	private String urlImagemPerfil;
	private String linkPerfil;

	public Usuario() {
		super();
	}

	public Usuario(String login, String urlImagemPerfil, String linkPerfil) {
		super();
		this.login = login;
		this.urlImagemPerfil = urlImagemPerfil;
		this.linkPerfil = linkPerfil;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getUrlImagemPerfil() {
		return urlImagemPerfil;
	}
	public void setUrlImagemPerfil(String urlImagemPerfil) {
		this.urlImagemPerfil = urlImagemPerfil;
	}
	public String getLinkPerfil() {
		return linkPerfil;
	}
	public void setLinkPerfil(String linkPerfil) {
		this.linkPerfil = linkPerfil;
	}

	@Override
	public String toString() {
		return String.format("Usuario [login=%s, urlImagemPerfil=%s, linkPerfil=%s]", login, urlImagemPerfil,
				linkPerfil);
	}
}
