package com.youcode.ecommerce.security;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 0;
	public static final long REFRESH_EXPIRATION_TIME = 1000*60*60*24;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/api/users";
	public static final String TOKEN_SECRET = "myTokenSecret";
	
}
