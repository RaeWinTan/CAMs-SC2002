package com.example.datastructure;

import java.util.ArrayList;
import java.util.UUID;

import com.example.utility.Pair;

public class Message {
    private UUID messageId;
    protected User author;
    protected String text;

    /**
     * Constructor for Message Class.
     * @param text      String to be displayed.
     * @param author    User who made the message.
     */
    public Message(String text, User author){
        this.messageId = UUID.randomUUID();
        this.text = text;
        this.author = author;
    }

    public Message(UUID messageID, String text, User author){
        this.messageId = messageID;
        this.text = text;
        this.author = author;
    }

    /**
     * Get method for author.
     * @return      User who made the message.
     */
    public User getAuthor(){
        return this.author;
    }

    /**
     * Get method for text.
     * @return      String that would be displayed.
     */
    public String getText(){
        return this.text;
    }

    /**
     * Set method for text.
     * @param text      String that would be displayed.
     */
    public void setText(String text){
        this.text = text;
    }

    public UUID getMessageId(){
        return this.messageId;
    }

    public Message copyOf(){
        return new Message(this.getMessageId(), this.getText(), this.getAuthor());
    }

    public ArrayList<Pair<String, String>> toAttributeValueMapping(){
		ArrayList<Pair<String, String>> rtn = new ArrayList<Pair<String, String>>();
		rtn.add(new Pair<String, String>("text", this.text));
		rtn.add(new Pair<String, String>("author", this.author.getName()));
		return rtn;
	}
}
