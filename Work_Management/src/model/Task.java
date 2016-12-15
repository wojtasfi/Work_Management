package model;

import java.io.File;
import java.sql.Date;
import java.sql.Time;

import javafx.beans.property.SimpleStringProperty;

public class Task  {
	
	private int id;
	private String task_name;
	private String preparer;
	private String reviewer;
	private Date deadline;
	private Time deadline_hour;
	private int minutes;
	private String task_type;
	private String frequency;
	
	
	private String comment;
	private File attachment;
	private float prc_status;
	private boolean review_required;
	private boolean done;
	private String orderer;
	private String category_1;
	private boolean reviewed;
	

	public Task(int id, String task_name, String preparer, String reviewer, Date deadline, Time deadline_hour,
			int minutes, String task_type, String frequency, String comment, File attachment, float prc_status,
			boolean review_required, boolean done, String orderer, String category_1, boolean reviewed) {
		this.id = id;
		this.task_name = task_name;
		this.preparer = preparer;
		this.reviewer = reviewer;
		this.deadline = deadline;
		this.deadline_hour = deadline_hour;
		this.minutes = minutes;
		this.task_type = task_type;
		this.frequency = frequency;
		this.comment = comment;
		this.attachment = attachment;
		this.prc_status = prc_status;
		this.review_required = review_required;
		this.done = done;
		this.orderer = orderer;
		this.category_1 = category_1;
		this.reviewed = reviewed;
	}


	public Task() {
		// TODO Auto-generated constructor stub
	}


	public String getOrderer() {
		return orderer;
	}


	public String getCategory_1() {
		return category_1;
	}


	public int getId() {
		return id;
	}


	public String getTask_name() {
		return task_name;
	}


	public String getPreparer() {
		return preparer;
	}


	public String getReviewer() {
		return reviewer;
	}


	public Date getDeadline() {
		return deadline;
	}


	public Time getDeadline_hour() {
		return deadline_hour;
	}


	public int getMinutes() {
		return minutes;
	}


	public String getTask_type() {
		return task_type;
	}


	public String getFrequency() {
		return frequency;
	}


	public String getComment() {
		return comment;
	}


	public File getAttachment() {
		return attachment;
	}


	public float getPrc_status() {
		return prc_status;
	}


	public boolean isReview_required() {
		return review_required;
	}


	public boolean isDone() {
		return done;
	}


	public boolean getReviewed() {
		// TODO Auto-generated method stub
		return reviewed;
	}

}
