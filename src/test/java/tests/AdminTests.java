package tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.AdminDao;
import edu.iastate.models.Admin;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;
import edu.iastate.models.Player;

public class AdminTests {

    AdminDao adminDao;

    @Before
    public void setUp() throws Exception {
        adminDao = new AdminDao();
        Admin admin = new Admin("Sam", "sam", "123");
        adminDao.save(admin);
    }

    @Test
    public void changeCurrentView() {
        Admin admin = adminDao.getAdminById(1);
        System.out.println("Name: " + admin.getName() + " View: " + admin.getCurrentView());
        admin.setCurrentView(UserType.PLAYER);
        System.out.println("Name: " + admin.getName() + " View: " + admin.getCurrentView());
        adminDao.save(admin);
    }

    @Test
    public void getAllAdminsTest() {
        List<Admin> admins = adminDao.getAllAdmins();
        System.out.println("Names:");
        for (Admin admin : admins) {
            System.out.println(admin.getName());
        }
    }
    
    @Test
    public void promoteTest() {
        Member member = new Member("member1", "member1", "123");
        adminDao.save(member);
        member.setUserType(UserType.ADMIN);
        adminDao.promote(member);
        
        Player player1 = new Player("player1", "player1", "123");
        adminDao.save(player1);
        player1.setUserType(UserType.ADMIN);
        adminDao.promote(player1);
        
        Player player2 = new Player("player2", "player2", "123");
        adminDao.save(player2);
        player2.setUserType(UserType.ADMIN);
        adminDao.promote(player2);
    }

}
