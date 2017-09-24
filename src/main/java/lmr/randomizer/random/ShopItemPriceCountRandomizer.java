package lmr.randomizer.random;

import javafx.util.Pair;
import lmr.randomizer.Settings;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by thezerothcat on 8/19/2017.
 */
public class ShopItemPriceCountRandomizer {
    private boolean specialAmmoPlaced = false;
    private boolean normalPriceWeightsPlaced = false;

    private Random random;

    // 50-70
    private List<String> PRICE_TIER1 = Arrays.asList("yagomap.exe", "bunemon.exe", "Glove", "Shell Horn",
            "xmailer.exe", "bunplus.com", "guild.exe", "Buckler", "Helmet", "Bronze Mirror", "emusic.exe", "beolamu.exe",
            "Waterproof Case", "Heatproof Case", "Map");

    // 80-120
    private List<String> PRICE_TIER2 = Arrays.asList("Key of Eternity", "Life Seal", "Death Seal",
            "Knife", "Key Sword", "Shuriken", "Rolling Shuriken", "Scalesphere", "Pepper", "Talisman", "Magatama Jewel",
            "yagostr.exe", "Mini Doll", "Treasures", "Anchor", "Grapple Claw", "Perfume", "Hand Scanner", "Hermes' Boots",
            "bounce.exe", "Ankh Jewel");

    // 140-160
    private List<String> PRICE_TIER3 = Arrays.asList("Origin Seal", "Birth Seal", "Fruit of Eden", "Twin Statue",
            "Woman Statue", "Maternity Statue", "Eye of Truth", "Diary", "Ice Cape", "Serpent Staff", "Dragon Bone",
            "Caltrops", "Earth Spear", "Fake Silver Shield", "Silver Shield", "Flare Gun", "Pistol", "Mobile Super X2",
            "Isis' Pendant", "Bracelet", "Crucifix", "miracle.exe", "torude.exe", "mirai.exe", "reader.exe", "Sacred Orb");

    // 180-220
    private List<String> PRICE_TIER4 = Arrays.asList("Feather", "Plane Model", "Philosopher's Ocarina", "Dimensional Key",
            "mantra.exe", "Djed Pillar", "Cog of the Soul", "Crystal Skull", "Mulana Talisman", "Vessel", "Pochette Key",
            "Ring", "Chain Whip", "Axe", "Katana", "Chakram",  "Bomb", "Book of the Dead", "Angel Shield", "Lamp of Time",
            "move.exe", "randc.exe", "mekuri.exe", "capstar.exe");

    // 250-350
    private List<String> PRICE_TIER5 = Arrays.asList("Fairy Clothes", "Scriptures", "Gauntlet", "deathv.exe",
            "Provocative Bathing Suit", "Spaulder", "Flail Whip", "lamulana.exe", "Holy Grail");

    public ShopItemPriceCountRandomizer(Random random) {
        this.random = random;
    }

    public Pair<Short, Short> getItemPriceAndCount(String itemName) {
        if(!specialAmmoPlaced && "Pistol Ammo".equals(itemName)) {
            // Special case
            if(random.nextInt(10) == 0) {
                return new Pair<>((short)400, (short)6);
            }
        }
        short price = getPrice(itemName);
        short count = getCount(itemName);
        return new Pair<>(price, count);
    }

    public short getPrice(String itemName) {
        if("Weights".equals(itemName)) {
            if(!normalPriceWeightsPlaced) {
                normalPriceWeightsPlaced = true;
                return 10;
            }
            int priceRoll = random.nextInt(20);
            if(priceRoll == 0 || priceRoll == 1) {
                return 15;
            }
            if(priceRoll == 2) {
                return 20;
            }
            return 10;
        }
        if("Shuriken Ammo".equals(itemName)) {
            return 10;
        }
        if("Rolling Shuriken Ammo".equals(itemName)) {
            return 10;
        }
        if("Earth Spear Ammo".equals(itemName)) {
            int priceRoll = random.nextInt(5);
            if(priceRoll < 3) {
                return 20;
            }
            return 25;
        }
        if("Flare Gun Ammo".equals(itemName)) {
            int priceRoll = random.nextInt(4);
            if(priceRoll < 2) {
                return 35;
            }
            if(priceRoll == 2) {
                return 40;
            }
            return 45;
        }
        if("Bomb Ammo".equals(itemName)) {
            int priceRoll = random.nextInt(5);
            if(priceRoll < 3) {
                return 70;
            }
            if(priceRoll == 3) {
                return 60;
            }
            return 80;
        }
        if("Chakram Ammo".equals(itemName)) {
            int priceRoll = random.nextInt(2);
            if(priceRoll == 0) {
                return 40;
            }
            return 45;
        }
        if("Caltrops Ammo".equals(itemName)) {
            int priceRoll = random.nextInt(5);
            if(priceRoll < 4) {
                return 30;
            }
            return 35;
        }
        if("Pistol Ammo".equals(itemName)) {
            // It looks like 6 is the count given by the Moonlight shop
            int priceRoll = random.nextInt(4);
            if(priceRoll < 3) {
                return 400;
            }
            return 350;
        }

        if("Hand Scanner".equals(itemName) && Settings.getNonRandomizedItems().contains("Hand Scanner")) {
            return 10;
        }
        if("Hermes' Boots".equals(itemName) && Settings.getNonRandomizedItems().contains("Hermes' Boots")) {
            return 60;
        }

        if(itemName.contains("Map")) {
            itemName = "Map";
        }
        else if(itemName.contains("Ankh Jewel")) {
            itemName = "Ankh Jewel";
        }
        else if(itemName.contains("Sacred Orb")) {
            itemName = "Sacred Orb";
        }

        if(PRICE_TIER1.contains(itemName)) {
            return (short)(50 + 5 * random.nextInt(5));
        }
        else if(PRICE_TIER2.contains(itemName)) {
            return (short)(80 + 10 * random.nextInt(5));
        }
        else if(PRICE_TIER3.contains(itemName)) {
            return (short)(140 + 5 * random.nextInt(5));
        }
        else if(PRICE_TIER4.contains(itemName)) {
            return (short)(180 + 10 * random.nextInt(5));
        }
        else if(PRICE_TIER4.contains(itemName)) {
            return (short)(250 + 50 * random.nextInt(3));
        }
        return (short)(10 + 10 * random.nextInt(25) + 1);
    }

    private static short getCount(String item) {
        if("Weights".equals(item)) {
            return 5;
        }
        if("Shuriken Ammo".equals(item)) {
            return 10;
        }
        if("Rolling Shuriken Ammo".equals(item)) {
            return 10;
        }
        if("Earth Spear Ammo".equals(item)) {
            return 10;
        }
        if("Flare Gun Ammo".equals(item)) {
            return 10;
        }
        if("Bomb Ammo".equals(item)) {
            return 10;
        }
        if("Chakram Ammo".equals(item)) {
            return 2;
        }
        if("Caltrops Ammo".equals(item)) {
            return 10;
        }
        if("Pistol Ammo".equals(item)) {
            return 1; // It looks like 6 is the count given by the Moonlight shop
        }
        return 1;
    }
}
