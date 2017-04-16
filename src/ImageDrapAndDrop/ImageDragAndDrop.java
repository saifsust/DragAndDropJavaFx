/**
 * 
 */
package ImageDrapAndDrop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import TextDragAndDrop.constant;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Saif_sust_2013331007
 *
 */
public class ImageDragAndDrop extends Application implements constant {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(scene);

		root.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				System.out.println("Drag Over Event");
				MouseDragOverEvent(event);
			}

		});

		root.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				System.out.println("Drag Dropped Event");
				MouseDragDroppedEvent(event);
			}

		});
		primaryStage.show();
	}

	private void addImages(Image img) {
		imgView = new ImageView();
		imgView.setImage(img);
		root.getChildren().add(imgView);
	}

	private void MouseDragDroppedEvent(final DragEvent event) {
		Dragboard db = event.getDragboard();
		boolean success = false;

		if (db.hasFiles()) {
			success = true;
			final File file = db.getFiles().get(0);
			System.out.println(file.getAbsoluteFile().toString());
			try {
				Image img = new Image(new FileInputStream(file.getAbsoluteFile()));
				if (!root.getChildren().isEmpty()) {
					root.getChildren().clear();
				}
				addImages(img);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		event.setDropCompleted(success);
		event.consume();
	}

	private void MouseDragOverEvent(DragEvent event) {

		Dragboard db = event.getDragboard();
		boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
				|| db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg")
				|| db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
				|| db.getFiles().get(0).getName().toLowerCase().endsWith(".gif");
		if (db.hasFiles()) {

			if (isAccepted) {
				event.acceptTransferModes(TransferMode.COPY);
			}

		} else {
			event.consume();
		}

	}

	private ImageView imgView;
	private StackPane root = new StackPane();
	private Scene scene = new Scene(root, 2 * width, 1.5 * height, Color.WHEAT);
}
