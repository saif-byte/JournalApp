package com.practice.journalApp.Repository;

import com.practice.journalApp.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepoImpl {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<User> getUserWithEmail(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").exists(true));
        query.addCriteria(Criteria.where("email").ne(null).ne(""));

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
