package com.example.retrofitcrud;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> daftar(@Field("npm") String npm,
                       @Field("nama") String nama,
                       @Field("kelas") String kelas,
                       @Field("sesi") String sesi);

    @GET("view.php")
    Call<Value> view();

    @FormUrlEncoded
    @POST("update.php")
    Call<Value> ubah(@Field("npm") String npm,
                     @Field("nama") String nama,
                     @Field("kelas") String kelas,
                     @Field("sesi") String sesi);

    @FormUrlEncoded
    @POST("insert_matkul.php")
    Call<Value_Matkul> matkul(@Field("nama_matkul") String nama_matkul,
                              @Field("sks") String sks);

    @GET("view_matkul.php")
    Call<Value_Matkul> hasil();

    @FormUrlEncoded
    @POST("update_matkul.php")
    Call<Value_Matkul> ubah_matkul(@Field("nama_matkul") String nama_matkul,
                     @Field("sks") String sks);
}
