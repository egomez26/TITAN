/**
 */
package PetriNets.impl;

import PetriNets.PTArc;
import PetriNets.PetriNet;
import PetriNets.PetriNetsPackage;
import PetriNets.Place;
import PetriNets.TPArc;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Place</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link PetriNets.impl.PlaceImpl#getNet <em>Net</em>}</li>
 *   <li>{@link PetriNets.impl.PlaceImpl#getName <em>Name</em>}</li>
 *   <li>{@link PetriNets.impl.PlaceImpl#getMarking <em>Marking</em>}</li>
 *   <li>{@link PetriNets.impl.PlaceImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link PetriNets.impl.PlaceImpl#getInputs <em>Inputs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlaceImpl extends MinimalEObjectImpl.Container implements Place {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMarking() <em>Marking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarking()
	 * @generated
	 * @ordered
	 */
	protected static final int MARKING_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMarking() <em>Marking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarking()
	 * @generated
	 * @ordered
	 */
	protected int marking = MARKING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputs()
	 * @generated
	 * @ordered
	 */
	protected EList<PTArc> outputs;

	/**
	 * The cached value of the '{@link #getInputs() <em>Inputs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<TPArc> inputs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PetriNetsPackage.Literals.PLACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PetriNet getNet() {
		if (eContainerFeatureID() != PetriNetsPackage.PLACE__NET) return null;
		return (PetriNet)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNet(PetriNet newNet, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newNet, PetriNetsPackage.PLACE__NET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNet(PetriNet newNet) {
		if (newNet != eInternalContainer() || (eContainerFeatureID() != PetriNetsPackage.PLACE__NET && newNet != null)) {
			if (EcoreUtil.isAncestor(this, newNet))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newNet != null)
				msgs = ((InternalEObject)newNet).eInverseAdd(this, PetriNetsPackage.PETRI_NET__PLACES, PetriNet.class, msgs);
			msgs = basicSetNet(newNet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PetriNetsPackage.PLACE__NET, newNet, newNet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PetriNetsPackage.PLACE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getMarking() {
		return marking;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMarking(int newMarking) {
		int oldMarking = marking;
		marking = newMarking;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PetriNetsPackage.PLACE__MARKING, oldMarking, marking));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PTArc> getOutputs() {
		if (outputs == null) {
			outputs = new EObjectWithInverseResolvingEList<PTArc>(PTArc.class, this, PetriNetsPackage.PLACE__OUTPUTS, PetriNetsPackage.PT_ARC__INPUT);
		}
		return outputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TPArc> getInputs() {
		if (inputs == null) {
			inputs = new EObjectWithInverseResolvingEList<TPArc>(TPArc.class, this, PetriNetsPackage.PLACE__INPUTS, PetriNetsPackage.TP_ARC__OUTPUT);
		}
		return inputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PetriNetsPackage.PLACE__NET:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetNet((PetriNet)otherEnd, msgs);
			case PetriNetsPackage.PLACE__OUTPUTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutputs()).basicAdd(otherEnd, msgs);
			case PetriNetsPackage.PLACE__INPUTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInputs()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PetriNetsPackage.PLACE__NET:
				return basicSetNet(null, msgs);
			case PetriNetsPackage.PLACE__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case PetriNetsPackage.PLACE__INPUTS:
				return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case PetriNetsPackage.PLACE__NET:
				return eInternalContainer().eInverseRemove(this, PetriNetsPackage.PETRI_NET__PLACES, PetriNet.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PetriNetsPackage.PLACE__NET:
				return getNet();
			case PetriNetsPackage.PLACE__NAME:
				return getName();
			case PetriNetsPackage.PLACE__MARKING:
				return getMarking();
			case PetriNetsPackage.PLACE__OUTPUTS:
				return getOutputs();
			case PetriNetsPackage.PLACE__INPUTS:
				return getInputs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PetriNetsPackage.PLACE__NET:
				setNet((PetriNet)newValue);
				return;
			case PetriNetsPackage.PLACE__NAME:
				setName((String)newValue);
				return;
			case PetriNetsPackage.PLACE__MARKING:
				setMarking((Integer)newValue);
				return;
			case PetriNetsPackage.PLACE__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends PTArc>)newValue);
				return;
			case PetriNetsPackage.PLACE__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends TPArc>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PetriNetsPackage.PLACE__NET:
				setNet((PetriNet)null);
				return;
			case PetriNetsPackage.PLACE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PetriNetsPackage.PLACE__MARKING:
				setMarking(MARKING_EDEFAULT);
				return;
			case PetriNetsPackage.PLACE__OUTPUTS:
				getOutputs().clear();
				return;
			case PetriNetsPackage.PLACE__INPUTS:
				getInputs().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PetriNetsPackage.PLACE__NET:
				return getNet() != null;
			case PetriNetsPackage.PLACE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PetriNetsPackage.PLACE__MARKING:
				return marking != MARKING_EDEFAULT;
			case PetriNetsPackage.PLACE__OUTPUTS:
				return outputs != null && !outputs.isEmpty();
			case PetriNetsPackage.PLACE__INPUTS:
				return inputs != null && !inputs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", marking: ");
		result.append(marking);
		result.append(')');
		return result.toString();
	}

} //PlaceImpl
