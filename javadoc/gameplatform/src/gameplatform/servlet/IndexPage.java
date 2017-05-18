package gameplatform.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import db.table.template.Template;

/**
 * Servlet implementation class IndexPage
 */

public class IndexPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pageName;
	private String[] template;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexPage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
		// TODO Auto-generated method stub
		super.init(config);
    	this.pageName = getInitParameter("pageIndex");
    	
    	Configuration conf = new Configuration().configure();
		Session session = conf.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		
    	String query = "from Template template join template.permessoTemplate joinPage where joinPage.pk.permesso = ? order by joinPage.priority";
    	List execute = session.createQuery(query).setString(0, pageName).list();
    	
    	session.getTransaction().commit();
		
		Iterator it = execute.iterator();
		int i = 0;
		this.template = new String[execute.size()];
        while (it.hasNext()) { 
        	Object[] obj = (Object[]) it.next();
        	Template temp = (Template) obj[0];  ;
        	this.template[i] = temp.getIndirizzo();
        	i++;
        }
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("template", this.template);
		
		RequestDispatcher view = request.getRequestDispatcher("JSP/index.jsp");
		view.forward(request, response);
	}

}
