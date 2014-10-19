package edu.iastate.tests;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.AdminDao;
import edu.iastate.models.Admin;

public class AdminTests {

    AdminDao adminDao;
    
    @Before
    public void setUp() throws Exception {
        adminDao = new AdminDao();
    }
    
//    /**
//     * change current view
//     */
    @Test
    public void changeCurrentView() {
        Admin admin = adminDao.getAdminById(7);
        System.out.println("Name: " + admin.getName() + " View: " + admin.getCurrentView());

    }
    
    @Test
    public void getAllAdminsTest() {
        List<Admin> admins = adminDao.getAllAdmins();
        System.out.println("Names:");
        for (Admin admin : admins) {
            System.out.println(admin.getName());
        }
    }

}
