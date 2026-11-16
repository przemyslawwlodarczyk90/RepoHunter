# RepoHunter

Ćwiczenie z odpytywania API GitHuba w Spring Boot. Po podaniu loginu API zwraca publiczne repozytoria użytkownika z pominięciem forków. Dla każdego podaje nazwę, login właściciela oraz gałęzie i SHA ich ostatnich commitów.

## Uruchomienie

Potrzebujesz JDK 21 i tokena GitHuba. Ustaw token w lokalnej zmiennej środowiskowej `GITHUB_API_TOKEN`. W PowerShell uruchom:

```powershell
.\mvnw.cmd spring-boot:run
```

Na Linuxie lub macOS użyj `./mvnw spring-boot:run`. Przykładowe zapytanie:

```http
GET http://localhost:8080/api/github/octocat
```

Nieistniejący użytkownik zwraca HTTP 404 z polami `status` i `message`.

## Testy

`.\mvnw.cmd test` uruchamia m.in. testy integracyjne odpowiedzi API. Korzystają one z GitHuba, więc obowiązują limity zapytań.
