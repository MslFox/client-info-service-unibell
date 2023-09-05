package com.mslfox.unibell.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "contact", description = "Client id, type, value must be unique.")
public class ContactWithIdDTO extends ContactDTO {
    @Min(1)
    private long clientId;
}

