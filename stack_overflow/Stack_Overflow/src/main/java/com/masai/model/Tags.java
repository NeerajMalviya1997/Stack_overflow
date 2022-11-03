package com.masai.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Tags {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tagsId;
	private String tagName;
	private String tagDescription;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Questions> questions;
}
