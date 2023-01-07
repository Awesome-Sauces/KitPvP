package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.Factions.data.BotKills;
import me.alpha.kitpvp.PitRemake.Factions.data.FactionData;
import me.alpha.kitpvp.PitRemake.Factions.data.FactionReward;
import me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants.*;
import me.alpha.kitpvp.PitRemake.MysticWell.GlobalEnchants.*;
import me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants.*;
import me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants.*;
import me.alpha.kitpvp.PitRemake.Perks.*;
import me.alpha.kitpvp.PitRemake.Perks.data.PerkSlotFour;
import me.alpha.kitpvp.PitRemake.Perks.data.PerkSlotOne;
import me.alpha.kitpvp.PitRemake.Perks.data.PerkSlotThree;
import me.alpha.kitpvp.PitRemake.Perks.data.PerkSlotTwo;
import me.alpha.kitpvp.PitRemake.Pets.PetStore.XpDragon;
import me.alpha.kitpvp.PitRemake.RenownShop.data.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ClassInstances {

    public static HashMap<String, Integer> ComboDamageHitCounter = new HashMap<>();
    public static HashMap<String, Integer> PerunHitCounter = new HashMap<>();
    public static HashMap<String, Integer> VenomHitCounter = new HashMap<>();
    public static HashMap<String, Integer> RgmHitCounter = new HashMap<>();
    public static HashMap<String, Long> CombatTag = new HashMap<>();
    public static HashMap<String, Boolean> KillMessages = new HashMap<>();
    public static HashMap<UUID, Arrow> ArrowStore = new HashMap<>();

    public static HashMap<String, Boolean> NightQuestsToggle = new HashMap<>();


    public static List<Integer> XpAmounts = new ArrayList<Integer>();

    // Save
    public static MoonStreak moonStreak = new MoonStreak("moonstreak");
    public static UberStreak uberStreak = new UberStreak("uberstreak");
    public static HighlanderStreak highlanderStreak = new HighlanderStreak("highlander");
    public static BeastmodeStreak beastmodeStreak = new BeastmodeStreak("beastmode");
    public static HermitStreak hermitStreak = new HermitStreak("hermit");
    public static MagnumOpus magnumOpus = new MagnumOpus("magnum");
    public static ExperienceIndustrialComplex experienceIndustrialComplex = new ExperienceIndustrialComplex("industrial");
    public static FastPass fastPass = new FastPass("fastpass");
    public static Heresy heresy = new Heresy("heresy");
    public static RenownGoldBoost renownGoldBoost = new RenownGoldBoost("goldboost");
    public static RenownXpBump renownXpBump = new RenownXpBump("xpbump");
    public static Tenacity tenacity = new Tenacity("tenacity");
    public static TheWay theWay = new TheWay("way");
    public static Mysticism mysticism = new Mysticism("mysticChance");
    public static Celebrity celebrity = new Celebrity("celebrity");
    public static ExtraHearts extraHearts = new ExtraHearts("hearts");
    public static Promotion promotion = new Promotion("promotion");
    public static GoldRequirementData goldRequirementData = new GoldRequirementData("goldreq");
    public static PrestigeData prestigeData = new PrestigeData("prestige");
    public static RenownData renownData = new RenownData("renown");
    public static XpData xpData = new XpData("exp");
    public static MegaStreakData megaStreakData = new MegaStreakData("megastreakData");
    public static PerkSlotOne perkSlotOne = new PerkSlotOne("perkone");
    public static PerkSlotTwo perkSlotTwo = new PerkSlotTwo("perktwo");
    public static PerkSlotThree perkSlotThree = new PerkSlotThree("perkthree");
    public static PerkSlotFour perkSlotFour = new PerkSlotFour("perkfour");
    public static FactionReward factionReward = new FactionReward("factionreward");
    public static BotKills botKills = new BotKills("botkill");
    public static FactionData factionData = new FactionData("faction");

    public static BotBoosterData botBoosterData = new BotBoosterData("botbooster");
    public static GoldBoosterData goldBoosterData = new GoldBoosterData("goldbooster");
    public static XpBoosterData xpBoosterData = new XpBoosterData("xpbooster");


    // Don't save
    public static StreakData streakData = new StreakData("DON'T SAVE");
    public static Vampire vampire = new Vampire();
    public static GoldenHeads goldenHeads = new GoldenHeads();
    public static AssistantStreaker assistantStreaker = new AssistantStreaker();
    public static Dirty dirty = new Dirty();
    public static FirstStrike firstStrike = new FirstStrike();
    public static Soup soup = new Soup();
    public static FishingRod fishingRod = new FishingRod();

    public static Gladiator gladiator = new Gladiator();
    public static StrengthChaining strengthChaining = new StrengthChaining();
    public static Streaker streaker = new Streaker();

    // Mystic Enchants
    public static PitPocketLore pitPocketLore = new PitPocketLore();
    public static GrasshopperLore grasshopperLore = new GrasshopperLore();
    public static GoldBoostedLore goldBoostedLore = new GoldBoostedLore();
    public static BerserkerLore berserkerLore = new BerserkerLore();
    public static PunisherLore punisherLore = new PunisherLore();
    public static ComboDamageLore combodamageLore = new ComboDamageLore();
    public static FancyRaiderLore fancyraiderLore = new FancyRaiderLore();
    public static XpboostLore xpboostLore = new XpboostLore();
    public static XpbumpLore xpbumpLore = new XpbumpLore();
    public static SweatyLore sweatyLore = new SweatyLore();
    public static GoldboostLore goldboostLore = new GoldboostLore();
    public static GoldbumpLore goldbumpLore = new GoldbumpLore();
    public static MoctezumaLore moctezumaLore = new MoctezumaLore();
    public static PantsRadarLore pantsRadarLore = new PantsRadarLore();
    public static BooBooLore booBooLore = new BooBooLore();
    public static DavidGoliathLore davidGoliathLore = new DavidGoliathLore();
    public static DiamondAllergyLore diamondAllergyLore = new DiamondAllergyLore();
    public static CriticallyFunkyLore criticallyFunkyLore = new CriticallyFunkyLore();
    public static GoldenHeartLore goldenHeartLore = new GoldenHeartLore();
    public static ProtectionLore protectionLore = new ProtectionLore();
    public static SolitudeLore solitudeLore = new SolitudeLore();
    public static CricketLore cricketLore = new CricketLore();
    public static RetroGravityMicrocosmLore retroGravityMicrocosmLore = new RetroGravityMicrocosmLore();
    public static RegularityLore regularityLore = new RegularityLore();
    public static NotGladiatorLore notGladiatorLore = new NotGladiatorLore();
    public static MirrorLore mirrorLore = new MirrorLore();
    public static FractionalReserveLore fractionalReserveLore = new FractionalReserveLore();
    public static PitBlobLore pitBlobLore = new PitBlobLore();
    public static EscapePodLore escapePodLore = new EscapePodLore();
    public static PrickLore prickLore = new PrickLore();
    public static GottaGoFastLore gottaGoFastLore = new GottaGoFastLore();
    public static BillyLore billyLore = new BillyLore();
    public static SelfCheckoutLore selfCheckoutLore = new SelfCheckoutLore();
    public static PebbleLore pebbleLore = new PebbleLore();
    public static ArrowArmoryLore arrowArmoryLore = new ArrowArmoryLore();
    public static FletchingLore fletchingLore = new FletchingLore();
    public static JumpspammerLore jumpspammerLore = new JumpspammerLore();
    public static PeroxideLore peroxideLore = new PeroxideLore();
    public static BillionaireLore billionaireLore = new BillionaireLore();
    public static PerunLore perunLore = new PerunLore();
    public static ExecutionerLore executionerLore = new ExecutionerLore();
    public static GambleLore gambleLore = new GambleLore();
    public static KingBusterLore kingBusterLore = new KingBusterLore();
    public static LifestealLore lifestealLore = new LifestealLore();
    public static PainFocusLore painFocusLore = new PainFocusLore();
    public static SharkLore sharkLore = new SharkLore();
    public static SharpLore sharpLore = new SharpLore();
    public static DiamondStompLore diamondStompLore = new DiamondStompLore();
    public static TelebowLore telebowLore = new TelebowLore();
    public static PullBowLore pullBowLore = new PullBowLore();
    public static MegaLongBowLore megaLongBowLore = new MegaLongBowLore();
    public static FasterThenTheirShadowLore fasterThenTheirShadowLore = new FasterThenTheirShadowLore();
    public static SprintDrainLore sprintDrainLore = new SprintDrainLore();
    public static WaspLore waspLore = new WaspLore();
    public static ParasiteLore parasiteLore = new ParasiteLore();
    public static VolleyLore volleyLore = new VolleyLore();
    public static ExplosiveLore explosiveLore = new ExplosiveLore();

    public static SomberLore somberLore = new SomberLore();
    public static VenomLore venomLore = new VenomLore();
    public static MindAssaultLore mindAssaultLore = new MindAssaultLore();
    public static SpiteLore spiteLore = new SpiteLore();
    public static MiseryLore miseryLore = new MiseryLore();
    public static NeedlessSufferingLore needlessSufferingLore = new NeedlessSufferingLore();

    public static PetData petData = new PetData("petData");

    public static XpDragon xpDragon = new XpDragon("xpdragon", "Dragon",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOThmNjkyMDliMThhZjZkMDZkZjJkZmIzNmFmZTMyYzdhZDFjNmFmYjcxZDJjZDg4NWFjMWQyOGQyZDhmZTdiZCJ9fX0"
            , 100, ChatColor.AQUA, 75000);

    public static void save(){


        petData.saveHashMap();
    }

    public static void load(){
        goldRequirementData.loadHashMap();
        prestigeData.loadHashMap();
        xpData.loadHashMap();
        renownData.loadHashMap();

        botBoosterData.loadHashMap();
        xpBoosterData.loadHashMap();
        goldBoosterData.loadHashMap();
        factionData.loadHashMap(true);
        factionReward.loadHashMap(true);

        megaStreakData.loadHashMap(true);
        perkSlotOne.loadHashMap(true);
        perkSlotTwo.loadHashMap(true);
        perkSlotThree.loadHashMap(true);
        perkSlotFour.loadHashMap(true);
        petData.loadHashMap(true);

        botKills.loadHashMap();
        moonStreak.loadHashMap();
        uberStreak.loadHashMap();
        beastmodeStreak.loadHashMap();
        highlanderStreak.loadHashMap();
        celebrity.loadHashMap();
        magnumOpus.loadHashMap();
        experienceIndustrialComplex.loadHashMap();
        mysticism.loadHashMap();
        fastPass.loadHashMap();
        extraHearts.loadHashMap();
        heresy.loadHashMap();
        promotion.loadHashMap();
        renownGoldBoost.loadHashMap();
        renownXpBump.loadHashMap();
        tenacity.loadHashMap();
        theWay.loadHashMap();
    }

}
