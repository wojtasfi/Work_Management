package model;

import java.io.File;
import java.sql.Time;
import java.time.LocalTime;

public class AddFixedTaskEvent {
	private int id;
	private String task_name;
	private int minutes;
	private String task_type;
	private String frequency;
	private LocalTime deadline_hour;

	private String comment;
	private File attachment;
	private boolean review_required;
	private String category_1;
	private String team;

	public AddFixedTaskEvent(String task_name, int minutes, String task_type, String frequency, String comment,
			File attachment, boolean review_required, String category_1, String team) {
		this.task_name = task_name;
		this.minutes = minutes;
		this.task_type = task_type;
		this.frequency = frequency;
		this.comment = comment;
		this.attachment = attachment;
		this.review_required = review_required;
		this.category_1 = category_1;
		this.team = team;
	}
	
	public AddFixedTaskEvent(String task_name, int minutes, String task_type, String frequency, String comment,
		 boolean review_required, String category_1, String team, LocalTime deadline_hour) {
		this.task_name = task_name;
		this.minutes = minutes;
		this.frequency = frequency;
		this.comment = comment;
		this.review_required = review_required;
		this.category_1 = category_1;
		this.team = team;
		this.deadline_hour = deadline_hour;
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

	public boolean isReview_required() {
		return review_required;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public LocalTime getDeadline_hour() {
		return deadline_hour;
	}

	public void setDeadline_hour(LocalTime deadline_hour) {
		this.deadline_hour = deadline_hour;
	}

}
