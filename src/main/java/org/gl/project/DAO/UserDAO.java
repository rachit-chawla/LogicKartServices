package org.gl.project.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.gl.project.Models.Orders;
import org.gl.project.Models.User;
import org.gl.project.PO.UpdateUserPO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public final class UserDAO {
	
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
	
	public void addUser(User user)
	{
		try
		{
			session = getSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void updateUserPassword(User user, String newPassword, String emailId)
	{
		session = getSession();
		try
		{
			session.beginTransaction();
			String queryString = "update User set password = :newPassword where emailId = :emailId";
			Query query = session.createQuery(queryString);
			query.setString("newPassword", newPassword);
			query.setString("emailId", emailId);
			int x = query.executeUpdate();
			System.out.println(x);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteUser(Long id)
	{
		try
		{
			session = getSession();
			//User user = getUserById(id);
			session.beginTransaction();
			//session.delete(user);
			Query query = session.createQuery("delete User where id = :id");
			query.setParameter("id", id);
			int x = query.executeUpdate();
			System.out.println(x);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
		
			e.printStackTrace();
		}
	}
	
	public void updateUser(UpdateUserPO updateUserPO, Long userId)
	{
		session = getSession();
		try
		{
			User user = getUserById(userId);
			session.beginTransaction();
			user.setFname(updateUserPO.getFname());
			user.setMname(updateUserPO.getMname());
			user.setLname(updateUserPO.getLname());
			user.setAddress(updateUserPO.getAddress());
			user.setDob(updateUserPO.getDob());
			user.setGender(updateUserPO.getGender());
			user.setPhone(updateUserPO.getPhone());
			user.setEmailId(updateUserPO.getEmailId());
			user.setPassword(updateUserPO.getPassword());
			user.setType(updateUserPO.getType());
			user.setStatus(updateUserPO.getStatus());
			//session.saveOrUpdate(user);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers()
	{
		List<User> users = new ArrayList<User>();
		session = getSession();
		try
		{
			users = session.createQuery("from User").list();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return users;
	}
	
	public User getUserById(Long id)
	{
		User user = null;
		session = getSession();
		try
		{
			String queryString = "from User where id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			user = (User) query.uniqueResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	
	public User getUserByEmailId(String emailId)
	{
		User user = null;
		try
		{
			session = getSession();
			String queryString = "from User where emailId = :emailId";
			Query query = session.createQuery(queryString);
			query.setString("emailId",emailId);
			user = (User) query.uniqueResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	
	public List<Orders> getOrdersById(Long userId)
	{
		User user = null;
		List<Orders> orders = null;
		session = getSession();
		try
		{
			String queString = "from User where id = :userId";
			Query query = session.createQuery(queString);
			query.setLong("id", userId);
			user = (User) query.uniqueResult();
			orders = (List<Orders>) user.getOrders(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public int deleteUsers()
	{
		session = getSession();
		int status = 0;
		try
		{
			String queryString = "delete from User";
			Query query = session.createQuery(queryString);
			status = query.executeUpdate();
			System.out.println(status);
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
		return status;
	}
	
	public String getNewPassword()
	{
		char text[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
		int len = text.length;
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<8;i++)
		{
			int index = new Random().nextInt(len);
			sb.append(text[index]);
		}
		return sb.toString();
	}
}