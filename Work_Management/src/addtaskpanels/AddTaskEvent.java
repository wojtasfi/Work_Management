package addtaskpanels;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EventObject;

import javafx.scene.control.CheckBox;

public class AddTaskEvent {
	
	public String getTask_name() {
		return task_name;
	}


	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}


	public String getPreparer() {
		return preparer;
	}


	public void setPreparer(String preparer) {
		this.preparer = preparer;
	}


	public String getReviewer() {
		return reviewer;
	}


	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}


	public java.sql.Date getDeadline() {
		return deadline;
	}


	public void setDeadline(java.sql.Date deadline) {
		this.deadline = deadline;
	}


	public LocalTime getDeadline_hour() {
		return deadline_hour;
	}


	public void setDeadline_hour(LocalTime deadline_hour) {
		this.deadline_hour = deadline_hour;
	}


	public int getMinutes() {
		return minutes;
	}


	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}


	public String getTask_type() {
		return task_type;
	}


	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}


	public String getFrequency() {
		return frequency;
	}


	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public File getAttachment() {
		return attachment;
	}


	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}


	public float getPrc_status() {
		return prc_status;
	}


	public void setPrc_status(float prc_status) {
		this.prc_status = prc_status;
	}


	public boolean isReview_required() {
		return review_required;
	}


	public void setReview_required(boolean review_required) {
		this.review_required = review_required;
	}


	public boolean isDone() {
		return done;
	}


	public void setDone(boolean done) {
		this.done = done;
	}


	public String getOrderer() {
		return orderer;
	}


	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}


	public String getCategory_1() {
		return category_1;
	}


	public void setCategory_1(String category_1) {
		this.category_1 = category_1;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public AddTaskEvent(String task_name, String preparer, String reviewer, java.sql.Date deadline,
			LocalTime deadline_hour, int minutes, String task_type, String frequency, String comment, File attachment,
			float prc_status, boolean review_required, boolean done, String orderer, String category_1) {
		
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
	}
	
	
	public AddTaskEvent( String task_name, String preparer, java.sql.Date task_deadline, LocalTime deadline_hour, int minutes,
			String category_1, String comm) {
		this.task_name = task_name;
		this.preparer = preparer;
		this.deadline = task_deadline;
		this.deadline_hour = deadline_hour;
		this.minutes = minutes;
		this.category_1 = category_1;
		this.comment = comm;
	}

//Create task
	public AddTaskEvent(String name, String prep, String rev, Date task_deadline, LocalTime time, int task_duration,
			String cat, String comm, boolean revRequired) {
		
		this.task_name = name;
		this.preparer = prep;
		this.reviewer = rev;
		this.deadline = task_deadline;
		this.deadline_hour = time;
		this.minutes = task_duration;
		this.category_1 = cat;
		this.comment = comm;
		this.review_required = revRequired;
	}
//Depute task	
	public AddTaskEvent(String name, String prep, String rev, Date task_deadline, LocalTime time, int task_duration,
			String cat, String comm) {
		
		this.task_name = name;
		this.preparer = prep;
		this.reviewer = rev;
		this.deadline = task_deadline;
		this.deadline_hour = time;
		this.minutes = task_duration;
		this.category_1 = cat;
		this.comment = comm;
	}


	private String task_name;
	private String preparer;
	private String reviewer;
	private java.sql.Date deadline;
	private LocalTime deadline_hour;
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



}
