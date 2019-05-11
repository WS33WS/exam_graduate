package entity;

import java.util.ArrayList;
import java.util.List;

public class LookSin {
	private List<LookSinSelect> select=new ArrayList();
	private List<LookSinSystem> System=new ArrayList();
	public List<LookSinSelect> getSelect() {
		return select;
	}
	public void setSelect(List<LookSinSelect> select) {
		this.select = select;
	}
	public List<LookSinSystem> getSystem() {
		return System;
	}
	public void setSystem(List<LookSinSystem> system) {
		System = system;
	}
	public LookSin() {}
	public LookSin(List<LookSinSelect> select, List<LookSinSystem> system) {
		super();
		this.select = select;
		System = system;
	}
	@Override
	public String toString() {
		return "LookSin [select=" + select + ", System=" + System + "]";
	}
	
	

}
