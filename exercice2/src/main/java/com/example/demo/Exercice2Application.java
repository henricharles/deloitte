package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Exercice2Application {
	//list keeping track ofthe masked alphabet character
	static final Logger logger=Logger.getLogger(Exercice2Application.class);
static List<String> maskedlist=new ArrayList<String>();
	public static void main(String[] args) {
		//Log4j 
		
		SpringApplication.run(Exercice2Application.class, args);
		
		List<String>masinInputList=new  ArrayList<String>();
		
		masinInputList.add("myabctest");
		masinInputList.add("myabctest12");
		masinInputList.add("myabctest");
		masinInputList.add("my12kbctest");
		masinInputList.add("myapctest");
		masinInputList.add("mynewtestqa12");
		System.out.println(maskerMethod(masinInputList));
		
		
	}
	
	//method to compute the task return a map of masked string with key the original stings
	public static  Map<String,String> maskerMethod(List<String> strings)
	{
		Map<String,String> maskedMap=new HashMap<String,String>();
		int l,p=2;
		logger.info("entering in the masked method");// log4j 
		if(strings.size()==0)
			logger.warn("String list is empty"); // warring for empty list
		for(String s1:strings)
		{
			if(s1.length()<3)
			{
				logger.warn("string lengh should be more than 3");
				
			}
			boolean t=true;
			StringBuffer maskb=new StringBuffer("");
		
			l=s1.length();
			maskb.append(s1.substring(0,1));
			for(int i=0;i<s1.length()-3;i++)
				maskb.append("*");
			maskb.append(s1.substring(l-2,l));
			//block of code to mask a string
			while(t)
			{
				String masked="";
				for(int j=0;j<maskb.toString().length();j++)
					if(maskb.toString().charAt(j)!='*')
					masked=masked+maskb.toString().charAt(j);
					
			if(ismasked(masked))
			{
				maskedMap.put(s1, maskb.toString());
				maskedlist.add(masked);
				logger.info(s1+"===>"+maskb.toString()+ " added to the map");
				
				t=false;
			}
			else{
				maskb.replace(p, p+1, s1.charAt(p)+"");
				p=p+2;
			}
			
			}
		}
		return maskedMap;	
			
		}
	// helper method to check is the marked string is already masked 
	static boolean ismasked(String s)
	{
		logger.info("checking if "+ s + " is already Mamrked");
		StringBuffer s1=new StringBuffer("");
		for(int i=0;i<s.length();i++ )
			s1.append(s.charAt(i));
		if(!maskedlist.contains(s1.toString()))
			return true;
		else 
			return false;
	}
	}
