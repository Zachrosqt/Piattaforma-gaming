package gameplatform.db.table;

import javax.persistence.*;

@Embeddable
public class PermessoTemplateId implements java.io.Serializable{
	private Permesso permesso;
	private Template template;
	
	@ManyToOne
	public Permesso getPermesso() {
		return permesso;
	}
	public void setPermesso(Permesso page) {
		this.permesso = page;
	}
	
	@ManyToOne
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	
}
