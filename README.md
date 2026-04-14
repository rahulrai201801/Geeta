# 🪷 Bhagavad Gita Krishna Mentor
### A Spiritual Web Application for Life Guidance

---

## 📁 PART 1 — Project Structure

```
gita-mentor/
│
├── database/
│   └── schema.sql                   ← All tables + 20 shlokas + quiz questions
│
├── backend/
│   ├── pom.xml                      ← Maven dependencies (Spring Boot 3.2)
│   └── src/main/
│       ├── java/com/gitamentor/
│       │   ├── GitaMentorApplication.java
│       │   ├── config/
│       │   │   └── SecurityConfig.java    ← BCrypt + CORS config
│       │   ├── model/
│       │   │   ├── User.java
│       │   │   ├── Shloka.java
│       │   │   ├── UserQuery.java
│       │   │   ├── UnansweredQuery.java
│       │   │   └── QuizQuestion.java
│       │   ├── repository/
│       │   │   ├── UserRepository.java
│       │   │   ├── ShlokaRepository.java
│       │   │   ├── UserQueryRepository.java
│       │   │   ├── UnansweredQueryRepository.java
│       │   │   └── QuizQuestionRepository.java
│       │   ├── service/
│       │   │   ├── AuthService.java       ← Register / Login logic
│       │   │   ├── MentorService.java     ← Keyword detection + Shloka fetch
│       │   │   └── QuizService.java       ← User-category quiz fetch
│       │   └── controller/
│       │       ├── AuthController.java    ← POST /register, POST /login
│       │       ├── MentorController.java  ← POST /mentor, POST /save-unanswered
│       │       └── QuizController.java    ← GET /quiz
│       └── resources/
│           └── application.properties
│
└── frontend/
    ├── html/
    │   ├── login.html
    │   ├── register.html
    │   ├── home.html
    │   ├── mentor.html
    │   └── quiz.html
    ├── css/
    │   └── style.css                ← Full spiritual design system
    └── js/
        └── utils.js                 ← Shared API helpers + session management
```

---

## 🗄️ PART 2 — Database (see database/schema.sql)

### Tables
| Table               | Purpose                                      |
|---------------------|----------------------------------------------|
| `users`             | Registered user accounts (BCrypt passwords) |
| `shlokas`           | 20 Bhagavad Gita shlokas across 20 topics   |
| `user_queries`      | Every query a user makes (with category)    |
| `unanswered_queries`| Queries where no shloka was found           |
| `quiz_questions`    | MCQ questions (3 per major category)        |

---

## 🔌 PART 3 — API Endpoints

| Method | Endpoint           | Description                            |
|--------|--------------------|----------------------------------------|
| POST   | `/register`        | Register new user                      |
| POST   | `/login`           | Login user → returns userId, username  |
| POST   | `/mentor`          | Get Krishna's advice for a query       |
| GET    | `/quiz?userId=X`   | Get 5 quiz questions for user          |
| POST   | `/save-unanswered` | Manually save an unanswered query      |

### Request / Response Examples

**POST /register**
```json
Request:  { "username": "Arjuna", "password": "gita123" }
Response: { "success": true, "message": "Registration successful!" }
```

**POST /login**
```json
Request:  { "username": "Arjuna", "password": "gita123" }
Response: { "success": true, "userId": 1, "username": "Arjuna", "message": "..." }
```

**POST /mentor**
```json
Request:  { "userId": 1, "query": "I am stressed about my exam results" }
Response: {
  "found": true,
  "category": "stress",
  "problem": "...",
  "sanskrit": "योगस्थः कुरु कर्माणि...",
  "meaning": "...",
  "explanation": "...",
  "lifeExample": "...",
  "guidance": "..."
}
```

**GET /quiz?userId=1**
```json
Response: [ { "id":1, "category":"stress", "question":"...", "optionA":"...", ..., "correctAnswer":"B" }, ... ]
```

---

## 📖 PART 5 — 20 Shlokas Dataset (Categories)

| # | Category        | Gita Verse |
|---|-----------------|------------|
| 1 | stress          | 2.48       |
| 2 | fear            | 2.23       |
| 3 | anger           | 2.63       |
| 4 | discipline      | 6.5        |
| 5 | karma           | 2.47       |
| 6 | motivation      | 3.35       |
| 7 | focus           | 6.35       |
| 8 | confusion       | 4.42       |
| 9 | purpose         | 18.45      |
|10 | duty            | 18.48      |
|11 | leadership      | 3.21       |
|12 | self_control    | 2.67       |
|13 | detachment      | 2.71       |
|14 | success         | 2.62       |
|15 | failure         | 2.40       |
|16 | mind_control    | 6.5 (Alt)  |
|17 | consistency     | 9.22       |
|18 | decision_making | 2.50       |
|19 | peace           | 2.71       |
|20 | confidence      | 4.39       |

---

## 🚀 PART 6 — Run Instructions

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8.0+
- Any browser (Chrome recommended)

---

### Step 1 — Setup MySQL Database

```sql
-- Open MySQL and run:
mysql -u root -p

-- Then paste or source the SQL file:
SOURCE /path/to/gita-mentor/database/schema.sql;

-- Verify:
USE gita_mentor;
SELECT COUNT(*) FROM shlokas;        -- Should show 20
SELECT COUNT(*) FROM quiz_questions; -- Should show 30+
```

---

### Step 2 — Configure Database Password

Open: `backend/src/main/resources/application.properties`

```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD   ← Change this
```

---

### Step 3 — Run the Backend (Permanent Solution - No Target Lock)

```bash
cd gita-mentor/backend
mvn clean package -DskipTests
java -jar target/gita-mentor-1.0.0.jar
```

- App runs on: `http://localhost:8080`
- This builds a JAR and runs it separately, preventing target directory locks

### Alternative: Development Mode (May Lock Target)

```bash
cd gita-mentor/backend
mvn spring-boot:run
```

⚠️ **Warning**: This locks the `target` directory. Use the JAR method above for permanent unlock.

Test it:
```bash
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"test123"}'
```

---

### Step 4 — Open the Frontend

Open any HTML file directly in your browser:
```
gita-mentor/frontend/html/login.html
```

Or serve with Python for best results:
```bash
cd gita-mentor/frontend
python3 -m http.server 5500
# Then open: http://localhost:5500/html/login.html
```

Or use VS Code **Live Server** extension → Right-click `login.html` → Open with Live Server.

---

### Step 5 — Use the Application

1. Open `login.html` → Register a new account
2. Login → You are taken to Home
3. Click **Krishna Mentorship** → Type your problem
4. Try topics: stress, fear, anger, focus, motivation, career, failure
5. Click **Quiz** → Answer 5 questions based on your searches
6. View your final score with a Gita verse

---

## ⚙️ How Keyword Detection Works

The `MentorService.java` has a `KEYWORD_CATEGORY_MAP` with 80+ keyword → category mappings.

When a user types: *"I am stressed about my career and fear of failure"*
- "stress" → maps to `stress` category
- "career" → maps to `decision_making` category
- "fear"   → maps to `fear` category

The **first match** is used to fetch a shloka. If no keyword matches → query is saved to `unanswered_queries` table for admin review.

---

## 🔒 Security Notes

- Passwords are hashed using **BCrypt** (never stored as plain text)
- Session stored in browser `sessionStorage` (cleared on tab close)
- CORS is open for local development (restrict in production)
- No JWT tokens (kept simple for beginners)

---

## 🛠️ Troubleshooting

| Problem | Fix |
|---------|-----|
| "Cannot connect to server" | Ensure backend runs on port 8080 |
| "Access denied" MySQL error | Check username/password in application.properties |
| White page / JS errors | Open browser DevTools (F12) → Console |
| Shlokas not found | Run `schema.sql` in MySQL to seed data |
| Quiz shows 0 questions | Check `quiz_questions` table has data |

---

## 🌸 Built with Devotion

> *"Yoga is skill in action."* — Bhagavad Gita 2.50

**Tech Stack:** Java 17 · Spring Boot 3.2 · MySQL 8 · Vanilla HTML/CSS/JS  
**Design:** Spiritual saffron & gold palette · Cinzel & Crimson Pro fonts  
**Fonts loaded from:** Google Fonts (requires internet on first load)

