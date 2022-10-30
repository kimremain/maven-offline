package com.kimremain.mavenoffline.common.util;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {

    private final static String TOPLINE   = "┌──────────────────────────────";
    private final static String MSGLINE   = "│ ";
    private final static String UNDERLINE = "└──────────────────────────────";
    private final static String NEWLINE   = "\r\n";
    private final static String CIRCLE = "●";

    public static void debugHighlight(String... messages) {
        log.debug(assemblyHighlight(messages));
    }
    public static void infoHighlight(String... messages) {
        log.info(assemblyHighlight(messages));
    }
    public static void warnHighlight(String... messages) {
        log.warn(assemblyHighlight(messages));
    }
    public static void errorHighlight(String... messages) {
        log.error(assemblyHighlight(messages));
    }

    /*Do not use for general logging*/
    public static void printConsole(String... messages){
        System.out.print(assemblyHighlight(messages));
    }

    public static String assemblyHighlight(String... messages){
        StringBuilder out = new StringBuilder();
        out.append(NEWLINE);
        out.append(TOPLINE).append(NEWLINE);
        if(messages != null) {
        	for(String msg : messages){
        		if(msg != null) {
        			out.append(MSGLINE).append(msg).append(NEWLINE);
        		}
        	}
        }else {
        	out.append(MSGLINE).append("Logging Argument Is Null").append(NEWLINE);
        }
        out.append(UNDERLINE).append(NEWLINE);
        return out.toString();
    }

    public static void debugTitle(String msg) { log.debug(assemblyTitle(msg)); }
    public static void infoTitle(String msg) {
        log.info(assemblyTitle(msg));
    }
    public static void warnTitle(String msg) {
        log.warn(assemblyTitle(msg));
    }
    public static void errorTitle(String msg) {
        log.error(assemblyTitle(msg));
    }

    public static String assemblyTitle(String msg){
        return new StringBuilder().append(CIRCLE).append(msg).append(CIRCLE).toString();
    }
    
    public static void printComplete() {
    	printConsole("", "Settings Complete", "Enjoy Your Development", "");
    }
    
    public static void printScannedBeans(ApplicationContext applicationContext) {
    	printConsole("Application Scanned bean");
    	printConsole(applicationContext.getBeanDefinitionNames());
    }
    
    public static String getObjectName(Object param) {
    	String result = "";
    	if(param != null && param.getClass() != null) {
    		result = param.getClass().getSimpleName();
    	}
    	return result;
    }
    
    public static void debugHighlightObject(Object param) {
    	log.debug(assemblyHighlight(assemblyHighlightObject(param, getObjectName(param))));
    }    
    
    public static void debugHighlightObject(Object param, String title) {
    	log.debug(assemblyHighlight(assemblyHighlightObject(param, title)));
    }        
    
    public static void infoHighlightObject(Object param) {
    	log.info(assemblyHighlight(assemblyHighlightObject(param, getObjectName(param))));
    }
    
    public static void infoHighlightObject(Object param, String title) {
    	log.info(assemblyHighlight(assemblyHighlightObject(param, title)));
    }    
    
    public static String[] assemblyHighlightObject(Object param, String title){
        try{
        	List<String> list = new ArrayList<String>();
        	list.add(CIRCLE + title + CIRCLE);        	
        	if(param.getClass() == Map.class 
        			|| param.getClass() == HashMap.class 
        			|| param.getClass() == LinkedHashMap.class
        			) {
        		@SuppressWarnings("unchecked")
        		Map<String, Object> map = (Map<String, Object>) param;
        		for (Map.Entry<String, Object> entry : map.entrySet()) {
        		    list.add(entry.getKey()+"->["+entry.getValue()+"]");
        		}
        	}else {
                for (Field field : param.getClass().getDeclaredFields()){
                    field.setAccessible(true);
                    Object value=field.get(param);
                    list.add(field.getName()+"->["+value+"]");
                }        		
        	}
            return list.toArray(new String[0]);
        }catch (Exception e){
            //e.printStackTrace();
    		return null;                
        }
    } 
    
    public static void debugCurrentMethod() {
    	debugTitle(getCallerMethodName());
    }
    
    public static void infoCurrentMethod() {
    	infoTitle(getCallerMethodName());
    }
    
    
    public static String getCurrentMethodName() {
        return StackWalker.getInstance()
                          .walk(s -> s.skip(1).findFirst())
                          .get()
                          .getMethodName();
    }

    public static String getCallerMethodName() {
        return StackWalker.getInstance()
                          .walk(s -> s.skip(2).findFirst())
                          .get()
                          .getMethodName();
    }
}
