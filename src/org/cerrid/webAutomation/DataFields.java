package org.cerrid.webAutomation;

public class DataFields {
	private double calculatedValue;
	private String indicesType;
	private String indices;
	private double inputValue;
	private String inputType;
	private int rowNumber;
	private int columnNumber;

	public DataFields(String indicesType, String indices, double inputValue, String inputType, int rowNumber, int columnNumber) {
		this.indicesType = indicesType;
		this.indices = indices;
		this.inputValue = inputValue;
		this.inputType = inputType;
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public double getCalculatedValue() {
		return calculatedValue;
	}

	public void setCalculatedValue(double calculatedValue) {
		this.calculatedValue = calculatedValue;
	}

	public String getIndicesType() {
		return indicesType;
	}

	public void setIndicesType(String indicesType) {
		this.indicesType = indicesType;
	}

	public String getIndices() {
		return indices;
	}

	public void setIndices(String indices) {
		this.indices = indices;
	}

	public double getInputValue() {
		return inputValue;
	}

	public void setInputValue(double inputValue) {
		this.inputValue = inputValue;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
}
