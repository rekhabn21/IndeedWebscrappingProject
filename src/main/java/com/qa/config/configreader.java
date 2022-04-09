package com.qa.config;
import java.util.*;
import java.io.*;
public class configreader {
		
		public static void main(String[] args)throws Exception{
		FileReader reader=new FileReader("config.properties");
		Properties p=new Properties();
		p.load(reader);
	}

}
