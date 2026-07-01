# ShopVyra Backend

Production-ready e-commerce REST API built with Spring Boot, Spring Security, JWT authentication, JPA/Hibernate, and PostgreSQL.

## Tech stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4 |
| Security | Spring Security, JWT (jjwt 0.13) |
| Persistence | Spring Data JPA / Hibernate |
| Database | PostgreSQL |
| Payments | Stripe Java SDK |
| Docs | springdoc-openapi (Swagger UI) |
| Object mapping | ModelMapper |
| Build | Maven |
| Deployment | Docker, Render |

## Architecture

Layered Spring MVC architecture — each request flows through security, controllers, services, and repositories before hitting the database:

```
Client request
      │
      ▼
JWT auth filter  ──────────────────────────────► validates bearer token, sets SecurityContext
      │
      ▼
REST controllers ─── Auth · Product · Category · Cart · Order · Address · Analytics
      │
      ▼
Service layer ─── business logic, DTO ↔ entity mapping (ModelMapper)
      │              │
      │              ├──► Stripe API (payment intents)
      │              └──► Local file storage (/media/images)
      ▼
Spring Data JPA repositories
      │
      ▼
PostgreSQL (Render)
```

**Security model**: stateless JWT auth. `AuthTokenFilter` intercepts every request, validates the token via `JwtUtils`, and loads the user through `UserDetailsServiceImpl`. Authorization is role-based (`ROLE_USER`, `ROLE_SELLER`, `ROLE_ADMIN`), enforced declaratively in `WebSecurityConfig` per route prefix (`/api/admin/**`, `/api/seller/**`, `/api/public/**`).

**Package structure** (`com.ecommerce.project`):
```
config/         AppConfig, SwaggerConfig, WebMvcConfig, DataSeeder, AppConstants
controller/     REST endpoints (7 controllers)
model/          JPA entities
payload/        DTOs and response wrappers
repositories/   Spring Data JPA interfaces
security/       WebSecurityConfig, JWT filter/utils, UserDetails
service/        Interface + Impl pairs per domain
exceptions/     Global exception handling
util/           AuthUtil (current-user helpers)
```

## Domain model

Core entities and relationships:

- `User` — has many `Address`, one `Cart`, many `Order`; many-to-many with `Role`
- `Role` — `AppRole` enum: `ROLE_USER`, `ROLE_SELLER`, `ROLE_ADMIN`
- `Category` — has many `Product`
- `Product` — belongs to `Category`, appears in `CartItem` / `OrderItem`
- `Cart` — belongs to `User`, has many `CartItem`
- `Order` — belongs to `User`, has many `OrderItem`, one `Payment`, one `Address`
- `Payment` — payment method + Stripe payment intent details

## API overview

All routes are prefixed `/api`. Access tiers: `/public/**` (open), authenticated user routes, `/seller/**` (SELLER or ADMIN), `/admin/**` (ADMIN only).

| Domain | Method | Path | Access |
|---|---|---|---|
| Auth | POST | `/auth/signup`, `/auth/signin`, `/auth/signout` | Public |
| Auth | GET | `/auth/user`, `/auth/username` | Authenticated |
| Auth | GET | `/auth/sellers` | Authenticated |
| Categories | GET | `/public/categories` | Public |
| Categories | POST/PUT/DELETE | `/admin/categories/**` | Admin |
| Products | GET | `/public/products`, `/public/categories/{id}/products`, `/public/products/keyword/{kw}` | Public |
| Products | POST/PUT/DELETE | `/admin/products/**`, `/seller/products/**` | Admin / Seller |
| Cart | POST | `/cart/create`, `/carts/products/{id}/quantity/{qty}` | Authenticated |
| Cart | GET/PUT/DELETE | `/carts/**`, `/cart/products/**` | Authenticated |
| Address | GET/POST/PUT/DELETE | `/addresses/**`, `/users/addresses` | Authenticated |
| Orders | POST | `/order/users/payments/{method}`, `/order/stripe-client-secret` | Authenticated |
| Orders | GET | `/users/orders`, `/admin/orders`, `/seller/orders` | Authenticated / Admin / Seller |
| Orders | PUT | `/admin/orders/{id}/status`, `/seller/orders/{id}/status` | Admin / Seller |
| Analytics | GET | `/admin/app/analytics` | Admin |

Interactive docs available at `/swagger-ui/index.html` once running.

## Deployment

- **Backend**: Render (Docker), with UptimeRobot pinging `/actuator/health` to prevent cold starts on the free tier
- **Database**: managed PostgreSQL on Render
- **Payments**: Stripe (test/live keys via env var)

## Features

- JWT-based authentication with HTTP-only cookie support
- Role-based access control (user / seller / admin)
- Product & category CRUD with pagination, sorting, and keyword search
- Cart management (add/update/remove items, quantity operations)
- Order placement with Stripe payment intent flow, plus COD-style methods
- Seller dashboard endpoints scoped to the seller's own products/orders
- Admin analytics endpoint
- Address book per user
- Centralized exception handling (`MyGlobalExceptionHandler`)
- OpenAPI/Swagger docs with bearer-token auth wired in
