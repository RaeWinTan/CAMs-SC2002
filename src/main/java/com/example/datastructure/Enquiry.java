package com.example.datastructure;

import java.util.ArrayList;
import java.util.UUID;

import com.example.datastore.IDataStoreObject;
import com.example.utility.Pair;

public class Enquiry extends Message implements IDataStoreObject<Enquiry>{
    private Camp to;
    private ArrayList<Message> replies;

    /**
     * Constructor for Enquiry class.
     */
    public Enquiry(){
        super();
    }

    /**
     * Constructor for Enquiry class.
     * @param text      String to be displayed.
     * @param author    Student who made the enquiry.
     * @param to        Camp the enquiry is made for.
     */
    public Enquiry(String text, Student author, Camp to){
        super(text,author);
        this.to = to;
        this.replies = new ArrayList<Message>();
    }

    /**
     * Constructor for Enquiry class.
     * @param enquiryId Unique identifier for the enquiry.
     * @param text      String to be displayed.
     * @param author    Student who made the enquiry.
     * @param to        Camp the enquiry is made for.
     */
    public Enquiry(UUID messageId, String text, Student author, Camp to, ArrayList<Message> replies){
        super(messageId, text,author);
        this.to = to;
        this.replies = replies;
    }

    /**
     * Get method for to.
     * @return      Camp the enquiry is made for.
     */
    public Camp getCamp(){
        return this.to;
    }

    public ArrayList<Message> getReplies(){
        return this.replies;
    }

    public void addReply(Message reply){
        this.replies.add(reply);
    }

	/**
	 * Returns true if o is equal to the enquiry. enquiryId is used for the comparison.
	 * @param o		other enquiry to compare to.
	 * @return		true if o is equal to the enquiry.
	 */
    @Override
	public boolean isEquals(Enquiry o) {
		return o.getMessageId().equals(this.getMessageId()); 
	}

	/**
	 * This method returns a copy of the enquiry.
	 * @return		A copy of the enquiry.
	 */
    @Override
	public Enquiry copyOf() {
        ArrayList<Message> repliesCopy = new ArrayList<Message>();
        for (Message message : this.replies) {
            repliesCopy.add(message.copyOf());
        }
		return new Enquiry(this.getMessageId(), this.getText(), (Student) this.getAuthor(), this.to, repliesCopy);
	}

    public ArrayList<Pair<String, String>> toAttributeValueMapping(){
		ArrayList<Pair<String, String>> rtn = new ArrayList<Pair<String, String>>();
		rtn.add(new Pair<String, String>("text", this.text));
		rtn.add(new Pair<String, String>("author", this.author.getName()));
		rtn.add(new Pair<String, String>("password", this.to.getCampName()));
		return rtn;
	}
}
