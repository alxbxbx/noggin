package com.noggin.lucene.search.queries;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;

import com.noggin.lucene.search.ResultRetriever;
import com.noggin.lucene.test.TestIndexer;


public class BooleanSearcher {
	
	public static void main(String[] args) throws Exception{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		File indexDir;
		String termin1;
		String termin2;
		String polje;
		String polje2;
		String veznaOp="";
		if (args.length != 6) {
			try{
				ResourceBundle rb=ResourceBundle.getBundle("rs.ac.uns.ftn.informatika.ues.lucene.test.luceneindex");
				indexDir=new File(rb.getString("indexDir"));
				System.out.println("unesite polje za pretragu:");
				polje=in.readLine();
				System.out.println("unesite prvi termin:");
				termin1=in.readLine();
				System.out.println("unesite polje za pretragu:");
				polje2=in.readLine();
				System.out.println("unesite drugi izraz:");
				termin2=in.readLine();
				System.out.println("Unesite naziv operacije");
				veznaOp=in.readLine();
			}catch(Exception e1){
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + TestIndexer.class.getName()+ " <index dir> <polje1> <term1> <polje2> <term2> <operator> or properties file needed");
			}
		}else{
			indexDir = new File(args[0]);
			polje = args[1];
		 	termin1 = args[2];
		 	polje2 = args[3];
		 	termin2 = args[4];
		 	veznaOp = args[5];
		 	
		}
		if (!indexDir.exists() || !indexDir.isDirectory()) {
			throw new Exception(indexDir +" does not exist or is not a directory.");
		}
		
		
		//konstruisati TermQuery-je koji ce biti kombinovani u BooleanQuery
		
		
		TermQuery query1=new TermQuery(new Term(polje,termin1));
		TermQuery query2= new TermQuery(new Term(polje2,termin2));
		
		//konstruisanje BooleanQuery-a ovde se kombinuju 2 maksimum 1024
		//moze se promeniti pomocu BooleanQuery.setMaxClauseCount();
		BooleanQuery bq=new BooleanQuery();
		if(veznaOp.equalsIgnoreCase("AND")){
			bq.add(query1,BooleanClause.Occur.MUST);
			bq.add(query2,BooleanClause.Occur.MUST);
		}else if(veznaOp.equalsIgnoreCase("OR")){
			bq.add(query1,BooleanClause.Occur.SHOULD);
			bq.add(query2,BooleanClause.Occur.SHOULD);
		}else if(veznaOp.equalsIgnoreCase("NOT")){
			bq.add(query1,BooleanClause.Occur.MUST);
			bq.add(query2,BooleanClause.Occur.MUST_NOT);
		}
		
		//poslacemo ga u nasu klasu za izvrsavanje pretrazivanja i print rezultata
		ResultRetriever rr=new ResultRetriever();
		rr.printSearchResults(bq, indexDir);		
	}

}
