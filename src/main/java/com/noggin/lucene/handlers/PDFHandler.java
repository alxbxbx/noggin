package com.noggin.lucene.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;



public class PDFHandler extends DocumentHandler {
	@Override
	public Document getDocument(File file) {
		Document doc = new Document();
		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			String text = getText(parser);
			doc.add(new TextField("text", text, Store.NO));

			// metadata extraction
			PDDocument pdf = parser.getPDDocument();
			PDDocumentInformation info = pdf.getDocumentInformation();

			String title = ""+info.getTitle();
			doc.add(new TextField("title", title, Store.YES));

			String keywords = ""+info.getKeywords();
			String[] splittedKeywords = keywords.split(" ");
			for (String keyword : splittedKeywords) {
				doc.add(new TextField("keyword", keyword, Store.YES));
			}
			
			doc.add(new StringField("filename", file.getCanonicalPath(),
					Store.YES));
			
			pdf.close();
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}

		return doc;
	}
	public Document changeKeywords(File file, String keywords) {
		Document doc = new Document();
		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			String text = getText(parser);
			doc.add(new TextField("text", text, Store.YES));

			// metadata extraction
			PDDocument pdf = parser.getPDDocument();
			PDDocumentInformation info = pdf.getDocumentInformation();

			String title = ""+info.getTitle();
			doc.add(new TextField("title", title, Store.YES));

			doc.add(new TextField("keyword", keywords, Store.YES));
			
			doc.add(new StringField("filename", file.getCanonicalPath(),
					Store.YES));
			
			pdf.close();
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}

		return doc;
	}
	

	@Override
	public String getText(File file) {
		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			PDFTextStripper textStripper = new PDFTextStripper("utf-8");
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}
	
	public String getText(PDFParser parser) {
		try {
			PDFTextStripper textStripper = new PDFTextStripper("utf-8");
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}


}
