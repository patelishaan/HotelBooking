# Neelgiri Hotel — Room Booking System

A full-stack web application for Neelgiri Hotel, Akbarpur (Ambedkar Nagar, UP).
Built to replace manual booking with a simple, fast, and mobile-friendly online system.

## What it does
- Displays available rooms filtered by type (Single / Double)
- Temporarily holds a room when a guest clicks on it
- Collects guest details and confirms the booking in real time
- Auto-registers new customers on their first booking — no account creation needed
- Stores all bookings and customer records in a PostgreSQL database (supabase)

## Tech Stack
**Backend** — Spring Boot, Spring Data JPA, Hibernate, PostgreSQL (Supabase)  
**Frontend** — HTML, Tailwind CSS, Vanilla JavaScript  
**Database** — PostgreSQL hosted on Supabase

## Project Structure
├── website/             → Spring Boot backend (REST API)
│   ├── entity/          → Room, Customer, Booking
│   ├── repository/      → JPA repositories
│   ├── service/         → Business logic
│   ├── controller/      → REST endpoints
│   └── dto/             → Request/Response DTOs
│
└── website-frontend/    → Static frontend
    ├── index.html       → Landing page
    ├── booking.html     → Room selection
    └── confirm.html     → Booking confirmation form
##Output Images
**Home Page** - 
<img width="1919" height="917" alt="image" src="https://github.com/user-attachments/assets/955897df-8386-4e4f-979c-65baa2e4a639" />
<img width="1919" height="920" alt="image" src="https://github.com/user-attachments/assets/454a6432-01e9-4a2f-8c03-a18bb7a6acf4" />

**Booking Page** - 
<img width="1918" height="918" alt="image" src="https://github.com/user-attachments/assets/b7913dfe-01e6-4fe5-9c63-9e7f6fb9ead3" />
**Confirmation Page** -
<img width="1919" height="916" alt="image" src="https://github.com/user-attachments/assets/925cecb7-aff5-4932-8a8d-df9ca77ed3de" />
<img width="1380" height="527" alt="image" src="https://github.com/user-attachments/assets/20a4d0be-66dd-4cf8-ae08-065cca54f305" />
**Email received by Customer** -
<img width="581" height="359" alt="image" src="https://github.com/user-attachments/assets/1e6722c5-e5fd-487f-97b5-376f93f84965" />
**Admin Panel** -
<img width="1919" height="915" alt="image" src="https://github.com/user-attachments/assets/ad30ae0c-cfd1-48d7-92d7-d6fe842a9041" />





