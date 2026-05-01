package neelgiri.website.service;

public interface EmailService {
    void sendBookingConfirmation(String toEmail, Long bookingId, Long roomNo);
}
