# Neelgiri Hotel — Room Booking System

A full-stack web application for Neelgiri Hotel, Akbarpur (Ambedkar Nagar, UP).
Built to replace manual booking with a simple, fast, and mobile-friendly online system.

## What it does
- Displays available rooms filtered by type (Single / Double)
- Temporarily holds a room when a guest clicks on it
- Collects guest details and confirms the booking in real time
- Auto-registers new customers on their first booking — no account creation needed
- Stores all bookings and customer records in a PostgreSQL database

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
