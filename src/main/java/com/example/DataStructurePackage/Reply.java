package com.example.DataStructurePackage;

public class Reply extends Message {
    private Enquiry to;

    /**
     * Constructor for Reply class.
     * @param text      String to be displayed.
     * @param author    User who made the reply.
     * @param to        Enquiry the reply is replying to.
     */
    public Reply(String text, User author, Enquiry to){
        super(text,author);
        this.to = to;
    }

    /**
     * Get method for to.
     * @return
     */
    public Enquiry getEnquiry(){
        return this.to;
    }
}
