package com.myutils.pdf;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.CertificateInfo;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.CollectionUtils;

import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * <b>PfdUtils</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/20
 */
@Slf4j
public class PfdUtils {


    /**
     * 校验
     * @param path    合同路径 ：本地
     * @param verifyContent  校验的印章名称
     * @return
     * @throws Exception
     */
    public static Map<String,Boolean> verification(String path, List<String> verifyContent) throws Exception {
        Map<String,Boolean> result = new HashMap<>();

        BouncyCastleProvider bcp = new BouncyCastleProvider();
        Security.insertProviderAt(bcp, 10);

        PdfReader pdfReader = new PdfReader(path);
        AcroFields acroFields = pdfReader.getAcroFields();
        List<String> names = acroFields.getSignatureNames();
        List<String> signNames = new ArrayList<>();
        for(String name : names){
            PdfPKCS7 pk = acroFields.verifySignature(name);
            CertificateInfo.X500Name fields = CertificateInfo.getSubjectFields(pk.getSigningCertificate());
            String field = fields.getField("CN");
            signNames.add(field);
            Date time = pk.getSignDate().getTime();
            String signTime = "null";
            if(null != time){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                signTime = dateFormat.format(time);
            }

            log.info("签名：{}，是否有效：{}，签约时间：{}！",field,pk.verify(),signTime);
        }

        if(CollectionUtils.isEmpty(verifyContent)){
            return null;
        }
        for (String item : verifyContent) {
            for (String name : signNames) {
                if (name.contains(item)) {
                    result.put(item, true);
                    break;
                }
                result.put(item, false);
            }
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\Kazyle\\Desktop\\E签宝.pdf";
        List<String> verifyContent = new ArrayList<>();
        String company = "测试全部渠道账户";
        verifyContent.add(company);
        PfdUtils.verification(path,verifyContent);
    }
}
