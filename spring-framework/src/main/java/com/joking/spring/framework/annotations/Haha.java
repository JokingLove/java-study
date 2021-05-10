package com.joking.spring.framework.annotations;


import com.joking.spring.framework.register.HahaRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author guoyongqiang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(HahaRegister.class)
public @interface Haha {

    String value();
}
