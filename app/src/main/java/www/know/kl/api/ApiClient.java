package www.retrofit.com.retrofitrxjavademo.api;

import android.support.compat.BuildConfig;
import android.util.SparseArray;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by kui.liu on 2016/10/13.
 */

public class ApiClient {
    public Retrofit retrofit = null;
    private ApiService mApiService;

    public static SparseArray<ApiClient> mRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);

    private ApiClient() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            //设置缓存,无网络时,也能显示数据
/*            File cacheFile = new File(MyApplication.context().getExternalCacheDir(),
                    "LiukuiCache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if (!NetWorkUtil.isNetworkAvailable(MyApplication.context())) {
                        request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                    }
                    Response response = chain.proceed(request);

                    if (NetWorkUtil.isNetworkAvailable(MyApplication.context())) {
                        int maxAge = 0;
                        // 有网络时,设置缓存超时时间0个小时
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .removeHeader("Liukui")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                                .build();
                    } else {
                        // 无网络时，设置超时为4周
                        int maxStale = 60 * 60 * 24 * 28;
                        response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .removeHeader("nyn")
                                .build();
                    }
                    return response;
                }
            };
            builder.cache(cache).addInterceptor(cacheInterceptor);*/

            //设置Log信息拦截器
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }

            //设置cookie,服务端可能需要保持请求是同一个cookie,根据需求而定
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            builder.cookieJar(new JavaNetCookieJar(manager));

            //设置连接超时,希望超时能重连
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);

            //设置头,有的接口需要对请求头进行设置
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder requestBuilder = request.newBuilder()
                            .header("AppType", "TPOS")
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .method(request.method(), request.body());
                    Request request1 = requestBuilder.build();
                    return chain.proceed(request1);
                }
            };
            builder.addInterceptor(headerInterceptor);

            OkHttpClient okHttpClient = builder.build();
            retrofit = new Retrofit.Builder().baseUrl(ApiConstants.getHost(HostType.ZHRB_NEWS))
                    //设置Json转换器
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //RxJava 适配器
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        mApiService = retrofit.create(ApiService.class);
    }

    /**
     * 通过主机类型来获取,Service
     * @param hostType
     * @return
     */
    public static ApiService getApiService(int hostType) {
        ApiClient retrofitManager = mRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new ApiClient();
            mRetrofitManager.put(hostType, retrofitManager);
        }
        return retrofitManager.mApiService;
    }
}
