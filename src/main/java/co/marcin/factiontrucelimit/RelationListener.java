package co.marcin.factiontrucelimit;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.event.EventFactionsRelationChange;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RelationListener implements Listener {
	@EventHandler
	public void onFactionsRelationChange(EventFactionsRelationChange event) {
		Rel oldRelation = event.getFaction().getRelationTo(event.getOtherFaction());
		Rel newRelation = event.getNewRelation();

		Rel wish = event.getOtherFaction().getRelationWish(event.getFaction());
		boolean works = wish == newRelation;

		FactionTruceLimit.info("wish: " + (wish==null ? "null" : wish.name()));
		FactionTruceLimit.info("old: " + oldRelation.name());
		FactionTruceLimit.info("new: " + newRelation.name());
		FactionTruceLimit.info("WORKS: " + works);
	}
}
