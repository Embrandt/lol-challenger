package de.drumcat.riotapichallengefx.ui;

import de.drumcat.riotapichallengefx.service.BuddyApiService;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Controller;

@Controller
public class BuddyListController {

    @FXML
    private ListView listViewBuddy;

    @FXML
    private AnchorPane anchorPaneBuddy;

    @FXML
    private TextField textBoxBuddy;

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        System.out.println("clicked on " + listViewBuddy.getSelectionModel().getSelectedItem());
    }
    @FXML
    public void initialize() {
        createContent();
    }

    public Parent createContent() {
        // create a console for logging key events
        // listen on console items and remove old ones when we get over 20 items
        ListChangeListener<? super String> listener =
                (ListChangeListener.Change<? extends String> change) -> {
                    while (change.next()) {
                        if (change.getList().size() > 20.0) {
                            change.getList().remove(0);
                        }
                    }
                };
        listViewBuddy.getItems().addListener(listener);
        listViewBuddy.setPrefHeight(150);
        listViewBuddy.setMaxHeight(ListView.USE_PREF_SIZE);

        textBoxBuddy.setPromptText("Search Buddy");
        // add a key listeners
        textBoxBuddy.setOnKeyPressed((KeyEvent ke) -> {
            listViewBuddy.refresh();
        });
        textBoxBuddy.setOnKeyReleased((KeyEvent ke) -> {
            searchInBuddies(ke.getText());
        });
        return anchorPaneBuddy;
    }

    private void searchInBuddies(String input) {
        BuddyApiService buddyApiService = new BuddyApiService();
        input = input.toUpperCase();
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for ( String entry: buddyApiService.getBuddies() ) {
            if ( entry.toUpperCase().contains(input) ) {
                subentries.add(entry);
            }
        }
        listViewBuddy.setItems(subentries);
    }
}
