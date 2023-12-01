package ee.iti0302.veebiback.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JWTSecurityFilter extends OncePerRequestFilter {

    private final OAuthJWTManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var tokenOptional = getToken(request);
        if (tokenOptional.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        var parsedToken = jwtManager.validate(tokenOptional.get());
        if (parsedToken.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        var securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(buildAuthentication(parsedToken.get()));
        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(HttpServletRequest request) {
        var bearerStr = "Bearer ";
        var headerStr = "Authorization";

        String header = request.getHeader(headerStr);
        if (header == null || !header.startsWith(bearerStr)) {
            return Optional.empty();
        }
        return Optional.of(header.substring(bearerStr.length()));
    }

    private Authentication buildAuthentication(DecodedJWT decodedJWT) {
        return new UsernamePasswordAuthenticationToken(
                decodedJWT.getClaim("personId"),
                "",
                List.of(new SimpleGrantedAuthority("user"))
        );
    }
}
