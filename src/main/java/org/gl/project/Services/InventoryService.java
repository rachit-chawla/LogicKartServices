package org.gl.project.Services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gl.project.DAO.InventoryDAO;
import org.gl.project.Models.Inventory;

@Path("/inventory")
public class InventoryService {
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInventoryById(@PathParam("id") Long id)
	{
		InventoryDAO ivd = new InventoryDAO();
		Inventory inventory = ivd.getInventoryById(id);
		return Response.status(200).entity(inventory).build();
	}
	
	@GET
	@Path("/list")
	public Response getInventories()
	{
		InventoryDAO ivd = new InventoryDAO();
		List<Inventory> inventories = ivd.getAllInventories();
		return Response.status(200).entity(inventories).build();
	}
}
