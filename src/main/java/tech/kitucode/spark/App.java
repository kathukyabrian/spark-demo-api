package tech.kitucode.spark;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.kitucode.spark.domain.Friend;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class App {

    static Gson gson = new Gson();

    // very important for you to log -> create a logger
    // helps in debugging
    private static final Logger logger = LoggerFactory.getLogger(App.class);


    // create a friends api
    // add a friend - POST ->
    // see your friends - GET
    // create a POJO that represents a friend..... : DONE

    // create a list to store the friends -- memory - we will be pushing our friends here
    // RAM - memory -> not using a db
    // demo purposes
    private static List<Friend> friendList = new ArrayList<>();

    public static void main(String[] args) {

        // convention -> noun -> plural
        // POST http://localhost:4567/friends - we will see this when we go to postman
        post("/friends", (req, res) -> {
            // logic goes here
            // normally this is not how you write code
            // good code is layered -> service, dao, domain, repository
            // for demo purposed we will have our logic here

            logger.info("Request to save a friend : {}", req.body());
            // we will query the request body
            String friendStr = req.body();

            // convert into into Friend -> deserialization -> json -> Friend
            // reflection in action
            Friend friend = gson.fromJson(friendStr, Friend.class);

            // we will push the instance of Friend into our list -> array list
            friendList.add(friend);

            // adding a header to res
            res.header("Content-Type", "application/json");

            // return the response -> send back what was created ->
            return friend;
        }, gson::toJson);


        get("/friends", (req, res) -> {
            logger.info("Request to get all friends");

            List<Friend> friends = friendList;

            // logic is just logic
            // in this case we used memory
            // but in production

            // here you will need to connect to a db if you are using one
            // to do that you can use jdbc - java database connector /

            // use ORM - Object Relational Mapper -> sql statements - high level language db.create()
            // an example is Hibernate........

            // remember : you have to get the theory right, before writing any code
            // Hibernate - hibernate docs - read and understand them then you can implement

            // creating a dummy API

            // we are doing an API - we need to return data in json
            res.header("Content-Type", "application/json");
            return friends;
        }, gson::toJson);

    }
}
