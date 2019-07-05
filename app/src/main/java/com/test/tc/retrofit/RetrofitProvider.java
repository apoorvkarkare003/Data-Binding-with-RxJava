package com.test.tc.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.test.tc.BuildConfig;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class RetrofitProvider {
    private static RetrofitProvider instance;
    private ApiService apis;

    private RetrofitProvider() {
        synchronized (Retrofit.class) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .addConverterFactory(PageAdapter.FACTORY)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(builder.build())
                    .build();

            apis = retrofit.create(ApiService.class);
        }
    }

    public static void initialize() {
        if (instance == null) {
            instance = new RetrofitProvider();
        }
    }

    public static RetrofitProvider getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ApiClient not initialized, use initialize()");
        }
        return instance;
    }

    public ApiService getApis() {
        return instance.apis;
    }

    static final class PageAdapter implements Converter<ResponseBody, Page> {
        static final Converter.Factory FACTORY = new Converter.Factory() {
            @Override
            public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                    Retrofit retrofit) {
                if (type == Page.class)
                    return new PageAdapter();
                return null;
            }
        };

        @Override
        public Page convert(ResponseBody responseBody) throws IOException {
            Document document = Jsoup.parse(responseBody.string());
            String content = document.html();
            return new Page(content);
        }
    }

    public static class Page {
        public String content;

        Page(String content) {
            this.content = content;
        }
    }


}
