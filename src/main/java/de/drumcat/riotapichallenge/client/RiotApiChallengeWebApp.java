package de.drumcat.riotapichallenge.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import de.drumcat.riotapichallenge.client.ui.MainPanelView;
import de.drumcat.riotapichallenge.common.ServicePreparator;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.options.BootboxLocale;
import org.gwtbootstrap3.extras.select.client.SelectClientBundle;

import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RiotApiChallengeWebApp implements EntryPoint {

	private static Logger logger = Logger.getLogger(RiotApiChallengeWebApp.class
			.getName());

	private static final String HOST_LOADING_IMAGE = "loadingImage";

	private static final String HISTORY_MAIN = "main";

	private static final String LOCALE_DE_DE = "de_DE";

	private final RiotApiChallengeWebAppGinjector injector = GWT
			.create(RiotApiChallengeWebAppGinjector.class);
	
	@Override
	public void onModuleLoad() {
		// Disable Back Button
		setupHistory();

		// Bootbox setup
		setupBootbox();

		initServices();

		injectScriptForSelect();
	}

	private void injectScriptForSelect() {
		// Workaround for bug:
		// https://github.com/gwtbootstrap3/gwtbootstrap3-extras/issues/308
		ScriptInjector
				.fromUrl(GWT.getModuleBaseURL() + SelectClientBundle.SELECT_JS)
				.setCallback(new Callback<Void, Exception>() {
					@Override
					public void onFailure(Exception reason) {
						logger.info("Script load failed Info: " + reason);
					}

					@Override
					public void onSuccess(Void result) {
						logger.info("Script for Select loaded successful!");
						createViews();

						removeLoadingImage();
					}
				})
				.setWindow(ScriptInjector.TOP_WINDOW).setRemoveTag(true)
				.inject();
	}

	private void removeLoadingImage() {
		// Remove loadingImage from Host HTML page
		RootPanel.getBodyElement().removeChild(
				RootPanel.get(HOST_LOADING_IMAGE).getElement());
	}

	private void initServices() {
		ServicePreparator servicePreparator = injector.getServicePreparator();
		servicePreparator.prepare();
	}

	private void setupHistory() {
		History.newItem(HISTORY_MAIN);

		// Add history listener
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String token = event.getValue();
				if (!token.equals(HISTORY_MAIN)) {
					History.newItem(HISTORY_MAIN);
				}
			}
		});
	}

	private void createViews() {
		// Views
		logger.info("Create Views begins...");

		MainPanelView mainPanelView = injector.getMainPanel();
		mainPanelView.setContentAreaVisible(false);

		RootPanel.get().add(mainPanelView);
		logger.info("Create Views ends...");
	}

	private void setupBootbox() {
		if (LocaleInfo.getCurrentLocale().getLocaleName().equals(LOCALE_DE_DE)) {
			logger.info("Locale: "
					+ LocaleInfo.getCurrentLocale().getLocaleName());
			Bootbox.setLocale(BootboxLocale.DE);
		}
	}
}
