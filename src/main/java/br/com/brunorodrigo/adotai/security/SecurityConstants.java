package br.com.brunorodrigo.adotai.security;

import java.math.BigInteger;

public class SecurityConstants {
    public static final String LOGIN_URL = "/api/v1/login";
    public static final String PETS_URL = "/api/v1/pets";
    public static final String USERS_URL = "/api/v1/users";
    public static final String BEARER = "Bearer ";
    public static final String JWT_SECRET = "changeit";
    public static final String ROLES = "roles";

    public static final long ACCESS_TOKEN_TIMING = 24 * 60 * 60 * 1000;

    public static final String USER_ROLE = "USER_ROLE";
    public static final String ADMIN_ROLE = "ADMIN_ROLE";
}
