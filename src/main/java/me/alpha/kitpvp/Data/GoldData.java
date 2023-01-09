package me.alpha.kitpvp.Data;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.MaxMoneyException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.math.BigDecimal;
import java.util.UUID;

import static com.earth2me.essentials.api.Economy.*;
import static com.earth2me.essentials.api.Economy.setMoney;

public class GoldData {

    public static void setEconomy(String player, int amount){
        try{
            setMoney(UUID.fromString(player), new BigDecimal(amount));
        } catch (UserDoesNotExistException | NoLoanPermittedException | MaxMoneyException e) {
            e.printStackTrace();
        }

    }

    public static void addEconomy(String player, int amount){
        try{
            add(UUID.fromString(player), new BigDecimal(amount));
        } catch (UserDoesNotExistException | NoLoanPermittedException | MaxMoneyException e) {
            e.printStackTrace();
        }

    }

    public static void removeEconomy(String player, int amount){
        try{
            subtract(UUID.fromString(player), new BigDecimal(amount));
        } catch (UserDoesNotExistException | NoLoanPermittedException | MaxMoneyException e) {
            e.printStackTrace();
        }
    }

    public static int getEconomy(String player){
        try{
            return getMoneyExact(UUID.fromString(player)).intValue();
        } catch (UserDoesNotExistException e) {
            return 0;
        }

    }

    public static int getEconomy(String name, boolean a){
        try{
            return (int) getMoney(ChatColor.stripColor(name));
        } catch (UserDoesNotExistException e) {
            return 0;
        }

    }

    public static boolean hasEconomy(String player){
        try{
            if(playerExists(UUID.fromString(player))){
                return true;
            }else{
                setMoney(UUID.fromString(player), new BigDecimal(0));
            }
            return true;
        } catch (UserDoesNotExistException | NoLoanPermittedException | MaxMoneyException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static double getGoldRequireMentAmount(String player){
        return GoldRequirementData.getGoldRequirement((int)ClassInstances.prestigeData.getValue(player));
    }

}
