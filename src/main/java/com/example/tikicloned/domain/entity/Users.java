package com.example.tikicloned.domain.entity;

import com.example.tikicloned.domain.converter.UserProviderConverter;
import com.example.tikicloned.domain.converter.UserStatusConverter;
import com.example.tikicloned.domain.enums.UserProvider;
import com.example.tikicloned.domain.enums.UserStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users extends AbstractEntity implements Serializable{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", unique = true, nullable = false, length = 36)
    private UUID id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "provider")
    @Convert(converter = UserProviderConverter.class)
    private UserProvider provider;
    @Column(name = "status")
    @Convert(converter = UserStatusConverter.class)
    private UserStatus status;

}
