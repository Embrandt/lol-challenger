package de.drumcat.riotapichallengefx.ui;

import de.drumcat.riotapichallengefx.service.BuddyApiService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BuddyListController {

    @FXML
    private ListView listView;

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
    }
    @FXML
    public void initialize() {
        BuddyApiService buddyApiService = new BuddyApiService();
        listView.setItems(FXCollections.observableArrayList(buddyApiService.getBuddies()));
    }
}
