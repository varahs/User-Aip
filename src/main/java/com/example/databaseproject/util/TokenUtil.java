package com.example.databaseproject.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

@Component
public class TokenUtil {

	@Value("${secret.key}")
	private static String TOKEN_SECRET;

	/*
	 * To generate token throws runtime exception
	 * 
	 * @param accepts long id
	 */
	public static String generateToken(Long id) {
		// type of alogrithm
		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
		// generating token based on id
		String token = JWT.create().withClaim("ID", id).sign(algorithm);
		return token;

	}

	/*
	 * to verify the given token for user say perform further operations after login
	 * 
	 * @return long id
	 */
	public static Long verifyToken(String token) {

		Long id;

		Verification verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));

		JWTVerifier jwtVerifier = verification.build();

		DecodedJWT decodedJWT = jwtVerifier.verify(token);

		Claim claim = decodedJWT.getClaim("ID");

		id = claim.asLong();

		return id;

	}
}
