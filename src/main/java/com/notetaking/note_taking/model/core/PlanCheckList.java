package com.notetaking.note_taking.model.core;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "plan_check_list")
public class PlanCheckList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    private String item;
    @Column(name = "is_done", nullable = false)
    private String isDone;
}
