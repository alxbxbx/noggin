package com.noggin.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noggin.lucene.model.RequiredHighlight;
import com.noggin.lucene.model.ResultData;
import com.noggin.lucene.model.SearchType;
import com.noggin.lucene.search.QueryBuilder;
import com.noggin.lucene.search.ResultRetriever;
import com.noggin.models.HighlightBook;
import com.noggin.models.SearchEntity;


@RestController
@RequestMapping(value = "/search")
public class SearchController {

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<HighlightBook> searchAuthor(@RequestBody SearchEntity searchEntity) {
		List<HighlightBook> hbooks = new ArrayList<HighlightBook>();
		List<ResultData> results = new ArrayList<ResultData>();
		try {
			BooleanQuery bquery = new BooleanQuery();
			List<RequiredHighlight> rhs = new ArrayList<RequiredHighlight>();
			
			if(!(searchEntity.getText() == null || searchEntity.getText().equals(""))){
				SearchType.Type textst = SearchType.getType(searchEntity.getTextST());
				Query query = QueryBuilder.buildQuery(textst, "text", searchEntity.getText());
				Occur textOccur = null;
				if(searchEntity.getTextSC().equals("MUST"))
					textOccur = Occur.MUST;
				else if(searchEntity.getTextSC().equals("MUST_NOT"))
					textOccur = Occur.MUST_NOT;
				else if(searchEntity.getTextSC().equals("SHOULD"))
					textOccur = Occur.SHOULD;
				else
					textOccur = Occur.SHOULD;
				bquery.add(query, textOccur);
				RequiredHighlight rh = new RequiredHighlight("text", searchEntity.getText());
				rhs.add(rh);
			}
			if(!(searchEntity.getAuthor() == null || searchEntity.getAuthor().equals(""))){
				SearchType.Type authorst = SearchType.getType(searchEntity.getAuthorST());
				Query query = QueryBuilder.buildQuery(authorst, "text", searchEntity.getAuthor());
				Occur authorOccur = null;
				if(searchEntity.getAuthorSC().equals("MUST"))
					authorOccur = Occur.MUST;
				else if(searchEntity.getAuthorSC().equals("MUST_NOT"))
					authorOccur = Occur.MUST_NOT;
				else if(searchEntity.getAuthorSC().equals("SHOULD"))
					authorOccur = Occur.SHOULD;
				else
					authorOccur = Occur.SHOULD;
				bquery.add(query, authorOccur);
			}
			if(!(searchEntity.getKeywords() == null || searchEntity.getKeywords().equals(""))){
				SearchType.Type keywordsst = SearchType.getType(searchEntity.getKeywordsST());
				Query query = QueryBuilder.buildQuery(keywordsst, "text", searchEntity.getAuthor());
				Occur keywordsoccur = null;
				if(searchEntity.getKeywordsSC().equals("MUST"))
					keywordsoccur = Occur.MUST;
				else if(searchEntity.getKeywordsSC().equals("MUST_NOT"))
					keywordsoccur = Occur.MUST_NOT;
				else if(searchEntity.getKeywordsSC().equals("SHOULD"))
					keywordsoccur = Occur.SHOULD;
				else
					keywordsoccur = Occur.SHOULD;
				bquery.add(query, keywordsoccur);
			}
			if(!(searchEntity.getTitle() == null || searchEntity.getTitle().equals(""))){
				SearchType.Type titlest = SearchType.getType(searchEntity.getTitleST());
				Query query = QueryBuilder.buildQuery(titlest, "text", searchEntity.getAuthor());
				Occur titleoccur = null;
				if(searchEntity.getTitleSC().equals("MUST"))
					titleoccur = Occur.MUST;
				else if(searchEntity.getTitleSC().equals("MUST_NOT"))
					titleoccur = Occur.MUST_NOT;
				else if(searchEntity.getTitleSC().equals("SHOULD"))
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
		
		HBookConverter hbc = new HBookConverter();
		hbooks = hbc.convert(results);
		return hbooks;
		
	}
}
