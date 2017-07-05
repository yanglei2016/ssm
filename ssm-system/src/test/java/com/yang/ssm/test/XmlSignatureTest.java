package com.yang.ssm.test;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.yang.ssm.BaseTest;

public class XmlSignatureTest extends BaseTest {
	
    /**
     * 签名算法
     */
    public static final String  SIGNATURE_ALGORITHM = "SHA1withRSA";
    /**
     * 加密算法RSA
     */
    public static final String  KEY_ALGORITHM       = "RSA";
    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY          = "RSAPublicKey";
    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY         = "RSAPrivateKey";
    
    /**
     * 加密节点属性名
     */
    private static final String SIGN_ELEMENT_ID         = "id";
    

	public static void main(String[] args) throws Exception {
		String xmlMsg = "<Cbhbpac>									"
						+ "	<Message id=\"10134522399\">                  "
						+ "		<SCSPReq id=\"SCSPReq\">                  "
						+ "			<version>1.0.0</version>            "
						+ "			<instId>100001</instId>             "
						+ "			<certId>cert0001</certId>           "
						+ "			<serialNo>10134522399</serialNo>    "
						+ "			<date>20130330 11:51:39</date>      "
						+ "			<pyrAct>20000110011</pyrAct>        "
						+ "			<pyrNam>x公司</pyrNam>              "
						+ "			<pyeAct>6228000011112222</pyeAct>   "
						+ "			<pyeNam>张三</pyeNam>               "
						+ "			<pyeBnk>1000100023</pyeBnk>         "
						+ "			<postscript>理赔款</postscript>     "
						+ "		</SCSPReq>                              "
						+ "	</Message>                                  "
						+ "</Cbhbpac>                                   ";
		
		generateXMLDigitalSignature(xmlMsg, "SCSPReq");
		
	}

	public static void generateXMLDigitalSignature(String xmlMsg, String attrValue) throws Exception {
		XMLSignatureFactory xmlSignFac = null;
		SignedInfo signedInfo = null;
		try {
			// 创建XML签名工厂
			xmlSignFac = XMLSignatureFactory.getInstance("DOM");
			
			DigestMethod digestMethod = xmlSignFac.newDigestMethod(DigestMethod.SHA1, null);
			Transform transform = xmlSignFac.newTransform(Transform.ENVELOPED, (TransformParameterSpec)null);
			Reference reference = xmlSignFac.newReference("#"+ attrValue , digestMethod, Collections.singletonList(transform), null, null);
//			Reference reference = xmlSignFac.newReference("#SCSPReq", digestMethod);
			
			CanonicalizationMethod canonicalizationMethod = xmlSignFac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec)null);
			SignatureMethod signatureMethod = xmlSignFac.newSignatureMethod(SignatureMethod.RSA_SHA1, null);
			signedInfo = xmlSignFac.newSignedInfo(canonicalizationMethod, signatureMethod, Collections.singletonList(reference));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 传入公钥路径
//		KeyInfo keyInfo = getKeyInfo(xmlSigFactory, publicKeyFilePath);

		// 获取私钥
		PrivateKey privateKey = getPrivateKey(genKeyPair());
		// 获取XML文档对象
		Document doc = getXmlDocument(xmlMsg);
		DOMSignContext domSignCtx = new DOMSignContext(privateKey, doc.getDocumentElement());
		Element e = (Element)doc.getElementsByTagName(attrValue).item(0);
		domSignCtx.setIdAttributeNS(e, null, SIGN_ELEMENT_ID);
		// 创建新的XML签名
		XMLSignature xmlSignature = xmlSignFac.newXMLSignature(signedInfo, null);
		try {
			// 对文档签名
			xmlSignature.sign(domSignCtx);
		} catch (MarshalException ex) {
			ex.printStackTrace();
		} catch (XMLSignatureException ex) {
			ex.printStackTrace();
		}

		//签名过的文档
		String resultXml = convertToXmlStr(doc);
		System.out.println("签名xml\n"+ resultXml);
	}
	
	public static Document getXmlDocument(String xmlMsg) throws Exception{
		DocumentBuilderFactory docBuilderFac = DocumentBuilderFactory.newInstance();
		docBuilderFac.setNamespaceAware(true);
		DocumentBuilder docBuilder = docBuilderFac.newDocumentBuilder();
		Document doc = docBuilder.parse(new InputSource(new StringReader(xmlMsg)));
//		System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());  
		return doc;
	}
	
	public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(512);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
	
	public static PrivateKey getPrivateKey(Map<String, Object> keyMap) throws Exception{
		byte[] keyBytes = Base64.decodeBase64(getPrivateKeyStr(keyMap));
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        return privateK;
	}
	
	/**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKeyStr(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKeyStr(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }
	
    
    public static String convertToXmlStr(Document doc)  
    {  
        TransformerFactory tf = TransformerFactory.newInstance();  
        Transformer transformer = null;  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        try{  
        	transformer = tf.newTransformer();  
//            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");  
            transformer.transform(new DOMSource(doc),new StreamResult(outStream));  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return outStream.toString();
    } 
}

















