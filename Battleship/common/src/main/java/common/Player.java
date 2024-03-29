package common;




import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {


    private String username;

    private boolean inGame;

    private PlayerRole playerRole;
    private PlayerStatus playerStatus;

    @JsonCreator
    public Player(@JsonProperty("username") String username) {
        this.username = username;
        this.inGame = false;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean getInGame() {
        return inGame;
    }

    public PlayerRole getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(PlayerRole playerRole) {
        this.playerRole = playerRole;
    }

    public void setPlayerStatus(PlayerStatus playerStatus)  {
        this.playerStatus= playerStatus;
    }

    public PlayerStatus getPlayerStatus() {
        return this.playerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(username, player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "nickName='" + username + '\'' +

                '}';
    }




}
