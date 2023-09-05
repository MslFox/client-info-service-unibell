package com.mslfox.unibell.controllers;

import com.mslfox.unibell.dto.ClientNameDTO;
import com.mslfox.unibell.services.impl.ClientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@Tag(name = "Client manager")
public class ClientController {
    private ClientServiceImpl clientService;

    @Operation(description = "Returns list of all clients name in DB")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ClientNameDTO.class)))
    @GetMapping("/api/clients")
    public List<ClientNameDTO> getClients() {
        return clientService.getClients();
    }

    @Operation(description = "Saves the client's name in DB, if this client's name is not represented in DB")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE))
    @PostMapping("/api/clients")
    public String createClient(@RequestBody @Valid ClientNameDTO clientNameDTO) {
        clientService.saveClient(clientNameDTO);
        return "Success client info saving";
    }

    @Operation(description = "Returns client name by id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ClientNameDTO.class)))
    @GetMapping(value = "/api/clients/{id}")
    public ClientNameDTO getClientNameById(@PathVariable long id) {
        return clientService.getClientNameById(id);
    }
}
