package com.noggin.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noggin.models.File;

@RestController
public class FileController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/file")
	public File file(@RequestParam(value="name", defaultValue="World") String name){
		return new File((int) counter.incrementAndGet(),
                String.format(template, name), "Come again!");
		
	}

}
