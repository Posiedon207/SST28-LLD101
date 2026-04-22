# Elevator System Design

A scalable, object-oriented design of a multi-elevator system that models real-world elevator behavior. The system separates input handling, request routing, and physical execution, enabling clean abstractions, extensibility, and maintainability.

## Design Philosophy

The architecture represents a clear separation of concerns, broken down into specialized Java packages:
- **`panels`**: Captures user interactions through switches (HallPanel, CabinPanel).
- **`controllers`**: Routes and assigns requests to the most suitable lift via the Dispatcher.
- **`models`**: Core domain entities like Lifts, Levels, Service Calls, and Gates.
- **`services`**: Manages execution logic, including TransitHandler and ProtectionUnit.
- **`strategies`**: Pluggable interfaces for defining lift selection and scheduling behaviors.

This structure reflects a highly modular system where user input is decoupled from routing logic and movement, allowing components to evolve independently.

## Core Features

### 1. Request Handling (Cabin and Hall)
- Hall calls (external) are requested at a `Level` using a `HallPanel`.
- Cabin calls (internal) are made from inside a `Lift` using a `CabinPanel`.
- Panel switches generate `ServiceCall` objects (`HallCall` or `CabinCall`) that are processed by the system.

### 2. Multi-Lift Coordination & Routing
- The `BuildingManager` manages the registry of all lifts and levels.
- A `Dispatcher` component receives requests and assigns exactly one lift to fulfill each call.

### 3. Pluggable Strategies (Strategy Pattern)
- **Lift Selection**: Extensible via `LiftSelectionPolicy`.
  - Default implementation: `ClosestLiftPolicy` which assigns the nearest available, functional lift to a hall call.
- **Scheduling**: Extensible via `SchedulingPolicy`.
  - Default implementation: `ScanSchedulingPolicy`, applying a SCAN-like (elevator algorithm) approach to ensure lifts process all requests in the current heading before reversing direction.

### 4. Safety Mechanisms
- **Protection Unit**: Evaluates whether the lift is safe to operate prior to transit.
- **Capacity Handlers**: Prevents movement and triggers a `CapacityAlert` if the internal `WeightSensor` exceeds its limit.
- **Crisis Overrides**: A panic switch inside the cabin halts operations, triggers a `CrisisAlert`, and unlocks the gates.

### 5. Floor Constraints
- Ground level panels cannot request to `DESCEND`.
- Top level panels cannot request to `ASCEND`.
- These constraints are securely enforced upon `Level` instantiation to prevent invalid input states.

## Architecture & Package Layout

```text
src/
├── alerts/        (Alert, CrisisAlert, CapacityAlert)
├── controllers/   (BuildingManager, Dispatcher)
├── enums/         (ActionType, Heading, LiftStatus)
├── models/        (CabinCall, Gate, HallCall, Level, Lift, ServiceCall, WeightSensor)
├── panels/        (ActionSwitch, CabinPanel, HallPanel, HeadingSwitch, LevelSwitch, Switch)
├── services/      (ProtectionUnit, SetupService, TransitHandler)
├── strategies/    (ClosestLiftPolicy, LiftSelectionPolicy, ScanSchedulingPolicy, SchedulingPolicy)
└── App.java       (Main entry point / Simulation runner)
```

## Running the Simulation

A simulated environment (`App.java`) is included to demonstrate configuring a building (10 levels, 3 lifts), accepting random queries, and ticking the operations clock so lifts perform their routines.

### Compilation
From the project root:
```bash
javac -sourcepath src -d out src/App.java src/enums/*.java src/alerts/*.java src/models/*.java src/panels/*.java src/strategies/*.java src/services/*.java src/controllers/*.java
```

### Execution
Run the compiled code output:
```bash
java -cp out App
```
