/**
 */
package PetriNets.impl;

import PetriNets.Arc;
import PetriNets.PetriNet;
import PetriNets.PetriNetsPackage;
import PetriNets.Place;
import PetriNets.Transition;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Petri Net</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link PetriNets.impl.PetriNetImpl#getPlaces <em>Places</em>}</li>
 *   <li>{@link PetriNets.impl.PetriNetImpl#getTrans <em>Trans</em>}</li>
 *   <li>{@link PetriNets.impl.PetriNetImpl#getArcs <em>Arcs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PetriNetImpl extends MinimalEObjectImpl.Container implements PetriNet {
	/**
	 * The cached value of the '{@link #getPlaces() <em>Places</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Place> places;

	/**
	 * The cached value of the '{@link #getTrans() <em>Trans</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrans()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> trans;

	/**
	 * The cached value of the '{@link #getArcs() <em>Arcs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArcs()
	 * @generated
	 * @ordered
	 */
	protected EList<Arc> arcs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PetriNetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PetriNetsPackage.Literals.PETRI_NET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Place> getPlaces() {
		if (places == null) {
			places = new EObjectContainmentWithInverseEList<Place>(Place.class, this, PetriNetsPackage.PETRI_NET__PLACES, PetriNetsPackage.PLACE__NET);
		}
		return places;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Transition> getTrans() {
		if (trans == null) {
			trans = new EObjectContainmentWithInverseEList<Transition>(Transition.class, this, PetriNetsPackage.PETRI_NET__TRANS, PetriNetsPackage.TRANSITION__NET);
		}
		return trans;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Arc> getArcs() {
		if (arcs == null) {
			arcs = new EObjectContainmentWithInverseEList<Arc>(Arc.class, this, PetriNetsPackage.PETRI_NET__ARCS, PetriNetsPackage.ARC__NET);
		}
		return arcs;
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
			case PetriNetsPackage.PETRI_NET__PLACES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPlaces()).basicAdd(otherEnd, msgs);
			case PetriNetsPackage.PETRI_NET__TRANS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTrans()).basicAdd(otherEnd, msgs);
			case PetriNetsPackage.PETRI_NET__ARCS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getArcs()).basicAdd(otherEnd, msgs);
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
			case PetriNetsPackage.PETRI_NET__PLACES:
				return ((InternalEList<?>)getPlaces()).basicRemove(otherEnd, msgs);
			case PetriNetsPackage.PETRI_NET__TRANS:
				return ((InternalEList<?>)getTrans()).basicRemove(otherEnd, msgs);
			case PetriNetsPackage.PETRI_NET__ARCS:
				return ((InternalEList<?>)getArcs()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PetriNetsPackage.PETRI_NET__PLACES:
				return getPlaces();
			case PetriNetsPackage.PETRI_NET__TRANS:
				return getTrans();
			case PetriNetsPackage.PETRI_NET__ARCS:
				return getArcs();
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
			case PetriNetsPackage.PETRI_NET__PLACES:
				getPlaces().clear();
				getPlaces().addAll((Collection<? extends Place>)newValue);
				return;
			case PetriNetsPackage.PETRI_NET__TRANS:
				getTrans().clear();
				getTrans().addAll((Collection<? extends Transition>)newValue);
				return;
			case PetriNetsPackage.PETRI_NET__ARCS:
				getArcs().clear();
				getArcs().addAll((Collection<? extends Arc>)newValue);
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
			case PetriNetsPackage.PETRI_NET__PLACES:
				getPlaces().clear();
				return;
			case PetriNetsPackage.PETRI_NET__TRANS:
				getTrans().clear();
				return;
			case PetriNetsPackage.PETRI_NET__ARCS:
				getArcs().clear();
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
			case PetriNetsPackage.PETRI_NET__PLACES:
				return places != null && !places.isEmpty();
			case PetriNetsPackage.PETRI_NET__TRANS:
				return trans != null && !trans.isEmpty();
			case PetriNetsPackage.PETRI_NET__ARCS:
				return arcs != null && !arcs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PetriNetImpl
