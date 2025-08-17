
---

# **AAIB API Automation Tests**

This project contains **API test automation scripts** using **Java, RestAssured, TestNG, and JSON-Simple**.
It demonstrates **CRUD operations** (Create, Read, Update, Delete) on the [ReqRes](https://reqres.in/) sample API.

---

## **Features Tested**

The script covers a **full user lifecycle workflow**:

1. **Create User** → `POST /users`
2. **Update User** → `PUT /users/{id}`
3. **Get User (after update)** → `GET /users/{id}`
4. **Delete User** → `DELETE /users/{id}`
5. **Verify User Deletion** → `GET /users/{id}`

> Note: The ReqRes API is a **mock API**. It does not persist updates or deletions. Responses may return `200`, `201`, `204`, or `404` depending on API behavior.

---

## **Tools & Dependencies**

* **Java 21**
* **RestAssured 5.x** – API automation library
* **TestNG 7.x** – test framework & assertions
* **JSON-Simple 1.x** – JSON request body handling
* **Maven** – build & dependency management

Dependencies are defined in `pom.xml`.

---

## ** Project Structure**

```
AAIB-PROJECT/
 ├── src/test/java/
 │    └── AAIB.java          # Main test class
 ├── pom.xml                 # Maven dependencies
 ├── README.md               # Documentation
 └── allure-results/         # (Optional) Test report results
```

---

## **Running the Tests**

### **1. Clone the Repo**

```bash
git clone https://github.com/<your-username>/AAIB-REPO.git
cd AAIB-REPO
```

### **2. Run with Maven**

```bash
mvn clean test
```

### **3. Run Specific Test (Optional)**

```bash
mvn -Dtest=AAIB test
```

---

## ** Test Reporting**

* Integrated with **Allure Reports** (optional).
* To generate report:

```bash
mvn allure:serve
```

---

## ** Sample Test Flow**

1. Create user → Assert **201 Created**
2. Update user → Assert **200 OK**
3. Fetch user → Assert **200 or 404**
4. Delete user → Assert **204 No Content**
5. Verify deletion → Assert **404 or 200 (mock response)**

---

## **Notes**

* This project uses **ReqRes ([https://reqres.in/](https://reqres.in/))**, a public mock API for testing.
* Data persistence is **not guaranteed**.
* The `"x-api-key"` header is a **dummy header** for demonstration only.

---


