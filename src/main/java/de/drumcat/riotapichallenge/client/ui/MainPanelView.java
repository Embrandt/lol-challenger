package de.drumcat.riotapichallenge.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import de.drumcat.riotapichallenge.common.Startable;
import de.drumcat.riotapichallenge.common.WidgetName;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Image;
import org.gwtbootstrap3.client.ui.NavbarLink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MainPanelView extends Composite {

	private static Logger logger = Logger
			.getLogger(MainPanelView.class.getName());

	private static final String USER_PLUS_SYMBOL_BEGIN = "Angemeldet als: ";

	private static final String USER_PLUS_SYMBOL_END = " \u00BB Abmelden";

	private static MainPanelViewUiBinder uiBinder = GWT
			.create(MainPanelViewUiBinder.class);

	interface MainPanelViewUiBinder extends UiBinder<Widget, MainPanelView> {
	}

	private final MainPanelEventBinder eventBinder = GWT
			.create(MainPanelEventBinder.class);

	interface MainPanelEventBinder extends EventBinder<MainPanelView> {
	}

	private final Map<WidgetName, Widget> widgets = new HashMap<>();

	@UiField
	Column contentColumn;
	
	@Inject
	public MainPanelView(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		eventBinder.bindEventHandlers(this, eventBus);

		logger.info("MainPanel created...");
	}

	public void addWidget(WidgetName name, Widget widget) {
		this.widgets.put(name, widget);
		this.contentColumn.add(widget);
		widget.setVisible(false);
	}

	public void showWidget(WidgetName name) {
		hideAllWidgets();
		Widget widget = this.widgets.get(name);
		widget.setVisible(true);
	}

	private void hideAllWidgets() {
		final int count = this.contentColumn.getWidgetCount();
		for (int i = 0; i < count; i++) {
			this.contentColumn.getWidget(i).setVisible(false);
		}
	}

	void showAndStartWidget(WidgetName name) {
		hideAllWidgets();
		Widget widget = this.widgets.get(name);
		widget.setVisible(true);
		if (widget instanceof Startable) {
			Startable startable = (Startable) widget;
			startable.start();
		}
	}

	public void setContentAreaVisible(boolean visible) {
		this.contentColumn.setVisible(visible);
	}

}
