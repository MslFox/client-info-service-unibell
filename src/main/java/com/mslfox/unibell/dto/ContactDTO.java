package com.mslfox.unibell.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.mslfox.unibell.common.enums.ContactType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "contact", description = "Simple contact info.")
public class ContactDTO {
    @NotNull
    private ContactType type;
    @NotBlank
    private String value;
    @JsonCreator
    public void setType(String type){
        this.type = ContactType.fromValue(type).get();
    }
}
