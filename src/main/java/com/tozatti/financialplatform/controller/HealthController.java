package com.tozatti.financialplatform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/v1/status")
    public StatusResponse status() {
        return new StatusResponse(
            "Financial Processing Platform",
            "RUNNING"
        );
    }

    public record StatusResponse(
        String application,
        String status
    ) {}
}