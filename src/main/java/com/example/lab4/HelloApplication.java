
package com.example.lab4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public HelloApplication() {
    }

    public void start(Stage primaryStage) {
        final Text txtNote = new Text("Notification");
        txtNote.setFont(Font.font("Comic Sans MS", 25.0));
        txtNote.setFill(Color.FUCHSIA);
        final TextField fldAdd = new TextField();
        fldAdd.setPromptText("Add Field");
        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(85.0);
        Button btnRemove = new Button("Remove");
        btnRemove.setMinWidth(85.0);
        final ListView<String> listList = new ListView();
        final ObservableList<String> animals = FXCollections.observableArrayList(new String[]{"Harry Potter", "The Little Mermaid", "Snow White", "Cinderella", "Maleficent"});
        listList.setItems(animals);
        listList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listList.setMaxSize(300.0, 300.0);
        animals.addListener(new ListChangeListener() {
            public void onChanged(ListChangeListener.Change change) {
                txtNote.setText("ListView Successfully changed");
            }
        });
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                String textToAdd = fldAdd.getText();
                if (!textToAdd.isEmpty()) {
                    if (textToAdd.length() < 5) {
                        txtNote.setText("Please write a name with at least 5 characters long");
                    } else if (!Character.isUpperCase(textToAdd.charAt(0))) {
                        txtNote.setText("Please start the name with an uppercase letter");
                    } else {
                        animals.add(textToAdd);
                        txtNote.setText("");
                    }
                } else {
                    txtNote.setText("Please add information to Add field");
                }

                listList.getSelectionModel().clearSelection();
            }
        });
        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (!listList.getSelectionModel().isEmpty()) {
                    animals.remove(listList.getSelectionModel().getSelectedItem());
                } else {
                    txtNote.setText("Select at least 1 Element on the ListView");
                }

                listList.getSelectionModel().clearSelection();
            }
        });
        VBox right = new VBox(10.0);
        right.setPadding(new Insets(10.0));
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(new Node[]{fldAdd, btnAdd, btnRemove});
        BorderPane root = new BorderPane();
        root.setCenter(listList);
        root.setRight(right);
        root.setBottom(txtNote);
        Scene scene = new Scene(root, 800.0, 500.0);
        primaryStage.setTitle("JavaFX - Lab4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
