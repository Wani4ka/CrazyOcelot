# CrazyOcelot
Ten podłącz Minecraftowy jest rozwiązaniem zadania przykładowego dla projektu DiamondWorld.
Jego autor nie ponosi żadnej odpowiedzialności za jego pracę i nie będzie utrzymał go w przyszłości.

Wymagana wersja serwera to [**1.13.2**](https://cdn.getbukkit.org/spigot/spigot-1.13.2.jar).

## Spis zadań
- [x] 1.  Po zabiciu zombie na jego miejscu pojawia się ocelot.
- [x] 2.  Nazwa ocelota składa się z 5 losowych znaków (liter różnej wielkości liter i cyfr).
- [x] 3.  Po zabiciu tego ocelota przez gracza do bazy danych wprowadzany jest zapis takiej treści:
  
    | Nick gracza    | Imię ocelotu | Czas zabijania |
    | :------------- | :----------- | :------------- |

    SQLite jest używany jako baza danych.
- [x] 4. Ocelot nie ucieka przed graczem, ale go atakuje.
- [ ] 5. Po zabiciu ocelota spada z niego jedna skórka, nad którą pojawi się nick gracza.
    Dla każdego patrzączego gracza nick widoczny nick jest swój. 
         Na przykład, jeżeli na skórką patrzy Wani4ka, on widzi "Wani4ka", a jeżeli NiaszMiasz99, to "NiaszMiasz99".