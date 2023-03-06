package me.alpha.kitpvp.PitRemake.MysticWell.New;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.New.BowEnchants.*;
import me.alpha.kitpvp.PitRemake.MysticWell.New.ResourceEnchants.GoldBoostEnchant;
import me.alpha.kitpvp.PitRemake.MysticWell.New.ResourceEnchants.GoldBumpEnchant;
import me.alpha.kitpvp.PitRemake.MysticWell.New.ResourceEnchants.SweatyEnchant;
import me.alpha.kitpvp.PitRemake.MysticWell.New.ResourceEnchants.XpBoostEnchant;
import me.alpha.kitpvp.PitRemake.MysticWell.New.SwordEnchants.*;
import me.alpha.kitpvp.utils.EnchantSound;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.script.ScriptException;
import java.util.*;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class EnchantMechanic implements Listener {
    static HashMap<String, MysticEnchant> mysticEnchants = new HashMap<>();

    public static void registerEnchant(MysticEnchant enchant) {
        if(mysticEnchants.containsKey(enchant.getRefID())) return;
        else mysticEnchants.put(enchant.getRefID(), enchant);
    }

    public static MysticEnchant getEnchant(String string){
        if(mysticEnchants.containsKey(string)) return mysticEnchants.get(string);
        return null;
    }

    public static void autoRegistry(){
        // Resource Enchants
        registerEnchant(new SweatyEnchant());
        registerEnchant(new GoldBumpEnchant());
        registerEnchant(new GoldBoostEnchant());
        registerEnchant(new XpBoostEnchant());

        // Sword Enchants
        registerEnchant(new BillionaireEnchant());
        registerEnchant(new PerunEnchant());
        registerEnchant(new GambleEnchant());
        registerEnchant(new ExecutionerEnchant());

        registerEnchant(new BeatTheSpammerEnchant());
        registerEnchant(new BerserkerEnchant());
        registerEnchant(new BountyReaperEnchant());
        registerEnchant(new ComboDamageEnchant());
        registerEnchant(new ComboXpEnchant());
        registerEnchant(new CriticallyRichEnchant());
        registerEnchant(new DiamondStompEnchant());

        // Bow Enchants
        registerEnchant(new ArrowArmoryEnchant());
        registerEnchant(new ExplosiveEnchant());
        registerEnchant(new FasterThanTheirShadowEnchant());
        registerEnchant(new FletchingEnchant());
        registerEnchant(new JumpSpammerEnchant());
        registerEnchant(new MegalongBowEnchant());
        registerEnchant(new ParasiteEnchant());
        registerEnchant(new PullBowEnchant());
        registerEnchant(new SprintDrainEnchant());
        registerEnchant(new TelebowEnchant());
        registerEnchant(new VolleyEnchant());
        registerEnchant(new WaspEnchant());
    }

    private static Boolean percentChance(double chance) {
        return Math.random() <= chance;
    }

    public static ItemStack enchantWithRandom(Player player, ItemStack itemStack) throws ScriptException {
        if(itemStack == null) return itemStack;

        List<MysticEnchant> validEnchants = new ArrayList<>();

        NBTItem nbtItem = new NBTItem(itemStack);
        //nbtItem.setInteger("mysticTier", tier);

        if(nbtItem.hasKey("mysticTier")) nbtItem.setInteger("mysticTier", Math.min(3, nbtItem.getInteger("mysticTier")));
        else nbtItem.setInteger("mysticTier", 1);

        NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

        List<String> lore = new ArrayList<>();
        List<String> enchants = new ArrayList<>(nbtCompound.getKeys());

        boolean skip = true;

        String finalEnchant = "";

        for(MysticEnchant mysticEnchant : mysticEnchants.values()){

            if(mysticEnchant.getMysticType().getMaterials().contains(itemStack.getType())){
                if(nbtCompound.hasKey(mysticEnchant.getRefID())) {
                    if(nbtCompound.getInteger(mysticEnchant.getRefID())>=3) {
                        skip = false;
                    }
                }else{
                    skip = true;
                }

                if(skip) {
                    validEnchants.add(mysticEnchant);
                }
            }
        }

        boolean done = true;

        while(done) {
            if(validEnchants.isEmpty()) break;

            for (MysticEnchant enchant : validEnchants) {
                if (enchants.contains(enchant.getRefID())) {
                    if (percentChance(enchant.getEnchantGeneral().getRollChance() * 1.25)) {
                        while (true) {
                            if (percentChance(.50)) {
                                finalEnchant = enchant.getRefID();
                                nbtCompound.setInteger(enchant.getRefID(), Math.min(3, nbtCompound.getInteger(enchant.getRefID()) + 1));
                                done = false;
                                break;
                            } else if (percentChance(.15)) {
                                finalEnchant = enchant.getRefID();
                                nbtCompound.setInteger(enchant.getRefID(), Math.min(3, nbtCompound.getInteger(enchant.getRefID()) + 2));
                                done = false;
                                break;
                            }
                        }
                    }
                } else if (percentChance(enchant.getEnchantGeneral().getRollChance())) {
                    if (percentChance(enchant.getEnchantGeneral().getRollChance())) {
                        while (true) {
                            if (percentChance(.35)) {
                                finalEnchant = enchant.getRefID();
                                nbtCompound.setInteger(enchant.getRefID(), 1);
                                done = false;
                                break;
                            } else if (percentChance(.20)) {
                                finalEnchant = enchant.getRefID();
                                nbtCompound.setInteger(enchant.getRefID(), 2);
                                done = false;
                                break;
                            } else if (percentChance(.05)) {
                                finalEnchant = enchant.getRefID();
                                nbtCompound.setInteger(enchant.getRefID(), 3);
                                done = false;
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(!Objects.equals(finalEnchant, "")) new EnchantSound(player, player.getLocation()).play(EnchantSound.Tier.getTier(nbtItem.getInteger("mysticTier")), Objects.requireNonNull(getEnchant(finalEnchant)).getEnchantGeneral().equals(EnchantGeneral.RARE));

        ItemMeta itemMeta = nbtItem.getItem().getItemMeta();

        int currentLives = nbtItem.getInteger("lives");
        int maxLives = nbtItem.getInteger("maxLives");
        String currentLivesColor = "&a";

        if(currentLives<=(maxLives/3)) currentLivesColor = "&c";

        String livesTemplate = colorCode("&7Lives: " + currentLivesColor + currentLives+"&7/"+maxLives);

        lore.add(livesTemplate);
        lore.add("   ");

        for (String key : nbtCompound.getKeys()){
            int level = nbtCompound.getInteger(key);

            lore.addAll(Arrays.asList(Objects.requireNonNull(getEnchant(key)).getLore(level).split("\n")));
        }

        if(MysticType.SWORD.getMaterials().contains(itemStack.getType())){
            lore.add(ChatColor.BLUE + "+6.5 Attack Damage");
        }else if(MysticType.PANT.getMaterials().contains(itemStack.getType())){
            lore.add(ChatColor.DARK_GRAY + "As strong as iron");
        }

        itemMeta.setLore(lore);

        nbtItem.mergeCompound(nbtCompound);
        nbtItem.getItem().setItemMeta(itemMeta);

        return nbtItem.getItem();
    }

    @EventHandler
    public void DamageEvent(ReduxDamageEvent event) {
        for(MysticEnchant enchant : mysticEnchants.values()) if(enchant.getRegisterEvents()
                    .contains(RegisterEvent.REDUX_DAMAGE_EVENT)) enchant.DamageEvent(event);
    }

    @EventHandler
    public void DeathEvent(ReduxDeathEvent event) {
        for(MysticEnchant enchant : mysticEnchants.values()) if(enchant.getRegisterEvents()
                .contains(RegisterEvent.REDUX_DEATH_EVENT)) enchant.DeathEvent(event);
    }

    @EventHandler
    public void BowEvent(ReduxBowEvent event) {
        for(MysticEnchant enchant : mysticEnchants.values()) if(enchant.getRegisterEvents()
                .contains(RegisterEvent.REDUX_BOW_EVENT)) enchant.BowEvent(event);
    }

    @EventHandler
    public void ArmorEvent(ArmorEquipEvent event) {
        for(MysticEnchant enchant : mysticEnchants.values()) if(enchant.getRegisterEvents()
                .contains(RegisterEvent.ARMOR_EQUIP_EVENT)) enchant.ArmorEvent(event);
    }

    @EventHandler
    public void ShootEvent(EntityShootBowEvent event) {
        for(MysticEnchant enchant : mysticEnchants.values()) if(enchant.getRegisterEvents()
                .contains(RegisterEvent.ARROW_SHOOT_EVENT)) enchant.ShootEvent(event);
    }

    @EventHandler
    public void ArrowHitEvent(ProjectileHitEvent event) {
        for(MysticEnchant enchant : mysticEnchants.values()) if(enchant.getRegisterEvents()
                .contains(RegisterEvent.ARROW_HIT_EVENT)) enchant.ArrowHitEvent(event);
    }
}
