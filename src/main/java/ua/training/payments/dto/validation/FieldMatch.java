package ua.training.payments.dto.validation;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Objects;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatch.FieldMatchValidatorImpl.class)
@Repeatable(FieldMatch.List.class)
public @interface FieldMatch {

    String first();

    String second();

    String message() default "{field.not.match}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {

        FieldMatch[] value();
    }

    class FieldMatchValidatorImpl implements ConstraintValidator<FieldMatch, Object> {

        private String firstFieldName;
        private String secondFieldName;

        @Override
        public void initialize(final FieldMatch constraintAnnotation) {
            firstFieldName = constraintAnnotation.first();
            secondFieldName = constraintAnnotation.second();
        }

        @Override
        public boolean isValid(final Object value, final ConstraintValidatorContext context) {
            try {
                final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
                final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

                return Objects.isNull(firstObj) && Objects.isNull(secondObj) ||
                        Objects.equals(firstObj, secondObj);
            } catch (final Exception ignore) {
                return false;
            }
        }

    }

}
