package gameplatform.pojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import gameplatform.db.table.Template;

public class PageControl {

	 private static PageControl istanza = null;	
	 
	 private PageControl() {}
	 
	 public static synchronized PageControl getPageControl() {
		if (istanza == null) {
         istanza = new PageControl();
	   }
       return istanza;
	 }
	 
	 public List<Template> getTemplate(String pageName){
		 
		 List<Template> template = new ArrayList();
		 
		 	Configuration conf = new Configuration().configure();
			Session session = conf.buildSessionFactory().getCurrentSession();
			session.beginTransaction();
			
	    	String query = "from Template template join template.permessoTemplate joinPage where joinPage.pk.permesso = ? order by joinPage.priority";
	    	List execute = session.createQuery(query).setString(0, pageName).list();
	    	
	    	session.getTransaction().commit();
			
			Iterator it = execute.iterator();

	        while (it.hasNext()) { 
	        	Object[] obj = (Object[]) it.next();
	        	Template temp = (Template) obj[0];  ;
	        	template.add(temp);
	        }
	        
	        return template;
	 }
	
}