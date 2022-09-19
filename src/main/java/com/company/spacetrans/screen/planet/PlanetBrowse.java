package com.company.spacetrans.screen.planet;

import com.company.spacetrans.app.PlanetsLoadService;
import io.jmix.core.FileRef;
import io.jmix.ui.Dialogs;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.app.inputdialog.DialogOutcome;
import io.jmix.ui.app.inputdialog.InputParameter;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Planet;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("st_Planet.browse")
@UiDescriptor("planet-browse.xml")
@LookupComponent("planetsTable")
public class PlanetBrowse extends StandardLookup<Planet> {
    @Autowired
    private CollectionLoader<Planet> planetsDl;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private PlanetsLoadService planetsLoadService;
    @Autowired
    private Notifications notifications;

    @Subscribe("planetsTable.load")
    public void onPlanetsTableImportData(Action.ActionPerformedEvent event) {
        dialogs.createInputDialog(this)
                .withCaption("Загрузка данных")
                .withParameter(
                        InputParameter.fileParameter("file")
                                .withCaption("CSV file")
                                .withRequired(true)
                )
                .withCloseListener(closeEvent -> {
                    if (closeEvent.closedWith(DialogOutcome.OK)) {
                        FileRef fileRef = closeEvent.getValue("file");
                        planetsLoadService.importPlanetsFromFile(fileRef);
                        planetsDl.load();
                        notifications
                                .create(Notifications.NotificationType.HUMANIZED)
                                .withCaption("Загрузка окончена!")
                                .show();
                    }
                })
                .show();
    }

    @Subscribe("planetsTable")
    public void onPlanetsTableSelection(Table.SelectionEvent<Planet> event) {
        
    }


}