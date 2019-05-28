package com.corgi.r2dbc.convert;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * @title: MoneyWriteConvert
 * @description:
 * @author: dengmiao
 * @create: 2019-05-28 15:26
 **/
public class MoneyWriteConvert implements Converter<Money, Long> {
    @Override
    public Long convert(Money money) {
        return money.getAmountMajorLong();
    }
}
