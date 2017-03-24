package com.testing.Services;


import com.testing.Models.Dataset;
import com.testing.Models.ListResponse;
import com.testing.Models.OurResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DatasetService {

    //https://apidata.mos.ru/v1/datasets/658/rows?$top=2&$orderby=global_id
    //rows?$top=2&$orderby=global_id
    @GET("rows?$orderby=global_id")
    Call<ListResponse<OurResponse<Dataset>>> getDataSets(@Query("$top") int top);

    @GET("rows?$top=1&$orderby=global_id")
    Call<ListResponse<OurResponse<Dataset>>> getFirstDataSet();

//    GET https://apidata.mos.ru/v1/datasets?$skip=1&$top=1&$inlinecount=allpages
//    @GET("datasets?$inlinecount=allpages")
//    Call<OurResponse<Dataset>> getDataSets(@Query("$top") int top, @Query("$skip") int skip);
//
//    @GET("datasets?$skip=1&$top=1&$inlinecount=allpages")
//    Call<OurResponse<Dataset>> getFirstDataSet();
}
