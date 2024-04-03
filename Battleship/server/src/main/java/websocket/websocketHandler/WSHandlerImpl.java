package websocket.websocketHandler;

import websocket.topics.Topic;

import java.util.List;
import java.util.Map;

/***
 * This class models the fact that each websocket has his topics.
 *
 */

public class WSHandlerImpl implements WsHandler {

    private List<Topic> topicList;

    public WSHandlerImpl(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<Topic> getTopicList() {
        return this.topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    @Override
    public List<Topic> getTopics() {
        return this.topicList;
    }

    @Override
    public Topic getTopicByName(String topicName) {
        for (Topic topic : this.topicList) {
            if (topic.getTopicName().equals(topicName)) {
                return topic;
            }
        }
        return null;
    }

    @Override
    public Map<Topic, String> getClientConnected() {
        return null;
    }
}
