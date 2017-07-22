//package alan.example.com.persontestdemo.ssl;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.http.ssl.TrustStrategy;
//import org.apache.http.util.EntityUtils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.security.KeyStore;
//
//import javax.net.ssl.SSLContext;
//
//
///**
// * Created by qk14 on 2017/6/27.
// */
//
//public class ClientCustomSSL {
//
//    public final static void main(String[] args) throws Exception {
//
//        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//
//        //加载证书文件
//        FileInputStream instream = new FileInputStream(new File("/home/victor/my.store"));
//        try {
//            trustStore.load(instream, "mypassword".toCharArray());
//        } finally {
//            instream.close();
//        }
//
//        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial((TrustStrategy) trustStore).build();
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                sslcontext,
//                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER
//        );
//        CloseableHttpClient httpclient =
//                HttpClients
//                        .custom()
//                        .setSSLSocketFactory(sslsf)
//                        .build();
//        try {
//            //访问支付宝
//            HttpPost httpPost = new HttpPost("https://www.alipay.com/");
//            System.out.println("executing request" + httpPost.getRequestLine());
//            CloseableHttpResponse response = httpclient.execute(httpPost);
//            try {
//                HttpEntity entity = response.getEntity();
//                System.out.println("----------------------------------------");
//                System.out.println(response.getStatusLine());
//                if (entity != null) {
//                    System.out.println(EntityUtils.toString(entity));
//                }
//            } finally {
//                response.close();
//            }
//        } finally {
//            httpclient.close();
//        }
//    }
//
//}
