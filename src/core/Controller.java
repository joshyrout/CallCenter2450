package core;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.text.Text;
import javafx.util.converter.DefaultStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable
{

    @FXML private TableView callHistory;
    @FXML private TableColumn<CallHistoryData, Integer> id;
    @FXML private TableColumn<CallHistoryData, String> length;
    @FXML private TableColumn<CallHistoryData, String> date;
    @FXML private TableColumn<CallHistoryData, String> reason;
    private TableColumn<CallHistoryData, String> status;

    private ObservableList<CallHistoryData> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //  public CallHistoryData(int callID, int agentID, int length, LocalDateTime date, String reason, CallStatus status)
        data = FXCollections.observableArrayList();


        DBConnection instance = DBConnection.getInstance();
        instance.retrieveCustomerData(3);
        CallHistoryData[] testArray = instance.getCallHistoryArray();
        data.addAll(testArray);

        callHistory.setItems(data);
        int index = 0;
        for (CallHistoryData ca: data)
        {
            final int col = index;
            id.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getAgentID()));
            length.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>((c.getValue().getLengthOfCall() / 60) + " min " + (c.getValue().getLengthOfCall() % 60)  + " sec"));
            date.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getDateOfCall().toString()));

            reason.setCellFactory(cell ->
            {
                return new TableCell<CallHistoryData, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty)
                    {
                        super.updateItem(item, empty);

                        Text text = new Text(item);
                        text.setStyle("-fx-text-alignment:center;");
                        text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(25));
                        setGraphic(text);
                    }
                };
        });
            reason.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getReasonForCall()));


            index++;
        }

        status = new TableColumn<>();
        status.setPrefWidth(125);
        status.setText("Call Status");
        status.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getCallStatus().toString()));

        status.setEditable(false);
        callHistory.getColumns().add(status);


    }






}


