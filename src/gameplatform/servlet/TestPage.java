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

import gameplatform.business.GameplatformCRUD;
import gameplatform.business.GameplatformService;
import gameplatform.business.impl.GameplatformCRUDImpl;
import gameplatform.business.impl.GameplatformServiceImpl;
import gameplatform.db.table.Categoria;
import gameplatform.db.table.Giocare;
import gameplatform.db.table.Gioco;
import gameplatform.db.table.Gruppo;
import gameplatform.db.table.Immagine;
import gameplatform.db.table.Livello;
import gameplatform.db.table.Permesso;
import gameplatform.db.table.PermessoTemplate;
import gameplatform.db.table.Template;
import gameplatform.db.table.Trofeo;
import gameplatform.db.table.Utente;

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
		permesso.setNome("UserActivity");
		permesso.setIndirizzo("useractivity.op");

		Template template = new Template("footer.jsp");
		session.saveOrUpdate(template);

		PermessoTemplate permessoTemplate = new PermessoTemplate();
		permessoTemplate.setPermesso(permesso);
		permessoTemplate.setTemplate(template);
		permessoTemplate.setPriority(4);
		
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
		
		Utente user = new Utente("Romolo", "De Roma", 20, "Er Zezzo", "Prova", "test@test.it", 3000, false,  3);
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
        
        Categoria categoria = new Categoria();
        categoria.setCategoria("RPG");
        
        session.saveOrUpdate(categoria);
        
        Gioco gioco = new Gioco();
        gioco.setNome("Zezzo");
        gioco.setDescrizione("Bel Gioco Di Merda");
        gioco.setSpecifiche("2Gb di Rom");
        gioco.setCategoria(categoria);

		Giocare giocare = new Giocare();
		giocare.setExp(3000);
		giocare.setMinuti(new Time(2000));
		giocare.setNumAccessi(30);
		giocare.setRecensione("Bello ma Brutto");
		giocare.setVoto(5);
		giocare.setData(new GregorianCalendar(2017,5,20));
		giocare.setApprovato(true);
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
		
		Immagine img = new Immagine();
		img.setPath("image11.jpg");
		img.setGioco(gioco);
		
		//session.saveOrUpdate(img);
		
		session.getTransaction().commit();
		
		GameplatformCRUD crud = GameplatformCRUDImpl.getGameplatformCRUDImpl();
		crud.saveOrUpdate(img);
		
		//Query query = session.createQuery("from Template template join template.permessoTemplate joinPage where joinPage.pk.permesso = 'Index' order by joinPage.priority");
		//List<Template> temp = query.list();
		
		List<?> temp1 = crud.executeQuery("from Template template join template.permessoTemplate joinPage where joinPage.pk.permesso = 'Index' order by joinPage.priority");
		
		Iterator<?> it1 = temp1.iterator();
        while (it1.hasNext()) { 
        	Object[] obj = (Object[]) it1.next();
        	Template test1 = (Template) obj[0];
            response.getWriter().append(" " + test1.getIndirizzo());          
        }
        
        GameplatformService service = GameplatformServiceImpl.getGameplatformServiceImpl();
		
        List<Template> templ = service.templates("Game");
        
        Iterator<?> it2 = templ.iterator();
        while (it2.hasNext()) { 
        	Template te = (Template) it2.next();
            response.getWriter().append("<br> " + te.getIndirizzo());          
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
