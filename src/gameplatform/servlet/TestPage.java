package gameplatform.servlet;

import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import db.table.template.Giocare;
import db.table.template.Gioco;
import db.table.template.Gruppo;
import db.table.template.Livello;
import db.table.template.Permesso;
import db.table.template.PermessoTemplate;
import db.table.template.Template;
import db.table.template.Trofeo;
import db.table.template.Utente;

/**
 * Servlet implementation class IndexPage
 */

public class TestPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Configuration conf = new Configuration().configure();
		Session session = conf.buildSessionFactory().getCurrentSession();
		session.beginTransaction();

		Permesso permesso = new Permesso();
		permesso.setNome("AccessDenied");
		permesso.setIndirizzo("accessdenied.op");

		Template template = new Template("accessdenied.jsp");
		session.saveOrUpdate(template);

		PermessoTemplate permessoTemplate = new PermessoTemplate();
		permessoTemplate.setPermesso(permesso);
		permessoTemplate.setTemplate(template);
		permessoTemplate.setPriority(1);
		
		permesso.getPermessoTemplate().add(permessoTemplate);
		
		session.saveOrUpdate(permesso);
		
		Query query = session.createQuery("from Template template join template.permessoTemplate joinPage where joinPage.pk.permesso = 'Index' order by joinPage.priority");
		List<Template> temp = query.list();
		
		Iterator it = temp.iterator();
        while (it.hasNext()) { 
        	Object[] obj = (Object[]) it.next();
        	Template test = (Template) obj[0];
            response.getWriter().append(" " + test.getIndirizzo());          
        }
		
		Gruppo test = new Gruppo("Admin");
		session.saveOrUpdate(test);
		
		Utente user = new Utente("Romolo", "De Roma", 20, "Er Zezzo", "Prova", "test@test.it", 3000, 3);
		user.setGruppo(test);
		test.getUtente().add(user);
		test.getPermesso().add(permesso);
		
		session.saveOrUpdate(user);
		
		/*Recensione recensione = new Recensione();
		recensione.setDescrizione("Gioco Bello");
		recensione.setVoto(4);
		recensione.setUtente(user);
		
		session.saveOrUpdate(recensione);*/
		
		Query control = session.createQuery("from Livello");
		List<Livello> controlList = control.list();
		
		boolean bool = false;
		Iterator iter = controlList.iterator();
        while (iter.hasNext()) { 
        	//Object[] obj = (Object[]) 
        	Livello lv = (Livello) iter.next();
        	
        	if (lv.getLivello()==3 && lv.getUtente().getUsername().equals(user.getUsername())){
        		bool=true;
        	}
        }
        if (!bool){
        	Livello livello = new Livello();
    		livello.setLivello(3);
    		livello.setDate(new GregorianCalendar(2017,5,20));
    		livello.setUtente(user);
    			
    		session.saveOrUpdate(livello);
        }
        
        Gioco gioco = new Gioco();
        gioco.setNome("Zezzo");
        gioco.setDescrizione("Bel Gioco Di Merda");
        gioco.setSpecifiche("2Gb di Rom");

		Giocare giocare = new Giocare();
		giocare.setExp(3000);
		giocare.setMinuti(new Time(2000));
		giocare.setNumAccessi(30);
		giocare.setRecensione("Bello ma Brutto");
		giocare.setVoto(5);
		giocare.setUtente(user);
		giocare.setGioco(gioco);
		
		gioco.getGiocare().add(giocare);
		
		session.saveOrUpdate(gioco);
		
		Trofeo trofeo = new Trofeo();
		trofeo.setNome("Test");
		trofeo.setIcona("icona.jpg");
		trofeo.setObiettivo(5);
		trofeo.setGioco(gioco);
		user.getTrofeo().add(trofeo);
		
		session.saveOrUpdate(trofeo);
		
		session.getTransaction().commit();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
