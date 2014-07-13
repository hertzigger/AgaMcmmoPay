package com.craftaga.agamcmmowage;

import com.gmail.nossr50.api.ExperienceAPI;
import org.bukkit.entity.Player;
import com.gmail.nossr50.api.exceptions.*;

/**
 * Wrapper For the ExperienceAPI to help with DI and Unit testing
 *
 * @author Jonathan
 * @since 11/04/14
 */
public class ExperienceApiWrapper {
    /**
     * Returns whether given string is a valid type of skill suitable for the
     * other API calls in this class.
     *
     * This function is designed for API usage.
     *
     * @param skillType A string that may or may not be a skill
     * @return true if this is a valid mcMMO skill
     */
    public boolean isValidSkillType(String skillType) {
        return ExperienceAPI.isValidSkillType(skillType);
    }

    /**
     * Returns whether the given skill type string is both valid and not a
     * child skill. (Child skills have no XP of their own, and their level is
     * derived from the parent(s).)
     *
     * This function is designed for API usage.
     *
     * @param skillType the skill to check
     * @return true if this is a valid, non-child mcMMO skill
     */
    public boolean isNonChildSkill(String skillType) {
        return ExperienceAPI.isNonChildSkill(skillType);
    }
    /**
     * Adds raw XP to the player.
     *
     * This function is designed for API usage.
     *
     * @param player The player to add XP to
     * @param skillType The skill to add XP to
     * @param XP The amount of XP to add
     *
     * @throws com.gmail.nossr50.api.exceptions.InvalidSkillException if the given skill is not valid
     */
    public void addRawXP(Player player, String skillType, float XP) {
        ExperienceAPI.addRawXP(player, skillType, XP);
    }

    /**
     * Adds raw XP to an offline player.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to add XP to
     * @param skillType The skill to add XP to
     * @param XP The amount of XP to add
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public void addRawXPOffline(String playerName, String skillType, float XP) {
        ExperienceAPI.addRawXPOffline(playerName, skillType, XP);
    }

    /**
     * Adds XP to the player, calculates for XP Rate only.
     *
     * This function is designed for API usage.
     *
     * @param player The player to add XP to
     * @param skillType The skill to add XP to
     * @param XP The amount of XP to add
     *
     * @throws InvalidSkillException if the given skill is not valid
     */
    public void addMultipliedXP(Player player, String skillType, int XP) {
        ExperienceAPI.addMultipliedXP(player, skillType, XP);
    }

    /**
     * Adds XP to an offline player, calculates for XP Rate only.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to add XP to
     * @param skillType The skill to add XP to
     * @param XP The amount of XP to add
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public void addMultipliedXPOffline(String playerName, String skillType, int XP) {
        ExperienceAPI.addMultipliedXPOffline(playerName, skillType, XP);
    }

    /**
     * Adds XP to the player, calculates for XP Rate and skill modifier.
     *
     * This function is designed for API usage.
     *
     * @param player The player to add XP to
     * @param skillType The skill to add XP to
     * @param XP The amount of XP to add
     *
     * @throws InvalidSkillException if the given skill is not valid
     */
    public void addModifiedXP(Player player, String skillType, int XP) {
        ExperienceAPI.addMultipliedXP(player, skillType, XP);
    }

    /**
     * Adds XP to an offline player, calculates for XP Rate and skill modifier.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to add XP to
     * @param skillType The skill to add XP to
     * @param XP The amount of XP to add
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public void addModifiedXPOffline(String playerName, String skillType, int XP) {
        ExperienceAPI.addMultipliedXPOffline(playerName, skillType, XP);
    }

    /**
     * Adds XP to the player, calculates for XP Rate, skill modifiers, perks, child skills,
     * and party sharing.
     *
     * This function is designed for API usage.
     *
     * @param player The player to add XP to
     * @param skillType The skill to add XP to
     * @param XP The amount of XP to add
     *
     * @throws InvalidSkillException if the given skill is not valid
     */
    public void addXP(Player player, String skillType, int XP) {
        ExperienceAPI.addXP(player, skillType, XP);
    }

    /**
     * Get the amount of XP a player has in a specific skill.
     *
     * This function is designed for API usage.
     *
     * @param player The player to get XP for
     * @param skillType The skill to get XP for
     * @return the amount of XP in a given skill
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getXP(Player player, String skillType) {
        return ExperienceAPI.getXP(player, skillType);
    }

    /**
     * Get the amount of XP an offline player has in a specific skill.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to get XP for
     * @param skillType The skill to get XP for
     * @return the amount of XP in a given skill
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getOfflineXP(String playerName, String skillType) {
        return ExperienceAPI.getOfflineXP(playerName, skillType);
    }

    /**
     * Get the raw amount of XP a player has in a specific skill.
     *
     * This function is designed for API usage.
     *
     * @param player The player to get XP for
     * @param skillType The skill to get XP for
     * @return the amount of XP in a given skill
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public float getXPRaw(Player player, String skillType) {
        return ExperienceAPI.getXPRaw(player, skillType);
    }

    /**
     * Get the raw amount of XP an offline player has in a specific skill.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to get XP for
     * @param skillType The skill to get XP for
     * @return the amount of XP in a given skill
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public float getOfflineXPRaw(String playerName, String skillType) {
        return ExperienceAPI.getOfflineXPRaw(playerName, skillType);
    }

    /**
     * Get the total amount of XP needed to reach the next level.
     *
     * This function is designed for API usage.
     *
     * @param player The player to get the XP amount for
     * @param skillType The skill to get the XP amount for
     * @return the total amount of XP needed to reach the next level
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getXPToNextLevel(Player player, String skillType) {
        return ExperienceAPI.getXPToNextLevel(player, skillType);
    }

    /**
     * Get the total amount of XP an offline player needs to reach the next level.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to get XP for
     * @param skillType The skill to get XP for
     * @return the total amount of XP needed to reach the next level
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getOfflineXPToNextLevel(String playerName, String skillType) {
        return ExperienceAPI.getOfflineXPToNextLevel(playerName, skillType);
    }

    /**
     * Get the amount of XP remaining until the next level.
     *
     * This function is designed for API usage.
     *
     * @param player The player to get the XP amount for
     * @param skillType The skill to get the XP amount for
     * @return the amount of XP remaining until the next level
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getXPRemaining(Player player, String skillType) {
        return ExperienceAPI.getXPRemaining(player, skillType);
    }

    /**
     * Get the amount of XP an offline player has left before leveling up.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to get XP for
     * @param skillType The skill to get XP for
     * @return the amount of XP needed to reach the next level
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getOfflineXPRemaining(String playerName, String skillType) {
        return ExperienceAPI.getOfflineXPRemaining(playerName, skillType);
    }

    /**
     * Add levels to a skill.
     *
     * This function is designed for API usage.
     *
     * @param player The player to add levels to
     * @param skillType Type of skill to add levels to
     * @param levels Number of levels to add
     *
     * @throws InvalidSkillException if the given skill is not valid
     */
    public void addLevel(Player player, String skillType, int levels) {
        ExperienceAPI.addLevel(player, skillType, levels);
    }

    /**
     * Add levels to a skill for an offline player.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to add levels to
     * @param skillType Type of skill to add levels to
     * @param levels Number of levels to add
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public void addLevelOffline(String playerName, String skillType, int levels) {
        ExperienceAPI.addLevelOffline(playerName, skillType, levels);
    }

    /**
     * Get the level a player has in a specific skill.
     *
     * This function is designed for API usage.
     *
     * @param player The player to get the level for
     * @param skillType The skill to get the level for
     * @return the level of a given skill
     *
     * @throws InvalidSkillException if the given skill is not valid
     */
    public int getLevel(Player player, String skillType) {
        return ExperienceAPI.getLevel(player, skillType);
    }

    /**
     * Get the level an offline player has in a specific skill.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to get the level for
     * @param skillType The skill to get the level for
     * @return the level of a given skill
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public int getLevelOffline(String playerName, String skillType) {
        return ExperienceAPI.getLevelOffline(playerName, skillType);
    }

    /**
     * Gets the power level of a player.
     *
     * This function is designed for API usage.
     *
     * @param player The player to get the power level for
     * @return the power level of the player
     */
    public int getPowerLevel(Player player) {
        return ExperienceAPI.getPowerLevel(player);
    }

    /**
     * Gets the power level of an offline player.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to get the power level for
     * @return the power level of the player
     *
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public int getPowerLevelOffline(String playerName) {
        return ExperienceAPI.getPowerLevelOffline(playerName);
    }

    /**
     * Get the level cap of a specific skill.
     *
     * This function is designed for API usage.
     *
     * @param skillType The skill to get the level cap for
     * @return the level cap of a given skill
     *
     * @throws InvalidSkillException if the given skill is not valid
     */
    public int getLevelCap(String skillType) {
        return ExperienceAPI.getLevelCap(skillType);
    }

    /**
     * Get the power level cap.
     *
     * This function is designed for API usage.
     *
     * @return the overall power level cap
     */
    public int getPowerLevelCap() {
        return ExperienceAPI.getPowerLevelCap();
    }

    /**
     * Get the position on the leaderboard of a player.
     *
     * This function is designed for API usage.
     *
     * @param playerName The name of the player to check
     * @param skillType The skill to check
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     *
     * @return the position on the leaderboard
     */
    public int getPlayerRankSkill(String playerName, String skillType) {
        return ExperienceAPI.getPlayerRankSkill(playerName, skillType);
    }


    /**
     * Get the position on the power level leaderboard of a player.
     *
     * This function is designed for API usage.
     *
     * @param playerName The name of the player to check
     *
     * @throws InvalidPlayerException if the given player does not exist in the database
     *
     * @return the position on the power level leaderboard
     */
    public int getPlayerRankOverall(String playerName) {
        return ExperienceAPI.getPlayerRankOverall(playerName);
    }

    /**
     * Sets the level of a player in a specific skill type.
     *
     * This function is designed for API usage.
     *
     * @param player The player to set the level of
     * @param skillType The skill to set the level for
     * @param skillLevel The value to set the level to
     *
     * @throws InvalidSkillException if the given skill is not valid
     */
    public void setLevel(Player player, String skillType, int skillLevel) {
        ExperienceAPI.setLevel(player, skillType, skillLevel);
    }

    /**
     * Sets the level of an offline player in a specific skill type.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to set the level of
     * @param skillType The skill to set the level for
     * @param skillLevel The value to set the level to
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public void setLevelOffline(String playerName, String skillType, int skillLevel) {
        ExperienceAPI.setLevelOffline(playerName, skillType, skillLevel);
    }

    /**
     * Sets the XP of a player in a specific skill type.
     *
     * This function is designed for API usage.
     *
     * @param player The player to set the XP of
     * @param skillType The skill to set the XP for
     * @param newValue The value to set the XP to
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public void setXP(Player player, String skillType, int newValue) {
        ExperienceAPI.setXP(player, skillType, newValue);
    }

    /**
     * Sets the XP of an offline player in a specific skill type.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to set the XP of
     * @param skillType The skill to set the XP for
     * @param newValue The value to set the XP to
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public void setXPOffline(String playerName, String skillType, int newValue) {
        ExperienceAPI.setXPOffline(playerName, skillType, newValue);
    }

    /**
     * Removes XP from a player in a specific skill type.
     *
     * This function is designed for API usage.
     *
     * @param player The player to change the XP of
     * @param skillType The skill to change the XP for
     * @param xp The amount of XP to remove
     *
     * @throws com.gmail.nossr50.api.exceptions.InvalidSkillException if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public void removeXP(Player player, String skillType, int xp) {
        ExperienceAPI.removeXP(player, skillType, xp);
    }

    /**
     * Removes XP from an offline player in a specific skill type.
     *
     * This function is designed for API usage.
     *
     * @param playerName The player to change the XP of
     * @param skillType The skill to change the XP for
     * @param xp The amount of XP to remove
     *
     * @throws InvalidSkillException if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public void removeXPOffline(String playerName, String skillType, int xp) {
        ExperienceAPI.removeXPOffline(playerName, skillType, xp);
    }
}
