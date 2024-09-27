/**
 */
package PetriNets;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link PetriNets.Arc#getName <em>Name</em>}</li>
 *   <li>{@link PetriNets.Arc#getNet <em>Net</em>}</li>
 *   <li>{@link PetriNets.Arc#getWeight <em>Weight</em>}</li>
 * </ul>
 *
 * @see PetriNets.PetriNetsPackage#getArc()
 * @model abstract="true"
 * @generated
 */
public interface Arc extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see PetriNets.PetriNetsPackage#getArc_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link PetriNets.Arc#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Net</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link PetriNets.PetriNet#getArcs <em>Arcs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Net</em>' container reference.
	 * @see #setNet(PetriNet)
	 * @see PetriNets.PetriNetsPackage#getArc_Net()
	 * @see PetriNets.PetriNet#getArcs
	 * @model opposite="arcs" required="true" transient="false"
	 * @generated
	 */
	PetriNet getNet();

	/**
	 * Sets the value of the '{@link PetriNets.Arc#getNet <em>Net</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Net</em>' container reference.
	 * @see #getNet()
	 * @generated
	 */
	void setNet(PetriNet value);

	/**
	 * Returns the value of the '<em><b>Weight</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight</em>' attribute.
	 * @see #setWeight(int)
	 * @see PetriNets.PetriNetsPackage#getArc_Weight()
	 * @model default="1" required="true"
	 * @generated
	 */
	int getWeight();

	/**
	 * Sets the value of the '{@link PetriNets.Arc#getWeight <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight</em>' attribute.
	 * @see #getWeight()
	 * @generated
	 */
	void setWeight(int value);

} // Arc
