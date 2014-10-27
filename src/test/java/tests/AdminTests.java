package tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.AdminDao;
import edu.iastate.models.Admin;
import edu.iastate.models.Member.UserType;

public class AdminTests {

    AdminDao adminDao;

    @Before
    public void setUp() throws Exception {
        adminDao = new AdminDao();
    }

    @Test
    public void changeCurrentView() {
        Admin admin = adminDao.getAdminById(7);
        System.out.println("Name: " + admin.getName() + " View: " + admin.getCurrentView());
        admin.setCurrentView(UserType.PLAYER);
        System.out.println("Name: " + admin.getName() + " View: " + admin.getCurrentView());
        adminDao.saveAdmin(admin);
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
