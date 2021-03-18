package com.syntheticfeelingss.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@Embeddable
public class Name {

    private String title;
    private String first;
    private String last;
}
