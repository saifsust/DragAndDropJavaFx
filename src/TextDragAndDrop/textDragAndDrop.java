/**
 * 
 */
package TextDragAndDrop;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Saif_sust_2013331007
 *
 */
public class textDragAndDrop extends Application implements constant {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(scene);
		scene.setFill(Color.BROWN);
		source.setText("Saiful Islam");
		source.setLayoutX(50.0);
		source.setLayoutY(100.0);
		source.setScaleX(2.0);
		source.setScaleY(2.0);
		source.setFill(Color.CYAN);

		target.setText(" is a Bangladeshi");
		target.setLayoutX(200.0);
		target.setLayoutY(300);
		target.setScaleX(2.5);
		target.setScaleY(2.5);
		target.setFill(Color.KHAKI);

		source.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("sorce Event Taken");
				source.setFill(Color.GRAY);
				Dragboard db = source.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString(source.getText());
				db.setContent(content);
				event.consume();
			}

		});
		target.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				System.out.println("Drag is Over");
				if (event.getGestureSource() != target && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
					// target.setFill(Color.RED);

				}
				event.consume();
			}

		});
		target.setOnDragEntered(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				System.out.println("Drag Entered");
				if (event.getGestureSource() != target && event.getDragboard().hasString()) {
					target.setFill(Color.GREEN);

				}
				event.consume();
			}

		});

		target.setOnDragExited(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				System.out.println("Drag Exit");
				target.setFill(Color.BLACK);
				event.consume();
			}

		});
		target.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				System.out.println("Drag Dropped");
				boolean success = false;
				Dragboard db = event.getDragboard();
				if (db.hasString()) {
					target.setText(db.getString());
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}

		});

		source.setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				System.out.println("Drag Done");
				if (event.getTransferMode() == TransferMode.MOVE) {
					source.setText("");
				}

			}

		});
		Button clear = new Button("Refresh");
		clear.setLayoutX(250.0);
		clear.setLayoutY(20.0);
		clear.setPrefSize(100.0, 30.0);
		clear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				source.setText("Saiful Islam");
				source.setFill(Color.CYAN);
				target.setText(" is a Bangladeshi");
				target.setFill(Color.KHAKI);
			}

		});
		root.getChildren().add(clear);
		root.getChildren().add(target);
		root.getChildren().add(source);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private final Text source = new Text();
	private final Text target = new Text();
	private Group root = new Group();
	private Scene scene = new Scene(root, width, height);
}
