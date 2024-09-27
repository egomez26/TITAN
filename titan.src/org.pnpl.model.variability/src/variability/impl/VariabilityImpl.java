/**
 */
package variability.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import variability.FileURI;
import variability.PresenceCondition;
import variability.Variability;
import variability.VariabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variability</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link variability.impl.VariabilityImpl#getPetrinet <em>Petrinet</em>}</li>
 *   <li>{@link variability.impl.VariabilityImpl#getFeaturemodel <em>Featuremodel</em>}</li>
 *   <li>{@link variability.impl.VariabilityImpl#getPresencecondition <em>Presencecondition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariabilityImpl extends MinimalEObjectImpl.Container implements Variability {
	/**
	 * The cached value of the '{@link #getPetrinet() <em>Petrinet</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPetrinet()
	 * @generated
	 * @ordered
	 */
	protected FileURI petrinet;

	/**
	 * The cached value of the '{@link #getFeaturemodel() <em>Featuremodel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeaturemodel()
	 * @generated
	 * @ordered
	 */
	protected FileURI featuremodel;

	/**
	 * The cached value of the '{@link #getPresencecondition() <em>Presencecondition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPresencecondition()
	 * @generated
	 * @ordered
	 */
	protected EList<PresenceCondition> presencecondition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariabilityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VariabilityPackage.Literals.VARIABILITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FileURI getPetrinet() {
		return petrinet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPetrinet(FileURI newPetrinet, NotificationChain msgs) {
		FileURI oldPetrinet = petrinet;
		petrinet = newPetrinet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VariabilityPackage.VARIABILITY__PETRINET, oldPetrinet, newPetrinet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPetrinet(FileURI newPetrinet) {
		if (newPetrinet != petrinet) {
			NotificationChain msgs = null;
			if (petrinet != null)
				msgs = ((InternalEObject)petrinet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VariabilityPackage.VARIABILITY__PETRINET, null, msgs);
			if (newPetrinet != null)
				msgs = ((InternalEObject)newPetrinet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VariabilityPackage.VARIABILITY__PETRINET, null, msgs);
			msgs = basicSetPetrinet(newPetrinet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VariabilityPackage.VARIABILITY__PETRINET, newPetrinet, newPetrinet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FileURI getFeaturemodel() {
		return featuremodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFeaturemodel(FileURI newFeaturemodel, NotificationChain msgs) {
		FileURI oldFeaturemodel = featuremodel;
		featuremodel = newFeaturemodel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VariabilityPackage.VARIABILITY__FEATUREMODEL, oldFeaturemodel, newFeaturemodel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeaturemodel(FileURI newFeaturemodel) {
		if (newFeaturemodel != featuremodel) {
			NotificationChain msgs = null;
			if (featuremodel != null)
				msgs = ((InternalEObject)featuremodel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VariabilityPackage.VARIABILITY__FEATUREMODEL, null, msgs);
			if (newFeaturemodel != null)
				msgs = ((InternalEObject)newFeaturemodel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VariabilityPackage.VARIABILITY__FEATUREMODEL, null, msgs);
			msgs = basicSetFeaturemodel(newFeaturemodel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VariabilityPackage.VARIABILITY__FEATUREMODEL, newFeaturemodel, newFeaturemodel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PresenceCondition> getPresencecondition() {
		if (presencecondition == null) {
			presencecondition = new EObjectContainmentEList<PresenceCondition>(PresenceCondition.class, this, VariabilityPackage.VARIABILITY__PRESENCECONDITION);
		}
		return presencecondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VariabilityPackage.VARIABILITY__PETRINET:
				return basicSetPetrinet(null, msgs);
			case VariabilityPackage.VARIABILITY__FEATUREMODEL:
				return basicSetFeaturemodel(null, msgs);
			case VariabilityPackage.VARIABILITY__PRESENCECONDITION:
				return ((InternalEList<?>)getPresencecondition()).basicRemove(otherEnd, msgs);
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
			case VariabilityPackage.VARIABILITY__PETRINET:
				return getPetrinet();
			case VariabilityPackage.VARIABILITY__FEATUREMODEL:
				return getFeaturemodel();
			case VariabilityPackage.VARIABILITY__PRESENCECONDITION:
				return getPresencecondition();
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
			case VariabilityPackage.VARIABILITY__PETRINET:
				setPetrinet((FileURI)newValue);
				return;
			case VariabilityPackage.VARIABILITY__FEATUREMODEL:
				setFeaturemodel((FileURI)newValue);
				return;
			case VariabilityPackage.VARIABILITY__PRESENCECONDITION:
				getPresencecondition().clear();
				getPresencecondition().addAll((Collection<? extends PresenceCondition>)newValue);
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
			case VariabilityPackage.VARIABILITY__PETRINET:
				setPetrinet((FileURI)null);
				return;
			case VariabilityPackage.VARIABILITY__FEATUREMODEL:
				setFeaturemodel((FileURI)null);
				return;
			case VariabilityPackage.VARIABILITY__PRESENCECONDITION:
				getPresencecondition().clear();
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
			case VariabilityPackage.VARIABILITY__PETRINET:
				return petrinet != null;
			case VariabilityPackage.VARIABILITY__FEATUREMODEL:
				return featuremodel != null;
			case VariabilityPackage.VARIABILITY__PRESENCECONDITION:
				return presencecondition != null && !presencecondition.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	@Override
	public PresenceCondition getPresencecondition(String name) {
		for (PresenceCondition pc : this.getPresencecondition()) {
			System.out.println(pc.getExpression());
			for (EObject obj : pc.getElements()) {	
				obj = EcoreUtil.resolve(obj, pc);
				EClass cl = obj.eClass();
				for (EAttribute atr : cl.getEAllAttributes()) {
					if (atr.getName().equals("name")) {
						String vname = (String)obj.eGet(atr);
						if (name.equals(vname)) return pc;
					}
				}
			}
		}
		return null; // null is true
	}

} //VariabilityImpl
