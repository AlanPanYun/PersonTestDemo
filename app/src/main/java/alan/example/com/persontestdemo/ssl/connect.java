//package alan.example.com.persontestdemo.ssl;
//
//import org.apache.http.Header;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.scheme.Scheme;
//import org.apache.http.conn.scheme.SchemeSocketFactory;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.protocol.HTTP;
//import org.apache.http.util.EntityUtils;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URLDecoder;
//import java.security.KeyStore;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.net.ssl.KeyManagerFactory;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.TrustManagerFactory;
//
//import alan.example.com.persontestdemo.MainActivity;
//
///**
// * Created by qk14 on 2017/6/27.
// */
//
//public class connect {
//    public static String sendSSLRequest(String requestUrl) {
//        long responseLength = 0; // 响应长度
//        String responseContent = null; // 响应内容
//        HttpClient httpClient = new DefaultHttpClient(); // 创建默认的httpClient实例
//        KeyStore trustStore = null;
//        InputStream fis = null;
//        HttpGet httpGet = null;
//        HttpResponse response = null;
//        try {
//            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            fis = MainActivity.class.getClassLoader().getResourceAsStream(
//                    "my.keystore");
//            trustStore.load(fis, "pwd".toCharArray()); // 加载KeyStore
//
//            SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore); // 创建Socket工厂,将trustStore注入
//            Scheme sch = new Scheme("https", socketFactory, 443); // 创建Scheme
//            httpClient.getConnectionManager().getSchemeRegistry().register(sch); // 注册Scheme
//            httpGet = new HttpGet(requestUrl); // 创建HttpGet
//
//            Header headers[] = {new BasicHeader("myheader", "value")};
//            httpGet.setHeaders(headers); // 设置头信息
//            // 设置请求参数,get好像只能在url那里用?xx=xx传参(暂时没找到到别的可传参方法)
//
//            // 如用post传参如下
//            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//            formparams.add(new BasicNameValuePair("prameName", "value"));
//            UrlEncodedFormEntity uefEntity;
//            uefEntity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
//            HttpPost post = new HttpPost(requestUrl);
//            post.setEntity(uefEntity);
//            post.setHeader("myheader", "value");
//
//            // response = httpClient.execute(post); // 执行POST请求
//            response = httpClient.execute(httpGet); // 执行GET请求
//            HttpEntity entity = response.getEntity(); // 获取响应实体
//            if (null != entity) {
//                responseLength = entity.getContentLength();
//                responseContent = EntityUtils.toString(entity, "UTF-8");
//                EntityUtils.consume(entity); // Consume response content
//            }
//
//            System.out.println("请求地址: " + httpGet.getURI());
//            System.out.println("响应状态: " + response.getStatusLine());
//            System.out.println("响应长度: " + responseLength);
//            System.out.println("响应内容: " + responseContent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fis.close();
//            } finally {
//                httpClient.getConnectionManager().shutdown(); // 关闭连接,释放资源
//                return responseContent;
//            }
//        }
//    }
//
//    public String connect(String url1, String xml) throws Exception {
//        BufferedReader in = null;
//        String keyStore = "c:\\a.pfx"; //证书的路径，pfx格式
//        String trustStore = "c:\\b.jks";//密钥库文件，jks格式
//        String keyPass = "111111"; //pfx文件的密码
//        String trustPass = "22222"; //jks文件的密码
//
//        KeyStore ts = null;
//        SSLContext sslContext = null;
//        try {
//
//            KeyStore ks = KeyStore.getInstance("pkcs12");
//            // 加载pfx文件
//            ks.load(new FileInputStream(keyStore), keyPass.toCharArray());
//            KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
//            kmf.init(ks, keyPass.toCharArray());
//
//            ts = KeyStore.getInstance("jks");
//            //加载jks文件
//            ts.load(new FileInputStream(trustStore), trustPass.toCharArray());
//            TrustManager[] tm;
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
//            tmf.init(ts);
//            tm = tmf.getTrustManagers();
//
//            sslContext = SSLContext.getInstance("SSL");
//            //初始化
//            sslContext.init(kmf.getKeyManagers(), tm, null);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String result = null;
//        try {
//
//            HttpClient httpclient = new DefaultHttpClient();
//            //下面是重点
//            SSLSocketFactory socketFactory = new SSLSocketFactory(ts);
//            Scheme sch = new Scheme("https", 800, (SchemeSocketFactory) socketFactory);
//            httpclient.getConnectionManager().getSchemeRegistry().register(sch);
//            HttpPost httpPost = null;
//
//            httpPost = new HttpPost(url1);
//
//            // 创建名/值组列表
//            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
//            parameters.add(new BasicNameValuePair("Name", "zhangsan"));
//            parameters.add(new BasicNameValuePair("passWord", "123456"));
//
//            // 创建UrlEncodedFormEntity对象
//            UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters);
//
//            httpPost.setEntity(formEntiry);
//            HttpResponse httpResponse = httpclient.execute(httpPost);
//            in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//            StringBuffer sb = new StringBuffer("");
//            String line = "";
//            String NL = System.getProperty("line.separator");
//            while ((line = in.readLine()) != null) {
//                sb.append(line + NL);
//            }
//            in.close();
//            result = sb.toString();
//            result = URLDecoder.decode(result.toString(), "GBK");
//
//            return result;
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//}
