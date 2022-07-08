package microservices.demo.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import microservices.demo.exception.JwtTokenException;

@Component
public class JwtTokenUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	public Claims getClaims(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		return null;
	}

	public void validateToken(final String token) throws JwtTokenException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new JwtTokenException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenException("JWT claims string is empty.");
		}
	}
}