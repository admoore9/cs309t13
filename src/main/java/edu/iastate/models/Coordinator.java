package edu.iastate.models;

public class Coordinator extends Member {

    public Coordinator() {
        super(UserType.COORDINATOR);
    }

    public Coordinator(String name, String username, String password,
            UserType userType) {
        super(name, username, password, userType);
    }

}
