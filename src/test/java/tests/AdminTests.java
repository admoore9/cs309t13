package tests;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.AdminDao;
import edu.iastate.models.Member;

public class AdminTests {

    AdminDao adminDao;

    @Before
    public void setUp() throws Exception {
        adminDao = new AdminDao();
//        Admin admin = new Admin("Sam", "sam", "123");
//        adminDao.save(admin);
    }

//    @Test
//    public void changeCurrentView() {
//        Admin admin = adminDao.getAdminById(1);
//        System.out.println("Name: " + admin.getName() + " View: " + admin.getCurrentView());
//        admin.setCurrentView(UserType.PLAYER);
//        System.out.println("Name: " + admin.getName() + " View: " + admin.getCurrentView());
//        adminDao.save(admin);
//    }
//
//    @Test
//    public void getAllAdminsTest() {
//        List<Admin> admins = adminDao.getAllAdmins();
//        System.out.println("Names:");
//        for (Admin admin : admins) {
//            System.out.println(admin.getName());
//        }
//    }
    
    @Test
    public void promoteTest() {
        Member member = adminDao.getMemberById(1);
        adminDao.promote(member);
    }

}
