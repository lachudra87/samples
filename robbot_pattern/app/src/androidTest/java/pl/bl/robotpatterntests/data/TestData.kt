package pl.bl.robotpatterntests.data

import pl.bl.robotpatterntests.domain.model.Hero

val HERO_1 = Hero(1, "Nano-robot", "Created to serve the world")
val HERO_2 = Hero(2, "Dantooine hero", "Rides bantas like a charm")
val HERO_WITH_LONG_NAME_AND_DESCRIPTION = Hero(
    2,
    "Capitan Pazur with lack of chain command and no authority among oders",
    "He tries to do everything what he can to achive his goals. Working for young dynamic team somwhere in the middle of eastern europe. No strings attached, no additional fees and charges."
)

val HEROES = listOf(
    HERO_1,
    HERO_2,
    HERO_WITH_LONG_NAME_AND_DESCRIPTION
)