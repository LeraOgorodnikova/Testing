package Tests;



import Models.Data;
import Models.OurResponse;
import Models.Response;
import Services.DatasetService;
import Services.Service;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.Test;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

public class DatasetTest {

    private final static Logger logger = Logger.getLogger(DatasetTest.class);
    private DatasetService service;
    private final String CATEGORY = "'Кинотеатры'";
    private long idCategory;

    public DatasetTest(){
        service = Service.getRetrofit().create(DatasetService.class);
    }

    @Test
    public void getDataSetSize() {

        try {
            findIdCategory();
            Call<List<OurResponse>> responseCall = service.getDataSets(idCategory,1);
            List<OurResponse> response = responseCall.execute().body();
            logger.debug(response);

            Assert.assertEquals(response.size(),1);

        } catch (IOException e) {
            logger.error(e);
            throw new TestException(e);
        }
    }

    @Test
    public void getGlobalIdDataSet(){

        try {
            findIdCategory();
            Call<List<OurResponse>> responseCall = service.getDataSets(idCategory,1);
            List<OurResponse> response = responseCall.execute().body();
            logger.debug(response);

            Assert.assertEquals(response.get(0).getGlobal_id(),62829117);
        } catch (IOException e) {
            logger.error(e);
            throw new TestException(e);
        }

    }

    @Test
    public void getNumberDataSet(){

        try {
            findIdCategory();
            Call<List<OurResponse>> responseCall = service.getDataSets(idCategory,1);
            List<OurResponse> response = responseCall.execute().body();
            logger.debug(response);

            Assert.assertEquals(response.get(0).getNumber(),1);

        } catch (IOException e) {
            logger.error(e);
            throw new TestException(e);
        }

    }

    @Test
    public void getCellsDataSet(){

        try {
            findIdCategory();
            Call<List<OurResponse>> responseCall = service.getDataSets(idCategory,1);
            List<OurResponse> response = responseCall.execute().body();
            logger.debug(response);

            Assert.assertEquals(response.get(0).getCells().getTicketCost(),0);
            Assert.assertNotEquals(response.get(0).getCells().getNumberOfZone(),1);
            Assert.assertEquals(response.get(0).getCells().getNameOfTariff(),null);
            Assert.assertEquals(response.get(0).getCells().getNameOfCarrier(),null);
            Assert.assertEquals(response.get(0).getCells().getTicketZone(),null);
        } catch (IOException e) {
            logger.error(e);
            throw new TestException(e);
        }

    }

    @Test
    public void getIdentificationNumberDataset(){

        try {
            Call<Response<Data>> responseCall = service.getDataSetByFilter("Caption eq 'Кинотеатры'");
            Response<Data> response = responseCall.execute().body();

            Assert.assertEquals(response.getItems().get(0).getIdentificationNumber(),"7719028495-MovieTheaters");
        } catch (IOException e) {
            logger.error(e);
            throw new TestException(e);
        }

    }
    private void findIdCategory(){

        try {
            Call<Response<Data>> responseCall = service.getDataSetByFilter("Caption eq 'Кинотеатры'");
            Response<Data> response = responseCall.execute().body();

                if (response.getItems().size() != 0) {
                    idCategory = response.getItems().get(0).getId();
                    logger.debug(String.format("category id - %s", idCategory));
                }else {
                logger.debug(String.format("Category does not exist %s", CATEGORY));
                throw new IllegalArgumentException("Category does not exist: "+ CATEGORY);
            }
        } catch (IllegalArgumentException | IOException e) {
            logger.debug(e.getMessage());
            throw new TestException(e);
        }
    }

}
