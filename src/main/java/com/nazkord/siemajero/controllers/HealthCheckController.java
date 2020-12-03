package com.nazkord.siemajero.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthCheck")
public class HealthCheckController {

    public static final String HEALTH_CHECK = "/healthCheck";
    private static final String HEALTH_CHECK_RESPONSE = "OK\n";
    private static final String HEALTH_CHECK_RESPONSE_AUTHORIZED = "OK! You're an authorized user\n";

    @RequestMapping(method = RequestMethod.GET)
    public String healthCheck() {
        return HEALTH_CHECK_RESPONSE;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/authorized")
    public String healthCheckAuthorized() {
        return HEALTH_CHECK_RESPONSE_AUTHORIZED;
    }
}
