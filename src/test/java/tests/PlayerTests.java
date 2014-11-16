package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;

public class PlayerTests {

    MemberDao playerDao;
    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;
    EntityTransaction transaction;
    TypedQuery<Member> query;
    String selectAllQuery = "from Player";
    String selectByIdQuery = "from Player p where p.member_id = 0";
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        entityManagerFactory = mock(EntityManagerFactory.class);
        entityManager = mock(EntityManager.class);
        transaction = mock(EntityTransaction.class);
        query = mock(TypedQuery.class);
        
        // Given
        playerDao = new MemberDao(entityManagerFactory);
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void getAllPlayersTest() {
        
        // Given
        when(entityManager.createQuery(selectAllQuery, Member.class)).thenReturn(query);
        List<Member> expectedPlayerList = new ArrayList<Member>();
        expectedPlayerList.add(new Member("Brian", "mike", "123"));
        expectedPlayerList.add(new Member("Andrew", "mike", "123"));
        expectedPlayerList.add(new Member("Shubang", "mike", "123"));
        // When
        when(query.getResultList()).thenReturn(expectedPlayerList);
        
        List<Member> actualPlayerList = playerDao.getAllByUserType(UserType.PLAYER);
        // Then
        assertNotNull(actualPlayerList);
        assertSame(expectedPlayerList, actualPlayerList);
    }
    
    @Test
    public void getPlayerByIdTest() {
        // Given
        when(entityManager.createQuery(selectByIdQuery, Member.class)).thenReturn(query);
        Member expectedPlayer = new Member("test1", "test1", "asdf");
        // When
        when(query.getSingleResult()).thenReturn(expectedPlayer);
        
        Member actualPlayer = playerDao.getMemberById(1);
        // Then
        assertNotNull(actualPlayer);
        assertSame(expectedPlayer, actualPlayer);
    }
    
}

