package com.example.dainim.model;

import java.util.List;

/**
 * User class, Defines User data for bdd
 */
public class User {
        /**
         * uId for one User
         */
        private String uId;
        /**
         * Username
         */
        private String username;
        /**
         * the favorite anime list
         */
        private List<Anime> fav_list;

        /**
         * Constructor for FireBase
         */
        public User(){

        }

        /**
         * Constructor
         * @param uId String
         * @param username String
         * @param fav_list List<Anime>
         */
        public User(String uId,String username,List<Anime> fav_list){
            this.uId = uId;
            this.username = username;
            this.fav_list = fav_list;
        }

        // ------- Getters --------

        /**
         * getuId from User
         * @return String
         */
        public String getuId(){return uId;}

        /**
         * getUsername from User
         * @return String
         */
        public String getUsername(){return username;}

        /**
         * get favorite_list from User
         * @return List<Anime>
         */
        public List<Anime> getFav_list() {return fav_list;}

        // ----------------Setters ----------

        /**
         * Sets a new favorite anime list
         * @param fav_list List<Anime>
         */
        public void setFav_list(List<Anime> fav_list) { this.fav_list = fav_list; }

        /**
         * sets a new uId for User
         * @param uId String
         */
        public void setuId(String uId) { this.uId = uId; }
        /**
         * sets a new Username for User
         * @param username String
         */
        public void setUsername(String username) { this.username = username; }
}
