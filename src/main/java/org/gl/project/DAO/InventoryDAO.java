package org.gl.project.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.gl.project.Models.Inventory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public final class InventoryDAO {
	
	private static SessionFactory sessionFactory;
	private Transaction trans;
	private Session session;
	
	public InventoryDAO()
	{
		sessionFactory  = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	public static Session getSession()
	{
		if(sessionFactory == null)
		{
			new InventoryDAO();
		}
		return sessionFactory.openSession();
	}
	
	public void addInventory(Inventory inventory) throws IllegalStateException, SystemException
	{
		session = getSession();
		try
		{
			session.beginTransaction();
			session.save(inventory);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(trans != null)
			{
				trans.rollback();
			}
			e.printStackTrace();
		}
		finally
		{
			session.flush();
			session.close();
		}
	}
	
	public void deleteInventory(Long id) throws IllegalStateException, SystemException
	{
		session = getSession();
		try
		{
			session.beginTransaction();
			Inventory inventory = (Inventory) session.load(Inventory.class, new Long(id));
			session.delete(inventory);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(trans != null)
			{
				trans.rollback();
			}
			e.printStackTrace();
		}
		finally
		{
			session.flush();
			session.close();
		}
	}
	
	public void updateInventory(Inventory inventory) throws IllegalStateException, SystemException
	{
		session = getSession();
		try
		{
			session.beginTransaction();
			session.update(inventory);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(trans != null)
			{
				trans.rollback();
			}
			e.printStackTrace();
		}
		finally
		{
			session.flush();
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Inventory> getAllInventories()
	{
		List<Inventory> inventories = new ArrayList<Inventory>();
		session = getSession();
		try
		{
			trans = (Transaction) session.beginTransaction();
			inventories = session.createQuery("from Inventory").list();
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
		return inventories;
	}
	
	public Inventory getInventoryById(Long inventoryId)
	{
		Inventory inventory = null;
		session = getSession();
		try
		{
			trans = (Transaction) session.beginTransaction();
			String queryString = "from Inventory where id = :inventoryId";
			Query query = session.createQuery(queryString);
			query.setLong("id", inventoryId);
			inventory = (Inventory) query.uniqueResult();
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
		return inventory;
	}
}