package com.omegadeveloper.database;

import com.omegadeveloper.entity.DatastoreEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GuavaCacheEntityServiceImplTest {

    GuavaCacheEntityServiceImpl entityService = new GuavaCacheEntityServiceImpl();

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
}