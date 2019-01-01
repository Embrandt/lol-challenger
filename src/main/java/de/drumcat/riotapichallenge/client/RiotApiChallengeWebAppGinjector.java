package de.drumcat.riotapichallenge.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import de.drumcat.riotapichallenge.client.ui.MainPanelView;
import de.drumcat.riotapichallenge.common.ServicePreparator;

@GinModules(RiotApiChallengeWebAppGinModule.class)
public interface RiotApiChallengeWebAppGinjector extends Ginjector {

	ServicePreparator getServicePreparator();

	MainPanelView getMainPanel();

}
