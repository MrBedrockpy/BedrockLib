package ru.mrbedrockpy.bedrocklib.economy;

import java.util.HashMap;
import java.util.Map;

public class Currency {

    private final String id;

    private String displayName;

    private char symbol;

    private final Map<String, Integer> balances;

    public Currency(String id) {
        this.id = id;
        this.displayName = id;
        this.symbol = ' ';
        this.balances = new HashMap<>();
    }

    public Currency(String id, String displayName) {
        this.id = id;
        this.displayName = id;
        this.symbol = ' ';
        this.balances = new HashMap<>();
    }

    public Currency(String id, char symbol) {
        this.id = id;
        this.displayName = id;
        this.symbol = symbol;
        this.balances = new HashMap<>();
    }

    public Currency(String id, String displayName, char symbol) {
        this.id = id;
        this.displayName = displayName;
        this.symbol = symbol;
        this.balances = new HashMap<>();
    }

    public void addBalance(String Player, int amount) {
        if (!balances.containsKey(Player)) {
            balances.put(Player, amount);
            return;
        }
        balances.put(Player, balances.get(Player) + amount);
    }

    public void subtractBalance(String Player, int amount) {
        if (!balances.containsKey(Player)) {
            balances.put(Player, -amount);
        }
        balances.put(Player, balances.get(Player) - amount);
    }

    public void setBalance(String Player, int amount) {
        balances.put(Player, amount);
    }

    public void resetBalance(String Player) {
        balances.put(Player, 0);
    }

    public int getBalance(String Player) {
        if (!balances.containsKey(Player)) return 0;
        return balances.get(Player);
    }

    public String getBalanceString(String Player) {
        if (!balances.containsKey(Player)) return "0" + symbol;
        return String.valueOf(balances.get(Player)) + symbol;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Map<String, Integer> getBalances() {
        return balances;
    }

    public void setBalances(Map<String, Integer> balances) {
        this.balances.clear();
        this.balances.putAll(balances);
    }
}
