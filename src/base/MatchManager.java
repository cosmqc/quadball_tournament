package base;

import java.util.ArrayList;
import java.util.List;

public class MatchManager {
	public GameEnvironment game;
	public List<Team> matchOptions = new ArrayList<Team>();
	
	public MatchManager(GameEnvironment game) {
		this.game = game;
		refreshMatches(game);
	}

	public void refreshMatches(GameEnvironment game) {
		matchOptions.removeAll(matchOptions);
		for (int i = 0; i <= game.numMatches; i++) {
			matchOptions.add(new Team(game));
		}
	}
}
