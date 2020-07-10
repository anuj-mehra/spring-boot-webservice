package com.anuj.springrestservice.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anuj.springrestservice.helloworld.HelloWorldBean;

//@Controller --> We use RestController instead.
@RestController
@RequestMapping(path = "/hello-world")
public class HelloWorldController {

	// GET
	// URI- /helloWorld
	// method - "hello world"
	@RequestMapping(method=RequestMethod.GET)
	public String helloWorld(){
		return "hello-world";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/bean")
	public HelloWorldBean helloWorldReturnBean(){
		return new HelloWorldBean("hello-world-bean");
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/bean/uppercase/{name}")
	public HelloWorldBean helloWorldReturnBean(@PathVariable String name){
		return new HelloWorldBean(name.toUpperCase());
	} 
}
