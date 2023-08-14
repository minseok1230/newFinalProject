package com.soccer.common;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import reactor.core.publisher.Mono;


@Service
public class StadiumApiService {
private final WebClient webClient;
    
    // WebClient 인스턴스 생성 , 기본 URL설정
    public StadiumApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://openapi.gg.go.kr").build();
    }
    
    /*DOM파싱
     * HTML or XML 문서를 해석하고 구조화된 형태로 변환하는 과정을 의미한다.
     * 1. 문서로드 : 서버측 스크립트 언어를 사용해 동적으로 생성되는 내용도 함꼐 가져옵니다. 
     * 2. 토큰화  : 문서를 문자열 형태로 읽은 후, 이를 논리적인 요소로 분할하는 과정
     * 3. 파싱   : 토큰화된 요소들을 가지고 문서 구조를 파악하고 계층 구조를 형성합니다.
     * 4. DOM트리 생성 : 파서가 문서를 읽으면서 요소들의 계층 구조를 파악하여 DOM트리를 생성합니다. 이 트리는 문서의 
     * 				   시작과 긑까지 모든 요소를 부모-자식 관꼐로 표현한 구조입니다. 
     * 5. DOM 조작    : 생성된 DOM트리를 통해 문서의 요소에 접근하고 조작. 
     * */
    
    public List<Map<String, Object>> getAllRowValues() {
    	// 결과 데이터를 받을 List<Map<>>
        List<Map<String, Object>> rowValuesList = new ArrayList<>();
        int page = 1;
        int itemsPerPage = 100;
        
    	while(true) {
	        Mono<String> xmlResponseMono = this.webClient
	                .get()
	                .uri("/PublicTrainingFacilitySoccer?key=49ae9fa134524c1eb22a22a1067f0f85&page=" + page)
	                .retrieve()
	                .bodyToMono(String.class);
	        
	        // block은 비동기 작업의 결과를 동기식으로 가져오기 위해 사용
	        String xmlResponse = xmlResponseMono.block();
	        
	        
	        
	        if (xmlResponse != null) {
	            try {
	            	
	            	/* XML 파싱 (DOM 파싱방법) - XML문서를 계층 구조로 표현하여 데이터를 조작하고 검색할 수 있는 방법 */
	            	// 인스턴스 생성
	                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	                // DocumentBuilder 실제로 파싱하는 역할
	                DocumentBuilder builder = factory.newDocumentBuilder();
	                Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));
	                NodeList rowNodes = document.getElementsByTagName("row");
	                
	             if (rowNodes.getLength() == 0) {
	                 // 더 이상 데이터가 없는 경우 반복 종료
	                 break;
	             }
	                for (int i = 0; i < rowNodes.getLength(); i++) {
	                    Element rowElement = (Element) rowNodes.item(i);
	                    Map<String, Object> rowValues = new HashMap<>();
	                    
	                    rowValues.put("집계년도", getElementValue(rowElement, "SUM_YY"));
	                    rowValues.put("시군명", getElementValue(rowElement, "SIGUN_NM"));
	                    rowValues.put("시설명", getElementValue(rowElement, "FACLT_NM"));
	                    rowValues.put("소유기관명", getElementValue(rowElement, "POSESN_INST_NM"));
	                    rowValues.put("운영조직명", getElementValue(rowElement, "OPERT_ORGNZT_NM"));
	                    rowValues.put("연락처", getElementValue(rowElement, "CONTCT_NO"));
	                    rowValues.put("홈페이지주소", getElementValue(rowElement, "HMPG_ADDR"));
	                    rowValues.put("관리주체명", getElementValue(rowElement, "MANAGE_MAINBD_NM"));
	                    rowValues.put("부지면적", getElementValue(rowElement, "PLOT_AR"));
	                    rowValues.put("연면적", getElementValue(rowElement, "TOT_AR"));
	                    rowValues.put("바닥재료명", getElementValue(rowElement, "BOTM_MATRL_NM"));
	                    rowValues.put("폭", getElementValue(rowElement, "BT"));
	                    rowValues.put("길이", getElementValue(rowElement, "LENG"));
	                    rowValues.put("면수", getElementValue(rowElement, "PLANE_CNT"));
	                    rowValues.put("관람석좌석수", getElementValue(rowElement, "AUDTRM_SEAT_CNT"));
	                    rowValues.put("수용인원수", getElementValue(rowElement, "ACEPTNC_PSN_CNT"));
	                    rowValues.put("좌석형태명", getElementValue(rowElement, "SEAT_FORM_NM"));
	                    rowValues.put("건축구조명", getElementValue(rowElement, "BUILD_RESCUE_NM"));
	                    rowValues.put("준고연도", getElementValue(rowElement, "COMPLTN_YY"));
	                    rowValues.put("건설사업비", getElementValue(rowElement, "CONSTR_BIZ_EXPN"));
	                    rowValues.put("비고사항", getElementValue(rowElement, "RM_MATR"));
	                    rowValues.put("소재지지번주소", getElementValue(rowElement, "REFINE_LOTNO_ADDR"));
	                    rowValues.put("소재지도로명주소", getElementValue(rowElement, "REFINE_ROADNM_ADDR"));
	                    rowValues.put("소재지우편번호", getElementValue(rowElement, "REFINE_ZIP_CD"));
	                    rowValues.put("WGS84경도", getElementValue(rowElement, "REFINE_WGS84_LOGT"));
	                    rowValues.put("WGS84위도", getElementValue(rowElement, "REFINE_WGS84_LAT"));
	                    
	                    
	                    // ... add more fields as needed
	                    
	                    rowValuesList.add(rowValues);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        page++;
	        
    	}
	        return rowValuesList;
	      
    }

    /* DOM(Document Object Model)을 사용하여 XML 엘리먼트에서 특정 태그의 값을 추출하는 메서드를 정의 */
    private String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "없음";
    }
}
