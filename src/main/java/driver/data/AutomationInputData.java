package driver.data;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AutomationInputData {

    private String TC_ID;
    private final String PATH = "data/Automation_Input_Data_Sheet.xlsx";
    private Fillo fillo;
    private Connection connection;
    private HashMap<String,String> hmap;

    /**
     *
     * @param TC_ID
     */
    public AutomationInputData(String TC_ID){
        this.TC_ID = TC_ID;
        fillo = new Fillo();
        try {
            connection = fillo
                    .getConnection(PATH);
        } catch (FilloException e) {
            e.printStackTrace();
        }
        storeDataHashMap();
    }

    /**
     * Get Data Sheet Name for the test case
     * @return
     */
    private String getDataSheetName(){
        String columnValue = null;
        String query = "Select DataSheetName from TestCaseDetails where TC_ID = '" + this.TC_ID + "'";
        Recordset recSet = null;
        try {
            recSet = connection.executeQuery(query);
            while(recSet.next()){
                columnValue = recSet.getField("DataSheetName");
            }
        } catch (FilloException e) {
            e.printStackTrace();
        }
        recSet.close();
        return columnValue;
    }

    /**
     * Strore all data of a specific test case in hash map
     */
    private void storeDataHashMap(){
        String dataSheetName = getDataSheetName();
        hmap = new HashMap<String, String>();
        List<String> header = new ArrayList<String>();

        String query = "Select * from " + dataSheetName + " where TC_ID = '" + this.TC_ID + "'";
        Recordset recSet = null;
        try{
            recSet = connection.executeQuery(query);
            while(recSet.next()){
                header = recSet.getFieldNames();
            }
        } catch (FilloException e) {
            e.printStackTrace();
        }

        for(String hrd : header){
            try{
                if(! recSet.getField(hrd).equals("")) {
                    hmap.put(hrd,recSet.getField(hrd));
                }
            } catch (FilloException e){
                e.printStackTrace();
            }
        }
        recSet.close();
    }

    /**
     * get value of any column of a test cases
     * @param colName
     * @return
     */
    public String getData(String colName){
        return hmap.get(colName);
    }

    /**
     * Close Connection
     */
    public void closeConnection(){
        connection.close();
    }

}
