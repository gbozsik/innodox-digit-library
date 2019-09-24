package com.innodox.library.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "ROLE", schema = "public")
@SequenceGenerator(name = "default_gen", sequenceName = "SEQ_ROLE")
public class Role extends BaseEntity {

    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;


}
