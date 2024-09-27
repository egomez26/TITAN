/**
 */
package PetriNets.impl;

import PetriNets.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PetriNetsFactoryImpl extends EFactoryImpl implements PetriNetsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PetriNetsFactory init() {
		try {
			PetriNetsFactory thePetriNetsFactory = (PetriNetsFactory)EPackage.Registry.INSTANCE.getEFactory(PetriNetsPackage.eNS_URI);
			if (thePetriNetsFactory != null) {
				return thePetriNetsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PetriNetsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PetriNetsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PetriNetsPackage.PETRI_NET: return createPetriNet();
			case PetriNetsPackage.PLACE: return createPlace();
			case PetriNetsPackage.TIMED_PLACE: return createTimedPlace();
			case PetriNetsPackage.TRANSITION: return createTransition();
			case PetriNetsPackage.PT_ARC: return createPTArc();
			case PetriNetsPackage.TP_ARC: return createTPArc();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PetriNet createPetriNet() {
		PetriNetImpl petriNet = new PetriNetImpl();
		return petriNet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Place createPlace() {
		PlaceImpl place = new PlaceImpl();
		return place;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimedPlace createTimedPlace() {
		TimedPlaceImpl timedPlace = new TimedPlaceImpl();
		return timedPlace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PTArc createPTArc() {
		PTArcImpl ptArc = new PTArcImpl();
		return ptArc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TPArc createTPArc() {
		TPArcImpl tpArc = new TPArcImpl();
		return tpArc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PetriNetsPackage getPetriNetsPackage() {
		return (PetriNetsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PetriNetsPackage getPackage() {
		return PetriNetsPackage.eINSTANCE;
	}

} //PetriNetsFactoryImpl
