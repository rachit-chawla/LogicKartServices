package org.gl.project.DAO;

import org.gl.project.Models.UserAuthentication;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


@SuppressWarnings("deprecation")
public final class AuthenticationDAO {
	
	private static SessionFactory sessionFactory;
	private Session session;
	
	
	public static Session getSession()
	{
		if(sessionFactory == null)
		{
			sessionFactory  = new AnnotationConfiguration().configure().buildSessionFactory();
		}
		return sessionFactory.openSession();
	}
	
	public void addUserAuthentication(UserAuthentication userAuth)
	{
		session = getSession();
		try
		{
			session.beginTransaction();
			session.save(userAuth);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.flush();
			session.close();
		}
	}
	
	public void ExpireUserAuthentication(String token)
	{
		session = getSession();
		try
		{
			UserAuthentication userAuth = getUserAuthenticationByToken(token);
			session.beginTransaction();
			userAuth.setStatus("Expired");	
			//ssession.update(userAuth);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public UserAuthentication getUserAuthenticationByToken(String token)
	{
		UserAuthentication userAuthentication = null;
		try
		{
			session = getSession();
			String queryString = "from UserAuthentication where authenticationToken = :token";
			Query query = session.createQuery(queryString);
			query.setString("token", token);
			userAuthentication =  (UserAuthentication) query.uniqueResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userAuthentication;
	}
}