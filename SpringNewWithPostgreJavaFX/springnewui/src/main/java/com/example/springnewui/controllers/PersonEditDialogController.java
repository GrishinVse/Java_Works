package com.example.springnewui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.springnewui.models.Person;
import sample.utils.DateUtils;

public class PersonEditDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    @FXML
    private void initialize(){    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        cityField.setText(person.getCity());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        birthdayField.setText(DateUtils.format(person.getBirthday()));
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    @FXML
    private void handleOK(){
        if (isInputValid()){
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setCity(cityField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setBirthday(DateUtils.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid(){
        String errorMessage = "";
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0){
            errorMessage += "Не введено имя пользователя\n";
        }

        if (lastNameField.getText() == null || lastNameField.getText().length() == 0){
            errorMessage += "Не введена фамилия пользователя\n";
        }

        if (streetField.getText() == null || streetField.getText().length() == 0){
            errorMessage += "Не введена улица\n";
        }

        if (cityField.getText() == null || cityField.getText().length() == 0){
            errorMessage += "Не введен город\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0){
            errorMessage += "Не введен почтовый индекс\n";
        } else {
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e){
                errorMessage += "Почтовый индекс состоит из цифр\n";
            }
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0){
            errorMessage += "Не введена дата рождения\n";
        } else {
            if (!DateUtils.isValidDate(birthdayField.getText())){
                errorMessage += "Дата рождения введена некорректно. Введите в формате dd.mm.yyyy\n";
            }
        }

        if (errorMessage.length() == 0){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("ОШИБКА!");
            alert.setHeaderText("Поля введены некорретно");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }
}
