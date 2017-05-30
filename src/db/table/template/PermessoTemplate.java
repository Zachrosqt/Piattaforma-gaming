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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + priority;
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
		PermessoTemplate other = (PermessoTemplate) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}
	
}
