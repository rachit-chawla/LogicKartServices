package org.gl.project.Models;

import javax.persistence.Entity;

@Entity
public class Book extends Product
{
	private String book_Author;
	
	private String book_Description;
	
	private String book_Publisher;
	
	private Integer book_Publishing_Year;
		
	public String getBook_Author() {
		return book_Author;
	}
	
	public void setBook_Author(String book_Author) {
		this.book_Author = book_Author;
	}
	
	public String getBook_Description() {
		return book_Description;
	}
	
	public void setBook_Description(String book_Description) {
		this.book_Description = book_Description;
	}
	
	public String getBook_Publisher() {
		return book_Publisher;
	}
	
	public void setBook_Publisher(String book_Publisher) {
		this.book_Publisher = book_Publisher;
	}
	
	public Integer getBook_Publishing_Year() {
		return book_Publishing_Year;
	}

	public void setBook_Publishing_Year(Integer book_Publishing_Year) {
		this.book_Publishing_Year = book_Publishing_Year;
	}
}