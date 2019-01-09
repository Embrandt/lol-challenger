package de.drumcat.riotapichallengefx.ui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @FXML
    private Parent buddyListView;

    @FXML
    private Parent chartsView;

    @FXML
    private BuddyListController buddyListController;

    @FXML
    private ChartController chartController;

    public void initialize() {
    }
}
