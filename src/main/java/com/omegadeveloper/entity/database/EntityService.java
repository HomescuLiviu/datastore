package com.omegadeveloper.entity.database;


import javax.swing.text.html.parser.Entity;

public interface EntityService {

    void storeEntity(Entity entity);

    Entity getEntity(String id);


}
