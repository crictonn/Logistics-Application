package com.cherkas.vladimir.logisticstransportations.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtCore {

    @Value("$logistics.app.secret")
    private String secret;

    @Value("$logistics.app.lifetime")
    private int lifetime;

    public String generateToken(){

        return ;
    }
}
