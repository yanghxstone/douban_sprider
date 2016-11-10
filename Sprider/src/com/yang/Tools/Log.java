package com.yang.Tools;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	public static void write(String msg){
		 Logger log = Logger.getLogger("SPRIDER"); 
         log.setLevel(Level.INFO); 
         log.info(msg);
	}
}
