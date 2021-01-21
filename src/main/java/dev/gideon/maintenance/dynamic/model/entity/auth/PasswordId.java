package dev.gideon.maintenance.dynamic.model.entity.auth;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.ZonedDateTime;

@AllArgsConstructor @NoArgsConstructor @Setter
@Embeddable
public class PasswordId implements Serializable {
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    private ZonedDateTime updatedDate;
}
