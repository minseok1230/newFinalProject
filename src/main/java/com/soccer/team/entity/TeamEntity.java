package com.soccer.team.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="team")
@Getter
@Entity
public class TeamEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@Column(name = "leaderId")
	private int leaderId;
	private String introduce;
	
	@Column(name = "profileImagePath")
	private String profileImagePath;
	private String skill;
	private Integer rating;
	
	@Column(name = "activeArea")
	private String activeArea;
	
	@UpdateTimestamp
	@Column(name = "createdAt", updatable = false)
	private ZonedDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updatedAt")
	private ZonedDateTime updatedAt;

}
