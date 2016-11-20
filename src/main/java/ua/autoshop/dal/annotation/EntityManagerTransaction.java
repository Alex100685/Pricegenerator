package ua.autoshop.dal.annotation;

import java.lang.annotation.*;

/**
 * Created by Пользователь on 19.11.2016.
 */
//annotation wraps entity transaction with boilerplate code
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface EntityManagerTransaction {
}
