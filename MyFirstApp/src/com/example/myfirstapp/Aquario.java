package com.example.myfirstapp;


import java.util.Date;

public class Aquario {
	
	private Long id;
	
	private String dataMedicao;
	
	private String horaMedicao;

	private float tempAgua;
	
	private float tempTampa;
	
	private float tempAmb;
	
	private int nivelSump;
	
	private int nivelRepo;
	
	private int luzLigada;
	
	public Aquario(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataMedicao() {
		return dataMedicao;
	}

	public void setDataMedicao(String dataMedicao) {
		this.dataMedicao = dataMedicao;
	}

	public String getHoraMedicao() {
		return horaMedicao;
	}

	public void setHoraMedicao(String horaMedicao) {
		this.horaMedicao = horaMedicao;
	}

	public float getTempAgua() {
		return tempAgua;
	}

	public void setTempAgua(float tempAgua) {
		this.tempAgua = tempAgua;
	}

	public float getTempTampa() {
		return tempTampa;
	}

	public void setTempTampa(float tempTampa) {
		this.tempTampa = tempTampa;
	}

	public float getTempAmb() {
		return tempAmb;
	}

	public void setTempAmb(float tempAmb) {
		this.tempAmb = tempAmb;
	}

	public int getNivelSump() {
		return nivelSump;
	}

	public void setNivelSump(int nivelSump) {
		this.nivelSump = nivelSump;
	}

	public int getNivelRepo() {
		return nivelRepo;
	}

	public void setNivelRepo(int nivelRepo) {
		this.nivelRepo = nivelRepo;
	}

	public int getLuzLigada() {
		return luzLigada;
	}

	public void setLuzLigada(int luzLigada) {
		this.luzLigada = luzLigada;
	}
	
	public String toString(){
		return "[id"+id+
		" dataMedicao: "+dataMedicao+" horaMedicao: "+horaMedicao+
		" tempAgua "+tempAgua+" tempTampa: "+tempTampa+
		" tempAmb: " + tempAmb + " nivelSump: "+nivelSump+
		" nivelRepo: "+nivelRepo+ " luzLigada:"+luzLigada+"]";
	}

		
}	
