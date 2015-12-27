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
				switch(searchEntity.getTextSC()){
					case "MUST": textOccur = Occur.MUST; break;
					case "MUST_NOT": textOccur = Occur.MUST_NOT; break;
					case "SHOULD": textOccur = Occur.SHOULD; break;
					default: textOccur = Occur.SHOULD; break;
				}
				bquery.add(query, textOccur);
				RequiredHighlight rh = new RequiredHighlight("text", searchEntity.getText());
				rhs.add(rh);
			}
			if(!(searchEntity.getAuthor() == null || searchEntity.getAuthor().equals(""))){
				SearchType.Type authorst = SearchType.getType(searchEntity.getAuthorST());
				Query query = QueryBuilder.buildQuery(authorst, "text", searchEntity.getAuthor());
				Occur authorOccur = null;
				switch(searchEntity.getAuthorSC()){
					case "MUST": authorOccur = Occur.MUST; break;
					case "MUST_NOT": authorOccur = Occur.MUST_NOT; break;
					case "SHOULD": authorOccur = Occur.SHOULD; break;
					default: authorOccur = Occur.SHOULD; break;
				}
				bquery.add(query, authorOccur);
			}
			if(!(searchEntity.getKeywords() == null || searchEntity.getKeywords().equals(""))){
				SearchType.Type keywordsst = SearchType.getType(searchEntity.getKeywordsST());
				Query query = QueryBuilder.buildQuery(keywordsst, "text", searchEntity.getAuthor());
				Occur keywordsoccur = null;
				switch(searchEntity.getKeywordsSC()){
					case "MUST": keywordsoccur = Occur.MUST; break;
					case "MUST_NOT": keywordsoccur = Occur.MUST_NOT; break;
					case "SHOULD": keywordsoccur = Occur.SHOULD; break;
					default: keywordsoccur = Occur.SHOULD; break;
				}
				bquery.add(query, keywordsoccur);
			}
			if(!(searchEntity.getTitle() == null || searchEntity.getTitle().equals(""))){
				SearchType.Type titlest = SearchType.getType(searchEntity.getTitleST());
				Query query = QueryBuilder.buildQuery(titlest, "text", searchEntity.getAuthor());
				Occur titleoccur = null;
				switch(searchEntity.getTitleSC()){
					case "MUST": titleoccur = Occur.MUST; break;
					case "MUST_NOT": titleoccur = Occur.MUST_NOT; break;
					case "SHOULD": titleoccur = Occur.SHOULD; break;
					default: titleoccur = Occur.SHOULD; break;
				}
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
