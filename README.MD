![Header](./devsources/header.png)
[![Curseforge](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/curseforge_vector.svg)](https://www.curseforge.com/minecraft/mc-mods/guidebookapi) [![Modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/mod/guidebookapi) [![Discord](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/social/discord-plural_vector.svg)](https://discord.gg/vpkYUrB2RB)

WuidebookAPI, before called under the name "GuidebookAPI" ( Yes, only changed one letter- (😭) ) it's a mod API that allows the in-game creation of guides for minecraft mods.
The mod previously was just an update of the mod [GuideAPI](https://modrinth.com/mod/guide-api-legacy) developed by TehNut and Tombenpotter.

_Little note:_ The mod has **not** retro-compatibility with older versions of guides.

This mod is under the [MIT License](./LICENSE.MD)

## Most relevant features

- An own configuration system that allows the addition of custom configuration definitions and serializers by developers


- The creation of guides in-game


- Allows the registration custom page-content for the guides development

## Differences with older versions

A difference of WuidebookAPI with GuidebookAPI its not only the in-game creation of guides! The development of older guides of the mod its mostly in java-code, this mod register the Book Information of these in java and then this reads other files for the creation of content!

The old configuration systerm wasn't to different from the new, the principal difference it's the way the de/serializers does the de/serialization. The principal difference it's that developers can register custom serializers for their own custom configurations.

