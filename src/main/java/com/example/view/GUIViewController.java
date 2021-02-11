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
     * Lista dei rec contenuti nella tabella.
     */
    private ObservableList<Rec.REC> olRec;

    /**
     * Tabella utile per visualizzare ed inserire i dati nel rec.
     */
    @FXML
    TableView<Rec.REC> table;

    /**
     * Posizione del field nella tabella.
     */
    private int selectedField;

    /**
     * Posizione del rec nella tabella.
     */
    private int selectedRec;

    /**
     * Colonna degli id inerenti ai rec.
     */
    @FXML
    TableColumn<Rec.REC, Integer> idColumn;

    /**
     * Rec su cui vengono settati i campi prima di essere inviato tramite una richiesta POST.
     */
    private RecEntity rec;

    /**
     * Campo di testo utile ad impostare il nome di un field.
     */
    @FXML
    TextField fieldName;

    /**
     * Lista dei tipi di field.
     */
    private ObservableList<Integer> olFieldType;

    /**
     * Choice box utile per selezionare il tipo del field.
     */
    @FXML
    ChoiceBox<Integer> fieldType;

    /**
     * Lista delle dimensioni di field.
     */
    private ObservableList<Integer> olFieldSize;

    /**
     * Choice box utile per selezionare la dimensione del field.
     */
    @FXML
    ChoiceBox<Integer> fieldSize;

    /**
     * Campo testo utile per inserire il valore del rec.
     */
    @FXML
    TextField recValue;

    /**
     * Campo testo utile per inserire il nome del rec.
     */
    @FXML
    TextField recName;

    /**
     * Campo di testo utile per inserire l'indirizzo ip del del destinatario della richiesta POST.
     */
    @FXML
    TextField ipAddressPost;
    /**
     * Campo di testo dell'indirizzo ip del destinatario della richiesta GET.
     */
    @FXML
    TextField ipAddressGet;

    /**
     * Area di testo della risorsa ottenuta tramite la chiamata GET.
     */
    @FXML
    TextArea clientArea;

    /**
     * Area di testo utile per mostrare i messaggi del server.
     */
    @FXML
    TextArea serverArea;

    /**
     * Label utile per mostrare notifiche.
     */
    @FXML
    Label notification;

    /**
     * Metodo inizializzatore.
     *
     * @param url
     * @param resourceBundle
     */
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

    /**
     * Metodo utile all'inizializzazione della choicebox fieldType.
     */
    private void initFieldType() {
        olFieldType.add(rec.DEF_FLD_SZ);
        olFieldType.add(rec.DEF_DOUBLE_SZ);
        olFieldType.add(rec.DEF_FLOAT_SZ);
        olFieldType.add(rec.DEF_INT_SZ);
        olFieldType.add(rec.DEF_SHORT_SZ);
        olFieldType.add(rec.DEF_BYTE_SZ);
        fieldType.setItems(olFieldType);
    }

    /**
     * Metodo utile all'inizializzazione della choicebox fieldSize.
     */
    private void initFieldSize() {
        olFieldSize.add(RecEntity.FDT_STR);
        olFieldSize.add(RecEntity.FDT_DOUBLE);
        olFieldSize.add(RecEntity.FDT_FLOAT);
        olFieldSize.add(RecEntity.FDT_INT);
        olFieldSize.add(RecEntity.FDT_SHORT);
        olFieldSize.add(RecEntity.FDT_BYTE);
        fieldSize.setItems(olFieldSize);
    }

    /**
     * Metodo utile all'inizializzazione e l'aggiornamento del rec.
     *
     * @param fieldcount Nuomero dei field.
     * @param reccount   Numero dei rec.
     */
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

    /**
     * Metodo utile ad aggiornare la tabella in base al nuovo stato del rec.
     */
    private void updateTable() {
        try {
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
        } catch (Exception e) {
            notification.setText(e.getMessage());
        }
    }

    /**
     * Metodo utile per la selezione di una casella della tabella.
     */
    @FXML
    public void selected() {
        try {
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
        } catch (Exception e) {
            notification.setText(e.getMessage());
        }
    }

    /**
     * Aggiunta field.
     */
    @FXML
    public void addField() {
        init(this.rec.getFldcount() + 1, this.rec.getReccount());
    }

    /**
     * Rimozione field.
     */
    @FXML
    public void removeField() {
        if (this.rec.getFldcount() > 1)
            init(this.rec.getFldcount() - 1, this.rec.getReccount());
    }

    /**
     * Aggiunta rec.
     */
    @FXML
    public void addRec() {
        init(this.rec.getFldcount(), this.rec.getReccount() + 1);
    }

    /**
     * Rimozione rec.
     */
    @FXML
    public void removeRec() {
        if (this.rec.getReccount() > 1)
            init(this.rec.getFldcount(), this.rec.getReccount() - 1);
    }

    /**
     * Metodo utile a settare un field all'interno del rec.
     */
    public void setField() {
        try {
            if (!table.getItems().isEmpty() && rec != null) {
                this.rec.setFldData(selectedField, this.fieldName.getText(), this.fieldType.getValue(), this.fieldSize.getValue());
            }
            updateTable();
        } catch (Exception e) {
            notification.setText(e.getMessage());
        }
    }

    /**
     * Metodo utile a settare il valore di un rec.
     */
    @FXML
    public void setValue() {
        try {
            if (!table.getItems().isEmpty() && rec != null) {
                this.rec.setValue(selectedField, selectedRec, this.recValue.getText());
            }
            updateTable();
        } catch (Exception e) {
            notification.setText(e.getMessage());
        }
    }

    /**
     * Metodo utile ad inviare una richiesta POST.
     */
    @FXML
    public void post() {
        try {
            rec.setName(recName.getText());
            boolean b = Controller.getInstance().getClient().postRec(rec, "https://" + ipAddressPost.getText() + ":8080/");
            if (!b) {
                notification.setText("La richiesta POST non è andata a buon fine!");
            }
        } catch (Exception e) {
            notification.setText(e.getMessage());
        }
    }

    /**
     * Metodo utile ad inviare una richiesta GET.
     */
    public void get() {
        try {
            clientArea.setText(StringfyRec.stringOf(Controller.getInstance().getClient().getRec("https://" + ipAddressGet.getText() + ":8080/")));
        } catch (Exception e) {
            notification.setText(e.getMessage());
        }
    }
}
