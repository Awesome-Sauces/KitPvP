package me.alpha.kitpvp.Data;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.MaxMoneyException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.UUID;

import static com.earth2me.essentials.api.Economy.*;
import static com.earth2me.essentials.api.Economy.setMoney;

public class GoldData {

    public static void setEconomy(String player, int amount){
        try{
            setMoney(ChatColor.stripColor(Bukkit.getPlayer(UUID.fromString(player)).getDisplayName()), amount);
        } catch (UserDoesNotExistException | NoLoanPermittedException | MaxMoneyException e) {
            e.printStackTrace();
        }

    }

    public static void addEconomy(String player, int amount){
        try{
            add(ChatColor.stripColor(Bukkit.getPlayer(UUID.fromString(player)).getDisplayName()), amount);
        } catch (UserDoesNotExistException | NoLoanPermittedException | MaxMoneyException e) {
            e.printStackTrace();
        }

    }

    public static void removeEconomy(String player, int amount){
        try{
            subtract(ChatColor.stripColor(Bukkit.getPlayer(UUID.fromString(player)).getDisplayName()), amount);
        } catch (UserDoesNotExistException | NoLoanPermittedException | MaxMoneyException e) {
            e.printStackTrace();
        }
    }

    public static int getEconomy(String player){
        try{
            return (int) getMoney(ChatColor.stripColor(Bukkit.getPlayer(UUID.fromString(player)).getDisplayName()));
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
                setMoney(ChatColor.stripColor(Bukkit.getPlayer(UUID.fromString(player)).getDisplayName()), 0);
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
