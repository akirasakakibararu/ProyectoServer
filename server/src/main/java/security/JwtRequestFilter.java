package security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		System.out.println("Interceptando la peticion: " + request.getRequestURI());

		String authorizationHeader = request.getHeader("Authorization");

		String token = null;
		String username = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(token);
			System.out.println("Token recibido: " + token);
			System.out.println("Usuario extraido del token: " + username);
		} else {
			System.out.println("No se encontro un token valido en la peticion.");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtUtil.validateToken(token, userDetails)) {
				System.out.println("Token valido, autenticando al usuario...");
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			} else {
				System.out.println("Token invalido.");
			}
		}

		chain.doFilter(request, response);
	}
}