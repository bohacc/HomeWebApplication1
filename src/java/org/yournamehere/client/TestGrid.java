/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.data.*;
import com.smartgwt.client.types.FetchMode;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 *
 * @author Martin
 */
public class TestGrid extends VerticalPanel {
    public TestGrid(){
        //DataSource dataSource = DataSource.get("masterTest");
        final ListGrid lg = new ListGrid(); 
        lg.setHeight(170);  
        lg.setWidth(500);  
        lg.setAlternateRecordStyles(true);  
        //lg.setDataSource(dataSource);  
        lg.setAutoFetchData(true);  
        lg.setShowFilterEditor(true);  
        lg.setCanEdit(true);  
        lg.setEditEvent(ListGridEditEvent.CLICK);  
        lg.setCanRemoveRecords(true);
        
        //final Label label = null;
        
        final AsyncCallback<Test[]> callback = new AsyncCallback<Test[]>() {

            public void onSuccess(Test[] result) {
                ListGridRecord records[]=null;
                if(result!=null){
                  records = new ListGridRecord[result.length];
                  for(int cntr=0; cntr < result.length; cntr++){
                            ListGridRecord record = new ListGridRecord();
                            record.setAttribute("id", result[cntr].getId());
                            record.setAttribute("nazev", result[cntr].getNazev());
                            records[cntr] = record;
                            //label.setText(result[cntr].getNazev());
                  }
                  lg.setData(records);
                }
            }
            
            public void onFailure(Throwable caught) {
                
            }
        };
        GWTServiceAsync async = GWT.create(GWTService.class); 
        
        //lg.setAutoFetchData(true);  
  
        ListGridField idField = new ListGridField("id");  
        idField.setWidth("100");  
  
        ListGridField nameField = new ListGridField("nazev");
        nameField.setWidth("300");
  
        ListGridField textField = new ListGridField("text");  
        textField.setWidth("300");  
  
        lg.setFields(idField, nameField, textField);
        
        async.getTests(callback);
        
        add(lg); 
        
        //add(label);
    }
}
