package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_account where a_crtd_by=:id", name = "getIclubAccountByUser", resultClass = IclubAccount.class),
		@NamedNativeQuery(name = "getIclubAlarmTypeBySD", query = "select * from iclub_alarm_type where lower(at_short_desc) = lower(:sd) and at_id <> :id", resultClass = IclubAlarmType.class),
		@NamedNativeQuery(name = "getIclubAccountTypeBySD", query = "select * from iclub_account_type where lower(at_short_desc) = lower(:sd) and at_id <> :id", resultClass = IclubAccountType.class),
		@NamedNativeQuery(query = "select * from iclub_bank_master where bm_crtd_by=:id", name = "getIclubBankMasterByUser", resultClass = IclubBankMaster.class),
		@NamedNativeQuery(name = "getIclubBarTypeBySD", query = "select * from iclub_bar_type where lower(bt_short_desc) = lower(:sd) and bt_id <> :id", resultClass = IclubBarType.class),
		@NamedNativeQuery(name = "getIclubBoundaryTypeBySD", query = "select * from iclub_boundary_type where lower(bt_short_desc) = lower(:sd) and bt_id <> :id", resultClass = IclubBoundaryType.class),
		@NamedNativeQuery(name = "getIclubBuildingStateBySD", query = "select * from iclub_building_state where lower(bs_short_desc) = lower(:sd) and bs_id <> :id", resultClass = IclubBuildingState.class),
		@NamedNativeQuery(query = "select * from iclub_claim where c_policy_id=:policyId", name = "getClaimByPolicyId", resultClass = IclubClaim.class),
		@NamedNativeQuery(query = "select * from iclub_claim where c_crtd_by=:id", name = "getIclubClaimByUser", resultClass = IclubClaim.class),
		@NamedNativeQuery(query = "select * from iclub_claim_item where ci_crtd_by=:id", name = "getIclubClaimItemByUser", resultClass = IclubClaimItem.class),
		@NamedNativeQuery(name = "getIclubClaimStatusBySD", query = "select * from iclub_claim_status where lower(cs_short_desc) = lower(:sd) and cs_id <> :id", resultClass = IclubClaimStatus.class),
		@NamedNativeQuery(query = "select * from iclub_config where c_crtd_by=:id", name = "getIclubConfigByUser", resultClass = IclubConfig.class),
		@NamedNativeQuery(query = "select * from iclub_config where lower(c_key)=lower(:key)", name = "getIclubConfigByKey", resultClass = IclubConfig.class),
		@NamedNativeQuery(query = "select * from iclub_country_code where cc_crtd_by=:id", name = "getIclubCountryCodeByUser", resultClass = IclubCountryCode.class),
		@NamedNativeQuery(query = "select * from iclub_cover_type where ct_crtd_by=:id", name = "getIclubCoverTypeByUser", resultClass = IclubCoverType.class),
		@NamedNativeQuery(query = "select * from iclub_document where d_crtd_by=:id", name = "getIclubDocumentByUser", resultClass = IclubDocument.class),
		@NamedNativeQuery(name = "getIclubDocumentTypeBySD", query = "select * from iclub_document_type where lower(dt_short_desc) = lower(:sd) and dt_id <> :id", resultClass = IclubDocumentType.class),
		@NamedNativeQuery(query = "select * from iclub_driver where d_crtd_by=:id", name = "getIclubDriverByUser", resultClass = IclubDriver.class),
		@NamedNativeQuery(query = "select * from iclub_driver where d_person_id=:id", name = "getDriverByPersonId", resultClass = IclubDriver.class),
		@NamedNativeQuery(name = "getIclubEnityTypeBySD", query = "select * from iclub_entity_type where lower(et_short_desc) = lower(:sd) and et_id <> :id", resultClass = IclubEntityType.class),
		@NamedNativeQuery(query = "select * from iclub_event where e_crtd_by=:id", name = "getIclubEventByUser", resultClass = IclubEvent.class),
		@NamedNativeQuery(name = "getIclubEventTypeBySD", query = "select * from iclub_event_type where lower(et_short_desc) = lower(:sd) and et_id <> :id", resultClass = IclubEventType.class),
		@NamedNativeQuery(query = "select * from iclub_extras where e_crtd_by=:id", name = "getIclubExtrasByUser", resultClass = IclubExtras.class),
		@NamedNativeQuery(name = "getIclubIdTypeBySD", query = "select * from iclub_id_type where lower(it_short_desc) = lower(:sd) and it_id <> :id", resultClass = IclubIdType.class),
		@NamedNativeQuery(query = "select * from iclub_insurance_item where ii_quote_id=:quoteId ", name = "getInsuranceItemByQuoteId", resultClass = IclubInsuranceItem.class),
		@NamedNativeQuery(query = "select * from iclub_insurance_item where ii_type_id=:itemTypeId and ii_quote_id =:quoteId ", name = "getInsuranceItemByQuoteIdAndItemTypeId", resultClass = IclubInsuranceItem.class),
		@NamedNativeQuery(query = "select * from iclub_insurance_item where a_crtd_by=:id", name = "getIclubInsuranceItemByUser", resultClass = IclubInsuranceItem.class),
		@NamedNativeQuery(name = "getIclubInsuranceItemTypeBySD", query = "select * from iclub_insurance_item_type where lower(iit_short_desc) = lower(:sd) and iit_id <> :id", resultClass = IclubInsuranceItemType.class),
		@NamedNativeQuery(query = "select * from iclub_insurer_master where aim_crtd_by=:id", name = "getIclubInsurerMasterByUser", resultClass = IclubInsurerMaster.class),
		@NamedNativeQuery(query = "select * from iclub_license_code where lc_crtd_by=:id", name = "getIclubLicenseCodeByUser", resultClass = IclubLicenseCode.class),
		@NamedNativeQuery(name = "verifyLogin", query = "select * from iclub_login where l_name=:name and l_passwd=:pwd", resultClass = IclubLogin.class),
		@NamedNativeQuery(name = "getIclubMaritialStatusBySD", query = "select * from iclub_maritial_status where lower(ms_short_desc) = lower(:sd) and ms_id <> :id", resultClass = IclubMaritialStatus.class),
		@NamedNativeQuery(query = "select * from iclub_mb_comment where mbc_crtd_by=:id", name = "getIclubMbCommentByUser", resultClass = IclubMbComment.class),
		@NamedNativeQuery(query = "select * from iclub_message where m_crtd_by=:id", name = "getIclubMessageByUser", resultClass = IclubMessage.class),
		@NamedNativeQuery(query = "select * from iclub_message_board where mb_crtd_by=:id", name = "getIclubMessageBoardByUser", resultClass = IclubMessageBoard.class),
		@NamedNativeQuery(name = "getIclubMessageTypeBySD", query = "select * from iclub_message_type where lower(mt_short_desc) = lower(:sd) and mt_id <> :id", resultClass = IclubMessageType.class),
		@NamedNativeQuery(query = "select * from iclub_notif where n_crtd_by=:id", name = "getIclubNotifByUser", resultClass = IclubNotif.class),
		@NamedNativeQuery(name = "getIclubNotificationTypeBySD", query = "select * from iclub_notification_type where lower(nt_short_desc) = lower(:sd) and nt_id <> :id", resultClass = IclubNotificationType.class),
		@NamedNativeQuery(query = "select * from iclub_cccupation where o_crtd_by=:id", name = "getIclubOccupationByUser", resultClass = IclubOccupation.class),
		@NamedNativeQuery(name = "getIclubOccupiedStatusBySD", query = "select * from iclub_occupied_status where lower(os_short_desc) = lower(:sd) and os_id <> :id", resultClass = IclubOccupiedStatus.class),
		@NamedNativeQuery(name = "getIclubOwnerTypeBySD", query = "select * from iclub_owner_type where lower(ot_short_desc) = lower(:sd) and ot_id <> :id", resultClass = IclubOwnerType.class),
		@NamedNativeQuery(query = "select * from iclub_payment where p_crtd_by=:id", name = "getIclubPaymentByUser", resultClass = IclubPayment.class),
		@NamedNativeQuery(name = "getIclubPaymentTypeBySD", query = "select * from iclub_payment_type where lower(pt_short_desc) = lower(:sd) and pt_id <> :id", resultClass = IclubPaymentType.class),
		@NamedNativeQuery(name = "getIclubPaymentStatusBySD", query = "select * from iclub_payment_status where lower(ps_short_desc) = lower(:sd) and ps_id <> :id", resultClass = IclubPaymentStatus.class),
		@NamedNativeQuery(query = "select * from iclub_person where p_crtd_by=:id", name = "getIclubPersonByUser", resultClass = IclubPerson.class),
		@NamedNativeQuery(query = "select * from iclub_policy where p_quote_id=:quoteId", name = "getPolicyByQuoteId", resultClass = IclubPolicy.class),
		@NamedNativeQuery(query = "select * from iclub_policy where p_crtd_by=:id", name = "getIclubPolicyByUser", resultClass = IclubPolicy.class),
		@NamedNativeQuery(name = "getIclubPolicyStatusBySD", query = "select * from iclub_policy_status where lower(ps_short_desc) = lower(:sd) and ps_id <> :id", resultClass = IclubPolicyStatus.class),
		@NamedNativeQuery(name = "getIclubProductTypeBySD", query = "select * from iclub_product_type where lower(pt_short_desc) = lower(:sd) and pt_id <> :id", resultClass = IclubProductType.class),
		@NamedNativeQuery(query = "select * from iclub_property where p_crtd_by=:id", name = "getIclubPropertyByUser", resultClass = IclubProperty.class),
		@NamedNativeQuery(name = "getIclubPropertyTypeBySD", query = "select * from iclub_property_type where lower(pt_short_desc) = lower(:sd) and pt_id <> :id", resultClass = IclubPropertyType.class),
		@NamedNativeQuery(query = "select * from iclub_quote where q_crtd_by=:id", name = "getIclubQuoteByUser", resultClass = IclubQuote.class),
		@NamedNativeQuery(query = "select * from iclub_quote where q_crtd_by=:id and q_status_id=:statusId ", name = "getIclubQuoteByUserAndStatus", resultClass = IclubQuote.class),
		@NamedNativeQuery(query = "select * from iclub_purpose_type where pt_crtd_by=:id", name = "getIclubPurposeTypeByUser", resultClass = IclubPurposeType.class),
		@NamedNativeQuery(query = "select * from iclub_purpose_type where pt_item_type_id=:iIType", name = "getPurposeTypeByInsurnceItemType", resultClass = IclubPurposeType.class),
		@NamedNativeQuery(name = "getIclubQuoteStatusBySD", query = "select * from iclub_quote_status where lower(qs_short_desc) = lower(:sd) and qs_id <> :id", resultClass = IclubQuoteStatus.class),
		@NamedNativeQuery(query = "select * from iclub_rate_engine where re_crtd_by=:id", name = "getIclubRateEngineByUser", resultClass = IclubRateEngine.class),
		@NamedNativeQuery(query = "select * from iclub_rate_engine where re_type_id=:id", name = "getRateEngineByRateType", resultClass = IclubRateEngine.class),
		@NamedNativeQuery(name = "getIclubRateTypeBySD", query = "select * from iclub_rate_type where lower(rt_short_desc) = lower(:sd) and rt_id <> :id", resultClass = IclubRateType.class),
		@NamedNativeQuery(name = "getIclubRoleTypeBySD", query = "select * from iclub_role_type where lower(rt_short_desc) = lower(:sd) and rt_id <> :id", resultClass = IclubRoleType.class),
		@NamedNativeQuery(query = "select * from iclub_security_device where sd_crtd_by=:id", name = "getIclubSecurityDeviceByUser", resultClass = IclubSecurityDevice.class),
		@NamedNativeQuery(query = "select * from iclub_security_master where sm_crtd_by=:id", name = "getIclubSecurityMasterByUser", resultClass = IclubSecurityMaster.class),
		@NamedNativeQuery(name = "getIclubSecurityQuestionBySD", query = "select * from iclub_security_question where lower(sq_short_desc) = lower(:sd) and sq_id <> :id", resultClass = IclubSecurityQuestion.class),
		@NamedNativeQuery(name = "getIclubSupplierTypeBySD", query = "select * from iclub_supplier_type where lower(st_short_desc) = lower(:sd) and st_id <> :id", resultClass = IclubSupplierType.class),
		@NamedNativeQuery(query = "select * from iclub_suppl_master where sm_crtd_by=:id", name = "getIclubSupplMasterByUser", resultClass = IclubSupplMaster.class),
		@NamedNativeQuery(query = "select * from iclub_system_type where st_crtd_by=:id", name = "getIclubSystemTypeByUser", resultClass = IclubSystemType.class),
		@NamedNativeQuery(name = "getIclubThatchTypeBySD", query = "select * from iclub_thatch_type where lower(tt_short_desc) = lower(:sd) and tt_id <> :id", resultClass = IclubThatchType.class),
		@NamedNativeQuery(query = "select * from iclub_tracker_master where tm_crtd_by=:id", name = "getIclubTrackerMasterByUser", resultClass = IclubTrackerMaster.class),
		@NamedNativeQuery(query = "select * from iclub_vehicle where v_driver_id=:driverId", name = "getVehicleByDriverId", resultClass = IclubVehicle.class),
		@NamedNativeQuery(query = "select * from iclub_vehicle_master where vm_crtd_by=:id", name = "getIclubVehicleMasterByUser", resultClass = IclubVehicleMaster.class),
		@NamedNativeQuery(query = "select * from iclub_vehicle_master where upper(vm_make) like upper(:vmMake)", name = "getVehicleMasterByMake", resultClass = IclubVehicleMaster.class),
		@NamedNativeQuery(query = "select distinct vm_make from iclub_vehicle_master", name = "getAllVmMakes", resultClass = IclubVehicleMaster.class),
		@NamedNativeQuery(name = "getIclubVehicleTypeBySD", query = "select * from iclub_vehicle_type where lower(vt_short_desc) = lower(:sd) and vt_id <> :id", resultClass = IclubVehicleType.class),
		@NamedNativeQuery(name = "getIclubWallTypeBySD", query = "select * from iclub_wall_type where lower(wt_short_desc) = lower(:sd) and wt_id <> :id", resultClass = IclubWallType.class),
		@NamedNativeQuery(name = "getDocumentByEntity", query = "select * from iclub_document where lower(d_entity_id) = lower(:id) and d_entity_type_id = :typeId", resultClass = IclubDocument.class),
		@NamedNativeQuery(name = "getIclubAccessTypeBySD", query = "select * from iclub_access_type where lower(at_short_desc) = lower(:sd) and at_id <> :id", resultClass = IclubAccessType.class),
        @NamedNativeQuery(name = "getIclubMbCommnetByMbId", query = "select * from iclub_mb_comment where lower(mbc_mb_id) = lower(:id)", resultClass =IclubMbComment.class),
        @NamedNativeQuery(query = "select distinct bm_bank_name from iclub_bank_master", name = "getAllBankNames", resultClass = IclubBankMaster.class),
        @NamedNativeQuery(query = "select * from iclub_bank_master where lower(bm_bank_name)=lower(:bankName)", name = "getIclubBankMastersByBankName", resultClass = IclubBankMaster.class),
        @NamedNativeQuery(name = "getIclubRoofTypeBySD", query = "select * from iclub_roof_type where lower(rt_short_desc) = lower(:sd) and rt_id <> :id", resultClass = IclubRoofType.class),
        @NamedNativeQuery(name = "getIclubRateTypeByQuoteTypeAndFieldId", query = "select * from iclub_rate_type where lower(rt_quote_type) = lower(:quoteType) and rt_field_id =:id", resultClass = IclubRateType.class),
        @NamedNativeQuery(name = "getIclubFieldByFieldStatus", query = "select * from iclub_field where lower(f_status) = lower(:fieldStatus)", resultClass = IclubField.class),
        @NamedNativeQuery(name = "getIclubCohortTypeBySD", query = "select * from iclub_cohort_type where lower(ct_short_desc) = lower(:sd) and ct_id <> :id", resultClass = IclubCohortType.class),
        @NamedNativeQuery(query = "select * from iclub_suppl_person where sp_suppl_id=:id", name = "getIclubSupplPersonBySmId", resultClass = IclubSupplPerson.class),
        @NamedNativeQuery(query = "select * from iclub_login where l_person_id=:personId", name = "getIclubLoginByPersonId", resultClass = IclubLogin.class),
        @NamedNativeQuery(query = "select * from iclub_rate_engine where re_type_id=:rateTypeId and re_base_value=:baseValue and re_id <> :reId", name = "getIclubRateEngineByBaseValueAndRateTypeId", resultClass = IclubRateEngine.class),
        @NamedNativeQuery(query = "select * from iclub_rate_engine where ((re_base_value<=:baseValue and :baseValue<=re_max_value) or (re_base_value<=:maxValue and :maxValue<=re_max_value)) and re_id <> :reId and re_type_id=:rateTypeId", name = "getIclubRateEngineByBaseMaxValueAndRateTypeId", resultClass = IclubRateEngine.class),
//        @NamedNativeQuery(query = "select * from iclub_rate_engine where (:baseValue between re_base_value and re_max_value) or (:maxValue between re_base_value and re_max_value) and re_id <> :reId and re_type_id=:rateTypeId", name = "getIclubRateEngineByBaseMaxValueAndRateTypeId", resultClass = IclubRateEngine.class),
        @NamedNativeQuery(name = "getIclubGeoLocByLatAndLong", query = "SELECT gl_id, ( 3959 * acos( cos( radians(:lat) ) * cos( radians( gl_lat ) ) * cos( radians( gl_long ) - radians(:geoLong) ) + sin( radians(:lat) ) * sin( radians( gl_lat ) ) ) ) AS distance FROM iclub_geo_loc   ORDER BY distance LIMIT 0 , 1;", resultClass = IclubGeoLoc.class)})
@Table(name = "iclub_account_type")
public class IclubNamedQueries implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7107040889492342220L;
	private Long atId;

	@Id
	@Column(name = "at_id", unique = true, nullable = false)
	public Long getAtId() {
		return this.atId;
	}

	public void setAtId(Long atId) {
		this.atId = atId;
	}
}
