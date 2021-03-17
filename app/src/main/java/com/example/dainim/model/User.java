package com.example.dainim.model;

import java.util.List;
import reactor.util.annotation.Nullable;

public class User {

        private String uId;
        private String username;
        private List<Anime> fav_list;

        public User(){

        }

        public User(String uId,String username,List<Anime> fav_list){
            this.uId = uId;
            this.username = username;
            this.fav_list = fav_list;
        }

        // ------- Getters --------

        public String getuId(){return uId;}
        public String getUsername(){return username;}
        public List<Anime> getFav_list() {return fav_list;}

        // ----------------Setters ----------

        public void setFav_list(List<Anime> fav_list) { this.fav_list = fav_list; }
        public void setuId(String uId) { this.uId = uId; }
        public void setUsername(String username) { this.username = username; }

}
