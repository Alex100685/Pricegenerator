package ua.autoshop.dal.annotation;

import java.lang.annotation.*;

/**
 * Created by Пользователь on 20.11.2016.
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface AllowNullResult {
}
