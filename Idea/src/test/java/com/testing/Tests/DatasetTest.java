package com.testing.Tests;


import com.testing.Models.Dataset;
import com.testing.Models.ListResponse;
import com.testing.Models.OurResponse;
import com.testing.Services.DatasetService;
import com.testing.Services.Service;
import okhttp3.Request;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import retrofit2.Call;

import java.io.IOException;
import java.lang.reflect.Field;

public class DatasetTest {

    private final static Logger logger = Logger.getLogger(DatasetTest.class);
    @Test
    public void getDataSetTest() {
//        final String[] expectedFields = { "global_id", "Number",
//                "Cells",
//            "global_id",
//                    "NameOfTariff",
//                    "TypeOfTransport",
//                    "TariffDistance",
//                    "NameOfCarrier","NameOfCarrier","NameOfCarrier", "NameOfCarrier",
//            "TicketValidity",
//                    "TicketZone","TicketZone",
//            "NumberOfZone",
//                    "TicketCost"
//        };
        DatasetService service = Service.getRetrofit().create(DatasetService.class);

        try {
            Call<ListResponse<OurResponse<Dataset>>> responseCall = service.getDataSets(2);

            ListResponse<OurResponse <Dataset>> response = responseCall.execute().body();
            logger.debug(response);

//            response.getItems().forEach(item -> {
//                JsonElement jsonItem = new Gson().fromJson(item.toString(),
//                        JsonElement.class);
//                for(String field : expectedFields) {
//                    logger.debug(String.format("Field %s checked", field));
//                    Assert.assertTrue(jsonItem.getAsJsonObject().has(field),
//                            String.format("Expected field is absent in respose. Expected: %s", field));
//                }
//            });

            //Assert.assertNotEquals(0L, response.getCount(), "Count is 0");
        } catch (IOException e) {
            logger.error(e);
        }
    }
    //dirty hack
    private Request getRequestFromCall(Call call) {
        Field fld;
        try {
            fld = Call.class.getDeclaredField("originalRequest");
            fld.setAccessible(true);
            return (Request) fld.get(call);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Can't get Request data from a call object", e);
        }catch (IllegalAccessException e) {
            throw new RuntimeException("Can't get Request data from a call object", e);
        }
    }
}
