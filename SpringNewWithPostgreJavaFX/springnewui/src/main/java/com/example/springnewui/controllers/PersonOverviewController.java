package com.example.springnewui.controllers;

import com.example.springnewui.StageInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.springnewui.models.Person;
import com.example.springnewui.utils.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class PersonOverviewController{

    private final String urlApi = StageInitializer.url_api;

    @FXML
    private TableView<Person> personTableView;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    public void setPersonTableView(TableView<Person> personTableView) {
        this.personTableView = personTableView;
    }

    // Labels block

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label birthdayLabel;

    // Buttons block

    @FXML
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    private StageInitializer mainApp;

    public PersonOverviewController(){  }

    @FXML
    public void initialize(){

        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());

        showPersonDetails(null);

        personTableView.getSelectionModel().selectedItemProperty().addListener(
                (((observableValue, oldValue, newValue) -> showPersonDetails(newValue)))
        );
    }

    private void showPersonDetails(Person person){
        if (person != null){
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            cityLabel.setText(person.getCity());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            birthdayLabel.setText(DateUtils.format(person.getBirthday()));
        } else {
            firstNameLabel.setText("No Data");
            lastNameLabel.setText("No Data");
            streetLabel.setText("No Data");
            cityLabel.setText("No Data");
            postalCodeLabel.setText("No Data");
            birthdayLabel.setText("No Data");
        }
    }

    @FXML
    private void handleDeletePerson(){
        int selectedIndex = personTableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0){
            try {
                deletePerson(urlApi, personTableView.getSelectionModel().getSelectedItem().getId());
            } catch (IOException e) {
                e.printStackTrace();
            }

            personTableView.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error!");
            alert.setHeaderText("Nothing to delete!");
            alert.setContentText("Please select a user to delete!");

            alert.showAndWait();
        }
    }

    private void deletePerson(String requestUrl, Long loc_id) throws IOException{
        URL url = new URL(requestUrl + "/" + loc_id.toString());
        System.out.println("URL to user with ID = " + loc_id.toString() + "\n| " + url + " |");

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("DELETE");
        urlConnection.connect();
        System.out.println("RESPONSE CODE = " + urlConnection.getResponseCode());
    }

    @FXML
    private void handleNewPerson() throws IOException{
        Person tempPerson = new Person();
        boolean isOkClicked = mainApp.showPersonEditDialog(tempPerson);
        if (isOkClicked){
            mainApp.getPersonData().add(tempPerson);
            createPerson(urlApi, tempPerson);
        }
    }

    private void createPerson(String request, Person tempPerson) throws IOException {
        URL url = new URL(request);
        System.out.println("URL -> " + url);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setDoOutput(true);

        System.out.println("New Person created:" + tempPerson.toString());

        //JSONArray jsonObject = new JSONArray(tempPerson.toString());

        JSONObject jsonObject = new JSONObject(tempPerson.toString());
        OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(jsonObject.toString().getBytes("UTF-8"));
        outputStream.close();
        System.out.println("RESPONSE CODE = " + urlConnection.getResponseCode());
    }

    @FXML
    private void handleEditPerson() throws IOException {
        Person selectedPerson = personTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null){
            boolean isOkClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (isOkClicked){
                showPersonDetails(selectedPerson);
                editPerson(urlApi, selectedPerson);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error!");
            alert.setHeaderText("Nothing to edit!");
            alert.setContentText("Please select a user to edit!");

            alert.showAndWait();
        }
    }

    private void editPerson(String request, Person selectedPerson) throws IOException {
        URL url = new URL(request + "/" + selectedPerson.getId());
        System.out.println("URL -> " + url);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("PUT");
        urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setDoOutput(true);

        System.out.println("Person edited:" + selectedPerson.toString());

        JSONObject jsonObject = new JSONObject(selectedPerson.toString());
        OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(jsonObject.toString().getBytes("UTF-8"));
        outputStream.close();
        System.out.println(urlConnection.getResponseCode());
    }

    /*
    public void newButton(ActionEvent actionEvent) {
        System.out.println("Создать новую запись");
    }

    public void editButton(ActionEvent actionEvent) {
        System.out.println("Редактировать!");
    }

    public void deleteButton(ActionEvent actionEvent) {
        System.out.println("Удалятор записей 2000!");
    }

     */

    public void setMainApp(StageInitializer mainApp) {
        this.mainApp = mainApp;
        personTableView.setItems(mainApp.getPersonData());
    }
}
