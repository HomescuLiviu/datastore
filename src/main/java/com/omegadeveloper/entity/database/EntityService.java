package com.omegadeveloper.entity.database;


import com.omegadeveloper.entity.DatastoreEntity;

public interface EntityService {

    void storeEntity(DatastoreEntity entity);

    DatastoreEntity getEntity(String id);


}
