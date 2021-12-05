package com.example.recipe_app;

import android.app.Application;

public class myApp extends Application {
    NetworkingService networkingService = new NetworkingService();
    JsonService jsonService = new JsonService();

    public JsonService getJsonService() {
        return jsonService;
    }

    public NetworkingService getNetworkingService() {
        return networkingService;
    }
}
