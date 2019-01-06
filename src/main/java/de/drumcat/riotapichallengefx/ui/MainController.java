package de.drumcat.riotapichallengefx.ui;

import de.drumcat.riotapichallengefx.service.BuddyApiService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {@FXML
public ComboBox<String> comboBoxBuddies;

    @Autowired
    private BuddyApiService buddyApiService;

    @FXML
    public void initialize() {
        comboBoxBuddies.setItems(FXCollections.observableArrayList(buddyApiService.getBuddies()));
    }
}
