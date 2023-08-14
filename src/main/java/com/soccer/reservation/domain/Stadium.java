package com.soccer.reservation.domain;

import lombok.Data;

@Data
public class Stadium {
	
	private String LIST_TOTAL_COUNT;//	행총건수
    private String CODE;//	응답결과코드
    private String MESSAGE;//	응답결과메세지
    private String API_VERSION;//
    private String SUM_YY;//	집계년도
    private String SIGUN_NM;//	시군명
    private String SIGUN_CD;//	시군코드
    private String FACLT_NM;//	시설명
    private String COMPLTN_YY;//	준공연도
    private String POSESN_INST_NM;//	소유기관명
    private String MANAGE_MAINBD_NM	;//관리주체명
    private String OPERT_ORGNZT_NM;//	운영조직명
    private String BOTM_MATRL_NM;//	바닥재료명
    private String BT;//	폭(m)
    private String PLANE_CNT;//	면수
    private String AR;//	면적(㎡)
    private String LENG;//	길이(m)
    private String TOT_AR;//	연면적(㎡)
    private String BUILD_AR;//	건축면적(㎡)
    private String PLOT_AR;//	부지면적(㎡)
    private String CONSTR_BIZ_EXPN;//	건설사업비(백만원)
    private String ACEPTNC_PSN_CNT;//	수용인원수(명)
    private String AUDTRM_SEAT_CNT;//	관람석좌석수
    private String SEAT_FORM_NM	;//좌석형태명
    private String BUILD_RESCUE_NM;//	건축구조명
    private String RM_MATR;//	비고사항
    private String CONTCT_NO;//	연락처
    private String HMPG_ADDR;//	홈페이지주소
    private String REFINE_ZIP_CD;//	소재지우편번호
    private String REFINE_LOTNO_ADDR;//	소재지지번주소
    private String REFINE_ROADNM_ADDR;//	소재지도로명주소
    private String REFINE_WGS84_LAT;//	WGS84위도
    private String REFINE_WGS84_LOGT;//

}
