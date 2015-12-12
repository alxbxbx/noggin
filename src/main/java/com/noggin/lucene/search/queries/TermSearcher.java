package com.noggin.lucene.search.queries;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import com.noggin.lucene.search.ResultRetriever;
import com.noggin.lucene.test.TestIndexer;
public class TermSearcher {
	
	public static void main(String[] args) throws Exception{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		File indexDir;
		String termin;
		String polje;
		if (args.length != 3) {
			try{
				ResourceBundle rb=ResourceBundle.getBundle("rs.ac.uns.ftn.informatika.ues.lucene.test.luceneindex");
				indexDir=new File(rb.getString("indexDir"));
				System.out.println("unesite polje za pretragu:");
				polje=in.readLine();
				System.out.println("unesite izraz za pretragu:");
				termin=in.readLine();
			}catch(Exception e1){
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + TestIndexer.class.getName()+ " <indexDir> <naziv polja> <trazeni termin> or properties file needed");
			}
		}else{
			indexDir = new File(args[0]);
			polje = args[1];
		 	termin = args[2];
		}
		if (!indexDir.exists() || !indexDir.isDirectory()) {
			throw new Exception(indexDir +" does not exist or is not a directory.");
		}else{
			Term t=new Term(polje,termin);
			Query query=new TermQuery(t);
			ResultRetriever rr=new ResultRetriever();
			rr.printSearchResults(query, indexDir);
		}
	}

}
