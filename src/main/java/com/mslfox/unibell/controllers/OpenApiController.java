package com.mslfox.unibell.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Tag(name = "OpenApi manager")
public class OpenApiController {
    @Operation(description = "Returns OpenApi specification")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE))
    @GetMapping("/openapi.yaml")
    public ResponseEntity<String> getOpenApiYaml() throws IOException {
        try (FileInputStream fis = new FileInputStream("openapi.yaml")) {
            final var yamlContent = new String(fis.readAllBytes(), StandardCharsets.UTF_8);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(yamlContent);
        }
    }
}
