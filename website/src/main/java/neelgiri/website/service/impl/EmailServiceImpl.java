package neelgiri.website.service.impl;

import lombok.RequiredArgsConstructor;
import neelgiri.website.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Override
    public void sendBookingConfirmation(String toEmail, Long bookingId, Long roomNo) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Booking Confirmed - Neelgiri");

        message.setText(
                "Your booking is confirmed!\n\n" +
                        "Booking ID: " + bookingId + "\n" +
                        "Room No: " + roomNo + "\n\n" +
                        "Thank you for choosing Neelgiri.");
        mailSender.send(message);

    }
}
