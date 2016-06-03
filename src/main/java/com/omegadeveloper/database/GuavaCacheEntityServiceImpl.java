package com.omegadeveloper.database;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.omegadeveloper.entity.DatastoreEntity;

import java.util.Map;
import java.util.concurrent.*;

public class GuavaCacheEntityServiceImpl implements EntityService {

    private static ScheduledExecutorService cacheCleaner = Executors.newScheduledThreadPool(10);

    private static Map<String, DatastoreEntity> datastore = new ConcurrentHashMap<>();

    private static LoadingCache<String, DatastoreEntity> entityCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, DatastoreEntity>() {
                        @Override
                        public DatastoreEntity load(String id) throws Exception {
                            return getEntityFromDataStore(id);
                        }
                    }
            );
    static {
        cacheCleaner.scheduleAtFixedRate( () -> entityCache.cleanUp(), 0, 2, TimeUnit.MINUTES );
    }

    private static DatastoreEntity getEntityFromDataStore(String id) {
        return datastore.get(id);
    }

    @Override
    public void storeEntity(DatastoreEntity entity) {
       datastore.put(entity.getId(), entity);
       entityCache.put(entity.getId(), entity);
}

    @Override
    public DatastoreEntity getEntity(String id) {
        return entityCache.getIfPresent(id);
    }

    @Override
    public void removeEntity(String id) {
        entityCache.invalidate(id);
        datastore.remove(id);
    }
}
