# GithubTask
### Aplikacja Spring Boot udostępniająca API do wyświetlania listy repozytoriów GitHub (z wyłączeniem forków) wraz z ich gałęziami i informacjami o commitach.

## Technologia
- Java 25
- Spring Boot 4.0.1
- Spring Web MVC
- Spring RestClient
- WireMock (do testów integracyjnych)
- Gradle z Kotlin DSL

## Funkcje
Aplikacja udostępnia punkt końcowy REST API, który:
- Wyświetla listę wszystkich repozytoriów nieforkowanych dla danego użytkownika GitHub
- Zwraca nazwę repozytorium, login właściciela i informacje o gałęzi
- Dla każdej gałęzi podaje nazwę gałęzi i sumę kontrolną SHA ostatniego commita
- Zwraca poprawne odpowiedzi błędów dla nieistniejących użytkowników

## API
Pobieranie repozytoriów użytkowników
``` properties 
GET http://localhost:8080/users/{username}/repositories
```
Nagłówki:
- Akceptacja: application/json (wymagane)

Odpowiedź potwierdzająca (200 OK):
``` properties 
[
  {
    "name": "repository-name",
    "ownerLogin": "username",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "abc123def456..."
      },
      {
        "name": "develop",
        "lastCommitSha": "xyz789ghi012..."
      }
    ]
  }
]
```

Odpowiedź o błędzie — użytkownik nie został znaleziony (404 NIE ZNALEZIONO):
``` properties 
{
  "status": 404,
  "message": "User 'username' not found"
}
```

### Uruchamianie aplikacji
``` properties 
./gradlew bootRun
```

### Uruchamianie testów
``` properties 
./gradlew test
```

### Zewnętrzny interfejs API
Ta aplikacja korzysta z interfejsu GitHub REST API v3: ```https://developer.github.com/v3```

### Uwagi
- Aplikacja nie obsługuje paginacji.
- Nie zaimplementowano żadnych wzorców buforowania ani zabezpieczeń.
- Aplikacja działa jako serwer proxy między klientem a interfejsem GitHub API.
