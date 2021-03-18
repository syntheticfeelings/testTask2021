package com.syntheticfeelingss.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Name name;

}
