package com.example.casestudy.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Entity
@Getter
@Setter
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(unique = true, name = "user_login_id")
    private String loginId;

    @Column(name = "user_pass")
    private String password;

    @Column(name = "token")
    private String token;

    @Column(columnDefinition = "BOOLEAN DEFAULT 'false'", name = "is_user_admin")
    private Boolean isAdmin;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    @JsonSerialize(using = DateSerializer.class)
    Date createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date", nullable = false)
    @JsonSerialize(using = DateSerializer.class)
    Date lastModifiedDate;


}
