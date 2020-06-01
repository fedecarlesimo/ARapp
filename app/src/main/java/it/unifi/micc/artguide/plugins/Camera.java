package it.unifi.micc.artguide.plugins;

interface Camera {

    void start();
    void stop();

    int getCameraOrientation();
}
