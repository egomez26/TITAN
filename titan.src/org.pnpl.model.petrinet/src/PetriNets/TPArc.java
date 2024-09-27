/**
 */
package PetriNets;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TP Arc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link PetriNets.TPArc#getInput <em>Input</em>}</li>
 *   <li>{@link PetriNets.TPArc#getOutput <em>Output</em>}</li>
 * </ul>
 *
 * @see PetriNets.PetriNetsPackage#getTPArc()
 * @model
 * @generated
 */
public interface TPArc extends Arc {
	/**
	 * Returns the value of the '<em><b>Input</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link PetriNets.Transition#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input</em>' reference.
	 * @see #setInput(Transition)
	 * @see PetriNets.PetriNetsPackage#getTPArc_Input()
	 * @see PetriNets.Transition#getOutputs
	 * @model opposite="outputs" required="true"
	 * @generated
	 */
	Transition getInput();

	/**
	 * Sets the value of the '{@link PetriNets.TPArc#getInput <em>Input</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input</em>' reference.
	 * @see #getInput()
	 * @generated
	 */
	void setInput(Transition value);

	/**
	 * Returns the value of the '<em><b>Output</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link PetriNets.Place#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output</em>' reference.
	 * @see #setOutput(Place)
	 * @see PetriNets.PetriNetsPackage#getTPArc_Output()
	 * @see PetriNets.Place#getInputs
	 * @model opposite="inputs" required="true"
	 * @generated
	 */
	Place getOutput();

	/**
	 * Sets the value of the '{@link PetriNets.TPArc#getOutput <em>Output</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output</em>' reference.
	 * @see #getOutput()
	 * @generated
	 */
	void setOutput(Place value);

} // TPArc
