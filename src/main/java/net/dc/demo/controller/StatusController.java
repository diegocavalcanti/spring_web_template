package net.dc.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;


/**
 * Created by diego.cavalcanti on 2/10/2023.
 */

@RestController
@RequestMapping("/")
public class StatusController {

    @GetMapping
    public String status() throws UnknownHostException {
        return "Demo App UP - version 1.0.1 - " + Instant.now() + " Servidor [" + InetAddress.getLocalHost() +"]";
    }
}
