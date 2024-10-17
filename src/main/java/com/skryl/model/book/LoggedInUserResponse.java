
package com.skryl.model.book;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class LoggedInUserResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("authority_id")
    @Expose
    private Integer authorityId;
    @SerializedName("authority")
    @Expose
    private Authority authority;

}
