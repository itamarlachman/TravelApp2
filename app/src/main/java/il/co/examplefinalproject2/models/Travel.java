package il.co.examplefinalproject2.models;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import il.co.examplefinalproject2.interfaces.IKey;
import il.co.examplefinalproject2.utils.DateUtils;
import il.co.examplefinalproject2.utils.DistanceUtils;

public class Travel  implements Serializable, IKey {


    public enum Status {
        New,
        Suggested,
        Approved,
        Selected,
        Finished
    }
    protected Customer customer;
    protected Location source;
    protected Location destination;
    protected Integer passengers;
    protected Date date;
    protected Status status;
    protected Company company;
    protected Double distance;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Travel() {
        this.customer = new Customer();
        this.source = new Location();
        this.destination = new Location();
        this.date = DateUtils.getNow();
        this.distance = 0.0;
        this.status = Status.New;

    }

    public Travel(Customer customer, Location source, Location destination, Integer passengers, Date date, Status status, Company company) {
        this.customer = customer;
        this.source = source;
        this.destination = destination;
        this.passengers = passengers;
        this.date = date;
        this.status = status;
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Location getSource() {
        return source;
    }

    public void setSource(Location source) {
        this.source = source;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDateString(String date) throws ParseException {
        this.date = DateUtils.getDate(date);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getKey() {
        String tel = this.customer.tel;
        String date = DateUtils.getDateTime();
        String key = String.format("%s_%s", getDateString(),tel);
        return key;
    }

    @Override
    public String toString() {
        return  date.getTime() + " for " + customer + "from " + source.getAddress() +
                " to " + destination.getAddress() +
                " with " + passengers.toString() + " passengers ";

    }

    public String getDateString() {
        return DateUtils.getDate(this.date);
    }

    public void calcDistance() {
        this.distance = DistanceUtils.distance(this.source.lat,this.source.lng,this.destination.lat,this.destination.lng);
    }
}
