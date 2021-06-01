package com.oskarro.empik.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@Table(name = "request")
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "request_count", nullable = false)
    private int counter;

}
