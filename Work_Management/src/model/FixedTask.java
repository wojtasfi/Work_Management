
package model;

import java.io.File;
import java.sql.Date;
import java.sql.Time;

public class FixedTask extends Task {

	public FixedTask(int id, String task_name, String preparer, String reviewer, Date deadline, Time deadline_hour,
			int minutes, String task_type, String frequency, String comment, File attachment, float prc_status,
			boolean review_required, boolean done, String orderer, String category_1, boolean reviewed) {
		super(id, task_name, preparer, reviewer, deadline, deadline_hour, minutes, task_type, frequency, comment, attachment,
				prc_status, review_required, done, orderer, category_1, reviewed);
		// TODO Auto-generated constructor stub
	}

	private int id;
	private String task_name;
	private int minutes;
	private String task_type;
	private String frequency;

	private String comment;
	private File attachment;
	private boolean review_required;
	private String category_1;
	private String team;
	private Time deadline_hour;

	public FixedTask(int id, String task_name, int minutes, String task_type, String frequency, String comment,
			File attachment, boolean review_required, String category_1, String team, Time deadline_hour) {
		super();
		this.id = id;
		this.task_name = task_name;
		this.minutes = minutes;
		this.task_type = task_type;
		this.frequency = frequency;
		this.comment = comment;
		this.attachment = attachment;
		this.review_required = review_required;
		this.category_1 = category_1;
		this.team = team;
		this.setDeadline_hour(deadline_hour);
	}
	
	public FixedTask(int id, String task_name, int minutes, String task_type, String frequency, String comment,
			 boolean review_required, String category_1, String team, Time deadline_hour) {
		super();
		this.id = id;
		this.task_name = task_name;
		this.minutes = minutes;
		this.task_type = task_type;
		this.frequency = frequency;
		this.comment = comment;
		this.review_required = review_required;
		this.category_1 = category_1;
		this.team = team;
		this.setDeadline_hour(deadline_hour);
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

	public Time getDeadline_hour() {
		return deadline_hour;
	}

	public void setDeadline_hour(Time deadline_hour) {
		this.deadline_hour = deadline_hour;
	}

}
