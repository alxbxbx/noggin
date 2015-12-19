package com.noggin.lucene.model;


public class SearchType {
	public enum Type {
		regular,
		fuzzy,
		phrase,
		range,
		prefix
	}
	
	/**
	 * please, handle with care. order of messages should match the order in type enumeration 
	 */
	private static final String[] MESSAGES = {
		"Regular",
		"Fuzzy",
		"Phrase",
		"Range",
		"Prefix"
	};
	
	public static String getMessage(Type type){
		return MESSAGES[type.ordinal()];
	}
	
	public static Type getType(String type){
		for(int i = 0; i < MESSAGES.length; i++){
			if(MESSAGES[i].equals(type)){
				return Type.values()[i];
			}
		}
		return null;
	}
	
	public static String[] getMessages(){
		return MESSAGES;
	}

}
