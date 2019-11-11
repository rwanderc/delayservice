package com.wandercosta.delay;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DelayApplication
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DelayApplication.class);


    public static void main(String[] args)
    {
        SpringApplication.run(DelayApplication.class, args);
    }


    @GetMapping("/{delay}/**")
    DelayResponse get(
        @PathVariable final long delay,
        @RequestHeader final Map<String, String> headers,
        final HttpServletRequest request) throws InterruptedException
    {
        long realDelay = System.currentTimeMillis();
        Thread.sleep(delay);
        realDelay = -realDelay + System.currentTimeMillis();
        LOGGER.info("Request: {}?{}", request.getRequestURL(), request.getQueryString());
        LOGGER.info("Requested Headers: {}", headers);
        return new DelayResponse(delay, realDelay);
    }


    @PostMapping("/{delay}/**")
    DelayResponse post(
        @PathVariable final long delay,
        @RequestHeader final Map<String, String> headers,
        @RequestBody final String body,
        HttpServletRequest request) throws InterruptedException
    {
        long realDelay = System.currentTimeMillis();
        Thread.sleep(delay);
        realDelay = -realDelay + System.currentTimeMillis();
        LOGGER.info("Request: {}?{}", request.getRequestURL(), request.getQueryString());
        LOGGER.info("Requested Headers: {}", headers);
        LOGGER.info("Body: {}", body);
        return new DelayResponse(delay, realDelay);
    }


    @Getter
    @RequiredArgsConstructor
    private static class DelayResponse
    {
        private final String message = "OK";
        private final long requestedDelay;
        private final long realDelay;
    }

}