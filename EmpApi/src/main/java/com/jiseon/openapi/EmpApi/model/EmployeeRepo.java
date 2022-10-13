package com.jiseon.openapi.EmpApi.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "employee")
@DynamicInsert
@DynamicUpdate
public class EmployeeRepo {
    @Id
    @Column(nullable = true)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String mobile;

    @Column(nullable = false, length = 100)
    private String department;

    @Builder
    public EmployeeRepo(Integer id, String name, String mobile, String department) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.department = department;
    }
}
