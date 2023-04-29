package com.gl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="roles")
@Data
public class Role {
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
	@Column(name="role_name")
private String name;
	
}
