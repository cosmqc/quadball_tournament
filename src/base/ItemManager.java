package base;

import java.util.Random;

public class ItemManager {
	public Item[] items;
	GameEnvironment game;

	public ItemManager(GameEnvironment game) {
		this.game = game;
		
		items = new Item[64];
	
		items[0] = new Item(game, "Rocket Shoes", new int[]{1, 0, 1, 0}, 3, "Zoom into the sky!");
		items[1] = new Item(game, "Power Glove", new int[]{2, -1, 0, 0}, 7, "Unleash your true power!");
		items[2] = new Item(game, "Energy Drink", new int[]{0, 0, 2, -1}, 2, "Boost your stamina!");
		items[3] = new Item(game, "Turbo Ball", new int[]{0, 0, 1, 1}, 4, "Speed up your throws!");
		items[4] = new Item(game, "Shield Barrier", new int[]{-1, 2, 0, 0}, 8, "Defend like a fortress!");
		items[5] = new Item(game, "Agility Band", new int[]{0, 0, 1, 1}, 5, "Enhance your agility!");
		items[6] = new Item(game, "Precision Goggles", new int[]{1, -1, 0, 1}, 6, "Hit your mark with precision!");
		items[7] = new Item(game, "Lightning Boots", new int[]{0, 0, 2, -1}, 5, "Run as fast as lightning!");
		items[8] = new Item(game, "Force Field", new int[]{1, 1, -1, 0}, 7, "Create an impenetrable barrier!");
		items[9] = new Item(game, "Boost Pad", new int[]{0, 0, 2, 0}, 3, "Get an instant speed boost!");
		items[10] = new Item(game, "Sticky Gloves", new int[]{0, 1, 0, 1}, 4, "Improve your grip!");
		items[11] = new Item(game, "Time Freeze", new int[]{0, 1, 0, -1}, 6, "Insane reaction time!");
		items[12] = new Item(game, "Berserker Helm", new int[]{1, 0, 0, 1}, 6, "Tap into your berserker rage!");
		items[13] = new Item(game, "Super Whistle", new int[]{1, 1, -1, 0}, 5, "Command ultimate attention!");
		items[14] = new Item(game, "Stealth Cloak", new int[]{0, 0, 0, 2}, 7, "Become invisible to the opponent!");
		items[15] = new Item(game, "Power Shot", new int[]{2, -1, 0, 0}, 8, "Unleash a powerful shot!");
		items[16] = new Item(game, "Reflective Shield", new int[]{-1, 2, 0, 0}, 9, "Reflect incoming attacks!");
		items[17] = new Item(game, "Quickstep Shoes", new int[]{0, 0, 1, 1}, 4, "Step swiftly with agility!");
		items[18] = new Item(game, "Double Jump", new int[]{0, 0, 1, 1}, 3, "Take your jumps to new heights!");
		items[19] = new Item(game, "Spin Attack", new int[]{1, -1, 1, 0}, 5, "Unleash a spinning offensive!");
		items[20] = new Item(game, "Healing Potion", new int[]{0, 1, -1, 1}, 6, "Restore your health instantly!");
		items[21] = new Item(game, "Speed Burst", new int[]{0, 0, 2, -1}, 7, "Experience a sudden burst of speed!");
		items[22] = new Item(game, "Teleportation Device", new int[]{1, 1, 0, -1}, 9, "Transport to a different location!");
		items[23] = new Item(game, "Iron Fist", new int[]{2, 0, -1, 0}, 8, "Deliver devastating punches!");
		items[24] = new Item(game, "Boosted Shield", new int[]{0, 2, -1, 0}, 7, "Fortify your defense!");
		items[25] = new Item(game, "Precision Boots", new int[]{0, 0, 1, 1}, 4, "Enhance your footwork!");
		items[26] = new Item(game, "Phantom Cloak", new int[]{0, 0, 0, 2}, 9, "Disappear into the shadows!");
		items[27] = new Item(game, "Precision Throw", new int[]{1, -1, 0, 1}, 5, "Throw with pinpoint accuracy!");
		items[28] = new Item(game, "Sonic Sprint", new int[]{0, 0, 2, -1}, 6, "Sprint at supersonic speed!");
		items[29] = new Item(game, "Quick Reflexes", new int[]{1, 0, 0, 1}, 7, "React swiftly with lightning reflexes!");
		items[30] = new Item(game, "Magnetic Ball", new int[]{1, -1, 0, 1}, 6, "Control the ball magnetically!");
		items[31] = new Item(game, "Time Rewind", new int[]{0, 1, 0, -1}, 8, "Reverse time and undo mistakes!");
		items[32] = new Item(game, "Juggernaut Armor", new int[]{1, 1, -1, 0}, 9, "Become an unstoppable force!");
		items[33] = new Item(game, "Whirlwind Strike", new int[]{1, 0, 1, 0}, 7, "Unleash a devastating whirlwind attack!");
		items[34] = new Item(game, "Power Surge", new int[]{2, -1, 0, 0}, 8, "Channel an electrifying power surge!");
		items[35] = new Item(game, "Reflective Gloves", new int[]{-1, 2, 0, 0}, 6, "Deflect attacks with reflective gloves!");
		items[36] = new Item(game, "Lightning Dash", new int[]{0, 0, 2, -1}, 7, "Dash with the speed of lightning!");
		items[37] = new Item(game, "Adrenaline Boost", new int[]{0, 0, 2, -1}, 5, "Boost your performance with adrenaline!");
		items[38] = new Item(game, "Precision Shot", new int[]{1, -1, 0, 1}, 6, "Take accurate shots with precision!");
		items[39] = new Item(game, "Force Push", new int[]{1, 1, -1, 0}, 7, "Push opponents back with great force!");
		items[40] = new Item(game, "Evasion Cloak", new int[]{0, 0, 0, 2}, 8, "Evade enemy attacks with the cloak!");
		items[41] = new Item(game, "Power Punch", new int[]{2, 0, -1, 0}, 9, "Deliver powerful punches with enhanced strength!");
		items[42] = new Item(game, "Energy Shield", new int[]{0, 2, -1, 0}, 7, "Shield yourself with an energy barrier!");
		items[43] = new Item(game, "Agile Steps", new int[]{0, 0, 1, 1}, 4, "Move with agile steps!");
		items[44] = new Item(game, "Boosted Jump", new int[]{0, 0, 1, 1}, 5, "Jump higher with boosted power!");
		items[45] = new Item(game, "Cyclone Kick", new int[]{1, -1, 1, 0}, 6, "Unleash a cyclonic kick!");
		items[46] = new Item(game, "Healing Elixir", new int[]{0, 1, -1, 1}, 7, "Heal yourself with a magical elixir!");
		items[47] = new Item(game, "Speed Dash", new int[]{0, 0, 2, -1}, 8, "Dash with incredible speed!");
		items[48] = new Item(game, "Teleportation Orb", new int[]{1, 1, 0, -1}, 9, "Teleport instantly with the orb!");
		items[49] = new Item(game, "Iron Gauntlets", new int[]{2, 0, -1, 0}, 7, "Wear powerful iron gauntlets!");
		items[50] = new Item(game, "Reflective Shielding", new int[]{-1, 2, 0, 0}, 6, "Shield with reflective protection!");
		items[51] = new Item(game, "Quickstep Sneakers", new int[]{0, 0, 1, 1}, 4, "Step quickly with specialized sneakers!");
		items[52] = new Item(game, "Aerial Dodge", new int[]{0, 0, 1, 1}, 5, "Dodge attacks with aerial maneuvers!");
		items[53] = new Item(game, "Spin Strike", new int[]{1, -1, 1, 0}, 6, "Execute a spinning strike!");
		items[54] = new Item(game, "Healing Vial", new int[]{0, 1, -1, 1}, 7, "Use a healing vial to restore vitality!");
		items[55] = new Item(game, "Sonic Boost", new int[]{0, 0, 2, -1}, 8, "Boost your speed with a sonic burst!");
		items[56] = new Item(game, "Temporal Shift", new int[]{0, 1, 0, -1}, 9, "Shift time to gain an advantage!");
		items[57] = new Item(game, "Colossus Armor", new int[]{1, 1, -1, 0}, 7, "Wear armor that makes you a colossus!");
		items[58] = new Item(game, "Cyclone Blade", new int[]{1, 0, 1, 0}, 6, "Wield a cyclone blade in battle!");
		items[59] = new Item(game, "Power Wave", new int[]{2, -1, 0, 0}, 8, "Release a powerful wave of energy!");
		items[60] = new Item(game, "Reflective Gauntlets", new int[]{-1, 2, 0, 0}, 7, "Deflect attacks with gauntlets of reflection!");
		items[61] = new Item(game, "Swift Boots", new int[]{0, 0, 2, -1}, 5, "Gain swift movement with specialized boots!");
		items[62] = new Item(game, "Energy Sword", new int[]{1, 0, 0, 1}, 6, "Wield a sword infused with energy!");
		items[63] = new Item(game, "Stealth Helm", new int[]{0, 0, 0, 2}, 9, "Become stealthy with a specialized helmet!");
	}

	public Item randomItem() {
		int n = new Random().nextInt(items.length);
		return items[n];
	}
}
