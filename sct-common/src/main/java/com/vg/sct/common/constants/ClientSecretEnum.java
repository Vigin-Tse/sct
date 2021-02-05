package com.vg.sct.common.constants;

public enum ClientSecretEnum {

    SCT_WEB_CLIENT("SCT_WEB", "sct@2021");

    private String client;

    private String secret;

    ClientSecretEnum(String client, String serect){
        this.client = client;
        this.secret = serect;
    }

    public String getClient(){
        return client;
    }

    public String getSecret(){
        return secret;
    }
}
