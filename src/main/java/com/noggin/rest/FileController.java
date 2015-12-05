package com.noggin.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noggin.models.File;

@RestController
@RequestMapping("file")
public class FileController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	File file = new File();
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
	public File file(@PathVariable String name){	
		file.setFileName(name);
		file.setMIME("New Mime");
		file.setId(1);
		
		return file;
		
	}

}
