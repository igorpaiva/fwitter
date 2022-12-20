package com.fwitter.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleId;
    private String authority;

    public Role(Integer roleId, String authority) {
        this.setRoleId(roleId);
        this.setAuthority(authority);
    }

    public Role() {

    }
}
