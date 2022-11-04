package org.superbiz.model;

public class RootUserWrapper {
    private User user;
    

    public RootUserWrapper() {
    }

    public RootUserWrapper(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

}