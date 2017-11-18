package kt09_streams.destination;

import java.io.Serializable;

class Destination implements Serializable {
    private String country = "";
    private String memberOfEU = "";
    private String accessionYear = "";
    private String currency = "";
    private String currencyCode = "";
    private String area = "";
    private String gdp = "";


    public Destination() {
    }

    Destination(String stringLine) {
        parseString(stringLine);
    }

    private void parseString(String stringLine) {

        String[] splitString = stringLine.split(",(?! )"); // split by comma, except comma followed by space

        this.country = splitString[0];
        this.memberOfEU = splitString[1];
        this.accessionYear = splitString[2];
        this.currency = splitString[8];
        this.currencyCode = splitString[9];
        this.area = splitString[12];

        try {
            this.gdp = splitString[16];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.gdp = "";
        }

    }

    double getAverageTemperature() {
        if (this.getMemberOfEU().equals("Member")) {
            return Integer.valueOf(this.getAccessionYear()) / 100.0 + 273.0;
        } else {
            return 299.0;
        }
    }

    String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String getMemberOfEU() {
        return memberOfEU;
    }

    public void setMemberOfEU(String memberOfEU) {
        this.memberOfEU = memberOfEU;
    }

    String getAccessionYear() {
        return accessionYear;
    }

    public void setAccessionYear(String accessionYear) {
        this.accessionYear = accessionYear;
    }

    String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    String getGdp() {
        return gdp;
    }

    public void setGdp(String gdp) {
        this.gdp = gdp;
    }

    @Override
    public String toString() {
        return country;
    }
}
