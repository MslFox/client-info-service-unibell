package com.mslfox.unibell.entities;

import com.mslfox.unibell.common.enums.ContactType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contacts",
        uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "type", "value"}))
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ContactType type;
    @NotBlank
    private String value;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;
}
