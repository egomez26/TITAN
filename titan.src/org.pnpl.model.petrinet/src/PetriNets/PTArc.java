/**
 */
package PetriNets;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PT Arc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link PetriNets.PTArc#getInput <em>Input</em>}</li>
 *   <li>{@link PetriNets.PTArc#getOutput <em>Output</em>}</li>
 * </ul>
 *
 * @see PetriNets.PetriNetsPackage#getPTArc()
 * @model
 * @generated
 */
public interface PTArc extends Arc {
	/**
	 * Returns the value of the '<em><b>Input</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link PetriNets.Place#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input</em>' reference.
	 * @see #setInput(Place)
	 * @see PetriNets.PetriNetsPackage#getPTArc_Input()
	 * @see PetriNets.Place#getOutputs
	 * @model opposite="outputs" required="true"
	 * @generated
	 */
	Place getInput();

	/**
	 * Sets the value of the '{@link PetriNets.PTArc#getInput <em>Input</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input</em>' reference.
	 * @see #getInput()
	 * @generated
	 */
	void setInput(Place value);

	/**
	 * Returns the value of the '<em><b>Output</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link PetriNets.Transition#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output</em>' reference.
	 * @see #setOutput(Transition)
	 * @see PetriNets.PetriNetsPackage#getPTArc_Output()
	 * @see PetriNets.Transition#getInputs
	 * @model opposite="inputs" required="true"
	 * @generated
	 */
	Transition getOutput();

	/**
	 * Sets the value of the '{@link PetriNets.PTArc#getOutput <em>Output</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output</em>' reference.
	 * @see #getOutput()
	 * @generated
	 */
	void setOutput(Transition value);

} // PTArc
