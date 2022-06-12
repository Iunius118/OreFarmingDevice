# O.F.Device

O.F.Device is a Minecraft mod to add devices for farming ores.

材料と燃料を消費して一定確率で鉱石を生成できる装置を追加するmodです。

## Download

[![O.F.Device (Ore Farming Device) - CurseForge](./docs/media/o-f-device_cf_badge.svg)](https://www.curseforge.com/minecraft/mc-mods/o-f-device)

## Description

Version 1.18-0.3.0

### OF Device (ＯＦ装置)

#### Recipe

<img src="docs/media/recipe_device_0_v010.png" title="Crafting recipe: OF Device">

4 Stones + Furnace + Lever + 2 Iron Ingots + Stone Pickaxe

#### GUI

<img src="docs/media/gui_device_0_v010.png" title="GUI: OF Device" alt="The upper right slot for materials, the left slot for fuel, and the bottom right slot for products">

1. Material slot (材料). Only Cobblestone, Cobbled Deepslate, Netherrack, or OF Cobblestone Feeder (丸石・深層岩の丸石・ネザーラック・ＯＦ丸石供給装置のみ)
2. Fuel slot (燃料)
3. Product slot (生成物)

If the output slot is full during operation, the products are ejected out of the block. Hopper can be used to push materials and fuel into device, and to pull products from device.

装置の上面・側面・下面にホッパーを接続してアイテムの搬入出が可能（それぞれ材料搬入・燃料搬入・生成物搬出）。特に生成物スロットのアイテムは自動的に排出されることがあるため、下面へのホッパー設置を強く推奨。

#### Products

|               |       Cobblestone        |                 Cobbled Deepslate                  |    Netherrack     |
|:-------------:|:------------------------:|:--------------------------------------------------:|:-----------------:|
| **Majority**  |          Stone           |                     Deepslate                      |   Nether Brick    |
| **Uncommon**  |         Coal Ore         |                                                    |                   |
|   **Rare**    | Iron Ore, <br>Copper Ore |                 Deepslate Iron Ore                 | Nether Quartz Ore |
| **Very Rare** |                          |                Deepslate Copper Ore                |  Nether Gold Ore  |
| **Legendary** |     Lapis Lazuli Ore     | Deepslate Lapis Lazuli Ore, <br>Deepslate Coal Ore |                   |

### OF Device Mod 1 (ＯＦ装置改)

#### Recipe

<img src="docs/media/recipe_device_1_v020.png" title="Crafting recipe: OF Device Mod 1">
<img src="docs/media/recipe_device_1_v010.png" title="Smithing recipe: OF Device Mod 1">

OF Device + Iron Pickaxe

#### Products

|               |          Cobblestone           |                            Cobbled Deepslate                            |    Netherrack     |
|:-------------:|:------------------------------:|:-----------------------------------------------------------------------:|:-----------------:|
| **Majority**  |             Stone              |                                Deepslate                                |   Nether Brick    |
| **Uncommon**  |            Coal Ore            |                                                                         |                   |
|   **Rare**    |    Iron Ore, <br>Copper Ore    |             Deepslate Redstone Ore, <br>Deepslate Iron Ore              | Nether Quartz Ore |
| **Very Rare** |          Redstone Ore          | Deepslate Copper Ore, <br>Deepslate Gold Ore, <br>Deepslate Diamond Ore |  Nether Gold Ore  |
| **Legendary** | Lapis Lazuli Ore, <br>Gold Ore |           Deepslate Lapis Lazuli Ore, <br>Deepslate Coal Ore            |                   |
|  **Mythic**   |          Diamond Ore           |                                                                         |                   |

### OF Device Mod 2 (ＯＦ装置改二)

#### Recipe

<img src="docs/media/recipe_device_2_v020.png" title="Crafting recipe: OF Device Mod 2">
<img src="docs/media/recipe_device_2_v010.png" title="Smithing recipe: OF Device Mod 2">

OF Device Mod 1 + Diamond Pickaxe

#### Products

|               |            Cobblestone            |                            Cobbled Deepslate                             |    Netherrack     |
|:-------------:|:---------------------------------:|:------------------------------------------------------------------------:|:-----------------:|
| **Majority**  |               Stone               |                                Deepslate                                 |   Nether Brick    |
| **Uncommon**  |     Coal Ore, <br>Copper Ore      |                                                                          |                   |
|   **Rare**    |      Iron Ore, <br>Gold Ore       | Deepslate Redstone Ore, <br>Deepslate Copper Ore, <br>Deepslate Iron Ore | Nether Quartz Ore |
| **Very Rare** |           Redstone Ore            |              Deepslate Gold Ore, <br>Deepslate Diamond Ore               |  Nether Gold Ore  |
| **Legendary** | Lapis Lazuli Ore, <br>Emerald Ore |            Deepslate Lapis Lazuli Ore, <br>Deepslate Coal Ore            |                   |
|  **Mythic**   |            Diamond Ore            |                          Deepslate Emerald Ore                           |  Ancient Debris   |

### OF Cobblestone Feeder (ＯＦ丸石供給装置)

<img src="docs/media/gui_cobblestone_feeder_v030.png" title="GUI: Using OF Cobblestone Feeder" alt="Place this item in material slot of OF device">

OF Cobblestone Feeder provides an infinite supply of Cobblestones to OF Devices.

ＯＦ装置の材料スロットに入れると装置へ無限に丸石を供給する。

#### Recipe

<img src="docs/media/recipe_cobblestone_feeder_v030.png" title="Crafting recipe: OF Cobblestone Feeder">

Lava Bucket + Redstone Repeater + Piston + Stone Pickaxe + Water Bucket

## License

- MIT license

----
Copyright © 2021 Iunius118
