package gui;

import java.util.EventListener;

import addtaskpanels.AddTaskEvent;

public interface FormListener extends EventListener{
	public void MyTaskAdded(AddTaskEvent e);
	

}
