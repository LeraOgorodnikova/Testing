package Models;

import com.google.gson.annotations.SerializedName;


public class Dataset {

    @SerializedName("global_id")
    private long global_id;
    @SerializedName("NameOfTariff")
    private String NameOfTariff;
    @SerializedName(" TypeOfTransport")
    private String TypeOfTransport;
    @SerializedName("TariffDistance")
    private String TariffDistance;
    @SerializedName("NameOfCarrier")
    private String[] NameOfCarrier;
    @SerializedName("TicketValidity")
    private String TicketValidity;
    @SerializedName("TicketZone")
    private String[] TicketZone;
    @SerializedName("NumberOfZone")
    private String NumberOfZone;
    @SerializedName("TicketCost")
    private int TicketCost;

    public long getGlobal_id() {
        return global_id;
    }

    public void setGlobal_id(long global_id) {
        this.global_id = global_id;
    }

    public String getNameOfTariff() {
        return NameOfTariff;
    }

    public void setNameOfTariff(String nameOfTariff) {
        NameOfTariff = nameOfTariff;
    }

    public String getTicketValidity() {
        return TicketValidity;
    }

    public void setTicketValidity(String ticketValidity) {
        TicketValidity = ticketValidity;
    }

    public String getNumberOfZone() {
        return NumberOfZone;
    }

    public void setNumberOfZone(String numberOfZone) {
        NumberOfZone = numberOfZone;
    }

    public String getTariffDistance() {
        return TariffDistance;
    }

    public void setTariffDistance(String tariffDistance) {
        TariffDistance = tariffDistance;
    }

    public String getTypeOfTransport() {
        return TypeOfTransport;
    }

    public void setTypeOfTransport(String typeOfTransport) {
        TypeOfTransport = typeOfTransport;
    }

    public String[] getNameOfCarrier() {
        return NameOfCarrier;
    }

    public void setNameOfCarrier(String[] nameOfCarrier) {
        NameOfCarrier = nameOfCarrier;
    }

    public int getTicketCost() {
        return TicketCost;
    }

    public void setTicketCost(int ticketCost) {
        TicketCost = ticketCost;
    }

    public String[] getTicketZone() {
        return TicketZone;
    }

    public void setTicketZone(String[] ticketZone) {
        TicketZone = ticketZone;
    }
}
