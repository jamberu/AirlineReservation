# AirlineReservation
A simple system for reservation of airplane seats

## Tasks
- [x] Use of two-dimensional array
- [x] Replica of output
- [x] Differentiation of Economy and Economy Plus seats
- [x] Prompt for Seat Plan Change if Current Plan is full
- [x] Prompt for Passenger Credentials
- [x] Prompt for Seat Occupation Method (Random/Manual)

## How To Test
The program runs on `default` mode. This means that the seat occupation status are all `Available`.
To test the other prompts of the program *E.g Change of Seat Plan Reservation*, change the parameter of the method `initializeAirplaneSeats()` at [**Line 281**](src/main/AirlineReservation.java#L281).

### The following are the options for changing the mode: `default`, `full`, `full-economy-plus`, `full-economy`
    
