package dev.gideon.maintenance.dynamic.model.entity.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Getter @NoArgsConstructor @Setter
public class Password implements Serializable {
    @EmbeddedId
    private PasswordId passwordId;

    private String passwordHash;

    @JoinColumn(name = "UPDATED_BY", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User updatedBy;
}