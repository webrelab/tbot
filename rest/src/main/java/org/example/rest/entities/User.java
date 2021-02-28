package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String userId;
    private String chatId;
    private String role;
    private String cityId;

    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "users"
    )
    private Set<FormedGroup> formedGroups = new HashSet<>();

    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "users"
    )
    private Set<FunctionalRole> functionalRoles = new HashSet<>();

    public User(
            final String chatId, final String role,
            final String cityId
    ) {
        this.chatId = chatId;
        this.role = role;
        this.cityId = cityId;
    }
}
