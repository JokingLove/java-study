package com.baomidou.mybatisplus.samples.crud.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import sun.security.pkcs11.Secmod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author joking
 */
@Slf4j
public class StringUtil {


    public static Float transPercentToFloat(String val) {
        try {
            if (StringUtils.isNotEmpty(val)) {
                String value = val.split("%")[0];
                return Float.parseFloat(value.trim()) / 100;
            }
        } catch (Exception e) {
            log.error("transPercentToFloat error: {}", e.getMessage());
        }
        return 0f;
    }

    public static Float stringToFloat(String val) {
        try{
            if(StringUtils.isNotEmpty(val)) {
                return Float.parseFloat(val.trim());
            }
        } catch (Exception e) {
            log.error("stringToFloat error: {}", e.getMessage());
        }
        return 0f;
    }

    public static LocalDate stringToLocalDate(String val) {
        try{
            if(StringUtils.isNotEmpty(val.trim())) {
                return LocalDate.parse(val);
            }
        } catch (Exception e) {
            log.error("stringToLocalDate error: {}", e.getMessage());
        }
        return null;
    }

    public static BigDecimal stringToBigDecimal(String val) {
        try{
            if(StringUtils.isNotEmpty(val)) {
                return BigDecimal.valueOf(Double.parseDouble(val.trim()));
            }
        } catch (Exception e) {
            log.error("stringToBigDecimal error: {}", e.getMessage());
        }
        return BigDecimal.ONE;
    }

    public static void main(String[] args) {
        System.out.println(transPercentToFloat("-56.6%"));
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        int size = list.size();
        System.out.println(list.subList(1,size));
    }
}
