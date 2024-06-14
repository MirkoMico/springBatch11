package com.example.MS3.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the jobTest database table.
 */
@Entity
@NamedQuery(name = "JobTest.findAll", query = "SELECT j FROM JobTest j")
@Table(name = "job_test")
public class JobTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int jobTestId;

    private String processId;

    //private String frequenza;

   // private String tipo;



    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDateTime;

    private String responseMessage;

    private short attempt;

    public JobTest() {
        this.attempt = 0;
    }


    public int getJobTestId() {
        return this.jobTestId;
    }

    public void setJobTestId(int jobTestId) {
        this.jobTestId = jobTestId;
    }

    public String getProcessId() {
        return this.processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Date getResponseDateTime() {
        return this.responseDateTime;
    }

    public void setResponseDateTime(Date responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public short getAttempt() {
        return attempt;
    }

    public void setAttempt(short attempt) {
        this.attempt = attempt;
    }


}