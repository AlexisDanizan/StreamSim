/**
 * 
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;

import core.runnable.RunnableStreamEmission;
import core.stream.IElementStream;


/**
 * @author Roland
 *
 */
public class ElementStreamBean implements Serializable{

	private IElementStream stream;
	private RunnableStreamEmission emission;
	
	private String name;
	private Integer port;
	private ArrayList<String> attrNames;
	private ArrayList<String> attrTypes;
	private Integer nbAttrs;
	private String variation;
	private ArrayList<Double> varTimestamps;
	private ArrayList<Double> varRates;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8054543050510774566L;

	/**
	 * 
	 */
	public ElementStreamBean() {	
	}

	/**
	 * @return the stream
	 */
	public IElementStream getStream() {
		return stream;
	}

	/**
	 * @param stream the stream to set
	 */
	public void setStream(IElementStream stream) {
		this.stream = stream;
	}

	/**
	 * @return the emission
	 */
	public RunnableStreamEmission getEmission() {
		return emission;
	}

	/**
	 * @param emission the emission to set
	 */
	public void setEmission(RunnableStreamEmission emission) {
		this.emission = emission;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the attrNames
	 */
	public ArrayList<String> getAttrNames() {
		return attrNames;
	}

	/**
	 * @param attrNames the attrNames to set
	 */
	public void setAttrNames(ArrayList<String> attrNames) {
		this.attrNames = attrNames;
	}

	/**
	 * @return the attrTypes
	 */
	public ArrayList<String> getAttrTypes() {
		return attrTypes;
	}

	/**
	 * @param attrTypes the attrTypes to set
	 */
	public void setAttrTypes(ArrayList<String> attrTypes) {
		this.attrTypes = attrTypes;
	}

	/**
	 * @return the variation
	 */
	public String getVariation() {
		return variation;
	}

	/**
	 * @param variation the variation to set
	 */
	public void setVariation(String variation) {
		this.variation = variation;
	}

	/**
	 * @return the nbAttrs
	 */
	public Integer getNbAttrs() {
		return nbAttrs;
	}

	/**
	 * @param nbAttrs the nbAttrs to set
	 */
	public void setNbAttrs(Integer nbAttrs) {
		this.nbAttrs = nbAttrs;
	}

	/**
	 * @return the varTimestamps
	 */
	public ArrayList<Double> getVarTimestamps() {
		return varTimestamps;
	}

	/**
	 * @param varTimestamps the varTimestamps to set
	 */
	public void setVarTimestamps(ArrayList<Double> varTimestamps) {
		this.varTimestamps = varTimestamps;
	}

	/**
	 * @return the varRates
	 */
	public ArrayList<Double> getVarRates() {
		return varRates;
	}

	/**
	 * @param varRates the varRates to set
	 */
	public void setVarRates(ArrayList<Double> varRates) {
		this.varRates = varRates;
	}

}
