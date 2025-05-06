package com.movieticketbooking.demo.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String currentShowTime;

    @Column(nullable = false)
    private int totalSeats = 200;

    @Column(nullable = false)
    private int bookedSeats = 0;

    @Column(nullable = false)
    private double ticketPrice; // Price per ticket

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentShowTime() {
        return currentShowTime;
    }

    public void setCurrentShowTime(String currentShowTime) {
        this.currentShowTime = currentShowTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

  
    public void bookSeats(int seats) {
        if (bookedSeats + seats > totalSeats) {
            throw new IllegalArgumentException("Not enough seats available");
        }
        bookedSeats += seats;
    }
}
