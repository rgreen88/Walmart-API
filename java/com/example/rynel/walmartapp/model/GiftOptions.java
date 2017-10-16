package com.example.rynel.walmartapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rynel on 10/16/2017.
 */

public class GiftOptions {

    @SerializedName("allowGiftWrap")
    @Expose
    private Boolean allowGiftWrap;
    @SerializedName("allowGiftMessage")
    @Expose
    private Boolean allowGiftMessage;
    @SerializedName("allowGiftReceipt")
    @Expose
    private Boolean allowGiftReceipt;

    public Boolean getAllowGiftWrap() {
        return allowGiftWrap;
    }

    public void setAllowGiftWrap(Boolean allowGiftWrap) {
        this.allowGiftWrap = allowGiftWrap;
    }

    public Boolean getAllowGiftMessage() {
        return allowGiftMessage;
    }

    public void setAllowGiftMessage(Boolean allowGiftMessage) {
        this.allowGiftMessage = allowGiftMessage;
    }

    public Boolean getAllowGiftReceipt() {
        return allowGiftReceipt;
    }

    public void setAllowGiftReceipt(Boolean allowGiftReceipt) {
        this.allowGiftReceipt = allowGiftReceipt;
    }

}