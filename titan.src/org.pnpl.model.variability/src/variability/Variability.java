/**
 */
package variability;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link variability.Variability#getPetrinet <em>Petrinet</em>}</li>
 *   <li>{@link variability.Variability#getFeaturemodel <em>Featuremodel</em>}</li>
 *   <li>{@link variability.Variability#getPresencecondition <em>Presencecondition</em>}</li>
 * </ul>
 *
 * @see variability.VariabilityPackage#getVariability()
 * @model
 * @generated
 */
public interface Variability extends EObject {
	/**
	 * Returns the value of the '<em><b>Petrinet</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Petrinet</em>' containment reference.
	 * @see #setPetrinet(FileURI)
	 * @see variability.VariabilityPackage#getVariability_Petrinet()
	 * @model containment="true" required="true"
	 * @generated
	 */
	FileURI getPetrinet();

	/**
	 * Sets the value of the '{@link variability.Variability#getPetrinet <em>Petrinet</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Petrinet</em>' containment reference.
	 * @see #getPetrinet()
	 * @generated
	 */
	void setPetrinet(FileURI value);

	/**
	 * Returns the value of the '<em><b>Featuremodel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Featuremodel</em>' containment reference.
	 * @see #setFeaturemodel(FileURI)
	 * @see variability.VariabilityPackage#getVariability_Featuremodel()
	 * @model containment="true" required="true"
	 * @generated
	 */
	FileURI getFeaturemodel();

	/**
	 * Sets the value of the '{@link variability.Variability#getFeaturemodel <em>Featuremodel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Featuremodel</em>' containment reference.
	 * @see #getFeaturemodel()
	 * @generated
	 */
	void setFeaturemodel(FileURI value);

	/**
	 * Returns the value of the '<em><b>Presencecondition</b></em>' containment reference list.
	 * The list contents are of type {@link variability.PresenceCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presencecondition</em>' containment reference list.
	 * @see variability.VariabilityPackage#getVariability_Presencecondition()
	 * @model containment="true" ordered="false"
	 *        annotation="http://www.eclipse.org/OCL/Collection nullFree='false'"
	 * @generated
	 */
	EList<PresenceCondition> getPresencecondition();

	PresenceCondition getPresencecondition(String name);

} // Variability
