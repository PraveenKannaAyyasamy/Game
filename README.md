Prerequisites
* Java 17+
* gradle
* Spring Boot 3+
* Installation
* Angular 14
* TypeScript
* RxJS & HttpClient (for API calls)
 
API Endpoints
* Upload Employee Data and Generate Assignments
* Endpoint: POST /api/assignments/generate
* Description: Uploads employee data and (optionally) last year's assignments to generate new Secret Santa pairs.

 Request Parameters:
* employeeFile (CSV) – List of employees.
* lastYearFile (CSV, optional) – Last year’s Secret Santa assignments.

 Request Example (Postman):
   Method: POST
   Headers: Content-Type: multipart/form-data
   Body (form-data):
   employeeFile: Upload employees.csv
   lastYearFile: Upload last_year.csv
* Response:
json
{
"message": "Assignments generated successfully!",
"downloadUrl": "http://localhost:8080/api/assignments/download"
}

 Design Patterns
* Singleton Pattern – Ensures only one instance of CSVService.
* Strategy Pattern – Supports multiple assignment strategies (RandomAssignmentStrategy, FairAssignmentStrategy).
* Factory Pattern – EntityFactory creates model instances.

 SOLID Principles 
* Single Responsibility: Separation of concerns (Controller, Service, Strategy).
* Open/Closed: Easy to add new assignment strategies.
* Liskov Substitution: Strategies are interchangeable.
* Interface Segregation: Separate interfaces for CSV parsing and assignments.
* Dependency Inversion: Uses constructor injection for dependencies.
  
 Error Handling & Logging
* Global Exception Handling – Returns meaningful error messages.
* Logging – Uses slf4j for debugging and tracking API requests.
* CORS Configuration – Enables frontend integration with Angular.

 Future Enhancements
* More Assignment Strategies (e.g., Preference-Based Matching)
* Database Storage ( MongoDB) instead of in-memory storage.
* Email Notifications to notify employees of their Secret Santa assignment.
