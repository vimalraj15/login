package com.example.demo;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Restcontroller {
	

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = {"/login-user"}, method = RequestMethod.POST)	
	public ModelAndView login(@RequestParam String uname,@RequestParam String psw) throws SQLException 
	{
		
		
		 try
		 {
			
		 String sql = "SELECT name FROM up WHERE name=?";

		 String username  = (String) jdbcTemplate.queryForObject(
		            sql, new Object[] { uname }, String.class);
		
		 String sqll = "SELECT password FROM up WHERE password=?";
		 
		 String password = (String) jdbcTemplate.queryForObject(
		            sqll, new Object[] { psw }, String.class);
		 
			    
	    if(username.equals(uname)  && password.equals(psw))
	    	
	    {
	    	 //ModelAndView next = new ModelAndView("DAshboardjsp");
		 	 ModelAndView next = new ModelAndView();	 
		
		 	 
		 	 
		 	 next.addObject("vimal","Welcome " + uname);
		 
		 	 next.setViewName("Sucess");  
			 
		 	 return next; 
	    }
	    else
	    {
		   ModelAndView message = new ModelAndView();
		   String g ="Invalid User name or password...Please check";

		   message.addObject("vimal", g);   
		
           message.setViewName("hi");    
           return message;
	     }
		}
		 catch(Exception e)
		 {
			   ModelAndView message = new ModelAndView();
			   String g ="Invalid Username or Password...please check";
			   message.addObject("vimal", g);   
	           message.setViewName("hi");    
	           return message;	 
		 }
		 
	}
}

