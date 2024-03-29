package presentation;

import com.google.gson.*;
import common.Lobby;
import common.Player;

import java.lang.reflect.Type;

public class LobbySerializer implements JsonSerializer<Lobby> {
    @Override
    public JsonElement serialize(Lobby src, Type typeOfSrc, JsonSerializationContext context) {
        var object = new JsonObject();

        JsonArray players = new JsonArray();

        for(Player user : src.getPlayers()){
            players.add(context.serialize(user));
        }

        object.addProperty("id", src.getId());
        object.addProperty("name", src.getName());
        object.add("players", players);

        return object;
    }
}
