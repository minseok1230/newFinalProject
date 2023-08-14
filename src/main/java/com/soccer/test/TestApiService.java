package com.soccer.test;

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
public class TestApiService {
    private final WebClient webClient;
    
    // WebClient 인스턴스 생성 , 기본 URL설정
    public TestApiService(WebClient.Builder webClientBuilder) {
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
        Mono<String> xmlResponseMono = this.webClient
                .get()
                .uri("/PublicTrainingFacilitySoccer?key=49ae9fa134524c1eb22a22a1067f0f85&SIGUN_NM=화성시")
                .retrieve()
                .bodyToMono(String.class);
        
        // block은 비동기 작업의 결과를 동기식으로 가져오기 위해 사용
        String xmlResponse = xmlResponseMono.block();
        
        
        // 결과 데이터를 받을 List<Map<>>
        List<Map<String, Object>> rowValuesList = new ArrayList<>();

        if (xmlResponse != null) {
            try {
            	
            	/* XML 파싱 (DOM 파싱방법) - XML문서를 계층 구조로 표현하여 데이터를 조작하고 검색할 수 있는 방법 */
            	// 인스턴스 생성
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                // DocumentBuilder 실제로 파싱하는 역할
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));
                NodeList rowNodes = document.getElementsByTagName("row");

                for (int i = 0; i < rowNodes.getLength(); i++) {
                    Element rowElement = (Element) rowNodes.item(i);
                    Map<String, Object> rowValues = new HashMap<>();
                    
                    rowValues.put("SUM_YY", getElementValue(rowElement, "SUM_YY"));
                    rowValues.put("SIGUN_NM", getElementValue(rowElement, "SIGUN_NM"));
                    rowValues.put("FACLT_NM", getElementValue(rowElement, "FACLT_NM"));
                    rowValues.put("POSESN_INST_NM", getElementValue(rowElement, "POSESN_INST_NM"));
                    rowValues.put("OPERT_ORGNZT_NM", getElementValue(rowElement, "OPERT_ORGNZT_NM"));
                    rowValues.put("CONTCT_NO", getElementValue(rowElement, "CONTCT_NO"));
                    rowValues.put("HMPG_ADDR", getElementValue(rowElement, "HMPG_ADDR"));
                    rowValues.put("MANAGE_MAINBD_NM", getElementValue(rowElement, "MANAGE_MAINBD_NM"));
                    rowValues.put("PLOT_AR", getElementValue(rowElement, "PLOT_AR"));
                    rowValues.put("TOT_AR", getElementValue(rowElement, "TOT_AR"));
                    rowValues.put("BOTM_MATRL_NM", getElementValue(rowElement, "BOTM_MATRL_NM"));
                    rowValues.put("BT", getElementValue(rowElement, "BT"));
                    rowValues.put("LENG", getElementValue(rowElement, "LENG"));
                    rowValues.put("PLANE_CNT", getElementValue(rowElement, "PLANE_CNT"));
                    rowValues.put("AUDTRM_SEAT_CNT", getElementValue(rowElement, "AUDTRM_SEAT_CNT"));
                    rowValues.put("ACEPTNC_PSN_CNT", getElementValue(rowElement, "ACEPTNC_PSN_CNT"));
                    rowValues.put("SEAT_FORM_NM", getElementValue(rowElement, "SEAT_FORM_NM"));
                    rowValues.put("BUILD_RESCUE_NM", getElementValue(rowElement, "BUILD_RESCUE_NM"));
                    rowValues.put("COMPLTN_YY", getElementValue(rowElement, "COMPLTN_YY"));
                    rowValues.put("CONSTR_BIZ_EXPN", getElementValue(rowElement, "CONSTR_BIZ_EXPN"));
                    rowValues.put("RM_MATR", getElementValue(rowElement, "RM_MATR"));
                    rowValues.put("REFINE_LOTNO_ADDR", getElementValue(rowElement, "REFINE_LOTNO_ADDR"));
                    rowValues.put("REFINE_ROADNM_ADDR", getElementValue(rowElement, "REFINE_ROADNM_ADDR"));
                    rowValues.put("REFINE_ZIP_CD", getElementValue(rowElement, "REFINE_ZIP_CD"));
                    rowValues.put("REFINE_WGS84_LOGT", getElementValue(rowElement, "REFINE_WGS84_LOGT"));
                    rowValues.put("REFINE_WGS84_LAT", getElementValue(rowElement, "REFINE_WGS84_LAT"));
                    
                    
                    // ... add more fields as needed
                    
                    rowValuesList.add(rowValues);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    
    

//    public Map<String, Object> getFirstTodosTestAsMap() {
//        Mono<String> xmlResponseMono =
//                this.webClient
//                .get()
//                .uri("/PublicTrainingFacilitySoccer?key=49ae9fa134524c1eb22a22a1067f0f85")
//                .retrieve()
//                .bodyToMono(String.class);
//        
//        String xmlResponse = xmlResponseMono.block();
//
//        if (xmlResponse != null) {
//            try {
//                XmlMapper xmlMapper = new XmlMapper();
//                ObjectMapper objectMapper = new ObjectMapper();
//                Map<String, Object> resultMap = objectMapper.convertValue(xmlMapper.readValue(xmlResponse, Map.class), Map.class);
//                return resultMap;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        // 예외 처리 필요
//        return new HashMap<>();
//    }
}
