package com.example.dione.attendanceapp.api.response_models.Logs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dione on 11/08/2016.
 */
public class Result {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("time_in")
    @Expose
    private String timeIn;
    @SerializedName("time_out")
    @Expose
    private String timeOut;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The timeIn
     */
    public String getTimeIn() {
        return timeIn;
    }

    /**
     *
     * @param timeIn
     * The time_in
     */
    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    /**
     *
     * @return
     * The timeOut
     */
    public String getTimeOut() {
        return timeOut;
    }

    /**
     *
     * @param timeOut
     * The time_out
     */
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
