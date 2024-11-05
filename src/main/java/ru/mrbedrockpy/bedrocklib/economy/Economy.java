package ru.mrbedrockpy.bedrocklib.economy;

import org.bukkit.plugin.Plugin;
import ru.mrbedrockpy.bedrocklib.util.SQLManager;

import java.util.*;

public class Economy {

    public static final Economy ECONOMY = new Economy();

    private final Map<Plugin, List<Currency>> currencies = new HashMap<>();

    public Economy() {}

    public void register(Plugin plugin, Currency currency) {
        String id = currency.getId();
        currency.setBalances(SQLManager.loadCurrency(id));
        if (!currencies.containsKey(plugin)) {
            currencies.put(plugin, Collections.singletonList(currency));
            return;
        }
        currencies.get(plugin).add(currency);
    }

    public Currency getCurrency(Plugin plugin, String id) {
        if (!currencies.containsKey(plugin)) return null;
        List<Currency> list = currencies.get(plugin);
        for (Currency currency : list) if (currency.getId().equals(id)) return currency;
        return null;
    }
}
