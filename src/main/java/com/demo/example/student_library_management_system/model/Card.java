package com.demo.example.student_library_management_system.model;

import com.demo.example.student_library_management_system.enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="card")
public class Card {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="created_date", nullable = false)
    @CreationTimestamp // it will automatically add the data and time while creating card
    private Date createdDate;

    @Column(name="updated_date")
    @UpdateTimestamp // it will automatically add the data and time while updating card
    private Date updatedDate;

    @Column(name="card_status", nullable = false)
    @Enumerated(value = EnumType.STRING)// this will convert enum value as string while storing in database
    private CardStatus cardStatus;

}
