package de.drumcat.riotapichallengefx.ui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import org.springframework.stereotype.Controller;


@Controller
public class MainController {

    @FXML
    private Parent buddyListView;

    @FXML
    private Parent challengeView;

    @FXML
    private BuddyListController buddyListViewController;

    @FXML
    private ChallengeControler challengeViewController;

    public void initialize() {
        buddyListViewController.setChallengeController(challengeViewController);
    }
}
