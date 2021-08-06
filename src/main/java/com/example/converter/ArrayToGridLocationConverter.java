package com.example.converter;

import com.example.exception.InvalidInputException;
import com.example.model.GridLocation;
import org.springframework.stereotype.Component;

@Component
public class ArrayToGridLocationConverter {

    public GridLocation convert(int[] location) {
        if (location == null || location.length != 2) {
            throw new InvalidInputException("location must have exactly two elements.");
        }

        return new GridLocation(location[0], location[1]);
    }
}
