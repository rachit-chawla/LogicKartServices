package org.gl.project.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.SystemException;

import org.gl.project.Models.Category;
import org.gl.project.Models.Product;
import org.gl.project.Models.ProductImages;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public final class ProductDAO {
	
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
	
	public void addProduct(Product product) throws IllegalStateException, SystemException
	{
		try
		{
			session = getSession();
			session.beginTransaction();
			session.save(product);
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
	
	public void deleteProduct(Long id) throws IllegalStateException, SystemException
	{
		try
		{
			session = getSession();
			session.beginTransaction();
			Product product = getProductById(id);
			session.delete(product);
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
	
	public void updateProduct(Product product) throws IllegalStateException, SystemException
	{
		session = getSession();
		try
		{
			session.beginTransaction();
			session.update(product);
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

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts()
	{
		List<Product> products = new ArrayList<Product>();
		session = getSession();
		try
		{
			session.beginTransaction();
			products = session.createQuery("from Product").list();
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
		return products;
	}
	
	public Product getProductById(Long productId)
	{
		Product product = null;
		session = getSession();
		try
		{
			session.beginTransaction();
			String queryString = "from Product where id = :productId";
			Query query = session.createQuery(queryString);
			query.setLong("id", productId);
			product = (Product) query.uniqueResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
//			session.flush();
//			session.close();
		}
		return product;
	}
	
	public Category getCategoryByProductId(Long productId)
	{
		Category category = null;
		Product product = null;
		session = getSession();
		try
		{
			session.beginTransaction();
			String querString = "from Product where productId = :productId";
			Query query = session.createQuery(querString);
			query.setLong("productId", productId);
			product = (Product) query.uniqueResult();
			category = product.getCategory();
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
		return category;
	}
	
	public Set<ProductImages> getProductImagesByProductId(Long productId)
	{
		Product product = null;
		Set<ProductImages> productImages = null;
		session = getSession();
		try
		{
			session.beginTransaction();
			String querString = "from Product where productId = :productId";
			Query query = session.createQuery(querString);
			query.setLong("productId", productId);
			product = (Product) query.uniqueResult();
			productImages = (Set<ProductImages>) product.getProductImages();
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
		return productImages;
	}
}