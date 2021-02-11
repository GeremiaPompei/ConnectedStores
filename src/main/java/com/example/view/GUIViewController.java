package com.example.view;

import com.example.model.RecEntity;
import com.example.service.NotificationManager;
import com.example.service.Controller;
import com.example.service.StringfyRec;
import it.mynext.iaf.nettrs.Rec;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe controller dell'interfaccia grafica GUIMain.fxml.
 */
public class GUIViewController implements Initializable {

    /**
     * Label utile per mostrare notifiche.
     */
    @FXML
    Label notification;

    /**
     * Area di testo utile per mostrare i messaggi del server.
     */
    @FXML
    TextArea serverArea;
    @FXML
    TextField ipAddress;
    @FXML
    TextArea textArea;
    private RecEntity rec;
    private ObservableList<Integer> olFieldType;
    private ObservableList<Integer> olFieldSize;
    private ObservableList<Rec.REC> olRec;
    @FXML
    TextField fieldName;
    @FXML
    ChoiceBox<Integer> fieldType;
    @FXML
    ChoiceBox<Integer> fieldSize;
    @FXML
    TextField recValue;
    @FXML
    TextField recName;
    @FXML
    TextField ipReceiver;
    @FXML
    TableView<Rec.REC> table;
    @FXML
    TableColumn<Rec.REC, Integer> idColumn;
    private int selectedField;
    private int selectedRec;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getInstance().init();
        NotificationManager.getInstance().setTextArea(serverArea);
        rec = new RecEntity();
        olFieldType = FXCollections.observableArrayList();
        olFieldSize = FXCollections.observableArrayList();
        olRec = FXCollections.observableArrayList();
        initFieldType();
        initFieldSize();
        init(1, 1);
    }

    private void initFieldType() {
        olFieldType.add(rec.DEF_FLD_SZ);
        olFieldType.add(rec.DEF_DOUBLE_SZ);
        olFieldType.add(rec.DEF_FLOAT_SZ);
        olFieldType.add(rec.DEF_INT_SZ);
        olFieldType.add(rec.DEF_SHORT_SZ);
        olFieldType.add(rec.DEF_BYTE_SZ);
        fieldType.setItems(olFieldType);
    }

    private void initFieldSize() {
        olFieldSize.add(RecEntity.FDT_STR);
        olFieldSize.add(RecEntity.FDT_DOUBLE);
        olFieldSize.add(RecEntity.FDT_FLOAT);
        olFieldSize.add(RecEntity.FDT_INT);
        olFieldSize.add(RecEntity.FDT_SHORT);
        olFieldSize.add(RecEntity.FDT_BYTE);
        fieldSize.setItems(olFieldSize);
    }

    private void init(int fieldcount, int reccount) {
        RecEntity rec = new RecEntity();
        rec.setName(this.rec.getName());
        rec.init(fieldcount, reccount);
        for (int i = 0; i < this.rec.getFldcount(); i++) {
            rec.setFldData(i, this.rec.getFld()[i].NAME, this.rec.getFld()[i].TYPE, this.rec.getFld()[i].SIZE);
        }
        for (int j = 0; j < this.rec.getFldcount(); j++) {
            for (int i = 0; i < this.rec.getReccount(); i++) {
                rec.setValue(j, i, this.rec.getValue(j, i));
            }
        }
        this.rec = rec;
        updateTable();
    }

    @FXML
    public void addField() {
        init(this.rec.getFldcount() + 1, this.rec.getReccount());
    }

    @FXML
    public void removeField() {
        if (this.rec.getFldcount() > 1)
            init(this.rec.getFldcount() - 1, this.rec.getReccount());
    }

    @FXML
    public void addRec() {
        init(this.rec.getFldcount(), this.rec.getReccount() + 1);
    }

    @FXML
    public void removeRec() {
        if (this.rec.getReccount() > 1)
            init(this.rec.getFldcount(), this.rec.getReccount() - 1);
    }

    @FXML
    public void setValue() {
        if (!table.getItems().isEmpty() && rec != null) {
            this.rec.setValue(selectedField, selectedRec, this.recValue.getText());
        }
        updateTable();
    }

    @FXML
    public void send() {
        try {
            rec.setName(recName.getText());
            Controller.getInstance().getClient().postRec(rec, "https://" + ipReceiver.getText() + ":8080/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void selected() {
        Rec.REC rec = table.getSelectionModel().getSelectedItem();
        if (!table.getItems().isEmpty() && rec != null) {
            table.getFocusModel().focusedCellProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal.getTableColumn() != null) {
                    selectedField = newVal.getColumn() - 1;
                    selectedRec = table.getSelectionModel().getFocusedIndex();
                    this.recValue.setText(this.rec.getValue(selectedField, selectedRec));
                    this.fieldName.setText(this.rec.getFldName(selectedField));
                    this.fieldType.setValue(this.rec.getFldType(selectedField));
                    this.fieldSize.setValue(this.rec.getFldSize(selectedField));
                }
            });
        }
    }

    private void updateTable() {
        olRec.removeAll(olRec);
        olRec.addAll(this.rec.getRec());
        table.setItems(olRec);
        table.getColumns().removeAll(table.getColumns());
        table.getColumns().add(idColumn);
        idColumn.setCellValueFactory(cellData -> {
            for (int i = 0; i < this.rec.getReccount(); i++)
                if (this.rec.getRec()[i].equals(cellData.getValue()))
                    return new SimpleObjectProperty<>(i);
            return null;
        });
        for (int i = 0; i < this.rec.getFldcount(); i++) {
            TableColumn<Rec.REC, String> tc = new TableColumn<>();
            tc.setText(this.rec.getFldName(i));
            table.getColumns().add(tc);
            final int x = i;
            tc.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().FLDREC[x] == null ?
                    "" :
                    cellData.getValue().FLDREC[x].VALUE));
        }
    }

    public void setField() {
        if (!table.getItems().isEmpty() && rec != null) {
            this.rec.setFldData(selectedField, this.fieldName.getText(), this.fieldType.getValue(), this.fieldSize.getValue());
        }
        updateTable();
    }

    public void done() {
        textArea.setText(StringfyRec.stringOf(Controller.getInstance().getClient()
                .getRec("https://" + ipAddress.getText() + ":8080/")));
    }
}
