package base;

import java.util.Random;

public class ItemManager {
	public Item[] items;
	GameEnvironment game;
	
	public ItemManager(GameEnvironment game) {
		this.game = game;
		
		items = new Item[64];
	
		items[0] = new Item("Rocket Shoes", new int[]{1, 0, 1, 0}, 3, "Zoom into the sky!");
		items[1] = new Item("Power Glove", new int[]{2, -1, 0, 0}, 7, "Unleash your true power!");
		items[2] = new Item("Energy Drink", new int[]{0, 0, 2, -1}, 2, "Boost your stamina!");
		items[3] = new Item("Turbo Ball", new int[]{0, 0, 1, 1}, 4, "Speed up your throws!");
		items[4] = new Item("Shield Barrier", new int[]{-1, 2, 0, 0}, 8, "Defend like a fortress!");
		items[5] = new Item("Agility Band", new int[]{0, 0, 1, 1}, 5, "Enhance your agility!");
		items[6] = new Item("Precision Goggles", new int[]{1, -1, 0, 1}, 6, "Hit your mark with precision!");
		items[7] = new Item("Lightning Boots", new int[]{0, 0, 2, -1}, 5, "Run as fast as lightning!");
		items[8] = new Item("Force Field", new int[]{1, 1, -1, 0}, 7, "Create an impenetrable barrier!");
		items[9] = new Item("Boost Pad", new int[]{0, 0, 2, 0}, 3, "Get an instant speed boost!");
		items[10] = new Item("Sticky Gloves", new int[]{0, 1, 0, 1}, 4, "Improve your grip!");
		items[11] = new Item("Time Freeze", new int[]{0, 1, 0, -1}, 6, "Insane reaction time!");
		items[12] = new Item("Berserker Helm", new int[]{1, 0, 0, 1}, 6, "Tap into your berserker rage!");
		items[13] = new Item("Super Whistle", new int[]{1, 1, -1, 0}, 5, "Command ultimate attention!");
		items[14] = new Item("Stealth Cloak", new int[]{0, 0, 0, 2}, 7, "Become invisible to the opponent!");
		items[15] = new Item("Power Shot", new int[]{2, -1, 0, 0}, 8, "Unleash a powerful shot!");
		items[16] = new Item("Reflective Shield", new int[]{-1, 2, 0, 0}, 9, "Reflect incoming attacks!");
		items[17] = new Item("Quickstep Shoes", new int[]{0, 0, 1, 1}, 4, "Step swiftly with agility!");
		items[18] = new Item("Double Jump", new int[]{0, 0, 1, 1}, 3, "Take your jumps to new heights!");
		items[19] = new Item("Spin Attack", new int[]{1, -1, 1, 0}, 5, "Unleash a spinning offensive!");
		items[20] = new Item("Healing Potion", new int[]{0, 1, -1, 1}, 6, "Restore your health instantly!");
		items[21] = new Item("Speed Burst", new int[]{0, 0, 2, -1}, 7, "Experience a sudden burst of speed!");
		items[22] = new Item("Teleportation Device", new int[]{1, 1, 0, -1}, 9, "Transport to a different location!");
		items[23] = new Item("Iron Fist", new int[]{2, 0, -1, 0}, 8, "Deliver devastating punches!");
		items[24] = new Item("Boosted Shield", new int[]{0, 2, -1, 0}, 7, "Fortify your defense!");
		items[25] = new Item("Precision Boots", new int[]{0, 0, 1, 1}, 4, "Enhance your footwork!");
		items[26] = new Item("Phantom Cloak", new int[]{0, 0, 0, 2}, 9, "Disappear into the shadows!");
		items[27] = new Item("Precision Throw", new int[]{1, -1, 0, 1}, 5, "Throw with pinpoint accuracy!");
		items[28] = new Item("Sonic Sprint", new int[]{0, 0, 2, -1}, 6, "Sprint at supersonic speed!");
		items[29] = new Item("Quick Reflexes", new int[]{1, 0, 0, 1}, 7, "React swiftly with lightning reflexes!");
		items[30] = new Item("Magnetic Ball", new int[]{1, -1, 0, 1}, 6, "Control the ball magnetically!");
		items[31] = new Item("Time Rewind", new int[]{0, 1, 0, -1}, 8, "Reverse time and undo mistakes!");
		items[32] = new Item("Juggernaut Armor", new int[]{1, 1, -1, 0}, 9, "Become an unstoppable force!");
		items[33] = new Item("Whirlwind Strike", new int[]{1, 0, 1, 0}, 7, "Unleash a devastating whirlwind attack!");
		items[34] = new Item("Power Surge", new int[]{2, -1, 0, 0}, 8, "Channel an electrifying power surge!");
		items[35] = new Item("Reflective Gloves", new int[]{-1, 2, 0, 0}, 6, "Deflect attacks with reflective gloves!");
		items[36] = new Item("Lightning Dash", new int[]{0, 0, 2, -1}, 7, "Dash with the speed of lightning!");
		items[37] = new Item("Adrenaline Boost", new int[]{0, 0, 2, -1}, 5, "Boost your performance with adrenaline!");
		items[38] = new Item("Precision Shot", new int[]{1, -1, 0, 1}, 6, "Take accurate shots with precision!");
		items[39] = new Item("Force Push", new int[]{1, 1, -1, 0}, 7, "Push opponents back with great force!");
		items[40] = new Item("Evasion Cloak", new int[]{0, 0, 0, 2}, 8, "Evade enemy attacks with the cloak!");
		items[41] = new Item("Power Punch", new int[]{2, 0, -1, 0}, 9, "Deliver powerful punches with enhanced strength!");
		items[42] = new Item("Energy Shield", new int[]{0, 2, -1, 0}, 7, "Shield yourself with an energy barrier!");
		items[43] = new Item("Agile Steps", new int[]{0, 0, 1, 1}, 4, "Move with agile steps!");
		items[44] = new Item("Boosted Jump", new int[]{0, 0, 1, 1}, 5, "Jump higher with boosted power!");
		items[45] = new Item("Cyclone Kick", new int[]{1, -1, 1, 0}, 6, "Unleash a cyclonic kick!");
		items[46] = new Item("Healing Elixir", new int[]{0, 1, -1, 1}, 7, "Heal yourself with a magical elixir!");
		items[47] = new Item("Speed Dash", new int[]{0, 0, 2, -1}, 8, "Dash with incredible speed!");
		items[48] = new Item("Teleportation Orb", new int[]{1, 1, 0, -1}, 9, "Teleport instantly with the orb!");
		items[49] = new Item("Iron Gauntlets", new int[]{2, 0, -1, 0}, 7, "Wear powerful iron gauntlets!");
		items[50] = new Item("Reflective Shielding", new int[]{-1, 2, 0, 0}, 6, "Shield with reflective protection!");
		items[51] = new Item("Quickstep Sneakers", new int[]{0, 0, 1, 1}, 4, "Step quickly with specialized sneakers!");
		items[52] = new Item("Aerial Dodge", new int[]{0, 0, 1, 1}, 5, "Dodge attacks with aerial maneuvers!");
		items[53] = new Item("Spin Strike", new int[]{1, -1, 1, 0}, 6, "Execute a spinning strike!");
		items[54] = new Item("Healing Vial", new int[]{0, 1, -1, 1}, 7, "Use a healing vial to restore vitality!");
		items[55] = new Item("Sonic Boost", new int[]{0, 0, 2, -1}, 8, "Boost your speed with a sonic burst!");
		items[56] = new Item("Temporal Shift", new int[]{0, 1, 0, -1}, 9, "Shift time to gain an advantage!");
		items[57] = new Item("Colossus Armor", new int[]{1, 1, -1, 0}, 7, "Wear armor that makes you a colossus!");
		items[58] = new Item("Cyclone Blade", new int[]{1, 0, 1, 0}, 6, "Wield a cyclone blade in battle!");
		items[59] = new Item("Power Wave", new int[]{2, -1, 0, 0}, 8, "Release a powerful wave of energy!");
		items[60] = new Item("Reflective Gauntlets", new int[]{-1, 2, 0, 0}, 7, "Deflect attacks with gauntlets of reflection!");
		items[61] = new Item("Swift Boots", new int[]{0, 0, 2, -1}, 5, "Gain swift movement with specialized boots!");
		items[62] = new Item("Energy Sword", new int[]{1, 0, 0, 1}, 6, "Wield a sword infused with energy!");
		items[63] = new Item("Stealth Helm", new int[]{0, 0, 0, 2}, 9, "Become stealthy with a specialized helmet!");
	}
	
	public Item randomItem() {
		int n = new Random().nextInt(items.length);
		return items[n];
	}
}
