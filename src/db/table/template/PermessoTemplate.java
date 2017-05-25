package db.table.template;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "permessotemplate")
@AssociationOverrides({
    @AssociationOverride(name = "pk.permesso",
        joinColumns = @JoinColumn(name = "PageNome")),
    @AssociationOverride(name = "pk.template",
        joinColumns = @JoinColumn(name = "TemplateNome")) })
public class PermessoTemplate implements java.io.Serializable{
	private PermessoTemplateId pk = new PermessoTemplateId();
	private int priority;
	
	@EmbeddedId
	public PermessoTemplateId getPk() {
		return pk;
	}
	public void setPk(PermessoTemplateId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Permesso getPermesso() {
		return getPk().getPermesso();
	}

	public void setPermesso(Permesso permesso) {
		getPk().setPermesso(permesso);
	}
	
	@Transient
	public Template getTemplate() {
		return getPk().getTemplate();
	}

	public void setTemplate(Template template) {
		getPk().setTemplate(template);
	}
	
	@Column(name = "priority", nullable = false)
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}
