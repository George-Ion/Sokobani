package com.george;

import javafx.scene.image.Image;
import java.util.HashMap;

public class ImageLoader {

    private static final HashMap<String, Image> cache = new HashMap<>();

    public static Image load(String name) {
        if (!cache.containsKey(name)) {
            try {
                Image img = new Image(ImageLoader.class.getResourceAsStream("/" + name));
                cache.put(name, img);
            } catch (Exception e) {
                System.err.println("Could not load image: " + name);
                return null;
            }
        }
        return cache.get(name);
    }
}