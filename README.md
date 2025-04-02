# RepoHunter 📈

Prosta aplikacja Spring Boot, która pobiera publiczne repozytoria GitHub danego użytkownika (z pominięciem forków) i zwraca listę gałęzi z SHA ostatniego commita.

---

## ✨ Funkcjonalności

- Wejście: nazwa użytkownika GitHub
- Wyjście: lista repozytoriów (bez forków) zawierająca:
  - nazwę repozytorium,
  - login właściciela,
  - listę gałęzi, z których każda zawiera:
    - nazwę gałęzi,
    - SHA ostatniego commita.
- Integracja z GitHub REST API v3
- Obsługa błędów (np. nieistniejący użytkownik)
- Obsługa tokena GitHub w celu uniknięcia limitów zapytań
- Zaimplementowane testy integracyjne

---

## ⚙️ Wymagania

- Java 21  
- Maven

---

## 🚀 Uruchomienie aplikacji

```bash
mvn spring-boot:run
```

### Przykładowe zapytanie:

```
GET /api/github/octocat
```

---

## ❌ Obsługa błędów

W przypadku nieistniejącego użytkownika API zwraca:

```json
{
  "status": 404,
  "message": "GitHub user not found: {username}"
}
```

---

## 🧪 Testy

Zawarte testy integracyjne obejmują m.in.:

- prawidłowego użytkownika z repozytoriami,
- użytkownika nieistniejącego,
- poprawność struktury JSON,
- obecność nagłówka `Content-Type: application/json`.

> ℹ️ Uwaga: Testy wymagają tokena GitHub z uwagi na limity zapytań (rate limit).

---

## 🔐 Token GitHub

Aby uniknąć limitu 60 zapytań na godzinę:

1. Wygeneruj token tutaj:  
   [https://github.com/settings/tokens](https://github.com/settings/tokens)

2. Umieść go lokalnie w pliku `application.properties`:

```properties
github.api.token=tu_wklej_token
```



## 👤 Autor

 **Przemek**.

---

