package com.corgi.r2dbc.convert;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * @title: MoneyReadConvert
 * @description:
 * @author: dengmiao
 * @create: 2019-05-28 15:24
 **/
public class MoneyReadConvert implements Converter<Long, Money> {

    @Override
    public Money convert(Long aLong) {
        return Money.ofMinor(CurrencyUnit.of("CNY"), aLong);
    }
}
