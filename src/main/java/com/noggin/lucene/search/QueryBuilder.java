package com.noggin.lucene.search;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;

import com.noggin.lucene.analysers.SerbianAnalyzer;
import com.noggin.lucene.model.SearchType;
import com.noggin.lucene.model.SearchType.Type;


public class QueryBuilder {
	private static Version version = Version.LUCENE_40;
	private static SerbianAnalyzer analyzer = new SerbianAnalyzer();
	private static int maxEdits = 1;
	
	public static int getMaxEdits(){
		return maxEdits;
	}
	
	public static void setMaxEdits(int maxEdits){
		QueryBuilder.maxEdits = maxEdits;
	}
	
	public static Query buildQuery(SearchType.Type queryType, String field, String value) throws IllegalArgumentException, ParseException{
		QueryParser parser = new QueryParser(version, field, analyzer);
		String errorMessage = "";
		if(field == null || field.equals("")){
			errorMessage += "Field not specified";
		}
		if(value == null){
			if(!errorMessage.equals("")) errorMessage += "\n";
			errorMessage += "Value not specified";
		}
		if(!errorMessage.equals("")){
			throw new IllegalArgumentException(errorMessage);
		}
		
		Query query = null;
		if(queryType.equals(Type.regular)){
			Term term = new Term(field, value);
			query = new TermQuery(term);
		}else if(queryType.equals(Type.fuzzy)){
			Term term = new Term(field, value);
			query = new FuzzyQuery(term, maxEdits);
		}else if(queryType.equals(Type.prefix)){
			Term term = new Term(field, value);
			query = new PrefixQuery(term);
		}else if(queryType.equals(Type.range)){
			String[] values = value.split(" ");
			query = new TermRangeQuery(field, new BytesRef(values[0]), new BytesRef(values[1]), true, true);
		}else{
			String[] values = value.split(" ");
			PhraseQuery pq = new PhraseQuery();
			for(String word : values){
				Term term = new Term(field, word);
				pq.add(term);
			}
			query = pq;
		}
		
		return parser.parse(query.toString(field));
	}

}
