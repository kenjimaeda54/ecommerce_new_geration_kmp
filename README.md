# Ecommerce New Geration

App de e-commerce multiplataforma construído com **Kotlin Multiplatform (KMP)**, compartilhando lógica de negócio entre **Android** e **iOS**.

## Visão geral

| Módulo | Plataforma | Stack |
|---|---|---|
| `shared` | Kotlin Multiplatform (Android + iOS) | Kotlin 2.0, Ktor 2.3, Koin 3.5, kotlinx.serialization, coroutines, SKIE (TouchLab) |
| `androidEcommerce` | Android (minSdk 26, target/compile 35) | Jetpack Compose (Material 3), Coil, Koin Android |
| `iosEcommerce` | iOS | SwiftUI, consome o framework `shared` gerado pelo KMP |

A API utilizada é a [Fake Store API da EscuelaJS](https://api.escuelajs.co/api/v1) (`https://api.escuelajs.co`).

## Arquitetura

O `shared` segue uma arquitetura em camadas (data / domain / ui):

```
shared/src/commonMain/kotlin/com/ecommercenewgeration/
├── data/
│   ├── dto/            # CategoryDto, ProductDto (kotlinx.serialization)
│   ├── mapper/         # DTO -> modelo de domínio (ProductMapper, CategoryMapper)
│   ├── remote/         # RemoteDataFromInternet: chamadas Ktor com tratamento de erro
│   └── repository/     # ProductRepositoryImpl (implementa interface do domínio)
├── domain/
│   ├── model/          # Product, ProductWithSession, Category, DataOrException
│   ├── repository/     # interface ProductRepository
│   └── usecase/        # GetProductByCategoryUseCase
├── infrastructure/remote/  # KtorApi / KtorApiImpl (HttpClient configurado)
├── di/                 # CommonModule: initKoin() com módulos client, usecase, repository
├── ui/viewmodel/       # HomeViewModel (StateFlow, roda em ambas as plataformas)
└── util/               # CoroutineViewModel (escopo de coroutines por plataforma)
```

Fluxo de dados:

```
HomeScreen (Compose / SwiftUI)
   └── HomeViewModel (StateFlow<DataOrException<List<ProductWithSession>>>)
        └── GetProductByCategoryUseCase
             └── ProductRepository
                  └── RemoteDataFromInternet (Ktor)
                       └── https://api.escuelajs.co
```

- `DataOrException<data, exception, isLoading>` encapsula sucesso / erro / loading sem lançar exceções para a UI.
- `getProductByCategory()` busca as categorias e, em paralelo (`async`/`awaitAll`), os produtos de cada categoria, montando as seções da tela inicial.

## Telas

**Home** (`HomeScreen`): lista de seções por categoria (`LazyColumn`), cada uma com um carrossel horizontal de produtos (`LazyRow`) — cards com imagem (Coil), título, preço e ícone de favorito.

No iOS, o `HomeState.swift` observa o `StateFlow` do `HomeViewModel` compartilhado via `for await` e expõe `loadingState` (`LoadingState`: loading / success / failure) para a `HomeScreen` em SwiftUI.

## Injeção de dependência

Koin inicializado no ponto de entrada de cada plataforma:

- **Android**: `MainApplication.onCreate()` → `initKoin { androidContext(...) }`
- **iOS**: `iOSApp.init()` → `CommonModuleKt.doInitKoin()` (SKIE expõe o `initKoin` do commonMain ao Swift)

## Como rodar

### Requisitos

- JDK 17+
- Android Studio (SDK 35)
- Xcode 15+ (para o iOS, em macOS)

### Android

```bash
./gradlew :androidEcommerce:installDebug
```

Ou abra o projeto no Android Studio e rode a configuração `androidEcommerce`.

### iOS

1. Gere o framework compartilhado (ou deixe o Xcode fazer via gradle task embutida):

```bash
./gradlew :shared:assembleXCFramework
```

2. Abra `iosEcommerce/iosEcommerce.xcodeproj` no Xcode e rode o target `iosEcommerce` (simulador iOS).

### Testes

```bash
./gradlew :shared:allTests
```

## Build do framework iOS

O `shared` gera frameworks estáticos para `iosX64`, `iosArm64` e `iosSimulatorArm64` (baseName `shared`). O projeto iOS referencia via `project.pbxproj`.
