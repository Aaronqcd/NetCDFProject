package com.ewangz.bean;

import java.util.List;

import ucar.nc2.Dimension;
import ucar.nc2.Variable;

public class NCDimVar {
	private List<Dimension> dim;
	private List<Variable> vari;
	
	public List<Dimension> getDim() {
		return dim;
	}

	public void setDim(List<Dimension> dim) {
		this.dim = dim;
	}

	public List<Variable> getVari() {
		return vari;
	}

	public void setVari(List<Variable> vari) {
		this.vari = vari;
	}

	/*public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("dim: ");
		for (Dimension dimension : dim) {
			strBuff.append(dimension.getShortName());
		}
		strBuff.append("vari: ");
		for (Variable variable : vari) {
			strBuff.append(variable.getShortName());
		}
		return strBuff.toString();
		//return "dim: "+dim+"vari: "+vari;
	}*/
	
}
