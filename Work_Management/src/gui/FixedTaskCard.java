package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import model.FixedTask;
import model.Person;

public class FixedTaskCard extends TaskCard{

	public FixedTaskCard(FixedTask task, Person currentUser) {
		super(task, currentUser);

		
		row = 1;
		grid.add(nameLbl, 1, row++);
		grid.add(task_name, 1, row++);
		
		grid.add(hourLbl, 1, row++);
		grid.add(deadline_hour, 1, row++);

		grid.add(minLbl, 1, row++);
		grid.add(minutes, 1, row++);

		grid.add(saveBtn, 1, row++);

		row = 1;

		grid.add(typeLbl, 2, row++);
		grid.add(task_type, 2, row++);

		grid.add(freqLbl, 2, row++);
		grid.add(frequency, 2, row++);

		grid.add(catLbl, 2, row++);
		grid.add(category_1, 2, row++);

		grid.add(commentLbl, 2, row++);
		grid.add(comment, 2, row++);
		
		show();
		saveBtn.setText("Delete");
		saveBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				cardListener.fixedTaskDeleted(taskID);
				cardListener.refreshTable();
			}

		});
		
		Scene scene = new Scene(grid);
		setScene(scene);
		show();
	}

}
