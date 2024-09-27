/**
 */
package PetriNets;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timed Place</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link PetriNets.TimedPlace#getTimestamp <em>Timestamp</em>}</li>
 * </ul>
 *
 * @see PetriNets.PetriNetsPackage#getTimedPlace()
 * @model
 * @generated
 */
public interface TimedPlace extends Place {
	/**
	 * Returns the value of the '<em><b>Timestamp</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timestamp</em>' attribute.
	 * @see #setTimestamp(int)
	 * @see PetriNets.PetriNetsPackage#getTimedPlace_Timestamp()
	 * @model default="0"
	 * @generated
	 */
	int getTimestamp();

	/**
	 * Sets the value of the '{@link PetriNets.TimedPlace#getTimestamp <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(int value);

} // TimedPlace
