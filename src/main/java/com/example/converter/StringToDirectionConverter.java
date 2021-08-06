package com.example.converter;

import com.example.exception.InvalidInputException;
import com.example.service.Direction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StringToDirectionConverter {

    public List<Direction> convert(String instructions) {
        if (instructions == null || instructions.isBlank()) {
            throw new InvalidInputException("instructions must be provided.");
        }

        var directionList = new ArrayList<Direction>();

        for (char character : instructions.toCharArray()) {
            var direction = Direction.from(character)
                    .orElseThrow(() -> new InvalidInputException(String.format("unable to convert '%s' to direction.", character)));

            directionList.add(direction);
        }

        return directionList;
    }
}
