package com.mslfox.unibell.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "client", description = "Client name must be unique")
public class ClientNameDTO {
    private long id;
    @NotBlank
    private String name;
}
