package com.example.view;

/**These are the different regex that the input
 * in prompts may need to match.
 */
public enum RegexType {
    /**This is to ensure input in date related prompts
     * matches a specific format so that it can
     * be safely parsed to a Date object
     */
    DATE("\\d{2}/\\d{2}/\\d{4}"),

    /**This is to ensure only integer values are entered*/
    INTEGER("\\d+");
    private String type;

    /**Constructor for the enum
     * @param type which is the regex type as a String
     */
    RegexType(String type){
        this.type = type;
    }

    /**Getter method to return the regex type
     * @return type which is a String
     */
    public String toString(){
        return this.type;
    }
}
/* "\\d+" */