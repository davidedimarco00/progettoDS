package presentation;

import com.google.gson.*;
import common.Lobby;
import common.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShipDeserializer implements JsonDeserializer<Lobby> {

    private String getPropertyAsString(JsonObject object, String name) {
        if (object.has(name)) {
            JsonElement value = object.get(name);
            if (value.isJsonNull()) return null;
            return value.getAsString();
        }
        return null;
    }

    private <T> T getPropertyAs(JsonObject object, String name, Class<T> type, JsonDeserializationContext context) {
        if (object.has(name)) {
            JsonElement value = object.get(name);
            if (value.isJsonNull()) return null;
            return context.deserialize(value, type);
        }
        return null;
    }

    @Override
    public Lobby deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            var object = json.getAsJsonObject();

            var id = getPropertyAs(object, "id", Integer.class, context);

            var name = getPropertyAs(object, "name", String.class, context);

            var playersArray = object.getAsJsonArray("players");

            List<Player> players = new ArrayList<>(playersArray.size());
            for (JsonElement item : playersArray) {
                if (item.isJsonNull()) continue;
                players.add(context.deserialize(item, Player.class));
            }

            Lobby lobby = new Lobby(id, name);
            for(Player player : players){
                lobby.addPlayer(player);
            }

            return lobby;
        }catch (ClassCastException | NullPointerException e){
            throw new JsonParseException("Invalid lobby: " + json, e);
        }
    }
}
