package me.alpha.kitpvp.utils;

import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.Commands.KitPvpCommand;
import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorListener;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler;
import me.alpha.kitpvp.PitRemake.Boosters.BoosterEvents;
import me.alpha.kitpvp.PitRemake.Factions.ArchAngelFaction;
import me.alpha.kitpvp.PitRemake.Factions.ArmageddonFaction;
import me.alpha.kitpvp.PitRemake.Factions.KingFaction;
import me.alpha.kitpvp.PitRemake.Fishing.FishingCore;
import me.alpha.kitpvp.PitRemake.Gems.gemEvents;
import me.alpha.kitpvp.PitRemake.Heresy.HeresyMenu;
import me.alpha.kitpvp.PitRemake.MysticWell.New.EnchantMechanic;
import me.alpha.kitpvp.PitRemake.Perks.KillStreaks.KillStreakCore;
import me.alpha.kitpvp.PitRemake.Perks.PerkHandler;
import me.alpha.kitpvp.PitRemake.Perks.gui.KillStreakPerkGUI;
import me.alpha.kitpvp.PitRemake.Perks.gui.PerkSelectGUI;
import me.alpha.kitpvp.PitRemake.Perks.gui.PermanentUpgrades;
import me.alpha.kitpvp.PitRemake.Perks.items.GoldenHeadItem;
import me.alpha.kitpvp.PitRemake.Perks.items.SoupItem;
import me.alpha.kitpvp.PitRemake.PitCommands.Repairs.ClickHandler;
import me.alpha.kitpvp.PitRemake.PitCommands.View.ViewCore;
import me.alpha.kitpvp.PitRemake.RenownShop.CookieMonster.MonsterHandler;
import me.alpha.kitpvp.PitRemake.RenownShop.gui.RenownShopGUI;
import me.alpha.kitpvp.PitRemake.RenownShop.gui.RenownShopKillstreaksGUI;
import me.alpha.kitpvp.PitRemake.RenownShop.gui.RenownShopUpgradesGUI;
import me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore;
import me.alpha.kitpvp.events.*;

import java.util.ArrayList;

public class EventRegistrar {
    public static void registerEvents(){

        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new KillStreakCore(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new EnchantMechanic(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ChatManager(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new KillStreakPerkGUI(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ScoreboardCore(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new MainDamageEvent(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ArmorListener(new ArrayList<>()), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new MonsterHandler(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ArchAngelFaction(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ArmageddonFaction(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new KingFaction(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new PerkHandler(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new BoosterEvents(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new OnJoin(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ReduxEvents(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ReduxPlayerHandler(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new GeneralEvents(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ClickHandler(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new PermanentUpgrades(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new GoldenHeadItem(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new SoupItem(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new FishingCore(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ViewCore(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new RenownShopUpgradesGUI(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new RenownShopGUI(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new RenownShopKillstreaksGUI(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new PerkSelectGUI(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new gemEvents(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new HeresyMenu(), KitPvP.INSTANCE);

    }
}
