package com.noggin.lucene.search.queries;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.BytesRef;

import com.noggin.lucene.search.ResultRetriever;
import com.noggin.lucene.test.TestIndexer;


public class RangeSearcher {
	public static void main(String[] args) throws Exception{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		File indexDir;
		String terminStart;
		String terminEnd;
		String polje;
		if (args.length != 4) {
			try{
				ResourceBundle rb=ResourceBundle.getBundle("rs.ac.uns.ftn.informatika.ues.lucene.test.luceneindex");
				indexDir=new File(rb.getString("indexDir"));
				System.out.println("unesite polje za pretragu:");
				polje=in.readLine();
				System.out.println("unesite pocetni izraz:");
				terminStart=in.readLine();
				System.out.println("unesite krajnji izraz:");
				terminEnd=in.readLine();
			}catch(Exception e1){
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + TestIndexer.class.getName()+ " <index dir> <polje> <pocetni izraz> <krajnji izraz> or properties file needed");
			}
		}else{
			indexDir = new File(args[0]);
			polje = args[1];
		 	terminStart = args[2];
		 	terminEnd = args[3];
		}
		if (!indexDir.exists() || !indexDir.isDirectory()) {
			throw new Exception(indexDir +" does not exist or is not a directory.");
		}
		
		BytesRef terminStartRef = new BytesRef(terminStart);
		BytesRef terminEndRef = new BytesRef(terminEnd);
		
		Query query=new TermRangeQuery(polje,terminStartRef,terminEndRef,true, true);
		
		//poslacemo ga u nasu klasu za izvrsavanje pretrazivanja i print rezultata
		ResultRetriever rr=new ResultRetriever();
		rr.printSearchResults(query, indexDir);
	}

}
