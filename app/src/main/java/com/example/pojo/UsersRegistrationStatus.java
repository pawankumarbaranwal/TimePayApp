package com.example.pojo;

/**
 * Created by Pawan on 10/31/2015.
 */
public class UsersRegistrationStatus {

    private String statusCode;
    private String statusMessage;

    public String getStatusCode ()
    {
        return statusCode;
    }
    public void setStatusCode (String statusCode)
    {
        this.statusCode = statusCode;
    }
    public String getStatusMessage ()
    {
        return statusMessage;
    }
    public void setStatusMessage (String statusMessage)
    {
        this.statusMessage = statusMessage;
    }
    @Override
    public String toString()
    {
        return "UsersRegistrationStatus [statusCode = "+statusCode+", statusMessage = "+statusMessage+"]";
    }
}
