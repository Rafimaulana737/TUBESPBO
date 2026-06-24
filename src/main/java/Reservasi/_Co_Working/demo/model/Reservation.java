package Reservasi._Co_Working.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingKode;
    private LocalDate tanggal;
    private String status;
    private LocalDateTime waktuCheckIn;
    private LocalDateTime waktuCheckOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "meja_id")
    private Meja meja;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public String getBookingKode() {
        return bookingKode;
    }

    public void setBookingKode(String bookingKode) {
        this.bookingKode = bookingKode;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getWaktuCheckIn() {
        return waktuCheckIn;
    }

    public void setWaktuCheckIn(LocalDateTime waktuCheckIn) {
        this.waktuCheckIn = waktuCheckIn;
    }

    public LocalDateTime getWaktuCheckOut() {
        return waktuCheckOut;
    }

    public void setWaktuCheckOut(LocalDateTime waktuCheckOut) {
        this.waktuCheckOut = waktuCheckOut;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Meja getMeja() {
        return meja;
    }

    public void setMeja(Meja meja) {
        this.meja = meja;
    }
}
