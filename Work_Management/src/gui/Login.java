package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Person;

public class Login extends Application {

	private Controller controller;
	private Alert alert;
	private Person currentUser;
	private MainMenu mainMenu;

	public Person getCurrentUser() {
		return currentUser;
	}

	public Login() {
		controller = new Controller();
		alert = new Alert(AlertType.INFORMATION);

		try {
			controller.loadUsersData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		alert.setTitle("Success!");

		// alert.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));

		alert.setHeight(400);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("JavaFX Welcome");
		GridPane grid = new GridPane();

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(10, 10, 10, 10));

		Scene scene = new Scene(grid, 300, 350);

		Text sceneTitle = new Text("Welcome");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(sceneTitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		// Dodaję nowy layout do tej samej sceny jako kolejny node

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);

		grid.add(hbBtn, 1, 4);

		Text validationText = new Text("Invalid username or password.");
		validationText.setFill(Color.RED);
		validationText.setVisible(false);
		grid.add(validationText, 1, 3);

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String user = userTextField.getText();
				String pass = pwBox.getText();

				if (controller.validate(user, pass)) {
					validationText.setVisible(false);

					currentUser = controller.getCurrentUser();
					
					String name = (currentUser.getName() + " " + currentUser.getSurname());
					String job = currentUser.getJob();

					alert.setContentText(
							"Witamy w aplikacji do zarządzania pracą osób w organiacji" + "\nJesteś zalogowany jako "
									+ name + ".\nPosiadasz uprawnienia na poziomie stanowiska " + job + ".");

					primaryStage.hide();

					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							alert.close();

							mainMenu = new MainMenu(currentUser, controller);
							mainMenu.show();

						}
					});
				} else {
					// zmień kolor na czerwony
					validationText.setVisible(true);
				}

			}

		});

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
