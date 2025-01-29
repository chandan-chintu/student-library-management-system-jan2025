package com.demo.example.student_library_management_system.model;

import com.demo.example.student_library_management_system.enums.Category;
import jakarta.persistence.*;

@Entity
@Table(name="book")
public class Book {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="pages", nullable = false)
    private int pages;

    @Column(name="publisher_name", nullable = false)
    private String publisherName;

    @Column(name="published_date")
    private String publishedDate;

    @Column(name="category", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(name="availability", nullable = false)
    private boolean availability;

    @Column(name="rack_no", nullable = false)
    private String rackNo;
}
