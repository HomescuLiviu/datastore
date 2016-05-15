package com.omegadeveloper.entity.database;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.omegadeveloper.entity.DatastoreEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class EntityServiceImpl implements EntityService {

    Map<String, DatastoreEntity> datastore = new ConcurrentHashMap<>();

    LoadingCache<String, DatastoreEntity> entityCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, DatastoreEntity>() {
                        @Override
                        public DatastoreEntity load(String id) throws Exception {
                            return getEntityFromDataStore(id);
                        }


                    }
            );

    private DatastoreEntity getEntityFromDataStore(String id) {
        return datastore.get(id);
    }

    @Override
    public void storeEntity(DatastoreEntity entity) {
            datastore.put(entity.getId(), entity);
           entityCache.put(entity.getId(), entity);
    }

    @Override
    public DatastoreEntity getEntity(String id) {
        try {
            return entityCache.get(id);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
       return null;
    }
}
