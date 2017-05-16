package com.rxjava_github.Model;

/**
 * Created by ankurkhandelwal on 16/05/17.
 */

import com.google.gson.annotations.SerializedName;

public class User {
        @SerializedName("id")
        public String mId;
        @SerializedName("login")
        public String mLogin;
        @SerializedName("avatar_url")
        public String mAvatarUrl;
}
