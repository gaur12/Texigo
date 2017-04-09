package com.example.user.texigo.Model;

import java.util.List;

/**
 * Created by USER on 09-Apr-17.
 */

public class RouteData {
    private String originName;
    private String destinationName;
    private int distance;
    private List<RouteModel> routes;
    private RouteModel fastestRoute;
    private RouteModel cheapestRoute;

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<RouteModel> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteModel> routes) {
        this.routes = routes;
    }

    public RouteModel getFastestRoute() {
        return fastestRoute;
    }

    public void setFastestRoute(RouteModel fastestRoute) {
        this.fastestRoute = fastestRoute;
    }

    public RouteModel getCheapestRoute() {
        return cheapestRoute;
    }

    public void setCheapestRoute(RouteModel cheapestRoute) {
        this.cheapestRoute = cheapestRoute;
    }
}
