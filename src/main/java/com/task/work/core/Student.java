package com.task.work.core;

import com.fasterxml.jackson.annotation.JsonView;
import com.task.work.core.json.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.OnlyId.class)
    private int id;

    @Column(name = "fio")
    @JsonView(Views.ForTable.class)
    private String fio;

    @Column(name = "date_accept")
    @JsonView(Views.ForTable.class)
    private String date_accept;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

}
