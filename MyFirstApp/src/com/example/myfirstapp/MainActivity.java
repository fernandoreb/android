package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class MainActivity extends Activity implements OnClickListener,Runnable{

	Button btn;
	ArrayList<Aquario>aquarioList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        new Thread(this).start();
        aquarioList = new ArrayList<Aquario>();
        
        btn = (Button) findViewById(R.id.Button02);
        btn.setOnClickListener(this);
        //btn.setEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
 // run when the button is clicked
    public void onClick(View view) {
    	criaLinhaTabela();
    }


	@Override
	public void run() {
		// TODO Auto-generated method stub
		carregarDadosAquario();
	}
	
	public void criaLinhaTabela(){
		
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
		table.removeAllViews();
        
        // create a new TableRow
        TableRow row;
        TextView t;
        Aquario aquario;
        
        if(aquarioList.size()<0){
        	row = new TableRow(this);
        	t = new TextView(this);
        	t.setText("Sem dados, ou erro no recebimento");
        	row.addView(t);
        	table.addView(row,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        	return;
        }
        
        
        for(int i = 0; i < aquarioList.size(); i++){
        	aquario = aquarioList.get(i);
        	row = new TableRow(this);
        	t = new TextView(this);
        	t.setText(aquario.getDataMedicao()+" - "+aquario.getHoraMedicao()+" - "+aquario.getTempAgua());
        	row.addView(t);
        	table.addView(row,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}
	
	public void carregarDadosAquario(){
		
		String SOAP_ACTION = "http://fargwebserver-2.fargweb.cloudbees.net/services/ManutencaoAquario.ManutencaoAquarioHttpSoap12Endpoint/listaCompleta";
		String METHOD_NAME = "listaCompleta";
		String NAMESPACE = "http://webservices";
		String URL = "http://fargwebserver-2.fargweb.cloudbees.net/services/ManutencaoAquario.ManutencaoAquarioHttpSoap12Endpoint/";
		
		SoapObject request = new SoapObject(NAMESPACE,
				METHOD_NAME);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		
		try {
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			
			SoapObject body =  (SoapObject)envelope.bodyIn;
			SoapObject temp;
			Aquario aquario;
			for(int i = 0; i < body.getPropertyCount(); i++){
				
				temp =(SoapObject) body.getProperty(i);
				//Log.d("obj2",""+temp);
				aquario = new Aquario();
				aquario.setDataMedicao(temp.getProperty(0)+"");
				aquario.setHoraMedicao(temp.getProperty(1)+"");
				aquario.setId(Long.parseLong( temp.getProperty(2)+""));
				aquario.setLuzLigada(Integer.parseInt(temp.getProperty(3)+""));
				aquario.setNivelRepo(Integer.parseInt(temp.getProperty(4)+""));
				aquario.setNivelSump(Integer.parseInt(temp.getProperty(5)+""));
				aquario.setTempAgua(Float.parseFloat(temp.getProperty(6)+""));
				aquario.setTempAmb(Float.parseFloat(temp.getProperty(7)+""));
				aquario.setTempTampa(Float.parseFloat(temp.getProperty(8)+""));
				aquarioList.add(aquario);
			}
			//btn.setEnabled(true);
        }
        catch(Exception e){
        	Log.d("ERRO",e.toString());
        }
		
		

	}
    
}
