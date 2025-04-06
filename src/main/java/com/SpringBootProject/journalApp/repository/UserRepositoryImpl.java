package com.SpringBootProject.journalApp.repository;

import com.SpringBootProject.journalApp.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {
    @Autowired
    public MongoTemplate mongoTemplate;

      public List<UserEntity> getUserforSA(){
          Query query = new Query();
          query.addCriteria(Criteria.where("email").regex(".*@gmail\\.com$", "i"));
          query.addCriteria(Criteria.where("sentiment").is(true));
          List<UserEntity> users = mongoTemplate.find(query,UserEntity.class);
       return users;
      }

}
