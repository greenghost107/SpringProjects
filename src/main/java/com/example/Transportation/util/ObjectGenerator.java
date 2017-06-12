package com.example.Transportation.util;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Michael on 6/11/2017.
 */
@Component
public class ObjectGenerator {

    public ObjectGenerator() {
    }

    public List generateObjectList(int amount, Class<?> objectType) {

        return EnhancedRandom.randomListOf(amount, objectType);
    }
}
