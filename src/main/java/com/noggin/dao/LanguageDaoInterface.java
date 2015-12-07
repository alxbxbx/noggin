package com.noggin.dao;

import java.util.List;

import com.noggin.models.Language;

public interface LanguageDaoInterface {
	
	public void addLanguage(Language language);
	public void removeLanguage(Integer id);
	public void updateLanguage(Language language);
	public Language getLanguage(Integer id);
	public List<Language> getAllLanguages();

}
