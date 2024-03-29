package presentation;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.Lobby;
import common.Player;

public class GsonUtils {
    public static Gson createGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .registerTypeAdapter(Player.class, new PlayerDeserializer())
                .registerTypeAdapter(Player.class, new PlayerSerializer())
                .registerTypeAdapter(Lobby.class, new LobbyDeserializer())
                .registerTypeAdapter(Lobby.class, new LobbySerializer())
                .create();
    }
}
