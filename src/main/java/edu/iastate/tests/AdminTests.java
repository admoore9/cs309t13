package edu.iastate.tests;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import edu.iastate.dao.AdminDao;
import edu.iastate.models.Admin;

public class AdminTests {

//    protected void setUp() throws Exception {
//        super.setUp();
//    }
//
//    protected void tearDown() throws Exception {
//        super.tearDown();
//    }
    
//    /**
//     * change current view
//     */
//    @Test
//    public void changeCurrentView() {
//        Admin admin = new Admin("adminUser", "adminUser123", "adminPassword");
//    }
    
    @Test
    public void returnAllAdminsTest() {
        AdminDao adminDao = new AdminDao();
        List<Admin> admins = adminDao.returnAllAdmins();
        System.out.println("Names:");
        for (Admin admin : admins) {
            System.out.println(admin.getName());
        }
    }

}
