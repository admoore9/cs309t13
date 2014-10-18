package edu.iastate.tests;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import edu.iastate.dao.OfficialDao;
import edu.iastate.models.Official;

public class OfficialTests {

    @Test
    public void returnAllOfficialTest() {
        OfficialDao officialDao = new OfficialDao();
        List<Official> officials = officialDao.returnAllOfficials();
        System.out.println("Names:");
        for (Official official : officials) {
            System.out.println(official.getName());
        }
    }
}
