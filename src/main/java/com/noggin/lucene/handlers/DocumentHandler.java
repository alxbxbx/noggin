package com.noggin.lucene.handlers;

import java.io.File;

import org.apache.lucene.document.Document;

public abstract class DocumentHandler {
	
	/**
	 * Od prosledjene datoteke se konstruise Lucene Document
	 * 
	 * @param file
	 *            datoteka u kojoj se nalaze informacije
	 * @return Lucene Document
	 */
	public abstract Document getDocument(File file);
	public abstract String getText(File file);

}
