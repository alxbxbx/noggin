package com.noggin.lucene.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class TestIndexer {

	public static void main(String[] args) throws Exception {
		Directory indexDir;
		File dataDir;
		if (args.length != 2) {
			try{
				ResourceBundle rb=ResourceBundle.getBundle("rs.ac.uns.ftn.informatika.ues.lucene.test.luceneindex");
				indexDir=new SimpleFSDirectory(new File(rb.getString("indexDir")));
				dataDir=new File(rb.getString("dataDir"));
			}catch(Exception e1){
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + TestIndexer.class.getName()+ " <index dir> <data dir> or properties file needed");
			}
		}else{
			indexDir = new SimpleFSDirectory(new File(args[0]));
			dataDir = new File(args[1]);
		}
		long start = new Date().getTime();
		int numIndexed = index(indexDir, dataDir);
		long end = new Date().getTime();
		System.out.println("Indexing " + numIndexed + " files took "
		+ (end - start) + " milliseconds");
	}
	
	// open an index and start file directory traversal
	
	public static int index(Directory indexDir, File dataDir) throws IOException {
		if (!dataDir.exists() || !dataDir.isDirectory()) {
			throw new IOException(dataDir + " does not exist or is not a directory");
		}
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, new StandardAnalyzer(Version.LUCENE_40));
		iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
		IndexWriter writer = new IndexWriter(indexDir, iwc);
		indexDirectory(writer, dataDir);
		int numIndexed = writer.numDocs();
		writer.close();
		return numIndexed;
	}
	// recursive method that calls itself when it finds a directory
	private static void indexDirectory(IndexWriter writer, File dir) throws IOException {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(writer, f);
			} else if (f.getName().endsWith(".txt")) {
				indexFile(writer, f);
			}
		}
	}
	
	// method to actually index a file using Lucene
	private static void indexFile(IndexWriter writer, File f)throws IOException {
		if (f.isHidden() || !f.exists() || !f.canRead()) {
			return;
		}
		System.out.println("Indexing " + f.getCanonicalPath());
		Document doc = new Document();
		InputStreamReader ir=new InputStreamReader(new FileInputStream(f),"UTF-8");
		BufferedReader br=new BufferedReader(ir);
		String naslov=br.readLine();
		System.out.println("Naslov: "+naslov);
		doc.add(new TextField("naslov", naslov, Store.YES));
		doc.add(new TextField("sadrzaj", br));
		String modificationDate=DateTools.dateToString(new Date(f.lastModified()),DateTools.Resolution.DAY);
		doc.add(new StringField("filename", f.getCanonicalPath(), Store.YES));
		doc.add(new TextField("filedate",modificationDate,Store.YES));
		writer.addDocument(doc);
		br.close();
	}
}
