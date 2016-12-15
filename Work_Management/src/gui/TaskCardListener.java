package gui;

public interface TaskCardListener {
	
	public void taskStatusUpdated(int id, int procent);
	
	public void taskReviewed(int id, boolean reviewed);
	
	public void refreshTable();

	public void taskPassed(int taskID, String choosenPrep, String choosenRev);

	public void refreshPassTable();

	public void fixedTaskDeleted(int taskID);

}
