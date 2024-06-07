package com.example.MS3.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the process_stack database table.
 * 
 */
@Entity
@Table(name="process_stack")
@NamedQuery(name="ProcessStack.findAll", query="SELECT p FROM ProcessStack p")
public class ProcessStack implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="processStackId")
	private int processStackId;

	private byte active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEnd;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;

	private String processId;

	public ProcessStack() {
	}

	public int getProcessStackId() {
		return this.processStackId;
	}

	public void setProcessStackId(int processStackId) {
		this.processStackId = processStackId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public Date getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

}