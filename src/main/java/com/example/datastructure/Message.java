package com.example.datastructure;

import java.util.UUID;

/**
 * Class storing data of a message.
 */
public class Message {
    private UUID messageId;
    protected User author;
    protected String text;
    
    /**
     * Constructor for Message Class.
     */
    public Message(){
        this.messageId = UUID.randomUUID();
    }

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

    /**
     * Constructor for Message class.
     * @param messageID Unique identifier for Message.
     * @param text      String to be displayed.
     * @param author    User who made the message.
     */
    protected Message(UUID messageID, String text, User author){
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

    /**
     * Get method for message id.
     * @return      Unique identifier for Message.
     */
    public UUID getMessageId(){
        return this.messageId;
    }

    /**
     * Create a copy of the message.
     * @return  Create a copy of the message.
     */
    public Message copyOf(){
        return new Message(this.getMessageId(), this.getText(), this.getAuthor());
    }
}
