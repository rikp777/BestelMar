package Logic.Models;

import Data.MessageType;


public class Message {
    private MessageType messageType;
    private String message;



    public Message(MessageType messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }
    public Message(){

    }



    public MessageType getMessageType() {
        return messageType;
    }
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}
