package com.example.dainim.controller;

import com.example.dainim.model.Anime;
import com.example.dainim.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.List;

/**
 * userHelper class, use to Connect FireBase to Application
 */
public class UserHelper {
    /**
     * Collection for Data Base
     */
    private static final String COLLECTION_NAME = "users";

    // --- COLLECTION REFERENCE ---

    /**
     * getUsersCollection, get all User in data Base
     * @return CollectionReference
     */
    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    /**
     * createUser, add User in data base
     * @param uid String
     * @param username String
     * @param fav ArrayList<Anime></Anime>
     * @return Task<Void>
     */
    public static Task<Void> createUser(String uid, String username, List<Anime> fav) {
        User userToCreate = new User(uid,username,fav);
        return UserHelper.getUsersCollection().document(uid).set(userToCreate);
    }

    // --- GET ---

    /**
     * getUser, get user in data base from uid
     * @param uid String
     * @return Task<DocumentSnapshot></DocumentSnapshot>
     */
    public static Task<DocumentSnapshot> getUser(String uid){
        return UserHelper.getUsersCollection().document(uid).get();
    }

    // --- UPDATE ---

    /**
     * updateUsername, update new username for user in data base
     * @param username String
     * @param uid String
     * @return Task<Void></Void>
     */
    public static Task<Void> updateUsername(String username, String uid) {
        return UserHelper.getUsersCollection().document(uid).update("username", username);
    }

    /**
     * updateFavAnime, update new favorite list Anime
     * @param uid String
     * @param fav_list List<Anime></Anime>
     * @return Task<Void></Void>
     */
    public static Task<Void> updateFavAnime(String uid, List<Anime> fav_list) {
        return UserHelper.getUsersCollection().document(uid).update("fav_list", fav_list);
    }

    // --- DELETE ---

    /**
     * deleteUser, delete user in data base
     * @param uid String
     * @return Task<Void></Void>
     */
    public static Task<Void> deleteUser(String uid) {
        return UserHelper.getUsersCollection().document(uid).delete();
    }
}
