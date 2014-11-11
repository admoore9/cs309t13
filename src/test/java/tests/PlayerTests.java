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

import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Player;

public class PlayerTests {

    PlayerDao playerDao;
    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;
    EntityTransaction transaction;
    TypedQuery<Player> query;
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
        playerDao = new PlayerDao(entityManagerFactory);
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void getAllPlayersTest() {
        
        // Given
        when(entityManager.createQuery(selectAllQuery, Player.class)).thenReturn(query);
        List<Player> expectedPlayerList = new ArrayList<Player>();
        expectedPlayerList.add(new Player("Brian", "mike", "123"));
        expectedPlayerList.add(new Player("Andrew", "mike", "123"));
        expectedPlayerList.add(new Player("Shubang", "mike", "123"));
        // When
        when(query.getResultList()).thenReturn(expectedPlayerList);
        
        List<Player> actualPlayerList = playerDao.getAllPlayers();
        // Then
        assertNotNull(actualPlayerList);
        assertSame(expectedPlayerList, actualPlayerList);
    }
    
    @Test
    public void getPlayerByIdTest() {
        // Given
        when(entityManager.createQuery(selectByIdQuery, Player.class)).thenReturn(query);
        Player expectedPlayer = new Player("test1", "test1", "asdf");
        // When
        when(query.getSingleResult()).thenReturn(expectedPlayer);
        
        Player actualPlayer = playerDao.getPlayerById(1, false);
        // Then
        assertNotNull(actualPlayer);
        assertSame(expectedPlayer, actualPlayer);
    }
    
}

