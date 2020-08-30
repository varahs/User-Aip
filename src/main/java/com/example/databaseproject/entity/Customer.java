package com.example.databaseproject.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long requestId; // private Long userId; // datatype changed
	@Setter
	@Getter
	private String executed;
	@Setter
	@Getter
	private Long jiraId; // datatype changed
	@Setter
	@Getter
	private String query;
	@Setter
	@Getter
	private String status;
	@Setter
	@Getter
	private Long releaseId; // datatype changed
	@Setter
	@Getter
	private String createdBy;
	@Setter
	@Getter
	private LocalDateTime approvedDate; // datatype changed
	@Setter
	@Getter
	private String approvedBy;
	@Setter
	@Getter
	private String comment;
	@Setter
	@Getter
	private Long intakeId; // datatype changed
	@Setter
	@Getter
	private LocalDateTime requestDate; // datatype changed
	@Setter
	@Getter
	private String path;
	@Setter
	@Getter
	private LocalDateTime createdTimestamp; // newly added
	@Setter
	@Getter
	private LocalDateTime updatedtimestamp; // newly added

}
