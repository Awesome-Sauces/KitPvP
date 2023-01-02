package me.alpha.kitpvp.PitRemake;

import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import static me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants.percentChance;

public class MapType {
    
    public static PitMap getMapType(World world){
        if(world.getBlockAt(new Location(world, 0.5, 80, 0.5))!=null &&
                !world.getBlockAt(new Location(world, 0.5, 80, 0.5)).getType().equals(Material.AIR)&&
                world.getBlockAt(new Location(world, 0.5, 80, 0.5)).getType().equals(Material.EMERALD_BLOCK)) return CoralMap;

        if(world.getBlockAt(new Location(world, 0.5, 69, 0.5))!=null &&
                !world.getBlockAt(new Location(world, 0.5, 69, 0.5)).getType().equals(Material.AIR)&&
                world.getBlockAt(new Location(world, 0.5, 69, 0.5)).getType().equals(Material.GOLD_BLOCK)) return KingMap;

        if(world.getBlockAt(new Location(world, 0.5, 41, 0.5))!=null &&
                !world.getBlockAt(new Location(world, 0.5, 41, 0.5)).getType().equals(Material.AIR)&&
                world.getBlockAt(new Location(world, 0.5, 41, 0.5)).getType().equals(Material.DIAMOND_BLOCK)) return GenesisMap;

        if(world.getBlockAt(new Location(world, 0.5, 80, 0.5))!=null &&
                !world.getBlockAt(new Location(world, 0.5, 80, 0.5)).getType().equals(Material.AIR)&&
                world.getBlockAt(new Location(world, 0.5, 80, 0.5)).getType().equals(Material.REDSTONE_BLOCK)) return WaterMap;

        return DefaultMap;
    }

    public static final PitMap KingMap = new PitMap(/* Spawn */ new Coordinates(-9.5, 95, 0.5, -90, -0F),
            /* Spawn */ new Coordinates(-11.5, 95, 12.5, 44.5F, -0.5F),
            /* Spawn */ new Coordinates(-10.5, 95, -10.5, 135F, 3.5F),
            /* Spawn */ new Coordinates(14.5, 95, -14.5, -135F, 3.0F),
            /* Spawn */ new Coordinates(13.5, 95, 16.5, -45F, 3.5F),
            /* Spawn */ new Coordinates(0.5, 95, 10.5, 180F, -2.5F),
            /* Spawn */ new Coordinates(10.5, 95, 0.5, 90F, -2.0F),
            /* BotSpawn */ new Coordinates(0.5,85,0.5),
            /* playPit - Jump Fight */  new Coordinates(0.5,97,0.5),
            /* Leaderboard */ new Coordinates(14.5, 101, 0.5),
            /* pit level perks */  new Coordinates(-5.5,98,11.5),
            /* Ender chest */ new Coordinates(-10.5, 97.5, 6.5),
            /* Mystic Well */ new Coordinates(-13.5,98.5,0.5),
            /* King NPC */ new Coordinates(-109.5, 79, -5.5),
            /* Bad NPC */  new Coordinates(17.5, 53, 58.5),
            /* Good NPC */ new Coordinates(-23.5, 49, -76.5),
            /* Ring middle */ new Coordinates(0.5,71,0.5),
            /* Non-Perm Upgrades */ new Coordinates(2.5,95,12.5, 180F, 0.5F),
            /* Perm Upgrades */ new Coordinates(-1.5,95,12.5, 180F, 0.5F),
            /* Stats */ new Coordinates(11.5,95,6.5, 90F, 0.5F),
            /* Quests */ new Coordinates(11.5,95,-5.5, 90F, 0.5F),
            /* Prestige */ new Coordinates(0.5, 96, -12.5, 0.5F, 1.0F));

    public static final PitMap CoralMap = new PitMap(/* Spawn */ new Coordinates(-9.5, 114, 0.5, -90, -0F),
            /* Spawn */ new Coordinates(-11.5, 114, 12.5, 44.5F, -0.5F),
            /* Spawn */ new Coordinates(-10.5, 114, -10.5, 135F, 3.5F),
            /* Spawn */ new Coordinates(14.5, 114, -14.5, -135F, 3.0F),
            /* Spawn */ new Coordinates(13.5, 114, 16.5, -45F, 3.5F),
            /* Spawn */ new Coordinates(0.5, 114, 10.5, 180F, -2.5F),
            /* Spawn */ new Coordinates(10.5, 114, 0.5, 90F, -2.0F),
            /* BotSpawn */ new Coordinates(0.5,101,0.5),
            /* playPit - Jump Fight */  new Coordinates(0.5,116,0.5),
            /* Leaderboard */ new Coordinates(13.5, 120, 0.5),
            /* pit level perks */  new Coordinates(-6.5,117,11.5),
            /* Ender chest */ new Coordinates(-11.5, 116.5, 6.5),
            /* Mystic Well */ new Coordinates(-12.5,117.5,0.5),
            /* King NPC */ new Coordinates(94.5,97,-59.5),
            /* Bad NPC */  new Coordinates(84.5, 93, 75.5),
            /* Good NPC */ new Coordinates(11.5, 106, 128.5),
            /* Ring middle */ new Coordinates(0.5,82,0.5),
            /* Non-Perm Upgrades */ new Coordinates(2.5,114,12.5, 180F, 0.5F),
            /* Perm Upgrades */ new Coordinates(-1.5,114,12.5, 180F, 0.5F),
            /* Stats */ new Coordinates(11.5,114,5.5, 90F, 0.5F),
            /* Quests */ new Coordinates(9.5,114,-4.5, 90F, 0.5F),
            /* Prestige */ new Coordinates(0.5, 115, -11.5, 0.5F, 1.0F));

    public static final PitMap WaterMap = new PitMap(/* Spawn */ new Coordinates(-9.5, 114, 0.5, -90, -0F),
            /* Spawn */ new Coordinates(-11.5, 114, 12.5, 44.5F, -0.5F),
            /* Spawn */ new Coordinates(-10.5, 114, -10.5, 135F, 3.5F),
            /* Spawn */ new Coordinates(14.5, 114, -14.5, -135F, 3.0F),
            /* Spawn */ new Coordinates(13.5, 114, 16.5, -45F, 3.5F),
            /* Spawn */ new Coordinates(0.5, 114, 10.5, 180F, -2.5F),
            /* Spawn */ new Coordinates(10.5, 114, 0.5, 90F, -2.0F),
            /* BotSpawn */ new Coordinates(0.5,101,0.5),
            /* playPit - Jump Fight */  new Coordinates(0.5,116,0.5),
            /* Leaderboard */ new Coordinates(13.5, 120, 0.5),
            /* pit level perks */  new Coordinates(-6.5,117,11.5),
            /* Ender chest */ new Coordinates(-12.5, 116.5, 7.5),
            /* Mystic Well */ new Coordinates(-12.5,117.5,0.5),
            /* King NPC */ new Coordinates(-86.5, 90, 90.5),
            /* Bad NPC */  new Coordinates(86.5, 96, 44.5),
            /* Good NPC */ new Coordinates(-92.5, 94, -32.5),
            /* Ring middle */ new Coordinates(0.5,82,0.5),
            /* Non-Perm Upgrades */ new Coordinates(2.5,114,12.5, 180F, 0.5F),
            /* Perm Upgrades */ new Coordinates(-1.5,114,12.5, 180F, 0.5F),
            /* Stats */ new Coordinates(11.5,114,5.5, 90F, 0.5F),
            /* Quests */ new Coordinates(9.5,114,-4.5, 90F, 0.5F),
            /* Prestige */ new Coordinates(0.5, 115, -11.5, 0.5F, 1.0F));

    public static final PitMap GenesisMap = new PitMap(/* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* BotSpawn */ new Coordinates(0,0,0),
            /* playPit - Jump Fight */  new Coordinates(0,0,0),
            /* Leaderboard */ new Coordinates(0,0,0),
            /* pit level perks */  new Coordinates(0,0,0),
            /* Ender chest */ new Coordinates(0,0,0),
            /* Mystic Well */ new Coordinates(0,0,0),
            /* King NPC */ new Coordinates(0,0,0),
            /* Bad NPC */  new Coordinates(0,0,0),
            /* Good NPC */ new Coordinates(0,0,0),
            /* Ring middle */ new Coordinates(0,0,0),
            /* Non-Perm Upgrades */ new Coordinates(0,0,0, 0F, 0.5F),
            /* Perm Upgrades */ new Coordinates(0,0,0, 0F, 0.5F),
            /* Stats */ new Coordinates(0,0,0, 0F, 0.5F),
            /* Quests */ new Coordinates(0,0,0, 0F, 0.5F),
            /* Prestige */ new Coordinates(0,0,0, 0F, 0.5F));

    public static final PitMap DefaultMap = new PitMap(/* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* Spawn */ new Coordinates(0,0,0),
            /* BotSpawn */ new Coordinates(0,0,0),
            /* playPit - Jump Fight */  new Coordinates(0,0,0),
            /* Leaderboard */ new Coordinates(0,0,0),
            /* pit level perks */  new Coordinates(0,0,0),
            /* Ender chest */ new Coordinates(0,0,0),
            /* Mystic Well */ new Coordinates(0,0,0),
            /* King NPC */ new Coordinates(0,0,0),
            /* Bad NPC */  new Coordinates(0,0,0),
            /* Good NPC */ new Coordinates(0,0,0),
            /* Ring middle */ new Coordinates(0,0,0),
            /* Non-Perm Upgrades */ new Coordinates(0,0,0, 0F, 0.5F),
            /* Perm Upgrades */ new Coordinates(0,0,0, 0F, 0.5F),
            /* Stats */ new Coordinates(0,0,0, 0F, 0.5F),
            /* Quests */ new Coordinates(0,0,0, 0F, 0.5F),
            /* Prestige */ new Coordinates(0,0,0, 0F, 0.5F));

    public static class PitMap {

        Coordinates spawn1;
        Coordinates spawn2;
        Coordinates spawn3;
        Coordinates spawn4;
        Coordinates spawn5;
        Coordinates spawn6;
        Coordinates spawn7;

        Coordinates botSpawn;
        Coordinates playPit;
        Coordinates leaderBoard;
        Coordinates pitLevel;
        Coordinates enderChest;
        Coordinates mysticWell;
        Coordinates kingNPC;
        Coordinates badNPC;
        Coordinates goodNPC;
        Coordinates ringMid;
        Coordinates nonPermNPC;
        Coordinates permNPC;
        Coordinates statsNPC;
        Coordinates questNPC;
        Coordinates prestigeNPC;


        public PitMap(Coordinates spawn1, Coordinates spawn2,
                      Coordinates spawn3, Coordinates spawn4,
                      Coordinates spawn5, Coordinates spawn6,
                      Coordinates spawn7,

                      Coordinates botSpawn, Coordinates playPit,
                      Coordinates leaderBoard, Coordinates pitLevel,
                      Coordinates enderChest, Coordinates mysticWell,
                      Coordinates kingNPC, Coordinates badNPC,
                      Coordinates goodNPC, Coordinates ringMid,
                      Coordinates nonPermNPC, Coordinates permNPC,
                      Coordinates statsNPC, Coordinates questNPC,
                      Coordinates prestigeNPC) {
            this.spawn1=spawn1;
            this.spawn2=spawn2;
            this.spawn3=spawn3;
            this.spawn4=spawn4;
            this.spawn5=spawn5;
            this.spawn6=spawn6;
            this.spawn7=spawn7;

            this.botSpawn=botSpawn;
            this.playPit=playPit;
            this.leaderBoard=leaderBoard;
            this.pitLevel=pitLevel;
            this.enderChest=enderChest;
            this.mysticWell=mysticWell;
            this.kingNPC=kingNPC;
            this.badNPC=badNPC;
            this.goodNPC=goodNPC;
            this.ringMid=ringMid;
            this.nonPermNPC=nonPermNPC;
            this.permNPC=permNPC;
            this.statsNPC=statsNPC;
            this.questNPC=questNPC;
            this.prestigeNPC=prestigeNPC;
        }

        public Location getSpawn(World world){
            while(true){
                if(percentChance(.25)){
                    return new Location(world, getSpawn1().x, getSpawn1().y, getSpawn1().z, getSpawn1().getYaw(), getSpawn1().getPitch());
                }else if(percentChance(.25)){
                    return new Location(world, getSpawn2().x, getSpawn2().y, getSpawn2().z, getSpawn2().getYaw(), getSpawn2().getPitch());
                }else if(percentChance(.25)){
                    return new Location(world, getSpawn3().x, getSpawn3().y, getSpawn3().z, getSpawn3().getYaw(), getSpawn3().getPitch());
                }else if(percentChance(.25)){
                    return new Location(world, getSpawn4().x, getSpawn4().y, getSpawn4().z, getSpawn4().getYaw(), getSpawn4().getPitch());
                }else if(percentChance(.25)){
                    return new Location(world, getSpawn5().x, getSpawn5().y, getSpawn5().z, getSpawn5().getYaw(), getSpawn5().getPitch());
                }else if(percentChance(.25)){
                    return new Location(world, getSpawn6().x, getSpawn6().y, getSpawn6().z, getSpawn6().getYaw(), getSpawn6().getPitch());
                }else if(percentChance(.25)){
                    return new Location(world, getSpawn7().x, getSpawn7().y, getSpawn7().z, getSpawn7().getYaw(), getSpawn7().getPitch());
                }
            }
        }

        public Coordinates getSpawn1() {
            return spawn1;
        }

        public void setSpawn1(Coordinates spawn1) {
            this.spawn1 = spawn1;
        }

        public Coordinates getSpawn2() {
            return spawn2;
        }

        public void setSpawn2(Coordinates spawn2) {
            this.spawn2 = spawn2;
        }

        public Coordinates getSpawn3() {
            return spawn3;
        }

        public void setSpawn3(Coordinates spawn3) {
            this.spawn3 = spawn3;
        }

        public Coordinates getSpawn4() {
            return spawn4;
        }

        public void setSpawn4(Coordinates spawn4) {
            this.spawn4 = spawn4;
        }

        public Coordinates getSpawn5() {
            return spawn5;
        }

        public void setSpawn5(Coordinates spawn5) {
            this.spawn5 = spawn5;
        }

        public Coordinates getSpawn6() {
            return spawn6;
        }

        public void setSpawn6(Coordinates spawn6) {
            this.spawn6 = spawn6;
        }

        public Coordinates getSpawn7() {
            return spawn7;
        }

        public void setSpawn7(Coordinates spawn7) {
            this.spawn7 = spawn7;
        }

        public Location getBotSpawn(World world) {
            return new Location(world, botSpawn.x, botSpawn.y, botSpawn.z);
        }

        public void setBotSpawn(Coordinates botSpawn) {
            this.botSpawn = botSpawn;
        }

        public Location getPlayPit(World world) {
            return new Location(world, playPit.x, playPit.y, playPit.z);
        }

        public void setPlayPit(Coordinates playPit) {
            this.playPit = playPit;
        }

        public Location getLeaderBoard(World world) {
            return new Location(world, leaderBoard.x, leaderBoard.y, leaderBoard.z);
        }

        public void setLeaderBoard(Coordinates leaderBoard) {
            this.leaderBoard = leaderBoard;
        }

        public Location getPitLevel(World world) {
            return new Location(world, pitLevel.x, pitLevel.y, pitLevel.z);
        }

        public void setPitLevel(Coordinates pitLevel) {
            this.pitLevel = pitLevel;
        }

        public Location getEnderChest(World world) {
            return new Location(world, enderChest.x, enderChest.y, enderChest.z);
        }

        public void setEnderChest(Coordinates enderChest) {
            this.enderChest = enderChest;
        }

        public Location getMysticWell(World world) {
            return new Location(world, mysticWell.x, mysticWell.y, mysticWell.z);
        }

        public void setMysticWell(Coordinates mysticWell) {
            this.mysticWell = mysticWell;
        }

        public Location getKingNPC(World world) {
            return new Location(world, kingNPC.x, kingNPC.y, kingNPC.z);
        }

        public void setKingNPC(Coordinates kingNPC) {
            this.kingNPC = kingNPC;
        }

        public Location getBadNPC(World world) {
            return new Location(world, badNPC.x, badNPC.y, badNPC.z);
        }

        public void setBadNPC(Coordinates badNPC) {
            this.badNPC = badNPC;
        }

        public Location getGoodNPC(World world) {
            return new Location(world, goodNPC.x, goodNPC.y, goodNPC.z);
        }

        public void setGoodNPC(Coordinates goodNPC) {
            this.goodNPC = goodNPC;
        }

        public Location getRingMid(World world) {
            return new Location(world, ringMid.x, ringMid.y, ringMid.z);
        }

        public void setRingMid(Coordinates ringMid) {
            this.ringMid = ringMid;
        }

        public Location getNonPermNPC(World world) {
            return new Location(world, nonPermNPC.x, nonPermNPC.y, nonPermNPC.z, nonPermNPC.yaw, nonPermNPC.pitch);
        }

        public void setNonPermNPC(Coordinates nonPermNPC) {
            this.nonPermNPC = nonPermNPC;
        }

        public Location getPermNPC(World world) {
            return new Location(world, permNPC.x, permNPC.y, permNPC.z, permNPC.yaw, permNPC.pitch);
        }

        public void setPermNPC(Coordinates permNPC) {
            this.permNPC = permNPC;
        }

        public Location getStatsNPC(World world) {
            return new Location(world, statsNPC.x, statsNPC.y, statsNPC.z, statsNPC.yaw, statsNPC.pitch);
        }

        public void setStatsNPC(Coordinates statsNPC) {
            this.statsNPC = statsNPC;
        }

        public Location getQuestNPC(World world) {
            return new Location(world, questNPC.x, questNPC.y, questNPC.z, questNPC.yaw, questNPC.pitch);
        }

        public void setQuestNPC(Coordinates questNPC) {
            this.questNPC = questNPC;
        }

        public Location getPrestigeNPC(World world) {
            return new Location(world, prestigeNPC.x, prestigeNPC.y, prestigeNPC.z, prestigeNPC.yaw, prestigeNPC.pitch);
        }

        public void setPrestigeNPC(Coordinates prestigeNPC) {
            this.prestigeNPC = prestigeNPC;
        }
    }
}

