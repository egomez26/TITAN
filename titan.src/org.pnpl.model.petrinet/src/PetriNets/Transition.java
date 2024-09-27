/**
 */
package PetriNets;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link PetriNets.Transition#getNet <em>Net</em>}</li>
 *   <li>{@link PetriNets.Transition#getName <em>Name</em>}</li>
 *   <li>{@link PetriNets.Transition#getInputs <em>Inputs</em>}</li>
 *   <li>{@link PetriNets.Transition#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link PetriNets.Transition#getDelay <em>Delay</em>}</li>
 * </ul>
 *
 * @see PetriNets.PetriNetsPackage#getTransition()
 * @model
 * @generated
 */
public interface Transition extends EObject {
	/**
	 * Returns the value of the '<em><b>Net</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link PetriNets.PetriNet#getTrans <em>Trans</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Net</em>' container reference.
	 * @see #setNet(PetriNet)
	 * @see PetriNets.PetriNetsPackage#getTransition_Net()
	 * @see PetriNets.PetriNet#getTrans
	 * @model opposite="trans" required="true" transient="false"
	 * @generated
	 */
	PetriNet getNet();

	/**
	 * Sets the value of the '{@link PetriNets.Transition#getNet <em>Net</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Net</em>' container reference.
	 * @see #getNet()
	 * @generated
	 */
	void setNet(PetriNet value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see PetriNets.PetriNetsPackage#getTransition_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link PetriNets.Transition#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Inputs</b></em>' reference list.
	 * The list contents are of type {@link PetriNets.PTArc}.
	 * It is bidirectional and its opposite is '{@link PetriNets.PTArc#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inputs</em>' reference list.
	 * @see PetriNets.PetriNetsPackage#getTransition_Inputs()
	 * @see PetriNets.PTArc#getOutput
	 * @model opposite="output"
	 * @generated
	 */
	EList<PTArc> getInputs();

	/**
	 * Returns the value of the '<em><b>Outputs</b></em>' reference list.
	 * The list contents are of type {@link PetriNets.TPArc}.
	 * It is bidirectional and its opposite is '{@link PetriNets.TPArc#getInput <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outputs</em>' reference list.
	 * @see PetriNets.PetriNetsPackage#getTransition_Outputs()
	 * @see PetriNets.TPArc#getInput
	 * @model opposite="input"
	 * @generated
	 */
	EList<TPArc> getOutputs();

	/**
	 * Returns the value of the '<em><b>Delay</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delay</em>' attribute.
	 * @see #setDelay(int)
	 * @see PetriNets.PetriNetsPackage#getTransition_Delay()
	 * @model default="0"
	 * @generated
	 */
	int getDelay();

	/**
	 * Sets the value of the '{@link PetriNets.Transition#getDelay <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay</em>' attribute.
	 * @see #getDelay()
	 * @generated
	 */
	void setDelay(int value);

} // Transition
