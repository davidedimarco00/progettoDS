package websocket.websocketHandler;

import io.javalin.websocket.WsConfig;
import websocket.topics.Topic;

import java.util.List;
import java.util.Map;

public interface WsHandler {

    List<Topic> getTopics();

    Topic getTopicByName(String topicName);

    Map<Topic, String> getClientConnected();


}
