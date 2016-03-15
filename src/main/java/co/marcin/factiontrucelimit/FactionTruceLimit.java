package co.marcin.factiontrucelimit;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class FactionTruceLimit extends JavaPlugin implements Listener {
	private final Map<Faction, RelationCounter> factions = new HashMap<>();

	public void onEnable() {
		getServer().getPluginManager().registerEvents(new RelationListener(), this);
		load();

		info("v" + getDescription().getVersion() + " Enabled");
	}

	public void onDisable() {
		info("v" + getDescription().getVersion() + " Disabled");
	}

	public static void info(String message) {
		Bukkit.getLogger().info("[FactionTruceLimit] " + message);
	}

	private void load() {
		for(Faction faction : FactionColl.get().getAll()) {
			RelationCounter counter = getCounter(faction);

			for(Faction otherFaction : FactionColl.get().getAll()) {
				Rel relation = faction.getRelationTo(otherFaction);

				if(relation == Rel.NEUTRAL) {
					continue;
				}

				counter.addRelation(relation);
			}

			factions.put(faction, counter);
		}
	}

	public RelationCounter getCounter(Faction faction) {
		if(factions.containsKey(faction)) {
			return factions.get(faction);
		}

		return new RelationCounter();
	}
}
