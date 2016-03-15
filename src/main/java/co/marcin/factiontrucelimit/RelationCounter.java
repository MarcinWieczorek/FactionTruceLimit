package co.marcin.factiontrucelimit;

import com.massivecraft.factions.Rel;

import java.util.HashMap;
import java.util.Map;

public class RelationCounter {
	private Map<Rel, Integer> relations = new HashMap<>();

	public void addRelation(Rel rel) {
		int count = 0;

		if(relations.containsKey(rel)) {
			count = getRelation(rel);
		}

		count++;

		relations.put(rel, count);
	}

	public void removeRelation(Rel rel) {
		if(relations.containsKey(rel)) {
			int count = getRelation(rel);

			if(count == 0) {
				return;
			}

			count--;

			relations.put(rel, count);
		}
	}

	public int getRelation(Rel rel) {
		if(relations.containsKey(rel)) {
			return relations.get(rel);
		}

		return 0;
	}
}
