# O.F.Device (Ore Farming Device)

## Blocks

### OF Devices (ＯＦ装置)

- Fuel: Same as Furnace
- Energy: Same as fuel's burning time in ticks
- Farming costs: 10 seconds and 200 units of energy

#### OF Device (ＯＦ装置)

- Loot table of product
  - Cobblestone -> Stone, Coal Ore, Copper Ore, Iron Ore, Lapis Lazuli Ore
  - Cobbled Deepslate -> Deepslate, Deepslate Coal Ore, Deepslate Copper Ore, Deepslate Iron Ore, Deepslate Lapis Lazuli Ore

**Table 1:** Cobblestone (device y > 32)

| Block | Probability (%) |
| :--- | ---: |
| Stone | 92 |
| Coal Ore | 5 |
| Iron Ore | 3 |

**Table 2:** Cobblestone (device y ≤ 32)

| Block | Probability (%) |
| :--- | ---: |
| Stone | 91.6 |
| Coal Ore | 5 |
| Iron Ore | 3 |
| Lapis Lazuli Ore | 0.4 |

**Table 3:** [1.17+] Cobbled Deepslate (any device y)

| Block | Probability (%) |
| :--- | ---: |
| Deepslate | 91.6 |
| Deepslate Coal Ore | 5 |
| Deepslate Iron Ore | 3 |
| Deepslate Lapis Lazuli Ore | 0.4 |

#### OF Device Mod 1 (ＯＦ装置改)

- Loot table of product
  - Cobblestone -> Mk I result, Gold Ore, Redstone Ore, Diamond Ore
  - Cobbled Deepslate -> Mk I result, Deepslate Gold Ore, Deepslate Redstone Ore, Deepslate Diamond Ore
  - Netherrack -> Nether Brick, Nether Quartz Ore, Nether Gold Ore

**Table 4:** Cobblestone (device y > 32)

| Block | Probability (%) |
| :--- | ---: |
| Stone | 92 |
| Coal Ore | 5 |
| Iron Ore | 3 |

**Table 5:** Cobblestone (device y ≤ 32)

| Block | Probability (%) |
| :--- | ---: |
| Stone | 86.5 |
| Coal Ore | 5 |
| Redstone Ore | 4 |
| Iron Ore | 3 |
| Gold Ore | 0.6 |
| Diamond Ore | 0.5 |
| Lapis Lazuli Ore | 0.4 |

**Table 6:** [1.17+] Cobbled Deepslate (any device y)

| Block | Probability (%) |
| :--- | ---: |
| Deepslate | 86.5 |
| Deepslate Coal Ore | 5 |
| Deepslate Redstone Ore | 4 |
| Deepslate Iron Ore | 3 |
| Deepslate Gold Ore | 0.6 |
| Deepslate Diamond Ore | 0.5 |
| Deepslate Lapis Lazuli Ore | 0.4 |

**Table 7:** Netherrack (any device y)

| Block | Probability (%) |
| :--- | ---: |
| Nether Brick | 96 |
| Nether Quartz Ore | 3 |
| Nether Gold Ore | 1 |

#### OF Device Mod 2 (ＯＦ装置改二)

- Loot table of product
  - Cobblestone -> Mk II result, Emerald Ore
  - Cobbled Deepslate -> Mk II result, Deepslate Emerald Ore
  - Netherrack -> Mk II result, Ancient Debris

**Table 8:** Cobblestone (device y > 32)

| Block | Probability (%) |
| :--- | ---: |
| Stone | 91 |
| Coal Ore | 5 |
| Iron Ore | 3 |
| Gold Ore | 1 |

**Table 9:** Cobblestone (device y ≤ 32)

| Block | Probability (%) |
| :--- | ---: |
| Stone | 86.4 |
| Coal Ore | 5 |
| Redstone Ore | 4 |
| Iron Ore | 3 |
| Gold Ore | 0.6 |
| Diamond Ore | 0.5 |
| Lapis Lazuli Ore | 0.4 |
| Emerald Ore | 0.1 |

**Table 10:** [1.17+] Deepslate (any device y)

| Block | Probability (%) |
| :--- | ---: |
| Deepslate | 86.4 |
| Deepslate Coal Ore | 5 |
| Deepslate Redstone Ore | 4 |
| Deepslate Iron Ore | 3 |
| Deepslate Gold Ore | 0.6 |
| Deepslate Diamond Ore | 0.5 |
| Deepslate Lapis Lazuli Ore | 0.4 |
| Deepslate Emerald Ore | 0.1 |

**Table 11:** Netherrack (device y > 32)

| Block | Probability (%) |
| :--- | ---: |
| Nether Brick | 95.97 |
| Nether Quartz Ore | 3 |
| Nether Gold Ore | 1 |
| Ancient Debris | 0.03 |

**Table 12:** Netherrack (device y ≤ 32)

| Block | Probability (%) |
| :--- | ---: |
| Nether Brick | 95.9 |
| Nether Quartz Ore | 3 |
| Nether Gold Ore | 1 |
| Ancient Debris | 0.1 |

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
