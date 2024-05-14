package com.nourdine.vetements.entities;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;



@Entity
public class Vetement {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	private Long idVet;
	
	@NotNull
	@Size (min = 4,max = 15)
	private String marqueVet;
	
	
	@Min(value = 10)
	@Max(value = 10000)
	private Double prixVet;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private Date DateAchat;
	
	@ManyToOne
	private Boutique boutique;
	
	
	public Long getIdVet() {
		return idVet;
	}
	public void setIdVet(Long idVet) {
		this.idVet = idVet;
	}
	public String getMarqueVet() {
		return marqueVet;
	}
	public void setMarqueVet(String marqueVet) {
		this.marqueVet = marqueVet;
	}
	public Double getPrixVet() {
		return prixVet;
	}
	public void setPrixVet(Double prixVet) {
		this.prixVet = prixVet;
	}
	public Date getDateAchat() {
		return DateAchat;
	}
	public void setDateAchat(Date dateAchat) {
		DateAchat = dateAchat;
	}
	public Vetement() {
		super();
		 
	}
	public Vetement(String marqueVet, Double prixVet, Date dateAchat) {
		super();
		this.marqueVet = marqueVet;
		this.prixVet = prixVet;
		DateAchat = dateAchat;
	}
	@Override
	public String toString() {
		return "Vetement [idVet=" + idVet + ", marqueVet=" + marqueVet + ", prixVet=" + prixVet + ", DateAchat="
				+ DateAchat + "]";
	}
	public Boutique getBoutique() {
		return boutique;
	}
	public void setBoutique(Boutique boutique) {
		this.boutique = boutique;
	}
	
	
	
	
}
