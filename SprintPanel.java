package projectNav;

import java.util.ArrayList;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import kanban.KanbanView;
import model.Project;
import model.Sprint;

public class SprintPanel extends VerticalLayout {

	final ArrayList<Sprint> sprintList;
	
	public SprintPanel(Project project, Navigator nav, KanbanView nextView) {
		this.sprintList = project.getSprintList();
		
		for (Sprint sprint:sprintList) {
			
			//layout
			Panel panel = new Panel();
			HorizontalLayout hl = new HorizontalLayout();
			VerticalLayout vl = new VerticalLayout();
			
			//name and dates
			Label name = new Label(sprint.getName());
			name.setStyleName(ValoTheme.LABEL_BOLD);
			Label startDate = new Label("Scheduled dates: " + sprint.getStartDate().toString());
			Label endDate =   new Label("- " + sprint.getEndDate().toString());
			startDate.setStyleName(ValoTheme.LABEL_LIGHT);
			endDate.setStyleName(ValoTheme.LABEL_LIGHT);
			
			//edit and delete features
			Button del = new Button("del");
			del.addClickListener(e -> {
				Notification.show("deleting srint.");
			});
			
			//description
			Label description = new Label(sprint.getDescription());
			description.setStyleName(ValoTheme.LABEL_SMALL);
			description.setWidth((Page.getCurrent().getBrowserWindowWidth() - 100) / 2 + "px");
			
			
			//add to layout
			hl.addComponents(name, startDate, endDate, del);
			hl.setComponentAlignment(del, Alignment.TOP_RIGHT);
			vl.addComponents(hl, description);
			vl.setSpacing(false);
			
			hl.setSpacing(true);
			panel.setContent(vl);
			
			panel.addStyleName(ValoTheme.PANEL_WELL);
			addComponent(panel);
			
			//click the panel to go to task board view
			panel.setDescription("Click for Task Board View");
			panel.addClickListener(e -> {
				nextView.setProject(project);
				nextView.setSprint(sprint);
				nav.navigateTo(nextView.VIEW_NAME);
			});
		}

		setSpacing(true);
		setMargin(false);



	}


}
