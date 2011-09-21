package controllers;

import models.Book;
import play.mvc.Controller;
import dto.ResultDto;

public class Application extends Controller {

    public static void index() {
    	render();
    }
    
    public static void search() {
    	render();
    }
    
    public static void results(Integer pageNumber, String keywords) {

    	if(pageNumber == null) {
    		pageNumber = 1;
    	}
    	ResultDto result = new Book().itemSearchByKeywords(keywords, pageNumber);
		render(keywords, result, pageNumber);
    }
    
    public static void register(String isbn) {
    	Book book = new Book().itemLookupByISBN(isbn);
    	render(book);
    }
    
    public static void save(String isbn, String comment, String status) {
    	Book book = new Book().itemLookupByISBN(isbn);
    	book.save();
    	index();
    }
}