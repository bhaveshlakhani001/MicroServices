package com.vedity.microservices.VaccinationCenter.Model;

import java.util.List;

import com.vedity.microservices.VaccinationCenter.Entity.VaccinationCenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequiredResponse {
	 
	private VaccinationCenter center;
	private List<Citizen> citizens;
	public List<Citizen> getCitizens() {
		return citizens;
	}
	public void setCitizens(List<Citizen> citizens) {
		this.citizens = citizens;
	}
	public void setCenter(VaccinationCenter center2) {
		
		this.center = center2;
	}
	

}

