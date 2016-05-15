package com.omegadeveloper.entity.database;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.omegadeveloper.entity.DatastoreEntity;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class EntityServiceImpl implements EntityService {

    LoadingCache<String, DatastoreEntity> entityCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, DatastoreEntity>() {
                        @Override
                        public DatastoreEntity load(String id) throws Exception {
                            return getEntity(id);
                        }
                    }
            );

    @Override
    public void storeEntity(DatastoreEntity entity) {
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
