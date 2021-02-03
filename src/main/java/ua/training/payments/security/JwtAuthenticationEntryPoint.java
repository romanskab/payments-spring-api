package ua.training.payments.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ua.training.payments.model.payload.ErrorResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.error("commence: responding with unauthorized error. Message - {}", e.getMessage());

        ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        OutputStream out = httpServletResponse.getOutputStream();

        ErrorResponse errorResponse = new ErrorResponse(
                401, "Security error", "Not authorized");
        objectWriter.writeValue(out, errorResponse);
        out.flush();
    }

}
