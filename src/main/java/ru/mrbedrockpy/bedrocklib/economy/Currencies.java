package ru.mrbedrockpy.bedrocklib.economy;

import ru.mrbedrockpy.bedrocklib.BedrockLib;

public class Currencies {

    public static final Currency COIN = register(new Currency("coin", "Coin", '$'));

    private static Currency register(Currency currency) {
        Economy.ECONOMY.register(BedrockLib.getInstance(), currency);
        return currency;
    }

    public static void initialize() {}

}
