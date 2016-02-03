package com.noggin.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noggin.dao.repositories.IBook;
import com.noggin.lucene.model.RequiredHighlight;
import com.noggin.lucene.model.ResultData;
import com.noggin.lucene.model.SearchType;
import com.noggin.lucene.search.QueryBuilder;
import com.noggin.lucene.search.ResultRetriever;
import com.noggin.models.Book;
import com.noggin.models.Category;
import com.noggin.models.HighlightBook;

@RestController
@RequestMapping(value = "/search")
public class SearchController {
	
	@Autowired
	private IBook ib;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<HighlightBook> searchAuthor(@RequestParam("text") String text, @RequestParam("textST") String textST,
			@RequestParam("textSC") String textSC, @RequestParam("author") String author,
			@RequestParam("authorST") String authorST, @RequestParam("authorSC") String authorSC,
			@RequestParam("keywords") String keywords, @RequestParam("keywordsST") String keywordsST,
			@RequestParam("keywordsSC") String keywordsSC, @RequestParam("title") String title,
			@RequestParam("titleST") String titleST, @RequestParam("titleSC") String titleSC, @RequestParam("category") String category) {
		List<HighlightBook> hbooks = new ArrayList<HighlightBook>();
		List<ResultData> results = new ArrayList<ResultData>();
		try {
			BooleanQuery bquery = new BooleanQuery();
			List<RequiredHighlight> rhs = new ArrayList<RequiredHighlight>();

			if (!(text == null || text.equals(""))) {
				SearchType.Type textst = SearchType.getType(textST);
				Query query = QueryBuilder.buildQuery(textst, "text", text);
				Occur textOccur = null;
				if (textSC.equals("MUST"))
					textOccur = Occur.MUST;
				else if (textSC.equals("MUST_NOT"))
					textOccur = Occur.MUST_NOT;
				else if (textSC.equals("SHOULD"))
					textOccur = Occur.SHOULD;
				else
					textOccur = Occur.SHOULD;
				bquery.add(query, textOccur);
				RequiredHighlight rh = new RequiredHighlight("text", text);
				rhs.add(rh);
			}

			if (!(author == null || author.equals(""))) {
				SearchType.Type authorst = SearchType.getType(authorST);
				Query query = QueryBuilder.buildQuery(authorst, "author", author);
				Occur authorOccur = null;
				if (authorSC.equals("MUST"))
					authorOccur = Occur.MUST;
				else if (authorSC.equals("MUST_NOT"))
					authorOccur = Occur.MUST_NOT;
				else if (authorSC.equals("SHOULD"))
					authorOccur = Occur.SHOULD;
				else
					authorOccur = Occur.SHOULD;
				bquery.add(query, authorOccur);
			}
			if (!(keywords == null || keywords.equals(""))) {
				SearchType.Type keywordsst = SearchType.getType(keywordsST);
				Query query = QueryBuilder.buildQuery(keywordsst, "keyword", keywords);
				Occur keywordsoccur = null;
				if (keywordsSC.equals("MUST"))
					keywordsoccur = Occur.MUST;
				else if (keywordsSC.equals("MUST_NOT"))
					keywordsoccur = Occur.MUST_NOT;
				else if (keywordsSC.equals("SHOULD"))
					keywordsoccur = Occur.SHOULD;
				else
					keywordsoccur = Occur.SHOULD;
				bquery.add(query, keywordsoccur);
			}
			if (!(title == null || title.equals(""))) {
				SearchType.Type titlest = SearchType.getType(titleST);
				Query query = QueryBuilder.buildQuery(titlest, "title", title);
				Occur titleoccur = null;
				if (titleSC.equals("MUST"))
					titleoccur = Occur.MUST;
				else if (titleSC.equals("MUST_NOT"))
					titleoccur = Occur.MUST_NOT;
				else if (titleSC.equals("SHOULD"))
					titleoccur = Occur.SHOULD;
				else
					titleoccur = Occur.SHOULD;

				bquery.add(query, titleoccur);
			}

			results = ResultRetriever.getResults(bquery, rhs);

		} catch (IllegalArgumentException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		List<Book> books = ib.findAll();
		if((author.equals("")) && (text.equals("")) && (keywords.equals("")) && (title.equals(""))){
			if(category == null){
				for (Book b : books){
					HighlightBook hbook = new HighlightBook(b);
					hbooks.add(hbook);
				}
			}else{
				for (Book b : books){
					if(b.getCategory().getId().equals(Integer.parseInt(category))){
						HighlightBook hbook = new HighlightBook(b);
						hbooks.add(hbook);
					}
					
				}
			}
		}else{
			for(ResultData r : results){
				Book book = new HighlightBook();
				for(Book b : books){
					if(b.getFilename().equals(r.getLocation())){
						book = b;
					}
				}
				HighlightBook hbook = new HighlightBook(book);
				try{
					hbook.setHighlight(r.getHighlight());
				}catch(NullPointerException e){
					System.out.println("There is no highlight.");
				}
				if(category != null){
					if(book.getCategory().getId().equals(Integer.parseInt(category)))
						hbooks.add(hbook);	
				}
				else{
					hbooks.add(hbook);
				}
		}
		
			
			
		}
		
		return hbooks;

	}
}
