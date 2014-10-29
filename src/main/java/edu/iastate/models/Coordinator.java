package edu.iastate.models;


public class Coordinator extends Official {

    public Coordinator() {
        super(UserType.COORDINATOR);
    }

    public Coordinator(String name, String username, String password) {
        super(name, username, password, UserType.COORDINATOR);
    }
    
    protected Coordinator(UserType userType) {
        super(userType);
    }

    protected Coordinator(String name, String username, String password,
            UserType userType) {
        super(name, username, password, userType);
    }

}
