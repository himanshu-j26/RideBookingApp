# Ride Booking App

A scalable ride booking backend system implementing matching and pricing strategies with clean architecture principles.

---

## Overview

RideBookingApp is a backend system that simulates a ride-sharing platform.  
It focuses on:

- Rider–Driver matching
- Strategy-based pricing
- Thread-safe service management
- Extensible architecture

This project demonstrates solid Low-Level Design (LLD) and clean code practices.

---

## Architecture

The system follows a modular design:

- Entities Layer
- Strategy Layer (Matching & Pricing)
- Service Layer
- Thread-safe Singleton Service

### High Level Flow

1. Rider requests ride
2. Matching strategy selects driver
3. Pricing strategy calculates fare
4. Trip is created and tracked

---

## Design Patterns Used

- Singleton Pattern (RideSharingService)
- Strategy Pattern (Matching & Pricing)
- Factory (if implemented)
- ConcurrentHashMap for thread safety

---

## Tech Stack

- Java
- OOP Principles
- Multithreading Concepts
- Design Patterns

---