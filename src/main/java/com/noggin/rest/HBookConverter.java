package com.noggin.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.noggin.dao.repositories.IBook;
import com.noggin.lucene.model.ResultData;
import com.noggin.models.Book;
import com.noggin.models.HighlightBook;

@RestController
public class HBookConverter {
	
	@Autowired
	private IBook ib;
	
	public List<HighlightBook> convert(List<ResultData> results){
		List<HighlightBook> hbooks = new ArrayList<HighlightBook>();
		
		for(ResultData r : results){
			Book book = new Book();
			book = ib.findByFilename(r.getLocation());
			HighlightBook hbook = (HighlightBook) book;
			hbook.setHighlight(r.getHighlight());
			hbooks.add(hbook);
			
		}
		
		return hbooks;
	}

}
