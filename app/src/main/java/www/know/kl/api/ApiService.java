package www.retrofit.com.retrofitrxjavademo.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.MainNewsBean;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.NewsDetailBean;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.model.LastVersionBean;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.model.StartImageBean;

/**
 * des:ApiService
 * Created by xsf
 * on 2016.06.15:47
 */
public interface ApiService {

    @GET("4/start-image/{pix}")
    Observable<StartImageBean> getStartImage(@Path("pix") String pix);

    @GET("4/version/android/{version}")
    Observable<LastVersionBean> getLastVersion(@Path("version") String version);

    @GET("4/news/{path}")
    Observable<MainNewsBean> getLastNews(@Path("path") String path);

    @GET("4/news/{id}")
    Observable<NewsDetailBean> getNewsDeatil(@Path("id") String id);

    /*@GET("login")
    Observable<BaseRespose<User>> login(@Query("username") String username, @Query("password") String password);

    @GET("nc/article/{postId}/full.html")
    Observable<Map<String, NewsDetail>> getNewDetail(
            @Header("Cache-Control") String cacheControl,
            @Path("postId") String postId);

    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsSummary>>> getNewsList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);

    @GET
    Observable<ResponseBody> getNewsBodyHtmlPhoto(
            @Header("Cache-Control") String cacheControl,
            @Url String photoPath);
    //@Url，它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
    // baseUrl 需要符合标准，为空、""、或不合法将会报错

    @GET("data/福利/{size}/{page}")
    Observable<GirlData> getPhotoList(
            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);

    @GET("nc/video/list/{type}/n/{startPage}-10.html")
    Observable<Map<String, List<VideoData>>> getVideoList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type,
            @Path("startPage") int startPage);*/
}
