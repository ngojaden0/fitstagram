package com.example.regularfeed;

public class DataModel
{
    String userName;
    String postType;
    int numberOfImages;
    int image;
    int time;

    public DataModel(String userName, String postType, int numberOfImages, int image, int time)
    {
        //Limit 1-3 Images, voting or picture post
        this.userName = userName;
        this.postType = postType;
        this.numberOfImages = numberOfImages;
        this.image=image;
    }

    public String getUserName()
    {
        return userName;
    }
    public String getPostType()
    {
        return postType;
    }
    public int getNumberOfImages()
    {
        return numberOfImages;
    }
    public int getImage()
    {
        return image;
    }
    public int getTime()
    {
        return time;
    }
}
