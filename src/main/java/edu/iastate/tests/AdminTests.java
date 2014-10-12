package edu.iastate.tests;

import org.junit.Test;

import edu.iastate.models.Admin;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;
import junit.framework.TestCase;

public class AdminTests extends TestCase {

//    protected void setUp() throws Exception {
//        super.setUp();
//    }
//
//    protected void tearDown() throws Exception {
//        super.tearDown();
//    }
    
    /**
     * change current view
     */
    @Test
    public void changeCurrentView() {
        Admin admin = new Admin("adminUser", "adminUser123", "adminPassword");
    }

}
