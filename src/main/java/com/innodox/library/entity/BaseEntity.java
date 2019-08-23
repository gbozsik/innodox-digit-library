package com.innodox.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * Minden entitás/DB tábla kiterjeszti és innen veszik az id-t
 */

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Version
    protected Long version;

    @Column(name = "CREATED_BY", length = 10)
    protected String createdBy;

    @Column(name = "CREATED_AT")
    protected LocalDateTime createdAt;

    @Column(name = "MDIFIED_BY")
    protected String modifiedBy;

    @Column(name = "MODIFIED_AT")
    protected LocalDateTime modifiedAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    private void prePersist() {
//        setCreatedAt(LocalDateTime.now());
        setCreatedAt(LocalDateTime.now());


//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (Objects.nonNull(userName)) {
//            setCreatedBy(userName);
//        }
        preUpdate();
    }

    @PreUpdate
    private void preUpdate() {
//        setModifiedAt(LocalDateTime.now());
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (Objects.nonNull(userName)) {
//            setModifiedBy(userName);
//        }
    }

}
