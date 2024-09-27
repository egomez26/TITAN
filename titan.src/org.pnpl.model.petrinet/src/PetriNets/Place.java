/**
 */
package PetriNets;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Place</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link PetriNets.Place#getNet <em>Net</em>}</li>
 *   <li>{@link PetriNets.Place#getName <em>Name</em>}</li>
 *   <li>{@link PetriNets.Place#getMarking <em>Marking</em>}</li>
 *   <li>{@link PetriNets.Place#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link PetriNets.Place#getInputs <em>Inputs</em>}</li>
 * </ul>
 *
 * @see PetriNets.PetriNetsPackage#getPlace()
 * @model
 * @generated
 */
public interface Place extends EObject {
	/**
	 * Returns the value of the '<em><b>Net</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link PetriNets.PetriNet#getPlaces <em>Places</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Net</em>' container reference.
	 * @see #setNet(PetriNet)
	 * @see PetriNets.PetriNetsPackage#getPlace_Net()
	 * @see PetriNets.PetriNet#getPlaces
	 * @model opposite="places" required="true" transient="false"
	 * @generated
	 */
	PetriNet getNet();

	/**
	 * Sets the value of the '{@link PetriNets.Place#getNet <em>Net</em>}' container reference.
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
	 * @see PetriNets.PetriNetsPackage#getPlace_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link PetriNets.Place#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Marking</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Marking</em>' attribute.
	 * @see #setMarking(int)
	 * @see PetriNets.PetriNetsPackage#getPlace_Marking()
	 * @model default="0"
	 * @generated
	 */
	int getMarking();

	/**
	 * Sets the value of the '{@link PetriNets.Place#getMarking <em>Marking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Marking</em>' attribute.
	 * @see #getMarking()
	 * @generated
	 */
	void setMarking(int value);

	/**
	 * Returns the value of the '<em><b>Outputs</b></em>' reference list.
	 * The list contents are of type {@link PetriNets.PTArc}.
	 * It is bidirectional and its opposite is '{@link PetriNets.PTArc#getInput <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outputs</em>' reference list.
	 * @see PetriNets.PetriNetsPackage#getPlace_Outputs()
	 * @see PetriNets.PTArc#getInput
	 * @model opposite="input"
	 * @generated
	 */
	EList<PTArc> getOutputs();

	/**
	 * Returns the value of the '<em><b>Inputs</b></em>' reference list.
	 * The list contents are of type {@link PetriNets.TPArc}.
	 * It is bidirectional and its opposite is '{@link PetriNets.TPArc#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inputs</em>' reference list.
	 * @see PetriNets.PetriNetsPackage#getPlace_Inputs()
	 * @see PetriNets.TPArc#getOutput
	 * @model opposite="output"
	 * @generated
	 */
	EList<TPArc> getInputs();

} // Place
