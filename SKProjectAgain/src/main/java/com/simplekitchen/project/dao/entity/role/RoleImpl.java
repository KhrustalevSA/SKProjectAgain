package com.simplekitchen.project.dao.entity.role;

import com.simplekitchen.project.dao.entity.role.api.Role;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleImpl implements Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "roleList")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<UserEntityImpl> users;


}
