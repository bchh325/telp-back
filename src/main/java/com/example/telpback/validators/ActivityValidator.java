package com.example.telpback.validators;

import com.example.telpback.models.Activity;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ActivityValidator implements ConstraintValidator<ActivityConstraints, Activity> {
    @Override
    public boolean isValid(Activity activity, ConstraintValidatorContext constraintValidatorContext) {
        if (activity == null) {
            return true;
        }

        boolean likedPlacesExist = activity.getLikedPlaces() != null;
        boolean visitedPlacesExist = activity.getVisitedPlaces() != null;

        if (likedPlacesExist && visitedPlacesExist) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Both likedPlaces and visitedPlaces cannot exist.")
                    .addConstraintViolation();

            return false;
        }

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext
                .buildConstraintViolationWithTemplate("Provided list cannot be empty.")
                .addConstraintViolation();

        if (likedPlacesExist) {
            return !activity.getLikedPlaces().isEmpty();
        } else {
            return !activity.getVisitedPlaces().isEmpty();
        }

    }
}
