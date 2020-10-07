package com.task.work.core;

import com.fasterxml.jackson.annotation.JsonView;
import com.task.work.core.json.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.OnlyId.class)
    private int Id;

    @Column(name = "number")
    @JsonView(Views.ForTable.class)
    private String number;

    @Column(name = "numerus_students")
    @JsonView(Views.ForTable.class)
    private int numerus_students = 0;


}
