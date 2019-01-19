package de.drumcat.riotapichallengefx.ui;

import de.drumcat.riotapichallengefx.domain.Challenge;
import de.drumcat.riotapichallengefx.service.BuddyApiService;
import de.drumcat.riotapichallengefx.service.ChallengeService;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class BuddyListController {

    private ChallengeController challengeController;

    @FXML
    private ListView<String> listViewBuddy;

    @FXML
    private Button addFavoriteButton;

    @FXML
    private ListView<String> listViewActiveChallenges;

    @FXML
    private AnchorPane anchorPaneBuddy;

    @FXML
    private TextField textBoxBuddy;

    @FXML
    public void initialize() {
        addFavoriteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
//                listViewActiveChallenges.getItems().(listViewBuddy.getSelectionModel().getSelectedItems());
                ObservableList<String> items = listViewActiveChallenges.getItems();
                ObservableList<String> selectedItems = listViewBuddy.getSelectionModel().getSelectedItems();
                if(selectedItems != null && !selectedItems.isEmpty())
                for(String selected : selectedItems){
                    if(!items.contains(selected)) {
                        items.add(selected);
                    }
                }
            }
        });
        createContent();
        createActiveChallengeContent();
    }

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        if (arg0.getSource() instanceof ListView) {
            ListView<String> clickedView = (ListView) arg0.getSource();
            String selectedItem = clickedView.getSelectionModel().getSelectedItem();
            challengeController.showUser(selectedItem);
            System.out.println("clicked on " + selectedItem);
        }
    }

    /**
     * Sets the controller for challenges to be able to trigger actions from here
     * on the challenges
     *
     * @param challengeController controller for the challenge view
     */
    void setChallengeController(ChallengeController challengeController) {
        this.challengeController = challengeController;
    }

    private Parent createContent() {
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
        textBoxBuddy.setOnKeyPressed((KeyEvent ke) -> listViewBuddy.refresh());
        textBoxBuddy.setOnKeyReleased((KeyEvent ke) -> searchInBuddies(textBoxBuddy.getText()));
        return anchorPaneBuddy;
    }

    public Parent createActiveChallengeContent() {
        searchChallenges();
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

    private void searchChallenges() {
        ChallengeService challengeService = new ChallengeService();
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for ( Map.Entry<String, Challenge> entry: challengeService.getChallengesFromDB().entrySet() ) {
                subentries.add(entry.getKey());
        }
        listViewActiveChallenges.setItems(subentries);
    }
}
