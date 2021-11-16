# O.F.Device (Ore Farming Device)

## Blocks

### OF Devices (ＯＦ装置)

- Fuel: Same as Furnace
- Energy: Same as fuel's burning time in ticks
- Farming costs: 10 seconds and 200 units of energy

#### OF Device (ＯＦ装置)

- Recipe
  - Crafting Table (# = Stone, F = Furnace, P = Piston, x = Stone Pickaxe): <br/># F # <br/># P # <br /># x #
- Loot table of product
  - Cobblestone -> Stone, Coal Ore, Copper Ore, Iron Ore, Lapis Lazuli Ore
  - Cobbled Deepslate -> Deepslate, Deepslate Coal Ore, Deepslate Copper Ore, Deepslate Iron Ore, Deepslate Lapis Lazuli Ore

**Table 1:** Cobblestone (device y > 32)

| Block | Probability (%) |
| --- | ---: |
| Stone | 92.00 |
| Coal Ore | 5.00 |
| Iron Ore | 3.00 |

**Table 2:** Cobblestone (device y ≤ 32)

| Block | Probability (%) |
| --- | ---: |
| Stone | 91.60 |
| Coal Ore | 5.00 |
| Iron Ore | 3.00 |
| Lapis Lazuli Ore | 0.40 |

**Table 3:** [1.17+] Cobbled Deepslate (any device y)

| Block | Probability (%) |
| --- | ---: |
| Deepslate | 91.60 |
| Deepslate Coal Ore | 5.00 |
| Deepslate Iron Ore | 3.00 |
| Deepslate Lapis Lazuli Ore | 0.40 |

#### OF Device Mod 1 (ＯＦ装置改)

- Recipe
  - Smithing Table: OF Device + Iron Pickaxe
- Loot table of product
  - Cobblestone -> Mk I result, Gold Ore, Redstone Ore, Diamond Ore
  - Cobbled Deepslate -> Mk I result, Deepslate Gold Ore, Deepslate Redstone Ore, Deepslate Diamond Ore
  - Netherrack -> Nether Brick, Nether Quartz Ore, Nether Gold Ore

**Table 4:** Cobblestone (device y > 32)

| Block | Probability (%) |
| --- | ---: |
| Stone | 92.00 |
| Coal Ore | 5.00 |
| Iron Ore | 3.00 |

**Table 5:** Cobblestone (device y ≤ 32)

| Block | Probability (%) |
| --- | ---: |
| Stone | 86.50 |
| Coal Ore | 5.00 |
| Redstone Ore | 4.00 |
| Iron Ore | 3.00 |
| Gold Ore | 0.60 |
| Diamond Ore | 0.50 |
| Lapis Lazuli Ore | 0.40 |

**Table 6:** [1.17+] Cobbled Deepslate (any device y)

| Block | Probability (%) |
| --- | ---: |
| Deepslate | 86.50 |
| Deepslate Coal Ore | 5.00 |
| Deepslate Redstone Ore | 4.00 |
| Deepslate Iron Ore | 3.00 |
| Deepslate Gold Ore | 0.60 |
| Deepslate Diamond Ore | 0.50 |
| Deepslate Lapis Lazuli Ore | 0.40 |

**Table 7:** Netherrack (any device y)

| Block | Probability (%) |
| --- | ---: |
| Nether Brick | 96.00 |
| Nether Quartz Ore | 3.00 |
| Nether Gold Ore | 1.00 |

#### OF Device Mod 2 (ＯＦ装置改二)

- Recipe
  - Smithing Table: OF Device Mod 1 + Diamond Pickaxe
- Loot table of product
  - Cobblestone -> Mk II result, Emerald Ore
  - Cobbled Deepslate -> Mk II result, Deepslate Emerald Ore
  - Netherrack -> Mk II result, Ancient Debris

**Table 8:** Cobblestone (device y > 32)

| Block | Probability (%) |
| --- | ---: |
| Stone | 91.00 |
| Coal Ore | 5.00 |
| Iron Ore | 3.00 |
| Gold Ore | 1.00 |

**Table 9:** Cobblestone (device y ≤ 32)

| Block | Probability (%) |
| --- | ---: |
| Stone | 86.40 |
| Coal Ore | 5.00 |
| Redstone Ore | 4.00 |
| Iron Ore | 3.00 |
| Gold Ore | 0.60 |
| Diamond Ore | 0.50 |
| Lapis Lazuli Ore | 0.40 |
| Emerald Ore | 0.10 |

**Table 10:** [1.17+] Deepslate (any device y)

| Block | Probability (%) |
| --- | ---: |
| Deepslate | 86.40 |
| Deepslate Coal Ore | 5.00 |
| Deepslate Redstone Ore | 4.00 |
| Deepslate Iron Ore | 3.00 |
| Deepslate Gold Ore | 0.60 |
| Deepslate Diamond Ore | 0.50 |
| Deepslate Lapis Lazuli Ore | 0.40 |
| Deepslate Emerald Ore | 0.10 |

**Table 11:** Netherrack (device y > 32)

| Block | Probability (%) |
| --- | ---: |
| Nether Brick | 95.97 |
| Nether Quartz Ore | 3.00 |
| Nether Gold Ore | 1.00 |
| Ancient Debris | 0.03 |

**Table 12:** Netherrack (device y ≤ 32)

| Block | Probability (%) |
| --- | ---: |
| Nether Brick | 95.90 |
| Nether Quartz Ore | 3.00 |
| Nether Gold Ore | 1.00 |
| Ancient Debris | 0.10 |

## Item

### OF Pickaxe (ＯＦツルハシ)

- Energy: Max 2560
- Recharging: Using energy of OF Device
- Recharging rate: 1 unit of pickaxe energy every 10 units of device energy
- Loot table of product
  - Stone, Deepslate -> Coal, Raw Copper, Raw Iron, Lapis Lazuli, Raw Gold, Redstone, Diamond, Emerald
  - Netherrack -> Nether Quartz, Gold Nugget

### OF Cobblestone Maker

- When this item is placed in the ingredient slot of an OF device, it will be smelted like Cobblestone without being consumed
