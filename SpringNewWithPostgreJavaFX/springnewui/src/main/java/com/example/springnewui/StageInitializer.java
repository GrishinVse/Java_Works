package com.example.springnewui;

import com.example.springnewui.JavaFXApplication.StageReadyEvent;
import com.example.springnewui.controllers.PersonEditDialogController;
import com.example.springnewui.controllers.PersonOverviewController;
import com.example.springnewui.models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Value("classpath:/rootLayout.fxml")
    private Resource rootResource;

    @Value("classpath:/main.fxml")
    private Resource mainResource;

    @Value("classpath:/personEditDialog.fxml")
    private Resource personEditDialogResource;

    private Stage primaryStage;
    private BorderPane rootLayout;

    private String applicationTitle;
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        this.primaryStage= stageReadyEvent.getStage();
        //stage.setTitle("Приложение адресов");
        this.primaryStage.setTitle(applicationTitle);

        initRootLayout();
        showPersonOverview();
    }

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle) {
        this.applicationTitle = applicationTitle;
        for (int i=0; i < 25;i++){
            personData.add(new Person("Name " + i ,"Last name " + i*2));
        }
    }

    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader(rootResource.getURL());
            //loader.setLocation(Main.class.getResource("views/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showPersonOverview(){
        try{
            FXMLLoader loader = new FXMLLoader(mainResource.getURL());
            //loader.setLocation(Main.class.getResource("views/main.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

            PersonOverviewController PController = loader.getController();
            PController.setMainApp(this);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog(Person person){
        try {
            FXMLLoader loader = new FXMLLoader(personEditDialogResource.getURL());
            //loader.setLocation(Main.class.getResource("views/personEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("EDIT");
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
