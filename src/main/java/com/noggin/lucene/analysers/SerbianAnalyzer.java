package com.noggin.lucene.analysers;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

import com.noggin.lucene.filters.CyrilicToLatinFilter;

public class SerbianAnalyzer extends Analyzer{
	/**
     * An array containing some common English words
     * that are usually not useful for searching.
     */
    public static final String[] STOP_WORDS =
    {
        "i","a","ili","ali","pa","te","da","u","po","na"
    };

    /**
     * Builds an analyzer.
     */
    public SerbianAnalyzer()
    {
    }


	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
		Tokenizer source = new StandardTokenizer(Version.LUCENE_40,reader);
		TokenStream result = new CyrilicToLatinFilter(source);
	    result = new LowerCaseFilter(Version.LUCENE_40, result);
	    result = new StopFilter(Version.LUCENE_40,result,StopFilter.makeStopSet(Version.LUCENE_40, STOP_WORDS));
		return new TokenStreamComponents(source, result);
	}


}
