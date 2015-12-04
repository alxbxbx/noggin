package com.noggin.dao;

import com.noggin.models.File;

public interface FileDaoInterface {
	
	public void addFile(File file);
	public void removeFile(Integer id);
	public void updateFile(File file);
	public File getFile(Integer id);


}
