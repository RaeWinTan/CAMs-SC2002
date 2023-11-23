package com.example;
//refer to StudentDatshBoardPromptPage to make this mapping 
//look at this link you should get where i am going with this:
//https://howtodoinjava.com/java/enum/java-enum-string-example/
public enum Page {
    //shared amonst all users
    ChangePassword("Change password"),
    ViewAvailableCamps("View Available Camps");
    
    private String pageView;
    Page(String pageView){
        this.pageView = pageView;
    }
    public String getPageView(){
        return this.pageView;
    }
}
