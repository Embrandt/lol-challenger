package de.drumcat.riotapichallengefx.domain;

import javax.persistence.*;

@Entity
@Table(name = "MATCH_RESULTS")
public class MatchResultsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String playerOne;
    @Column
    private String playerTwo;
    @Column
    private int lostGamesPlayerOne;
    @Column
    private int getLostGamesPlayerTwo;
    @Column
    private int wonGamesPlayerOne;
    @Column
    private int wonGamesPlayerTwo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public int getLostGamesPlayerOne() {
        return lostGamesPlayerOne;
    }

    public void setLostGamesPlayerOne(int lostGamesPlayerOne) {
        this.lostGamesPlayerOne = lostGamesPlayerOne;
    }

    public int getGetLostGamesPlayerTwo() {
        return getLostGamesPlayerTwo;
    }

    public void setGetLostGamesPlayerTwo(int getLostGamesPlayerTwo) {
        this.getLostGamesPlayerTwo = getLostGamesPlayerTwo;
    }

    public int getWonGamesPlayerOne() {
        return wonGamesPlayerOne;
    }

    public void setWonGamesPlayerOne(int wonGamesPlayerOne) {
        this.wonGamesPlayerOne = wonGamesPlayerOne;
    }

    public int getWonGamesPlayerTwo() {
        return wonGamesPlayerTwo;
    }

    public void setWonGamesPlayerTwo(int wonGamesPlayerTwo) {
        this.wonGamesPlayerTwo = wonGamesPlayerTwo;
    }
}
