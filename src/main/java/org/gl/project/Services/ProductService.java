package org.gl.project.Services;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gl.project.DAO.ProductDAO;
import org.gl.project.DAO.UserDAO;
import org.gl.project.Models.Book;
import org.gl.project.Models.Magazine;
import org.gl.project.Models.OpticalMedia;
import org.gl.project.Models.Product;
import org.gl.project.Models.User;
import org.gl.project.PO.NewProductPO;
import org.gl.project.VO.ProductVO;
import org.gl.project.VO.UserDetailsVO;

import com.google.gson.Gson;

@Path("/products")
public class ProductService 
{
	@POST
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts()
	{
		Gson gson = new Gson();
		ProductDAO pd = new ProductDAO();
		List<Product> product = pd.getAllProducts();
		String productList = gson.toJson(product);
		System.out.println(productList);
		return Response.status(200).entity(productList).build();
	}
	
	@POST
	@Path("users/{id}/view")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getProductById(@PathParam("id") Long id, String data)
	{
		Gson gson = new Gson();
		UserDetailsVO udv = new UserDetailsVO();
		UserDAO ud = new UserDAO();
		User user = ud.getUserById(id);
		udv.setFname(user.getFname());
		udv.setMname(user.getMname());
		udv.setLname(user.getLname());
		udv.setAddress(user.getAddress());
		udv.setDob(user.getDob());
		udv.setEmailId(user.getEmailId());
		udv.setPassword(user.getPassword());
		udv.setGender(user.getGender());
		udv.setPhone(user.getPhone());
		udv.setType(user.getType());
		udv.setStatus(user.getStatus());
		String json = gson.toJson(udv);
		System.out.println(udv);
		return Response.status(200).entity(json).build();
	}
	
	@POST
	@Path("/addproduct")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addProduct(String data)
	{
		Gson gson = new Gson();
		NewProductPO newProductPO = gson.fromJson(data, NewProductPO.class);
		System.out.println(newProductPO);
		String productType = newProductPO.getProductType();
		ProductVO productVO = new ProductVO();
		ProductDAO productDAO = new ProductDAO();
		String json = null;
		switch (productType) {
		case "Book":
			Book book = new Book();
			book.setProductName(newProductPO.getProductName());
			book.setProductPrice(newProductPO.getProductPrice());
			book.setProductQuantity(newProductPO.getProductQuantity());
			book.setBook_Author(newProductPO.getBook_Author());
			book.setBook_Description(newProductPO.getBook_Description());
			book.setBook_Publisher(newProductPO.getBook_Publisher());
			book.setBook_Publishing_Year(newProductPO.getMagazine_Publishing_Year());
			try 
			{
				productDAO.addProduct(book);
				productVO.setSuccessMessage("Book has been added successfully");
			} 
			catch(Exception e) 
			{
				productVO.setErrorMessage("Error in adding book : " + e);
			}
			
			json = gson.toJson(productVO);
			return Response.status(200).entity(json).build();
			
		case "Magazine":
			Magazine magazine = new Magazine();
			magazine.setProductName(newProductPO.getProductName());
			magazine.setProductPrice(newProductPO.getProductPrice());
			magazine.setProductQuantity(newProductPO.getProductQuantity());
			magazine.setMagazine_Author(newProductPO.getMagazine_Author());
			magazine.setMagazine_Description(newProductPO.getMagazine_Description());
			magazine.setMagazine_Publisher(newProductPO.getMagazine_Publisher());
			magazine.setMagazine_Publishing_Year(newProductPO.getMagazine_Publishing_Year());
			try 
			{
				productDAO.addProduct(magazine);
				productVO.setSuccessMessage("Magazine has been added successfully");
			} 
			catch(Exception e) 
			{
				productVO.setErrorMessage("Error in adding magazine : " + e);
			}
			
			json = gson.toJson(productVO);
			return Response.status(200).entity(json).build();
			
		case "Optical Media":
			OpticalMedia opticalMedia = new OpticalMedia();
			opticalMedia.setProductName(newProductPO.getProductName());
			opticalMedia.setProductPrice(newProductPO.getProductPrice());
			opticalMedia.setProductQuantity(newProductPO.getProductQuantity());
			opticalMedia.setOpticalMediaCategory(newProductPO.getOpticalMediaCategory());
			opticalMedia.setOpticalMediaDescription(newProductPO.getOpticalMediaDescription());
			opticalMedia.setOpticalMediaType(newProductPO.getOpticalMediaType());
			try 
			{
				productDAO.addProduct(opticalMedia);
				productVO.setSuccessMessage("Optical Media has been added successfully");
			} 
			catch(Exception e) 
			{
				productVO.setErrorMessage("Error in adding Optical Media : " + e);
			}
			
			json = gson.toJson(productVO);
			return Response.status(200).entity(json).build();
		}
		json = gson.toJson(productVO);
		return Response.status(200).entity(json).build();
	}
}