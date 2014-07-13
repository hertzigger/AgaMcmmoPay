Important this plugin is still in ALPHA

This plugin requires AgaBacbone please see https://github.com/hertzigger/AgaBacbone
This plugin also requires McMMo version 1.5 please see http://dev.bukkit.org/bukkit-plugins/mcmmo/

This is a minecraft plugin that works on bukkit or server build off of bukkit ie Spigot

Currently the plugin is not configurable with any yml files and to change the timers, amounts
etc you will need to edit the code. If you do want to make changes the easiest place to do this would be

src/java/com/craftaga/agamcmmowage/AgaMcMmoWage.java

About this plugin

The AgaMcmmoWage plugin pays a user a percentage of their MMO rank on a set interval. It is a very simple plugin
which will pay the user this amount based on their login times. This plugin does not use a single global timer
to pay all players on a set delay. Each user gets their own timer when they login.

Timers are setup for multi threading and this plugin uses spring to load up string files for plugin text. The
plugin should create very little overhead for the server if any at all.

TODO:
use yml configuration files
add a mysql backend to log user payments and payment totals
fix but where some users aren't being removed
remove afk logging to the console