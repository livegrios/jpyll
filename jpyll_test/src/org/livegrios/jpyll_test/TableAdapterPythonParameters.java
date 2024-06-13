package org.livegrios.jpyll_test;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import org.livegrios.jpyll.model.PythonParameter;

/**
 *
 * @author LiveGrios
 */
public class TableAdapterPythonParameters
{
    public static Color COLOR_PYTHON_TYPE = new Color(47 / 255.0 , 145 / 255.0, 175 / 255.0, 1.0);
    public static Font FONT_DEFAULT = Font.font("Monospaced", FontWeight.NORMAL, 16);
    public static Font FONT_TYPE = Font.font("Monospaced", FontWeight.BOLD, 16);
    
    TableColumn<PythonParameter, String> tcParamName;
        TableColumn<PythonParameter, PythonParameter.Type> tcParamType;
        TableColumn<PythonParameter, Object> tcParamValue;
    
    TableView<PythonParameter> tableView;
    ObservableList<PythonParameter> data;

    public TableAdapterPythonParameters(TableView<PythonParameter> tableView, ObservableList<PythonParameter> data)
    {
        this.tableView = tableView;
        this.data = data;
    }
    
    public void adapt()
    {
        tcParamName = new TableColumn<>("Name");
        tcParamType = new TableColumn<>("Type");
        tcParamValue = new TableColumn<>("Value");
        
        tcParamName.setPrefWidth(95);
        tcParamName.setCellFactory((TableColumn<PythonParameter, String> param) ->
        {
            TableCell<PythonParameter, String> cell = new TableCell<PythonParameter, String>()
            {
                @Override
                protected void updateItem(String value, boolean empty)
                {
                    super.updateItem(value, empty); //To change body of generated methods, choose Tools | Templates.
                    
                    if (getIndex() >= 0 && getIndex() < data.size())
                    {
                        setFont(FONT_DEFAULT);
                        setText(data.get(getIndex()).getName());
                        setGraphic(null);
                    }
                    else
                        setGraphic(null);
                }
            };
            return cell;
        });
        
        tcParamType.setPrefWidth(95);
        tcParamType.setCellFactory((TableColumn<PythonParameter, PythonParameter.Type> param) ->
        {
            TableCell<PythonParameter, PythonParameter.Type> cell = new TableCell<PythonParameter, PythonParameter.Type>()
            {
                @Override
                protected void updateItem(PythonParameter.Type value, boolean empty)
                {
                    super.updateItem(value, empty); //To change body of generated methods, choose Tools | Templates.
                    
                    if (getIndex() >= 0 && getIndex() < data.size())
                    {
                        setFont(FONT_TYPE);
                        setTextFill(COLOR_PYTHON_TYPE);
                        setText(data.get(getIndex()).getType().toString());
                        setGraphic(null);
                    }
                    else
                        setGraphic(null);
                }
            };
            return cell;
        });
        
        
        tcParamValue.setPrefWidth(95);
        tcParamValue.setCellFactory((TableColumn<PythonParameter, Object> param) ->
        {
            TableCell<PythonParameter, Object> cell = new TableCell<PythonParameter, Object>()
            {
                @Override
                protected void updateItem(Object value, boolean empty)
                {
                    super.updateItem(value, empty); //To change body of generated methods, choose Tools | Templates.
                    if (getIndex() >= 0 && getIndex() < data.size())
                    {
                        setFont(FONT_DEFAULT);
                        setText(data.get(getIndex()).getValue().toString());
                        setGraphic(null);
                    }
                    else
                        setGraphic(null);
                }
            };
            return cell;
        });
        tableView.setItems(data);
        update();
    }
    
    public void update()
    {
        tableView.getColumns().clear();
        tableView.getColumns().addAll(tcParamName, tcParamType, tcParamValue);
    }
}
