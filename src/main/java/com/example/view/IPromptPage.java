package com.example.view;

import java.util.ArrayList;

/**IPromptPage is the interface which is implemented by the various Prompt Page such as CreateCampPromptPage etc. 
 * It contains the relevant getter method
 * as well a type declaration so that the implementing classes can
 * specify what return type they will require.
 * It inherits from IViewPage
 */
public interface IPromptPage<T> extends IViewPage{
    /**getter method */
    public T getObject();

}
