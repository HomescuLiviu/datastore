package com.omegadeveloper.entity.database;

import com.omegadeveloper.entity.DatastoreEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by liviu on 5/15/2016.
 */
public class EntityServiceImplTest {

    EntityServiceImpl entityService = new EntityServiceImpl();

    @Test
    public void testStoreEntity() throws Exception {
        DatastoreEntity entity =  new DatastoreEntity();
        entity.setId("1234");

        entityService.storeEntity(entity);
        DatastoreEntity storedEntity = entityService.getEntity("1234");

        assertEquals("The stored entity does not mach the added entity", storedEntity, entity);
    }

    @Test
    public void testGetEntity() throws Exception {

    }
}