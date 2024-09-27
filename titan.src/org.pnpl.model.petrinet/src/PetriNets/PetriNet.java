/**
 */
package PetriNets;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Petri Net</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link PetriNets.PetriNet#getPlaces <em>Places</em>}</li>
 *   <li>{@link PetriNets.PetriNet#getTrans <em>Trans</em>}</li>
 *   <li>{@link PetriNets.PetriNet#getArcs <em>Arcs</em>}</li>
 * </ul>
 *
 * @see PetriNets.PetriNetsPackage#getPetriNet()
 * @model
 * @generated
 */
public interface PetriNet extends EObject {
	/**
	 * Returns the value of the '<em><b>Places</b></em>' containment reference list.
	 * The list contents are of type {@link PetriNets.Place}.
	 * It is bidirectional and its opposite is '{@link PetriNets.Place#getNet <em>Net</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Places</em>' containment reference list.
	 * @see PetriNets.PetriNetsPackage#getPetriNet_Places()
	 * @see PetriNets.Place#getNet
	 * @model opposite="net" containment="true" ordered="false"
	 * @generated
	 */
	EList<Place> getPlaces();

	/**
	 * Returns the value of the '<em><b>Trans</b></em>' containment reference list.
	 * The list contents are of type {@link PetriNets.Transition}.
	 * It is bidirectional and its opposite is '{@link PetriNets.Transition#getNet <em>Net</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trans</em>' containment reference list.
	 * @see PetriNets.PetriNetsPackage#getPetriNet_Trans()
	 * @see PetriNets.Transition#getNet
	 * @model opposite="net" containment="true" ordered="false"
	 * @generated
	 */
	EList<Transition> getTrans();

	/**
	 * Returns the value of the '<em><b>Arcs</b></em>' containment reference list.
	 * The list contents are of type {@link PetriNets.Arc}.
	 * It is bidirectional and its opposite is '{@link PetriNets.Arc#getNet <em>Net</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arcs</em>' containment reference list.
	 * @see PetriNets.PetriNetsPackage#getPetriNet_Arcs()
	 * @see PetriNets.Arc#getNet
	 * @model opposite="net" containment="true" ordered="false"
	 * @generated
	 */
	EList<Arc> getArcs();

} // PetriNet
