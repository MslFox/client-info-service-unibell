package com.mslfox.unibell.controllers;

import com.mslfox.unibell.common.enums.ContactType;
import com.mslfox.unibell.dto.ContactDTO;
import com.mslfox.unibell.dto.ContactWithIdDTO;
import com.mslfox.unibell.services.impl.ContactServiceImpl;
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
@Tag(name = "Contact manager")
@AllArgsConstructor
public class ContactController {
    private final ContactServiceImpl contactService;

    @Operation(description = "Returns list of contact by id. If the optional parameter 'type' is specified in the query string, returns list of contacts of the specified type(email/phone)")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ContactDTO.class)))
    @GetMapping("/api/contacts/{id}")
    public List<ContactDTO> getContacts(@PathVariable long id, @RequestParam(name = "type", required = false) String type) {
        return contactService.getContacts(id, ContactType.fromValue(type));
    }

    @Operation(description = "Saves the client's contact in DB, if this contact is not represented in the DB")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE))
    @PostMapping("/api/contacts/")
    public String createContact(@RequestBody @Valid ContactWithIdDTO contactWithIdDTO) {
        contactService.saveContact(contactWithIdDTO);
        return "Success contact saving";
    }
}
