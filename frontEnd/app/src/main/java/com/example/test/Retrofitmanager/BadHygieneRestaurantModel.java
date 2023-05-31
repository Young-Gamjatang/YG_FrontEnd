package com.example.test.Retrofitmanager;

import com.google.gson.annotations.SerializedName;

public class BadHygieneRestaurantModel {
    //UUID 코드
    @SerializedName("id")
    private String id;

    //시군구 코드
    @SerializedName("cggCode")
    private String cggCode;

    //처분 일자
    @SerializedName("admDispoYmd")
    private String admDispoYmd;

    //교부 번호
    @SerializedName("gntNo")
    private String gntNo;

    //업종명
    @SerializedName("sntCobNm")
    private String sntCobNm;

    //업소명
    @SerializedName("upsoNm")
    private String upsoNm;

    //소재지도로명
    @SerializedName("siteAddrRd")
    private String siteAddrRd;

    //소재지번
    @SerializedName("siteAddr")
    private String siteAddr;

    //지도 점검 일자
    @SerializedName("drtInspYmd")
    private String drtInspYmd;

    //행정 처분 상태
    @SerializedName("admmState")
    private String admmState;

    //위반 내역 분류
    @SerializedName("viorSort")
    private String viorSort;

    //법적 근거
    @SerializedName("basLaw")
    private String basLaw;

    //위반 일자
    @SerializedName("viorYmd")
    private String viorYmd;

    //위반 내용
    @SerializedName("violCn")
    private String violCn;

    //처분 내용
    @SerializedName("dispoCtnDt")
    private String dispoCtnDt;

    //처분 기간
    @SerializedName("dispoGigan")
    private String dispoGigan;

    @SerializedName("latitude")
    private Double latitude;

    @SerializedName("longitude")
    private Double longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCggCode() {
        return cggCode;
    }

    public void setCggCode(String cggCode) {
        this.cggCode = cggCode;
    }

    public String getAdmDispoYmd() {
        return admDispoYmd;
    }

    public void setAdmDispoYmd(String admDispoYmd) {
        this.admDispoYmd = admDispoYmd;
    }

    public String getGntNo() {
        return gntNo;
    }

    public void setGntNo(String gntNo) {
        this.gntNo = gntNo;
    }

    public String getSntCobNm() {
        return sntCobNm;
    }

    public void setSntCobNm(String sntCobNm) {
        this.sntCobNm = sntCobNm;
    }

    public String getUpsoNm() {
        return upsoNm;
    }

    public void setUpsoNm(String upsoNm) {
        this.upsoNm = upsoNm;
    }

    public String getSiteAddrRd() {
        return siteAddrRd;
    }

    public void setSiteAddrRd(String siteAddrRd) {
        this.siteAddrRd = siteAddrRd;
    }

    public String getSiteAddr() {
        return siteAddr;
    }

    public void setSiteAddr(String siteAddr) {
        this.siteAddr = siteAddr;
    }

    public String getDrtInspYmd() {
        return drtInspYmd;
    }

    public void setDrtInspYmd(String drtInspYmd) {
        this.drtInspYmd = drtInspYmd;
    }

    public String getAdmmState() {
        return admmState;
    }

    public void setAdmmState(String admmState) {
        this.admmState = admmState;
    }

    public String getViorSort() {
        return viorSort;
    }

    public void setViorSort(String viorSort) {
        this.viorSort = viorSort;
    }

    public String getBasLaw() {
        return basLaw;
    }

    public void setBasLaw(String basLaw) {
        this.basLaw = basLaw;
    }

    public String getViorYmd() {
        return viorYmd;
    }

    public void setViorYmd(String viorYmd) {
        this.viorYmd = viorYmd;
    }

    public String getViolCn() {
        return violCn;
    }

    public void setViolCn(String violCn) {
        this.violCn = violCn;
    }

    public String getDispoCtnDt() {
        return dispoCtnDt;
    }

    public void setDispoCtnDt(String dispoCtnDt) {
        this.dispoCtnDt = dispoCtnDt;
    }

    public String getDispoGigan() {
        return dispoGigan;
    }

    public void setDispoGigan(String dispoGigan) {
        this.dispoGigan = dispoGigan;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "BadHygieneRestaurantModel{" +
                "id='" + id + '\'' +
                ", cggCode='" + cggCode + '\'' +
                ", admDispoYmd='" + admDispoYmd + '\'' +
                ", gntNo='" + gntNo + '\'' +
                ", sntCobNm='" + sntCobNm + '\'' +
                ", upsoNm='" + upsoNm + '\'' +
                ", siteAddrRd='" + siteAddrRd + '\'' +
                ", siteAddr='" + siteAddr + '\'' +
                ", drtInspYmd='" + drtInspYmd + '\'' +
                ", admmState='" + admmState + '\'' +
                ", viorSort='" + viorSort + '\'' +
                ", basLaw='" + basLaw + '\'' +
                ", viorYmd='" + viorYmd + '\'' +
                ", violCn='" + violCn + '\'' +
                ", dispoCtnDt='" + dispoCtnDt + '\'' +
                ", dispoGigan='" + dispoGigan + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
