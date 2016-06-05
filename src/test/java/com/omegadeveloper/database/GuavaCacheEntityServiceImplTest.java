package com.omegadeveloper.database;

import com.omegadeveloper.entity.DatastoreEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GuavaCacheEntityServiceImplTest {

    GuavaCacheEntityServiceImpl entityService = new GuavaCacheEntityServiceImpl();

    @Before
    public void setup(){
        entityService = new GuavaCacheEntityServiceImpl();
    }

    @Test
    public void testStoreEntity() throws Exception {
        DatastoreEntity entity =  new DatastoreEntity();
        entity.setId("1234");

        entityService.storeEntity(entity);
        DatastoreEntity storedEntity = entityService.getEntity("1234");

        assertEquals("The stored entity does not match the added entity", storedEntity, entity);
    }

    @Test
    public void testStoreEntityFor1Minute() throws Exception {
        DatastoreEntity entity =  new DatastoreEntity();
        entity.setId("1234");

        entityService.storeEntity(entity);

        Thread.sleep(59 * 1000);

        DatastoreEntity storedEntity = entityService.getEntity("1234");

        assertEquals("The stored entity does not match the added entity", storedEntity, entity);
    }

    @Test
    public void testCanNotStoreEntityForOver1Minute() throws Exception {
        DatastoreEntity entity =  new DatastoreEntity();
        entity.setId("1234");

        entityService.storeEntity(entity);

        DatastoreEntity storedEntity = entityService.getEntity("1234");

        assertEquals("The stored entity does not match the added entity", storedEntity, entity);

        Thread.sleep(61 * 1000);

        storedEntity = entityService.getEntity("1234");

        assertNull("The stored entity was not removed after one minute", storedEntity);
    }


    @Test
    public void testRemoveEntity() throws Exception {
        DatastoreEntity entity =  new DatastoreEntity();
        entity.setId("1234");

        entityService.storeEntity(entity);
        DatastoreEntity storedEntity = entityService.getEntity("1234");

        assertEquals("The stored entity does not match the added entity", storedEntity, entity);

        entityService.removeEntity("1234");
        assertNull("The stored entity was not removed", entityService.getEntity("1234"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testThrowsExceptionWhenLoadingSameIdTwice() throws Exception {
        DatastoreEntity firstEntity =  new DatastoreEntity();
        firstEntity.setId("1234");

        DatastoreEntity secondEntity =  new DatastoreEntity();
        secondEntity.setId("1234");

        entityService.storeEntity(firstEntity);
        entityService.storeEntity(secondEntity);

    }

    @Test(expected=IllegalArgumentException.class)
    public void testThrowsExceptionWhenLoadingNullId() throws Exception {
        DatastoreEntity entity =  new DatastoreEntity();
        entity.setId(null);

        entityService.storeEntity(entity);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testThrowsExceptionWhenLoadingEmptyId() throws Exception {
        DatastoreEntity entity =  new DatastoreEntity();
        entity.setId("");

        entityService.storeEntity(entity);
    }
}