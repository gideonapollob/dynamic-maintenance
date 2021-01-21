package dev.gideon.maintenance.dynamic.model.entity.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Entity @Getter @NoArgsConstructor @Setter
public class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private BigInteger id;

    private String username;

    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User createdBy;

    private ZonedDateTime createdDate;
}
