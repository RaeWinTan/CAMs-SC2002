package com.example.datastructure;

import java.util.ArrayList;
import java.util.UUID;

import com.example.datastore.IDataStoreObject;
import com.example.utility.Pair;

public class Suggestion implements IDataStoreObject<Suggestion> {
    private UUID suggestionId;
    private Student author;
    private Camp newCamp;
    private boolean approved;

    /**
     * Constructor for Suggestion class.
     * @param author            Student who made the suggestion.
     * @param camp              Camp the suggestion was made for.
     */
    public Suggestion(Student author, Camp camp){
        this.suggestionId = UUID.randomUUID();
        this.author = author;
        this.newCamp = camp;
        this.approved = false;
    }

    /**
     * Constructor for Suggestion class.
     * @param suggestionId      Unique identifier of the suggestion.
     * @param author            Student who made the suggestion.
     * @param camp              Camp the suggestion was made for.
     * @param approved          Flag for if the suggestion was approved.
     */
    public Suggestion(UUID suggestionId, Student author, Camp camp, boolean approved){
        this.suggestionId = suggestionId;
        this.author = author;
        this.newCamp = camp;
        this.approved = approved;
    }

    public ArrayList<Pair<String, String>> toAttributeValueMapping(){
		ArrayList<Pair<String, String>> rtn = new ArrayList<Pair<String, String>>();
		rtn.add(new Pair<String, String>("author", this.author.getName()));
		rtn.add(new Pair<String, String>("camp", this.getCamp().getCampName()));
		rtn.add(new Pair<String, String>("approved", ""+this.approved));
		return rtn;
	}

    /**
     * Get method for author.
     * @return      Student who made the suggestion.
     */
    public Student getAuthor(){
        return this.author;
    }

    /**
     * Get method for camp.
     * @return      Camp the suggestion was made for.
     */
    public Camp getCamp(){
        return this.newCamp;
    }

    /**
     * Get method for approved.
     * @return      Flag for if the suggestion was approved.
     */
    public boolean getApproved(){
        return this.approved;
    }

    /**
     * Get method for suggestionId.
     * @return      Unique identifier of the suggestion.
     */
    public UUID getSuggestionId(){
        return this.suggestionId;
    }

    /**
     * This method approves the suggestion.
     */
    public void approve(){
        this.approved = true;
    }

	/**
	 * Returns true if o is equal to the suggestion. suggestionId is used for the comparison.
	 * @param o		other suggestion to compare to.
	 * @return		true if o is equal to the suggestion.
	 */
	public boolean isEquals(Suggestion o) {
		return o.getSuggestionId().equals(this.getSuggestionId()); 
	}

	/**
	 * This method returns a copy of the suggestion.
	 * @return		A copy of the suggestion.
	 */
	public Suggestion copyOf() {
		return new Suggestion(this.suggestionId,this.author,this.newCamp,this.approved);
	}
}
