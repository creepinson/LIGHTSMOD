package me.creepinson.lib.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

//MAIN MOD STUFF
    public static final String MODID = "lightson";
    public static final String VERSION = "1.0.0";
 
    public static final String SEVERPROXY = "me.creepinson.lib.proxy.CommonProxy";
    public static final String CLIENTPROXY = "me.creepinson.lib.proxy.ClientProxy";
    
//END MAIN MOD STUFF
	
	private static Logger logger;
	
	public static Logger getLogger(){
		
		if(logger == null){
			logger = LogManager.getFormatterLogger(MODID);
		}
		
		return logger;
		
	}
    
    
}
