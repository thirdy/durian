var dataSet = [
  [
    "magic",
    "attributes.txt",
    "rarity\u003dmagic"
  ],
  [
    "rint",
    "attributes.txt",
    "rint_min\u003d1"
  ],
  [
    "(\\d+)(es|energyshield)",
    "attributes.txt",
    "shield_min\u003d$GROUP1"
  ],
  [
    "rstr",
    "attributes.txt",
    "rstr_min\u003d1"
  ],
  [
    "rdex",
    "attributes.txt",
    "rdex_min\u003d1"
  ],
  [
    "identified",
    "attributes.txt",
    "identified\u003d1"
  ],
  [
    "(\\d+)rdex",
    "attributes.txt",
    "rdex_max\u003d$GROUP1"
  ],
  [
    "rare",
    "attributes.txt",
    "rarity\u003drare"
  ],
  [
    "notcrafted",
    "attributes.txt",
    "crafted\u003d0"
  ],
  [
    "(\\d+)(ar|armour)",
    "attributes.txt",
    "armour_min\u003d$GROUP1"
  ],
  [
    "(\\d+)dps",
    "attributes.txt",
    "dps_min\u003d$GROUP1"
  ],
  [
    "unid|unidentified",
    "attributes.txt",
    "identified\u003d0"
  ],
  [
    "(([0-9]+)?(\\.)?([0-9]{1,2})?)aps",
    "attributes.txt",
    "aps_min\u003d$GROUP1"
  ],
  [
    "normal",
    "attributes.txt",
    "rarity\u003dnormal"
  ],
  [
    "ar|armour",
    "attributes.txt",
    "armour_min\u003d1"
  ],
  [
    "lvl(\\d+)",
    "attributes.txt",
    "rlevel_max\u003d$GROUP1"
  ],
  [
    "q(\\d{1,2})",
    "attributes.txt",
    "q_min\u003d$GROUP1"
  ],
  [
    "(\\d+)pdps",
    "attributes.txt",
    "pdps_min\u003d$GROUP1"
  ],
  [
    "crafted",
    "attributes.txt",
    "crafted\u003d1"
  ],
  [
    "(\\d+)(blk|block)",
    "attributes.txt",
    "block_min\u003d$GROUP1"
  ],
  [
    "(\\d+)rstr",
    "attributes.txt",
    "rstr_max\u003d$GROUP1"
  ],
  [
    "ev|evasion",
    "attributes.txt",
    "evasion_min\u003d1"
  ],
  [
    "(\\d+)edps",
    "attributes.txt",
    "edps_min\u003d$GROUP1"
  ],
  [
    "(\\d+)rint",
    "attributes.txt",
    "rint_max\u003d$GROUP1"
  ],
  [
    "(\\d+)(ev|evasion)",
    "attributes.txt",
    "evasion_min\u003d$GROUP1"
  ],
  [
    "q(\\d{1,2})-(\\d{1,2})",
    "attributes.txt",
    "q_min\u003d$GROUP1\u0026q_max\u003d$GROUP2"
  ],
  [
    "altart",
    "attributes.txt",
    "altart\u003dx"
  ],
  [
    "es|energyshield",
    "attributes.txt",
    "shield_min\u003d1"
  ],
  [
    "(\\d+)crit",
    "attributes.txt",
    "crit_min\u003d$GROUP1"
  ],
  [
    "unique",
    "attributes.txt",
    "rarity\u003dunique"
  ],
  [
    "corrupt|corrupted",
    "attributes.txt",
    "corrupted\u003d1"
  ],
  [
    "(\\d{1,2})(ex|exalted)",
    "buyout.txt",
    "buyout_currency\u003dexalted\u0026buyout_max\u003d$GROUP1"
  ],
  [
    "(\\d{1,2})-(\\d{1,2})(ch|chaos)",
    "buyout.txt",
    "buyout_currency\u003dchaos\u0026buyout_min\u003d$GROUP1\u0026buyout_max\u003d$GROUP2"
  ],
  [
    "ge(\\d{1,2})(alt|alteration)",
    "buyout.txt",
    "buyout_currency\u003dalteration\u0026buyout_min\u003d$GROUP1"
  ],
  [
    "(\\d{1,2})(ch|chaos)",
    "buyout.txt",
    "buyout_currency\u003dchaos\u0026buyout_max\u003d$GROUP1"
  ],
  [
    "ge(\\d{1,2})(ch|chaos)",
    "buyout.txt",
    "buyout_currency\u003dchaos\u0026buyout_min\u003d$GROUP1"
  ],
  [
    "ge(\\d{1,2})(ex|exalted)",
    "buyout.txt",
    "buyout_currency\u003dexalted\u0026buyout_min\u003d$GROUP1"
  ],
  [
    "(\\d{1,2})(alc|alchemy)",
    "buyout.txt",
    "buyout_currency\u003dalchemy\u0026buyout_max\u003d$GROUP1"
  ],
  [
    "(\\d{1,2})(fuse|fusing)",
    "buyout.txt",
    "buyout_currency\u003dfusing\u0026buyout_max\u003d$GROUP1"
  ],
  [
    "(\\d{1,2})-(\\d{1,2})(alt|alteration)",
    "buyout.txt",
    "buyout_currency\u003dalteration\u0026buyout_min\u003d$GROUP1\u0026buyout_max\u003d$GROUP2"
  ],
  [
    "(\\d{1,2})(alt|alteration)",
    "buyout.txt",
    "buyout_currency\u003dalteration\u0026buyout_max\u003d$GROUP1"
  ],
  [
    "(\\d{1,2})-(\\d{1,2})(fuse|fusing)",
    "buyout.txt",
    "buyout_currency\u003dfusing\u0026buyout_min\u003d$GROUP1\u0026buyout_max\u003d$GROUP2"
  ],
  [
    "ge(\\d{1,2})(fuse|fusing)",
    "buyout.txt",
    "buyout_currency\u003dfusing\u0026buyout_min\u003d$GROUP1"
  ],
  [
    "(\\d{1,2})-(\\d{1,2})(ex|exalted)",
    "buyout.txt",
    "buyout_currency\u003dexalted\u0026buyout_min\u003d$GROUP1\u0026buyout_max\u003d$GROUP2"
  ],
  [
    "(\\d{1,2})-(\\d{1,2})(alc|alchemy)",
    "buyout.txt",
    "buyout_currency\u003dalchemy\u0026buyout_min\u003d$GROUP1\u0026buyout_max\u003d$GROUP2"
  ],
  [
    "ge(\\d{1,2})(alc|alchemy)",
    "buyout.txt",
    "buyout_currency\u003dalchemy\u0026buyout_min\u003d$GROUP1"
  ],
  [
    "Exalt|Ex|Exalted(Orb)?",
    "currencies.txt",
    "name\u003dExalted Orb"
  ],
  [
    "Glassblower\u0027?s?Bauble",
    "currencies.txt",
    "name\u003dGlassblower\u0027s Bauble"
  ],
  [
    "Eternal(Orb)?",
    "currencies.txt",
    "name\u003dEternal Orb"
  ],
  [
    "Transmute|Transmutation|Orb(of)?Transmutation",
    "currencies.txt",
    "name\u003dOrb of Transmutation"
  ],
  [
    "Orb(of)?Augmentation",
    "currencies.txt",
    "name\u003dOrb of Augmentation"
  ],
  [
    "AlterationShard",
    "currencies.txt",
    "name\u003dAlteration Shard"
  ],
  [
    "Whetstone|Blacksmith\u0027?s?Whetstone",
    "currencies.txt",
    "name\u003dBlacksmith\u0027s Whetstone"
  ],
  [
    "Scroll(of)?Wisdom",
    "currencies.txt",
    "name\u003dScroll of Wisdom"
  ],
  [
    "Divine(Orb)?",
    "currencies.txt",
    "name\u003dDivine Orb"
  ],
  [
    "Chromatic(Orb)?",
    "currencies.txt",
    "name\u003dChromatic Orb"
  ],
  [
    "Gemcutter\u0027?s?Prism",
    "currencies.txt",
    "name\u003dGemcutter\u0027s Prism"
  ],
  [
    "PortalScroll",
    "currencies.txt",
    "name\u003dPortal Scroll"
  ],
  [
    "AlchemyShard",
    "currencies.txt",
    "name\u003dAlchemy Shard"
  ],
  [
    "Vaal(Orb)?",
    "currencies.txt",
    "name\u003dVaal Orb"
  ],
  [
    "Mirror|Mirror(of)?Kalandra",
    "currencies.txt",
    "name\u003dMirror of Kalandra"
  ],
  [
    "TransmutationShard",
    "currencies.txt",
    "name\u003dTransmutation Shard"
  ],
  [
    "Alt|(Orb)?(of)?Alteration",
    "currencies.txt",
    "name\u003dOrb of Alteration"
  ],
  [
    "Ch|Chaos(Orb)?",
    "currencies.txt",
    "name\u003dChaos Orb"
  ],
  [
    "(Orb)?(of)?Regret",
    "currencies.txt",
    "name\u003dOrb of Regret"
  ],
  [
    "Regal(Orb)?",
    "currencies.txt",
    "name\u003dRegal Orb"
  ],
  [
    "ScrollFragment",
    "currencies.txt",
    "name\u003dScroll Fragment"
  ],
  [
    "Bless|Blessed(Orb)?",
    "currencies.txt",
    "name\u003dBlessed Orb"
  ],
  [
    "Chis|Cartographer\u0027?s?Chisel",
    "currencies.txt",
    "name\u003dCartographer\u0027s Chisel"
  ],
  [
    "Armourer\u0027?s?Scrap",
    "currencies.txt",
    "name\u003dArmourer\u0027s Scrap"
  ],
  [
    "Chance|Orb(of)?Chance",
    "currencies.txt",
    "name\u003dOrb of Chance"
  ],
  [
    "Fuse|Orb(of)?Fusing",
    "currencies.txt",
    "name\u003dOrb of Fusing"
  ],
  [
    "Alch|(Orb)?(of)?Alchemy",
    "currencies.txt",
    "name\u003dOrb of Alchemy"
  ],
  [
    "Jeweller\u0027?s?(Orb)?",
    "currencies.txt",
    "name\u003dJeweller\u0027s Orb"
  ],
  [
    "Scour|(Orb)?(of)?Scouring",
    "currencies.txt",
    "name\u003dOrb of Scouring"
  ],
  [
    "dualreslifedex",
    "explicitmodgroups.txt",
    "mod_name\u003d(pseudo) (total) +#% to Cold Resistance\u0026mod_min\u003d30\u0026mod_max\u003d\u0026mod_name\u003d(pseudo) (total) +#% to Fire Resistance\u0026mod_min\u003d30\u0026mod_max\u003d\u0026mod_name\u003d(pseudo) (total) +#% to Lightning Resistance\u0026mod_min\u003d30\u0026mod_max\u003d\u0026group_type\u003dCount\u0026group_min\u003d2\u0026group_max\u003d2\u0026group_count\u003d3\u0026mod_name\u003d(pseudo) (total) +# to maximum Life\u0026mod_min\u003d60\u0026mod_max\u003d\u0026mod_name\u003d(pseudo) (total) +# to Dexterity\u0026mod_min\u003d30\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d2"
  ],
  [
    "(\\d+)bowchaostrap",
    "explicitmodgroups.txt",
    "mod_name\u003d#% increased Area Damage\u0026mod_min\u003d\u0026mod_max\u003d\u0026mod_name\u003d#% increased Projectile Damage\u0026mod_min\u003d\u0026mod_max\u003d\u0026mod_name\u003d#% increased Chaos Damage\u0026mod_min\u003d\u0026mod_max\u003d\u0026mod_name\u003d#% increased Damage over Time\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dSum\u0026group_min\u003d$GROUP1\u0026group_max\u003d\u0026group_count\u003d4"
  ],
  [
    "(\\d+)-(\\d+)lightres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +#% to Lightning Resistance\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d$GROUP2\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)iir",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) #% increased Rarity of Items found\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)dex",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +# to Dexterity\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)lightres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +#% to Lightning Resistance\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "life",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +# to maximum Life\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)res",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) +#% total Resistance\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "iir",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) #% increased Rarity of Items found\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)fireres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +#% to Fire Resistance\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)(spelldmg|sdmg)",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) #% increased Spell Damage\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)eleres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) +#% total Elemental Resistance\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)str",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +# to Strength\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "res",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) +#% total Resistance\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)int",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +# to Intelligence\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "spelldmg|sdmg",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) #% increased Spell Damage\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)-(\\d+)ms",
    "explicitmods.txt",
    "mod_name\u003d#% increased Movement Speed\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d$GROUP2\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "ms",
    "explicitmods.txt",
    "mod_name\u003d#% increased Movement Speed\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)-(\\d+)life",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +# to maximum Life\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d$GROUP2\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)-(\\d+)(spelldmg|sdmg)",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) #% increased Spell Damage\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d$GROUP2\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "lightres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +#% to Lightning Resistance\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)coldres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +#% to Cold Resistance\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)-(\\d+)res",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) +#% total Resistance\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d$GROUP2\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)life",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +# to maximum Life\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "coldres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +#% to Cold Resistance\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)-(\\d+)coldres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +#% to Cold Resistance\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d$GROUP2\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)-(\\d+)fireres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +#% to Fire Resistance\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d$GROUP2\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "fireres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +#% to Fire Resistance\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)mana",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +# to maximum Mana\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "mana",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +# to maximum Mana\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)ms",
    "explicitmods.txt",
    "mod_name\u003d#% increased Movement Speed\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)-(\\d+)iir",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) #% increased Rarity of Items found\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d$GROUP2\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "eleres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) +#% total Elemental Resistance\u0026mod_min\u003d\u0026mod_max\u003d\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)-(\\d+)eleres",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) +#% total Elemental Resistance\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d$GROUP2\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "(\\d+)-(\\d+)mana",
    "explicitmods.txt",
    "mod_name\u003d(pseudo) (total) +# to maximum Mana\u0026mod_min\u003d$GROUP1\u0026mod_max\u003d$GROUP2\u0026group_type\u003dAnd\u0026group_min\u003d\u0026group_max\u003d\u0026group_count\u003d1"
  ],
  [
    "Frostbite",
    "gems.txt",
    "name\u003dFrostbite"
  ],
  [
    "BlockChanceReduction",
    "gems.txt",
    "name\u003dBlock Chance Reduction"
  ],
  [
    "LightningArrow",
    "gems.txt",
    "name\u003dLightning Arrow"
  ],
  [
    "Sweep",
    "gems.txt",
    "name\u003dSweep"
  ],
  [
    "LightningWarp",
    "gems.txt",
    "name\u003dLightning Warp"
  ],
  [
    "Enlighten",
    "gems.txt",
    "name\u003dEnlighten"
  ],
  [
    "VaalPowerSiphon",
    "gems.txt",
    "name\u003dVaal Power Siphon"
  ],
  [
    "AddedLightningDamage",
    "gems.txt",
    "name\u003dAdded Lightning Damage"
  ],
  [
    "DetonateMines",
    "gems.txt",
    "name\u003dDetonate Mines"
  ],
  [
    "MoltenStrike",
    "gems.txt",
    "name\u003dMolten Strike"
  ],
  [
    "WildStrike",
    "gems.txt",
    "name\u003dWild Strike"
  ],
  [
    "FlameTotem",
    "gems.txt",
    "name\u003dFlame Totem"
  ],
  [
    "KineticBlast",
    "gems.txt",
    "name\u003dKinetic Blast"
  ],
  [
    "SummonSkeletons",
    "gems.txt",
    "name\u003dSummon Skeletons"
  ],
  [
    "HeraldofThunder",
    "gems.txt",
    "name\u003dHerald of Thunder"
  ],
  [
    "LightningStrike",
    "gems.txt",
    "name\u003dLightning Strike"
  ],
  [
    "Anger",
    "gems.txt",
    "name\u003dAnger"
  ],
  [
    "Cleave",
    "gems.txt",
    "name\u003dCleave"
  ],
  [
    "MoltenShell",
    "gems.txt",
    "name\u003dMolten Shell"
  ],
  [
    "AddedColdDamage",
    "gems.txt",
    "name\u003dAdded Cold Damage"
  ],
  [
    "TrapandMineDamage",
    "gems.txt",
    "name\u003dTrap and Mine Damage"
  ],
  [
    "Vengeance",
    "gems.txt",
    "name\u003dVengeance"
  ],
  [
    "VaalSpectralThrow",
    "gems.txt",
    "name\u003dVaal Spectral Throw"
  ],
  [
    "Arc",
    "gems.txt",
    "name\u003dArc"
  ],
  [
    "WhirlingBlades",
    "gems.txt",
    "name\u003dWhirling Blades"
  ],
  [
    "CastonMeleeKill",
    "gems.txt",
    "name\u003dCast on Melee Kill"
  ],
  [
    "Knockback",
    "gems.txt",
    "name\u003dKnockback"
  ],
  [
    "SummonIceGolem",
    "gems.txt",
    "name\u003dSummon Ice Golem"
  ],
  [
    "Punishment",
    "gems.txt",
    "name\u003dPunishment"
  ],
  [
    "VaalGroundSlam",
    "gems.txt",
    "name\u003dVaal Ground Slam"
  ],
  [
    "ConcentratedEffect",
    "gems.txt",
    "name\u003dConcentrated Effect"
  ],
  [
    "SummonFlameGolem",
    "gems.txt",
    "name\u003dSummon Flame Golem"
  ],
  [
    "MinionLife",
    "gems.txt",
    "name\u003dMinion Life"
  ],
  [
    "IceBite",
    "gems.txt",
    "name\u003dIce Bite"
  ],
  [
    "IceShot",
    "gems.txt",
    "name\u003dIce Shot"
  ],
  [
    "ArcticBreath",
    "gems.txt",
    "name\u003dArctic Breath"
  ],
  [
    "Determination",
    "gems.txt",
    "name\u003dDetermination"
  ],
  [
    "SpellTotem",
    "gems.txt",
    "name\u003dSpell Totem"
  ],
  [
    "FireNovaMine",
    "gems.txt",
    "name\u003dFire Nova Mine"
  ],
  [
    "StaticStrike",
    "gems.txt",
    "name\u003dStatic Strike"
  ],
  [
    "Haste",
    "gems.txt",
    "name\u003dHaste"
  ],
  [
    "ElementalWeakness",
    "gems.txt",
    "name\u003dElemental Weakness"
  ],
  [
    "SiegeBallista",
    "gems.txt",
    "name\u003dSiege Ballista"
  ],
  [
    "ShieldCharge",
    "gems.txt",
    "name\u003dShield Charge"
  ],
  [
    "Portal",
    "gems.txt",
    "name\u003dPortal"
  ],
  [
    "VaalArc",
    "gems.txt",
    "name\u003dVaal Arc"
  ],
  [
    "BlinkArrow",
    "gems.txt",
    "name\u003dBlink Arrow"
  ],
  [
    "Hatred",
    "gems.txt",
    "name\u003dHatred"
  ],
  [
    "PurityofFire",
    "gems.txt",
    "name\u003dPurity of Fire"
  ],
  [
    "RallyingCry",
    "gems.txt",
    "name\u003dRallying Cry"
  ],
  [
    "Wither",
    "gems.txt",
    "name\u003dWither"
  ],
  [
    "FasterCasting",
    "gems.txt",
    "name\u003dFaster Casting"
  ],
  [
    "VaalCyclone",
    "gems.txt",
    "name\u003dVaal Cyclone"
  ],
  [
    "Conductivity",
    "gems.txt",
    "name\u003dConductivity"
  ],
  [
    "FirePenetration",
    "gems.txt",
    "name\u003dFire Penetration"
  ],
  [
    "LeapSlam",
    "gems.txt",
    "name\u003dLeap Slam"
  ],
  [
    "DecoyTotem",
    "gems.txt",
    "name\u003dDecoy Totem"
  ],
  [
    "LightningTendrils",
    "gems.txt",
    "name\u003dLightning Tendrils"
  ],
  [
    "GlacialHammer",
    "gems.txt",
    "name\u003dGlacial Hammer"
  ],
  [
    "StormCall",
    "gems.txt",
    "name\u003dStorm Call"
  ],
  [
    "Enfeeble",
    "gems.txt",
    "name\u003dEnfeeble"
  ],
  [
    "BurningArrow",
    "gems.txt",
    "name\u003dBurning Arrow"
  ],
  [
    "ItemQuantity",
    "gems.txt",
    "name\u003dItem Quantity"
  ],
  [
    "PowerChargeOnCritical",
    "gems.txt",
    "name\u003dPower Charge On Critical"
  ],
  [
    "Spark",
    "gems.txt",
    "name\u003dSpark"
  ],
  [
    "CastonDeath",
    "gems.txt",
    "name\u003dCast on Death"
  ],
  [
    "BallLightning",
    "gems.txt",
    "name\u003dBall Lightning"
  ],
  [
    "Riposte",
    "gems.txt",
    "name\u003dRiposte"
  ],
  [
    "BoneOffering",
    "gems.txt",
    "name\u003dBone Offering"
  ],
  [
    "SummonRagingSpirit",
    "gems.txt",
    "name\u003dSummon Raging Spirit"
  ],
  [
    "InfernalBlow",
    "gems.txt",
    "name\u003dInfernal Blow"
  ],
  [
    "ArcticArmour",
    "gems.txt",
    "name\u003dArctic Armour"
  ],
  [
    "EnduringCry",
    "gems.txt",
    "name\u003dEnduring Cry"
  ],
  [
    "AnimateGuardian",
    "gems.txt",
    "name\u003dAnimate Guardian"
  ],
  [
    "VaalSummonSkeletons",
    "gems.txt",
    "name\u003dVaal Summon Skeletons"
  ],
  [
    "VaalDetonateDead",
    "gems.txt",
    "name\u003dVaal Detonate Dead"
  ],
  [
    "Multistrike",
    "gems.txt",
    "name\u003dMultistrike"
  ],
  [
    "VaalImmortalCall",
    "gems.txt",
    "name\u003dVaal Immortal Call"
  ],
  [
    "GroundSlam",
    "gems.txt",
    "name\u003dGround Slam"
  ],
  [
    "TemporalChains",
    "gems.txt",
    "name\u003dTemporal Chains"
  ],
  [
    "Barrage",
    "gems.txt",
    "name\u003dBarrage"
  ],
  [
    "BloodMagic",
    "gems.txt",
    "name\u003dBlood Magic"
  ],
  [
    "LightningTrap",
    "gems.txt",
    "name\u003dLightning Trap"
  ],
  [
    "MirrorArrow",
    "gems.txt",
    "name\u003dMirror Arrow"
  ],
  [
    "FasterAttacks",
    "gems.txt",
    "name\u003dFaster Attacks"
  ],
  [
    "DominatingBlow",
    "gems.txt",
    "name\u003dDominating Blow"
  ],
  [
    "SpellEcho",
    "gems.txt",
    "name\u003dSpell Echo"
  ],
  [
    "MinionSpeed",
    "gems.txt",
    "name\u003dMinion Speed"
  ],
  [
    "VaalReave",
    "gems.txt",
    "name\u003dVaal Reave"
  ],
  [
    "AddedChaosDamage",
    "gems.txt",
    "name\u003dAdded Chaos Damage"
  ],
  [
    "FreezeMine",
    "gems.txt",
    "name\u003dFreeze Mine"
  ],
  [
    "MinionandTotemElementalResistance",
    "gems.txt",
    "name\u003dMinion and Totem Elemental Resistance"
  ],
  [
    "IncreasedDuration",
    "gems.txt",
    "name\u003dIncreased Duration"
  ],
  [
    "PhysicaltoLightning",
    "gems.txt",
    "name\u003dPhysical to Lightning"
  ],
  [
    "ShockwaveTotem",
    "gems.txt",
    "name\u003dShockwave Totem"
  ],
  [
    "VaalLightningWarp",
    "gems.txt",
    "name\u003dVaal Lightning Warp"
  ],
  [
    "Assassin\u0027?sMark",
    "gems.txt",
    "name\u003dAssassin\u0027s Mark"
  ],
  [
    "Hypothermia",
    "gems.txt",
    "name\u003dHypothermia"
  ],
  [
    "MagmaOrb",
    "gems.txt",
    "name\u003dMagma Orb"
  ],
  [
    "ConversionTrap",
    "gems.txt",
    "name\u003dConversion Trap"
  ],
  [
    "FasterProjectiles",
    "gems.txt",
    "name\u003dFaster Projectiles"
  ],
  [
    "Fork",
    "gems.txt",
    "name\u003dFork"
  ],
  [
    "VaalFireball",
    "gems.txt",
    "name\u003dVaal Fireball"
  ],
  [
    "PurityofIce",
    "gems.txt",
    "name\u003dPurity of Ice"
  ],
  [
    "RapidDecay",
    "gems.txt",
    "name\u003dRapid Decay"
  ],
  [
    "ViperStrike",
    "gems.txt",
    "name\u003dViper Strike"
  ],
  [
    "CastwhenStunned",
    "gems.txt",
    "name\u003dCast when Stunned"
  ],
  [
    "Vitality",
    "gems.txt",
    "name\u003dVitality"
  ],
  [
    "MultipleTraps",
    "gems.txt",
    "name\u003dMultiple Traps"
  ],
  [
    "CurseonHit",
    "gems.txt",
    "name\u003dCurse on Hit"
  ],
  [
    "VaalBurningArrow",
    "gems.txt",
    "name\u003dVaal Burning Arrow"
  ],
  [
    "PhaseRun",
    "gems.txt",
    "name\u003dPhase Run"
  ],
  [
    "Blind",
    "gems.txt",
    "name\u003dBlind"
  ],
  [
    "ReducedMana",
    "gems.txt",
    "name\u003dReduced Mana"
  ],
  [
    "Contagion",
    "gems.txt",
    "name\u003dContagion"
  ],
  [
    "IncreasedCriticalStrikes",
    "gems.txt",
    "name\u003dIncreased Critical Strikes"
  ],
  [
    "FireTrap",
    "gems.txt",
    "name\u003dFire Trap"
  ],
  [
    "Cyclone",
    "gems.txt",
    "name\u003dCyclone"
  ],
  [
    "Fortify",
    "gems.txt",
    "name\u003dFortify"
  ],
  [
    "VaalGlacialHammer",
    "gems.txt",
    "name\u003dVaal Glacial Hammer"
  ],
  [
    "ManaLeech",
    "gems.txt",
    "name\u003dMana Leech"
  ],
  [
    "GreaterMultipleProjectiles",
    "gems.txt",
    "name\u003dGreater Multiple Projectiles"
  ],
  [
    "MeleePhysicalDamage",
    "gems.txt",
    "name\u003dMelee Physical Damage"
  ],
  [
    "SummonChaosGolem",
    "gems.txt",
    "name\u003dSummon Chaos Golem"
  ],
  [
    "RejuvenationTotem",
    "gems.txt",
    "name\u003dRejuvenation Totem"
  ],
  [
    "Flammability",
    "gems.txt",
    "name\u003dFlammability"
  ],
  [
    "Convocation",
    "gems.txt",
    "name\u003dConvocation"
  ],
  [
    "PurityofLightning",
    "gems.txt",
    "name\u003dPurity of Lightning"
  ],
  [
    "Puncture",
    "gems.txt",
    "name\u003dPuncture"
  ],
  [
    "VigilantStrike",
    "gems.txt",
    "name\u003dVigilant Strike"
  ],
  [
    "WeaponElementalDamage",
    "gems.txt",
    "name\u003dWeapon Elemental Damage"
  ],
  [
    "Stun",
    "gems.txt",
    "name\u003dStun"
  ],
  [
    "SearingBond",
    "gems.txt",
    "name\u003dSearing Bond"
  ],
  [
    "AdditionalAccuracy",
    "gems.txt",
    "name\u003dAdditional Accuracy"
  ],
  [
    "RemoteMine",
    "gems.txt",
    "name\u003dRemote Mine"
  ],
  [
    "GlacialCascade",
    "gems.txt",
    "name\u003dGlacial Cascade"
  ],
  [
    "IronGrip",
    "gems.txt",
    "name\u003dIron Grip"
  ],
  [
    "VaalHaste",
    "gems.txt",
    "name\u003dVaal Haste"
  ],
  [
    "EnduranceChargeonMeleeStun",
    "gems.txt",
    "name\u003dEndurance Charge on Melee Stun"
  ],
  [
    "Bloodlust",
    "gems.txt",
    "name\u003dBloodlust"
  ],
  [
    "Pierce",
    "gems.txt",
    "name\u003dPierce"
  ],
  [
    "Fireball",
    "gems.txt",
    "name\u003dFireball"
  ],
  [
    "RaiseZombie",
    "gems.txt",
    "name\u003dRaise Zombie"
  ],
  [
    "Incinerate",
    "gems.txt",
    "name\u003dIncinerate"
  ],
  [
    "FrostWall",
    "gems.txt",
    "name\u003dFrost Wall"
  ],
  [
    "HeraldofIce",
    "gems.txt",
    "name\u003dHerald of Ice"
  ],
  [
    "DualStrike",
    "gems.txt",
    "name\u003dDual Strike"
  ],
  [
    "PoisonArrow",
    "gems.txt",
    "name\u003dPoison Arrow"
  ],
  [
    "PurityofElements",
    "gems.txt",
    "name\u003dPurity of Elements"
  ],
  [
    "Enhance",
    "gems.txt",
    "name\u003dEnhance"
  ],
  [
    "ShrapnelShot",
    "gems.txt",
    "name\u003dShrapnel Shot"
  ],
  [
    "VaalRighteousFire",
    "gems.txt",
    "name\u003dVaal Righteous Fire"
  ],
  [
    "EtherealKnives",
    "gems.txt",
    "name\u003dEthereal Knives"
  ],
  [
    "DoubleStrike",
    "gems.txt",
    "name\u003dDouble Strike"
  ],
  [
    "HeavyStrike",
    "gems.txt",
    "name\u003dHeavy Strike"
  ],
  [
    "BlastRain",
    "gems.txt",
    "name\u003dBlast Rain"
  ],
  [
    "VaalMoltenShell",
    "gems.txt",
    "name\u003dVaal Molten Shell"
  ],
  [
    "MeleeDamageonFullLife",
    "gems.txt",
    "name\u003dMelee Damage on Full Life"
  ],
  [
    "FleshOffering",
    "gems.txt",
    "name\u003dFlesh Offering"
  ],
  [
    "SlowerProjectiles",
    "gems.txt",
    "name\u003dSlower Projectiles"
  ],
  [
    "FreezingPulse",
    "gems.txt",
    "name\u003dFreezing Pulse"
  ],
  [
    "Firestorm",
    "gems.txt",
    "name\u003dFirestorm"
  ],
  [
    "Reave",
    "gems.txt",
    "name\u003dReave"
  ],
  [
    "RainofArrows",
    "gems.txt",
    "name\u003dRain of Arrows"
  ],
  [
    "Bladefall",
    "gems.txt",
    "name\u003dBladefall"
  ],
  [
    "HeraldofAsh",
    "gems.txt",
    "name\u003dHerald of Ash"
  ],
  [
    "PhysicalProjectileAttackDamage",
    "gems.txt",
    "name\u003dPhysical Projectile Attack Damage"
  ],
  [
    "ProjectileWeakness",
    "gems.txt",
    "name\u003dProjectile Weakness"
  ],
  [
    "DetonateDead",
    "gems.txt",
    "name\u003dDetonate Dead"
  ],
  [
    "Reckoning",
    "gems.txt",
    "name\u003dReckoning"
  ],
  [
    "FrostBlades",
    "gems.txt",
    "name\u003dFrost Blades"
  ],
  [
    "VaalLightningStrike",
    "gems.txt",
    "name\u003dVaal Lightning Strike"
  ],
  [
    "Chain",
    "gems.txt",
    "name\u003dChain"
  ],
  [
    "Generosity",
    "gems.txt",
    "name\u003dGenerosity"
  ],
  [
    "CullingStrike",
    "gems.txt",
    "name\u003dCulling Strike"
  ],
  [
    "Blasphemy",
    "gems.txt",
    "name\u003dBlasphemy"
  ],
  [
    "Wrath",
    "gems.txt",
    "name\u003dWrath"
  ],
  [
    "VaalFlameblast",
    "gems.txt",
    "name\u003dVaal Flameblast"
  ],
  [
    "LesserMultipleProjectiles",
    "gems.txt",
    "name\u003dLesser Multiple Projectiles"
  ],
  [
    "VaalClarity",
    "gems.txt",
    "name\u003dVaal Clarity"
  ],
  [
    "Empower",
    "gems.txt",
    "name\u003dEmpower"
  ],
  [
    "ElementalProliferation",
    "gems.txt",
    "name\u003dElemental Proliferation"
  ],
  [
    "ColdtoFire",
    "gems.txt",
    "name\u003dCold to Fire"
  ],
  [
    "ColdSnap",
    "gems.txt",
    "name\u003dCold Snap"
  ],
  [
    "MinionDamage",
    "gems.txt",
    "name\u003dMinion Damage"
  ],
  [
    "RighteousFire",
    "gems.txt",
    "name\u003dRighteous Fire"
  ],
  [
    "RaiseSpectre",
    "gems.txt",
    "name\u003dRaise Spectre"
  ],
  [
    "VaalRainofArrows",
    "gems.txt",
    "name\u003dVaal Rain of Arrows"
  ],
  [
    "IncreasedAreaofEffect",
    "gems.txt",
    "name\u003dIncreased Area of Effect"
  ],
  [
    "PowerSiphon",
    "gems.txt",
    "name\u003dPower Siphon"
  ],
  [
    "Trap",
    "gems.txt",
    "name\u003dTrap"
  ],
  [
    "PointBlank",
    "gems.txt",
    "name\u003dPoint Blank"
  ],
  [
    "SmokeMine",
    "gems.txt",
    "name\u003dSmoke Mine"
  ],
  [
    "Discharge",
    "gems.txt",
    "name\u003dDischarge"
  ],
  [
    "IceSpear",
    "gems.txt",
    "name\u003dIce Spear"
  ],
  [
    "VaalDiscipline",
    "gems.txt",
    "name\u003dVaal Discipline"
  ],
  [
    "BladeVortex",
    "gems.txt",
    "name\u003dBlade Vortex"
  ],
  [
    "Warlord\u0027?sMark",
    "gems.txt",
    "name\u003dWarlord\u0027s Mark"
  ],
  [
    "IceNova",
    "gems.txt",
    "name\u003dIce Nova"
  ],
  [
    "Poacher\u0027?sMark",
    "gems.txt",
    "name\u003dPoacher\u0027s Mark"
  ],
  [
    "Frenzy",
    "gems.txt",
    "name\u003dFrenzy"
  ],
  [
    "AbyssalCry",
    "gems.txt",
    "name\u003dAbyssal Cry"
  ],
  [
    "FlickerStrike",
    "gems.txt",
    "name\u003dFlicker Strike"
  ],
  [
    "IceCrash",
    "gems.txt",
    "name\u003dIce Crash"
  ],
  [
    "Desecrate",
    "gems.txt",
    "name\u003dDesecrate"
  ],
  [
    "Grace",
    "gems.txt",
    "name\u003dGrace"
  ],
  [
    "IronWill",
    "gems.txt",
    "name\u003dIron Will"
  ],
  [
    "TempestShield",
    "gems.txt",
    "name\u003dTempest Shield"
  ],
  [
    "CastwhenDamageTaken",
    "gems.txt",
    "name\u003dCast when Damage Taken"
  ],
  [
    "AddedFireDamage",
    "gems.txt",
    "name\u003dAdded Fire Damage"
  ],
  [
    "TornadoShot",
    "gems.txt",
    "name\u003dTornado Shot"
  ],
  [
    "MeleeSplash",
    "gems.txt",
    "name\u003dMelee Splash"
  ],
  [
    "AnimateWeapon",
    "gems.txt",
    "name\u003dAnimate Weapon"
  ],
  [
    "LightningPenetration",
    "gems.txt",
    "name\u003dLightning Penetration"
  ],
  [
    "VaalColdSnap",
    "gems.txt",
    "name\u003dVaal Cold Snap"
  ],
  [
    "RangedAttackTotem",
    "gems.txt",
    "name\u003dRanged Attack Totem"
  ],
  [
    "EssenceDrain",
    "gems.txt",
    "name\u003dEssence Drain"
  ],
  [
    "Innervate",
    "gems.txt",
    "name\u003dInnervate"
  ],
  [
    "VaalIceNova",
    "gems.txt",
    "name\u003dVaal Ice Nova"
  ],
  [
    "VaalDoubleStrike",
    "gems.txt",
    "name\u003dVaal Double Strike"
  ],
  [
    "DevouringTotem",
    "gems.txt",
    "name\u003dDevouring Totem"
  ],
  [
    "LifeLeech",
    "gems.txt",
    "name\u003dLife Leech"
  ],
  [
    "Vulnerability",
    "gems.txt",
    "name\u003dVulnerability"
  ],
  [
    "IncreasedCriticalDamage",
    "gems.txt",
    "name\u003dIncreased Critical Damage"
  ],
  [
    "CastOnCriticalStrike",
    "gems.txt",
    "name\u003dCast On Critical Strike"
  ],
  [
    "FlameSurge",
    "gems.txt",
    "name\u003dFlame Surge"
  ],
  [
    "ElementalHit",
    "gems.txt",
    "name\u003dElemental Hit"
  ],
  [
    "ChancetoIgnite",
    "gems.txt",
    "name\u003dChance to Ignite"
  ],
  [
    "SpectralThrow",
    "gems.txt",
    "name\u003dSpectral Throw"
  ],
  [
    "ShockNova",
    "gems.txt",
    "name\u003dShock Nova"
  ],
  [
    "LessDuration",
    "gems.txt",
    "name\u003dLess Duration"
  ],
  [
    "IncreasedBurningDamage",
    "gems.txt",
    "name\u003dIncreased Burning Damage"
  ],
  [
    "LifeGainonHit",
    "gems.txt",
    "name\u003dLife Gain on Hit"
  ],
  [
    "SplitArrow",
    "gems.txt",
    "name\u003dSplit Arrow"
  ],
  [
    "BloodRage",
    "gems.txt",
    "name\u003dBlood Rage"
  ],
  [
    "ControlledDestruction",
    "gems.txt",
    "name\u003dControlled Destruction"
  ],
  [
    "VaalGrace",
    "gems.txt",
    "name\u003dVaal Grace"
  ],
  [
    "VaalSpark",
    "gems.txt",
    "name\u003dVaal Spark"
  ],
  [
    "Discipline",
    "gems.txt",
    "name\u003dDiscipline"
  ],
  [
    "ExplosiveArrow",
    "gems.txt",
    "name\u003dExplosive Arrow"
  ],
  [
    "VaalLightningTrap",
    "gems.txt",
    "name\u003dVaal Lightning Trap"
  ],
  [
    "VoidManipulation",
    "gems.txt",
    "name\u003dVoid Manipulation"
  ],
  [
    "FlameDash",
    "gems.txt",
    "name\u003dFlame Dash"
  ],
  [
    "Clarity",
    "gems.txt",
    "name\u003dClarity"
  ],
  [
    "ChancetoFlee",
    "gems.txt",
    "name\u003dChance to Flee"
  ],
  [
    "ItemRarity",
    "gems.txt",
    "name\u003dItem Rarity"
  ],
  [
    "BearTrap",
    "gems.txt",
    "name\u003dBear Trap"
  ],
  [
    "VaalStormCall",
    "gems.txt",
    "name\u003dVaal Storm Call"
  ],
  [
    "Flameblast",
    "gems.txt",
    "name\u003dFlameblast"
  ],
  [
    "ImmortalCall",
    "gems.txt",
    "name\u003dImmortal Call"
  ],
  [
    "ColdPenetration",
    "gems.txt",
    "name\u003dCold Penetration"
  ],
  [
    "shield",
    "itemtypes.txt",
    "type\u003dShield"
  ],
  [
    "helmet|helm",
    "itemtypes.txt",
    "type\u003dHelmet"
  ],
  [
    "frag",
    "itemtypes.txt",
    "type\u003dVaal Fragments"
  ],
  [
    "mace|1hmace",
    "itemtypes.txt",
    "type\u003dOne Hand Mace"
  ],
  [
    "ring",
    "itemtypes.txt",
    "type\u003dRing"
  ],
  [
    "gloves?",
    "itemtypes.txt",
    "type\u003dGloves"
  ],
  [
    "jwl|jewel",
    "itemtypes.txt",
    "type\u003dJewel"
  ],
  [
    "quiver|quiv",
    "itemtypes.txt",
    "type\u003dQuiver"
  ],
  [
    "bow",
    "itemtypes.txt",
    "type\u003dBow"
  ],
  [
    "gem",
    "itemtypes.txt",
    "type\u003dGem"
  ],
  [
    "2hmace",
    "itemtypes.txt",
    "type\u003dTwo Hand Mace"
  ],
  [
    "boots?",
    "itemtypes.txt",
    "type\u003dBoots"
  ],
  [
    "1h",
    "itemtypes.txt",
    "type\u003d1h"
  ],
  [
    "amulet|ammy",
    "itemtypes.txt",
    "type\u003dAmulet"
  ],
  [
    "chest|body|bodyarmour",
    "itemtypes.txt",
    "type\u003dBody Armour"
  ],
  [
    "map",
    "itemtypes.txt",
    "type\u003dMap"
  ],
  [
    "dagger",
    "itemtypes.txt",
    "type\u003dDagger"
  ],
  [
    "belt",
    "itemtypes.txt",
    "type\u003dBelt"
  ],
  [
    "axe|1haxe",
    "itemtypes.txt",
    "type\u003dOne Hand Axe"
  ],
  [
    "2haxe",
    "itemtypes.txt",
    "type\u003dTwo Hand Axe"
  ],
  [
    "staff",
    "itemtypes.txt",
    "type\u003dStaff"
  ],
  [
    "sceptre",
    "itemtypes.txt",
    "type\u003dSceptre"
  ],
  [
    "claw",
    "itemtypes.txt",
    "type\u003dClaw"
  ],
  [
    "curren|currency",
    "itemtypes.txt",
    "type\u003dCurrency"
  ],
  [
    "2h",
    "itemtypes.txt",
    "type\u003d2h"
  ],
  [
    "fish",
    "itemtypes.txt",
    "type\u003dFishing Rods"
  ],
  [
    "wand",
    "itemtypes.txt",
    "type\u003dWand"
  ],
  [
    "sword|1hsword",
    "itemtypes.txt",
    "type\u003dOne Hand Sword"
  ],
  [
    "flsk|flask",
    "itemtypes.txt",
    "type\u003dFlask"
  ],
  [
    "div|divination|divcard",
    "itemtypes.txt",
    "type\u003dDivination Card"
  ],
  [
    "2hsword",
    "itemtypes.txt",
    "type\u003dTwo Hand Sword"
  ],
  [
    "te?mpstandard|te?mpsc",
    "leagues.txt",
    "league\u003dDarkshrine (IC003)"
  ],
  [
    "standard|sc",
    "leagues.txt",
    "league\u003dStandard"
  ],
  [
    "hardcore|hc",
    "leagues.txt",
    "league\u003dHardcore"
  ],
  [
    "te?mphardcore|te?mphc",
    "leagues.txt",
    "league\u003dDarkshrine HC (IC004)"
  ],
  [
    "offline",
    "seller.txt",
    "online\u003d"
  ],
  [
    "online",
    "seller.txt",
    "online\u003dx"
  ],
  [
    "nobo",
    "seller.txt",
    "buyout\u003d"
  ],
  [
    "bo",
    "seller.txt",
    "buyout\u003dx"
  ],
  [
    "(\\d{1})l",
    "socketslinks.txt",
    "link_min\u003d$GROUP1"
  ],
  [
    "(\\d{1})g",
    "socketslinks.txt",
    "sockets_g\u003d$GROUP1"
  ],
  [
    "(\\d{1})l(\\d{1})s",
    "socketslinks.txt",
    "sockets_min\u003d$GROUP2\u0026link_min\u003d$GROUP1"
  ],
  [
    "(\\d{1})s(\\d{1})l",
    "socketslinks.txt",
    "sockets_min\u003d$GROUP1\u0026link_min\u003d$GROUP2"
  ],
  [
    "(\\d{1})b",
    "socketslinks.txt",
    "sockets_b\u003d$GROUP1"
  ],
  [
    "(\\d{1})r",
    "socketslinks.txt",
    "sockets_r\u003d$GROUP1"
  ],
  [
    "(\\d{1})s",
    "socketslinks.txt",
    "sockets_min\u003d$GROUP1"
  ],
  [
    "sortev",
    "sort.txt",
    "quality_evasion"
  ],
  [
    "sortar",
    "sort.txt",
    "quality_armour"
  ],
  [
    "sortstr",
    "sort.txt",
    "#(pseudo) (total) +# to Strength"
  ],
  [
    "sortint",
    "sort.txt",
    "#(pseudo) (total) +# to Intelligence"
  ],
  [
    "sortlife",
    "sort.txt",
    "#(pseudo) (total) +# to maximum Life"
  ],
  [
    "sortes",
    "sort.txt",
    "quality_shield"
  ],
  [
    "sortlight(ning)?res",
    "sort.txt",
    "#(pseudo) (total) +#% to Lightning Resistance"
  ],
  [
    "sortdex",
    "sort.txt",
    "#(pseudo) (total) +# to Dexterity"
  ],
  [
    "sortmapq",
    "sort.txt",
    "mapq"
  ],
  [
    "sortcrit",
    "sort.txt",
    "crit"
  ],
  [
    "sortbl(oc)?k",
    "sort.txt",
    "block"
  ],
  [
    "sortpdps",
    "sort.txt",
    "quality_dps"
  ],
  [
    "sorted",
    "sort.txt",
    "ed"
  ],
  [
    "sortprice",
    "sort.txt",
    "price_in_chaos"
  ],
  [
    "sortcoldres",
    "sort.txt",
    "#(pseudo) (total) +#% to Cold Resistance"
  ],
  [
    "sortedps",
    "sort.txt",
    "edps"
  ],
  [
    "sortaps",
    "sort.txt",
    "aps"
  ],
  [
    "sortlvl",
    "sort.txt",
    "level"
  ],
  [
    "sortfireres",
    "sort.txt",
    "#(pseudo) (total) +#% to Fire Resistance"
  ],
  [
    "sortq",
    "sort.txt",
    "q"
  ],
  [
    "sortpd",
    "sort.txt",
    "quality_pd"
  ],
  [
    "sortgrp0",
    "sort.txt",
    "#|0"
  ],
  [
    "Shaper\u0027?s?Seed",
    "uniques.txt",
    "name\u003dShaper\u0027s Seed"
  ],
  [
    "MidnightBargain",
    "uniques.txt",
    "name\u003dMidnight Bargain"
  ],
  [
    "Pillar(of)?(the)?CagedGod",
    "uniques.txt",
    "name\u003dPillar of the Caged God"
  ],
  [
    "BrokenFaith",
    "uniques.txt",
    "name\u003dBroken Faith"
  ],
  [
    "LeerCast",
    "uniques.txt",
    "name\u003dLeer Cast"
  ],
  [
    "Dreadarc",
    "uniques.txt",
    "name\u003dDreadarc"
  ],
  [
    "Talisman(of)?(the)?Victor",
    "uniques.txt",
    "name\u003dTalisman of the Victor"
  ],
  [
    "Romira\u0027?s?Banquet",
    "uniques.txt",
    "name\u003dRomira\u0027s Banquet"
  ],
  [
    "Ondar\u0027?s?Clasp",
    "uniques.txt",
    "name\u003dOndar\u0027s Clasp"
  ],
  [
    "DoonCuebiyari",
    "uniques.txt",
    "name\u003dDoon Cuebiyari"
  ],
  [
    "AegisAurora",
    "uniques.txt",
    "name\u003dAegis Aurora"
  ],
  [
    "Atziri\u0027?s?Disfavour",
    "uniques.txt",
    "name\u003dAtziri\u0027s Disfavour"
  ],
  [
    "Ylfeban\u0027?s?Trickery",
    "uniques.txt",
    "name\u003dYlfeban\u0027s Trickery"
  ],
  [
    "Flesh-?Eater",
    "uniques.txt",
    "name\u003dFlesh-Eater"
  ],
  [
    "Veil(of)?(the)?Night",
    "uniques.txt",
    "name\u003dVeil of the Night"
  ],
  [
    "Rain(of)?Splinters",
    "uniques.txt",
    "name\u003dRain of Splinters"
  ],
  [
    "Fragility",
    "uniques.txt",
    "name\u003dFragility"
  ],
  [
    "SoulMantle",
    "uniques.txt",
    "name\u003dSoul Mantle"
  ],
  [
    "Greed\u0027?s?Embrace",
    "uniques.txt",
    "name\u003dGreed\u0027s Embrace"
  ],
  [
    "Atziri\u0027?s?Step",
    "uniques.txt",
    "name\u003dAtziri\u0027s Step"
  ],
  [
    "PerandusBlazon",
    "uniques.txt",
    "name\u003dPerandus Blazon"
  ],
  [
    "Song(of)?(the)?Sirens",
    "uniques.txt",
    "name\u003dSong of the Sirens"
  ],
  [
    "ColdSteel",
    "uniques.txt",
    "name\u003dCold Steel"
  ],
  [
    "Meginord\u0027?s?Girdle",
    "uniques.txt",
    "name\u003dMeginord\u0027s Girdle"
  ],
  [
    "Malachai\u0027?s?Artifice",
    "uniques.txt",
    "name\u003dMalachai\u0027s Artifice"
  ],
  [
    "MaliciousIntent",
    "uniques.txt",
    "name\u003dMalicious Intent"
  ],
  [
    "Army(of)?Bones",
    "uniques.txt",
    "name\u003dArmy of Bones"
  ],
  [
    "Andvarius",
    "uniques.txt",
    "name\u003dAndvarius"
  ],
  [
    "Chitus\u0027?Needle",
    "uniques.txt",
    "name\u003dChitus\u0027 Needle"
  ],
  [
    "Goldwyrm",
    "uniques.txt",
    "name\u003dGoldwyrm"
  ],
  [
    "Redbeak",
    "uniques.txt",
    "name\u003dRedbeak"
  ],
  [
    "Belly(of)?(the)?Beast",
    "uniques.txt",
    "name\u003dBelly of the Beast"
  ],
  [
    "VaalSentencing",
    "uniques.txt",
    "name\u003dVaal Sentencing"
  ],
  [
    "MutatedGrowth",
    "uniques.txt",
    "name\u003dMutated Growth"
  ],
  [
    "(The)?Gull",
    "uniques.txt",
    "name\u003dThe Gull"
  ],
  [
    "Demigod\u0027?s?Bounty",
    "uniques.txt",
    "name\u003dDemigod\u0027s Bounty"
  ],
  [
    "(The)?Pariah",
    "uniques.txt",
    "name\u003dThe Pariah"
  ],
  [
    "Brutus\u0027?LeadSprinkler",
    "uniques.txt",
    "name\u003dBrutus\u0027 Lead Sprinkler"
  ],
  [
    "Belt(of)?(the)?Deceiver",
    "uniques.txt",
    "name\u003dBelt of the Deceiver"
  ],
  [
    "Queen\u0027?s?Decree",
    "uniques.txt",
    "name\u003dQueen\u0027s Decree"
  ],
  [
    "RimeGaze",
    "uniques.txt",
    "name\u003dRime Gaze"
  ],
  [
    "Marylene\u0027?s?Fallacy",
    "uniques.txt",
    "name\u003dMarylene\u0027s Fallacy"
  ],
  [
    "Foxshade",
    "uniques.txt",
    "name\u003dFoxshade"
  ],
  [
    "Demigod\u0027?s?Beacon",
    "uniques.txt",
    "name\u003dDemigod\u0027s Beacon"
  ],
  [
    "Doomsower",
    "uniques.txt",
    "name\u003dDoomsower"
  ],
  [
    "SinTrek",
    "uniques.txt",
    "name\u003dSin Trek"
  ],
  [
    "MutewindPennant",
    "uniques.txt",
    "name\u003dMutewind Pennant"
  ],
  [
    "QuillRain",
    "uniques.txt",
    "name\u003dQuill Rain"
  ],
  [
    "Atziri\u0027?s?Foible",
    "uniques.txt",
    "name\u003dAtziri\u0027s Foible"
  ],
  [
    "CallinellusMalleus",
    "uniques.txt",
    "name\u003dCallinellus Malleus"
  ],
  [
    "Conqueror\u0027?s?Longevity",
    "uniques.txt",
    "name\u003dConqueror\u0027s Longevity"
  ],
  [
    "CorruptedEnergy",
    "uniques.txt",
    "name\u003dCorrupted Energy"
  ],
  [
    "(The)?Harvest",
    "uniques.txt",
    "name\u003dThe Harvest"
  ],
  [
    "Infractem",
    "uniques.txt",
    "name\u003dInfractem"
  ],
  [
    "Demigod\u0027?s?Stride",
    "uniques.txt",
    "name\u003dDemigod\u0027s Stride"
  ],
  [
    "EssentiaSanguis",
    "uniques.txt",
    "name\u003dEssentia Sanguis"
  ],
  [
    "LightbaneRaiment",
    "uniques.txt",
    "name\u003dLightbane Raiment"
  ],
  [
    "Mindspiral",
    "uniques.txt",
    "name\u003dMindspiral"
  ],
  [
    "Olmec\u0027?s?Sanctum",
    "uniques.txt",
    "name\u003dOlmec\u0027s Sanctum"
  ],
  [
    "GiftsfromAbove",
    "uniques.txt",
    "name\u003dGifts from Above"
  ],
  [
    "Voll\u0027?s?Devotion",
    "uniques.txt",
    "name\u003dVoll\u0027s Devotion"
  ],
  [
    "Stone(of)?Lazhwar",
    "uniques.txt",
    "name\u003dStone of Lazhwar"
  ],
  [
    "TrolltimberSpire",
    "uniques.txt",
    "name\u003dTrolltimber Spire"
  ],
  [
    "Ungil\u0027?s?Gauche",
    "uniques.txt",
    "name\u003dUngil\u0027s Gauche"
  ],
  [
    "Bloodboil",
    "uniques.txt",
    "name\u003dBloodboil"
  ],
  [
    "Maligaro\u0027?s?Restraint",
    "uniques.txt",
    "name\u003dMaligaro\u0027s Restraint"
  ],
  [
    "Wideswing",
    "uniques.txt",
    "name\u003dWideswing"
  ],
  [
    "Oba\u0027?s?CursedTrove",
    "uniques.txt",
    "name\u003dOba\u0027s Cursed Trove"
  ],
  [
    "Emberwake",
    "uniques.txt",
    "name\u003dEmberwake"
  ],
  [
    "Brightbeak",
    "uniques.txt",
    "name\u003dBrightbeak"
  ],
  [
    "Scold\u0027?s?Bridle",
    "uniques.txt",
    "name\u003dScold\u0027s Bridle"
  ],
  [
    "(The)?Magnate",
    "uniques.txt",
    "name\u003dThe Magnate"
  ],
  [
    "Geofri\u0027?s?Crest",
    "uniques.txt",
    "name\u003dGeofri\u0027s Crest"
  ],
  [
    "ShadowsandDust",
    "uniques.txt",
    "name\u003dShadows and Dust"
  ],
  [
    "SteppanEard",
    "uniques.txt",
    "name\u003dSteppan Eard"
  ],
  [
    "Shavronne\u0027?s?Pace",
    "uniques.txt",
    "name\u003dShavronne\u0027s Pace"
  ],
  [
    "Aurumvorax",
    "uniques.txt",
    "name\u003dAurumvorax"
  ],
  [
    "Sire(of)?Shards",
    "uniques.txt",
    "name\u003dSire of Shards"
  ],
  [
    "Doryani\u0027?s?Fist",
    "uniques.txt",
    "name\u003dDoryani\u0027s Fist"
  ],
  [
    "StaticElectricity",
    "uniques.txt",
    "name\u003dStatic Electricity"
  ],
  [
    "EnergyFromWithin",
    "uniques.txt",
    "name\u003dEnergy From Within"
  ],
  [
    "Voidbringer",
    "uniques.txt",
    "name\u003dVoidbringer"
  ],
  [
    "VisMortis",
    "uniques.txt",
    "name\u003dVis Mortis"
  ],
  [
    "Lioneye\u0027?s?Remorse",
    "uniques.txt",
    "name\u003dLioneye\u0027s Remorse"
  ],
  [
    "Izaro\u0027?s?Turmoil",
    "uniques.txt",
    "name\u003dIzaro\u0027s Turmoil"
  ],
  [
    "Fireborn",
    "uniques.txt",
    "name\u003dFireborn"
  ],
  [
    "PerandusSignet",
    "uniques.txt",
    "name\u003dPerandus Signet"
  ],
  [
    "Rumi\u0027?s?Concoction",
    "uniques.txt",
    "name\u003dRumi\u0027s Concoction"
  ],
  [
    "EphemeralEdge",
    "uniques.txt",
    "name\u003dEphemeral Edge"
  ],
  [
    "(The)?BrokenCrown",
    "uniques.txt",
    "name\u003dThe Broken Crown"
  ],
  [
    "FertileMind",
    "uniques.txt",
    "name\u003dFertile Mind"
  ],
  [
    "qotf|Queen(of)?(the)?Forest",
    "uniques.txt",
    "name\u003dQueen of the Forest"
  ],
  [
    "Mightflay",
    "uniques.txt",
    "name\u003dMightflay"
  ],
  [
    "Izaro\u0027?s?Dilemma",
    "uniques.txt",
    "name\u003dIzaro\u0027s Dilemma"
  ],
  [
    "Ichimonji",
    "uniques.txt",
    "name\u003dIchimonji"
  ],
  [
    "Assassin\u0027?s?Haste",
    "uniques.txt",
    "name\u003dAssassin\u0027s Haste"
  ],
  [
    "Meginord\u0027?s?Vise",
    "uniques.txt",
    "name\u003dMeginord\u0027s Vise"
  ],
  [
    "Berek\u0027?s?Grip",
    "uniques.txt",
    "name\u003dBerek\u0027s Grip"
  ],
  [
    "Winds(of)?Change",
    "uniques.txt",
    "name\u003dWinds of Change"
  ],
  [
    "Berek\u0027?s?Respite",
    "uniques.txt",
    "name\u003dBerek\u0027s Respite"
  ],
  [
    "Daresso\u0027?s?Defiance",
    "uniques.txt",
    "name\u003dDaresso\u0027s Defiance"
  ],
  [
    "BrinerotFlag",
    "uniques.txt",
    "name\u003dBrinerot Flag"
  ],
  [
    "Moonsorrow",
    "uniques.txt",
    "name\u003dMoonsorrow"
  ],
  [
    "Thief\u0027?s?Torment",
    "uniques.txt",
    "name\u003dThief\u0027s Torment"
  ],
  [
    "Poacher\u0027?s?Aim",
    "uniques.txt",
    "name\u003dPoacher\u0027s Aim"
  ],
  [
    "Astramentis",
    "uniques.txt",
    "name\u003dAstramentis"
  ],
  [
    "AgnerodNorth",
    "uniques.txt",
    "name\u003dAgnerod North"
  ],
  [
    "Atziri\u0027?s?Reign",
    "uniques.txt",
    "name\u003dAtziri\u0027s Reign"
  ],
  [
    "Devoto\u0027?s?Devotion",
    "uniques.txt",
    "name\u003dDevoto\u0027s Devotion"
  ],
  [
    "(The)?ThreeDragons",
    "uniques.txt",
    "name\u003dThe Three Dragons"
  ],
  [
    "Maligaro\u0027?s?Virtuosity",
    "uniques.txt",
    "name\u003dMaligaro\u0027s Virtuosity"
  ],
  [
    "Limbsplit",
    "uniques.txt",
    "name\u003dLimbsplit"
  ],
  [
    "(The)?SearingTouch",
    "uniques.txt",
    "name\u003dThe Searing Touch"
  ],
  [
    "Kaom\u0027?s?Heart",
    "uniques.txt",
    "name\u003dKaom\u0027s Heart"
  ],
  [
    "Crown(of)?Thorns",
    "uniques.txt",
    "name\u003dCrown of Thorns"
  ],
  [
    "Crown(of)?Eyes",
    "uniques.txt",
    "name\u003dCrown of Eyes"
  ],
  [
    "AgnerodEast",
    "uniques.txt",
    "name\u003dAgnerod East"
  ],
  [
    "Maligaro\u0027?s?Lens",
    "uniques.txt",
    "name\u003dMaligaro\u0027s Lens"
  ],
  [
    "Wurm\u0027?s?Molt",
    "uniques.txt",
    "name\u003dWurm\u0027s Molt"
  ],
  [
    "Saffell\u0027?s?Frame",
    "uniques.txt",
    "name\u003dSaffell\u0027s Frame"
  ],
  [
    "Lion\u0027?s?Roar",
    "uniques.txt",
    "name\u003dLion\u0027s Roar"
  ],
  [
    "Edge(of)?Madness",
    "uniques.txt",
    "name\u003dEdge of Madness"
  ],
  [
    "Wings(of)?Entropy",
    "uniques.txt",
    "name\u003dWings of Entropy"
  ],
  [
    "Apparitions",
    "uniques.txt",
    "name\u003dApparitions"
  ],
  [
    "Southbound",
    "uniques.txt",
    "name\u003dSouthbound"
  ],
  [
    "Bramblejack",
    "uniques.txt",
    "name\u003dBramblejack"
  ],
  [
    "AnatomicalKnowledge",
    "uniques.txt",
    "name\u003dAnatomical Knowledge"
  ],
  [
    "Doryani\u0027?s?Catalyst",
    "uniques.txt",
    "name\u003dDoryani\u0027s Catalyst"
  ],
  [
    "Shavronne\u0027?s?Wrappings",
    "uniques.txt",
    "name\u003dShavronne\u0027s Wrappings"
  ],
  [
    "Blood(of)?(the)?Karui",
    "uniques.txt",
    "name\u003dBlood of the Karui"
  ],
  [
    "(The)?DeepOne\u0027?s?Hide",
    "uniques.txt",
    "name\u003dThe Deep One\u0027s Hide"
  ],
  [
    "Lioneye\u0027?s?Paws",
    "uniques.txt",
    "name\u003dLioneye\u0027s Paws"
  ],
  [
    "Ungil\u0027?s?Harmony",
    "uniques.txt",
    "name\u003dUngil\u0027s Harmony"
  ],
  [
    "Alpha\u0027?s?Howl",
    "uniques.txt",
    "name\u003dAlpha\u0027s Howl"
  ],
  [
    "Sidhebreath",
    "uniques.txt",
    "name\u003dSidhebreath"
  ],
  [
    "Oro\u0027?s?Sacrifice",
    "uniques.txt",
    "name\u003dOro\u0027s Sacrifice"
  ],
  [
    "Lioneye\u0027?s?Vision",
    "uniques.txt",
    "name\u003dLioneye\u0027s Vision"
  ],
  [
    "Hyaon\u0027?s?Fury",
    "uniques.txt",
    "name\u003dHyaon\u0027s Fury"
  ],
  [
    "Kongor\u0027?s?UndyingRage",
    "uniques.txt",
    "name\u003dKongor\u0027s Undying Rage"
  ],
  [
    "Sibyl\u0027?s?Lament",
    "uniques.txt",
    "name\u003dSibyl\u0027s Lament"
  ],
  [
    "(The)?BloodReaper",
    "uniques.txt",
    "name\u003dThe Blood Reaper"
  ],
  [
    "Bloodseeker",
    "uniques.txt",
    "name\u003dBloodseeker"
  ],
  [
    "Tasalio\u0027?s?Sign",
    "uniques.txt",
    "name\u003dTasalio\u0027s Sign"
  ],
  [
    "BruteForceSolution",
    "uniques.txt",
    "name\u003dBrute Force Solution"
  ],
  [
    "Sentari\u0027?s?Answer",
    "uniques.txt",
    "name\u003dSentari\u0027s Answer"
  ],
  [
    "SoulTaker",
    "uniques.txt",
    "name\u003dSoul Taker"
  ],
  [
    "ArakuTiki",
    "uniques.txt",
    "name\u003dAraku Tiki"
  ],
  [
    "DeathRush",
    "uniques.txt",
    "name\u003dDeath Rush"
  ],
  [
    "Daresso\u0027?s?Salute",
    "uniques.txt",
    "name\u003dDaresso\u0027s Salute"
  ],
  [
    "Zahndethus\u0027?Cassock",
    "uniques.txt",
    "name\u003dZahndethus\u0027 Cassock"
  ],
  [
    "Hotfooted",
    "uniques.txt",
    "name\u003dHotfooted"
  ],
  [
    "Death\u0027?s?Hand",
    "uniques.txt",
    "name\u003dDeath\u0027s Hand"
  ],
  [
    "Briskwrap",
    "uniques.txt",
    "name\u003dBriskwrap"
  ],
  [
    "Demigod\u0027?s?Eye",
    "uniques.txt",
    "name\u003dDemigod\u0027s Eye"
  ],
  [
    "Shiversting",
    "uniques.txt",
    "name\u003dShiversting"
  ],
  [
    "Kikazaru",
    "uniques.txt",
    "name\u003dKikazaru"
  ],
  [
    "SurvivalSecrets",
    "uniques.txt",
    "name\u003dSurvival Secrets"
  ],
  [
    "PrismGuardian",
    "uniques.txt",
    "name\u003dPrism Guardian"
  ],
  [
    "Broadstroke",
    "uniques.txt",
    "name\u003dBroadstroke"
  ],
  [
    "Weight(of)?Sin",
    "uniques.txt",
    "name\u003dWeight of Sin"
  ],
  [
    "Craghead",
    "uniques.txt",
    "name\u003dCraghead"
  ],
  [
    "Atziri\u0027?s?Acuity",
    "uniques.txt",
    "name\u003dAtziri\u0027s Acuity"
  ],
  [
    "FortifiedLegion",
    "uniques.txt",
    "name\u003dFortified Legion"
  ],
  [
    "Surgebinders",
    "uniques.txt",
    "name\u003dSurgebinders"
  ],
  [
    "BrinerotWhalers",
    "uniques.txt",
    "name\u003dBrinerot Whalers"
  ],
  [
    "Windscream",
    "uniques.txt",
    "name\u003dWindscream"
  ],
  [
    "MartialArtistry",
    "uniques.txt",
    "name\u003dMartial Artistry"
  ],
  [
    "Cloak(of)?Flame",
    "uniques.txt",
    "name\u003dCloak of Flame"
  ],
  [
    "Asenath\u0027?s?GentleTouch",
    "uniques.txt",
    "name\u003dAsenath\u0027s Gentle Touch"
  ],
  [
    "CarefulPlanning",
    "uniques.txt",
    "name\u003dCareful Planning"
  ],
  [
    "VoidBattery",
    "uniques.txt",
    "name\u003dVoid Battery"
  ],
  [
    "Demigod\u0027?s?Touch",
    "uniques.txt",
    "name\u003dDemigod\u0027s Touch"
  ],
  [
    "(The)?BloodThorn",
    "uniques.txt",
    "name\u003dThe Blood Thorn"
  ],
  [
    "Pacifism",
    "uniques.txt",
    "name\u003dPacifism"
  ],
  [
    "Gang\u0027?s?Momentum",
    "uniques.txt",
    "name\u003dGang\u0027s Momentum"
  ],
  [
    "Sundance",
    "uniques.txt",
    "name\u003dSundance"
  ],
  [
    "Chernobog\u0027?s?Pillar",
    "uniques.txt",
    "name\u003dChernobog\u0027s Pillar"
  ],
  [
    "MightinAllForms",
    "uniques.txt",
    "name\u003dMight in All Forms"
  ],
  [
    "MatuaTupuna",
    "uniques.txt",
    "name\u003dMatua Tupuna"
  ],
  [
    "Abberath\u0027?s?Horn",
    "uniques.txt",
    "name\u003dAbberath\u0027s Horn"
  ],
  [
    "Mokou\u0027?s?Embrace",
    "uniques.txt",
    "name\u003dMokou\u0027s Embrace"
  ],
  [
    "Spire(of)?Stone",
    "uniques.txt",
    "name\u003dSpire of Stone"
  ],
  [
    "IncandescentHeart",
    "uniques.txt",
    "name\u003dIncandescent Heart"
  ],
  [
    "Gorebreaker",
    "uniques.txt",
    "name\u003dGorebreaker"
  ],
  [
    "Hyrri\u0027?s?Bite",
    "uniques.txt",
    "name\u003dHyrri\u0027s Bite"
  ],
  [
    "(The)?RatCage",
    "uniques.txt",
    "name\u003dThe Rat Cage"
  ],
  [
    "Berek\u0027?s?Pass",
    "uniques.txt",
    "name\u003dBerek\u0027s Pass"
  ],
  [
    "Windripper",
    "uniques.txt",
    "name\u003dWindripper"
  ],
  [
    "Inertia",
    "uniques.txt",
    "name\u003dInertia"
  ],
  [
    "Ngamahu\u0027?s?Sign",
    "uniques.txt",
    "name\u003dNgamahu\u0027s Sign"
  ],
  [
    "Lifesprig",
    "uniques.txt",
    "name\u003dLifesprig"
  ],
  [
    "HealthyMind",
    "uniques.txt",
    "name\u003dHealthy Mind"
  ],
  [
    "Fidelitas\u0027?Spike",
    "uniques.txt",
    "name\u003dFidelitas\u0027 Spike"
  ],
  [
    "ReverberationRod",
    "uniques.txt",
    "name\u003dReverberation Rod"
  ],
  [
    "Moonbender\u0027?s?Wing",
    "uniques.txt",
    "name\u003dMoonbender\u0027s Wing"
  ],
  [
    "FluidMotion",
    "uniques.txt",
    "name\u003dFluid Motion"
  ],
  [
    "Hrimnor\u0027?s?Resolve",
    "uniques.txt",
    "name\u003dHrimnor\u0027s Resolve"
  ],
  [
    "Victario\u0027?s?Influence",
    "uniques.txt",
    "name\u003dVictario\u0027s Influence"
  ],
  [
    "Spine(of)?(the)?FirstClaimant",
    "uniques.txt",
    "name\u003dSpine of the First Claimant"
  ],
  [
    "Doedre\u0027?s?Damning",
    "uniques.txt",
    "name\u003dDoedre\u0027s Damning"
  ],
  [
    "Abyssus",
    "uniques.txt",
    "name\u003dAbyssus"
  ],
  [
    "Atziri\u0027?s?Splendour",
    "uniques.txt",
    "name\u003dAtziri\u0027s Splendour"
  ],
  [
    "Taste(of)?Hate",
    "uniques.txt",
    "name\u003dTaste of Hate"
  ],
  [
    "Facebreaker",
    "uniques.txt",
    "name\u003dFacebreaker"
  ],
  [
    "RedbladeBanner",
    "uniques.txt",
    "name\u003dRedblade Banner"
  ],
  [
    "Death\u0027?s?Harp",
    "uniques.txt",
    "name\u003dDeath\u0027s Harp"
  ],
  [
    "Pledge(of)?Hands",
    "uniques.txt",
    "name\u003dPledge of Hands"
  ],
  [
    "MaoKun",
    "uniques.txt",
    "name\u003dMao Kun"
  ],
  [
    "MutewindSeal",
    "uniques.txt",
    "name\u003dMutewind Seal"
  ],
  [
    "DreamFragments",
    "uniques.txt",
    "name\u003dDream Fragments"
  ],
  [
    "Combustibles",
    "uniques.txt",
    "name\u003dCombustibles"
  ],
  [
    "Soulthirst",
    "uniques.txt",
    "name\u003dSoulthirst"
  ],
  [
    "Silverbranch",
    "uniques.txt",
    "name\u003dSilverbranch"
  ],
  [
    "LochtonialCaress",
    "uniques.txt",
    "name\u003dLochtonial Caress"
  ],
  [
    "Powerlessness",
    "uniques.txt",
    "name\u003dPowerlessness"
  ],
  [
    "Lakishu\u0027?s?Blade",
    "uniques.txt",
    "name\u003dLakishu\u0027s Blade"
  ],
  [
    "DeathandTaxes",
    "uniques.txt",
    "name\u003dDeath and Taxes"
  ],
  [
    "Rearguard",
    "uniques.txt",
    "name\u003dRearguard"
  ],
  [
    "Conqueror\u0027?s?Potency",
    "uniques.txt",
    "name\u003dConqueror\u0027s Potency"
  ],
  [
    "AncientWaystones",
    "uniques.txt",
    "name\u003dAncient Waystones"
  ],
  [
    "Heatshiver",
    "uniques.txt",
    "name\u003dHeatshiver"
  ],
  [
    "Victario\u0027?s?Acuity",
    "uniques.txt",
    "name\u003dVictario\u0027s Acuity"
  ],
  [
    "Auxium",
    "uniques.txt",
    "name\u003dAuxium"
  ],
  [
    "(The)?DarkSeer",
    "uniques.txt",
    "name\u003dThe Dark Seer"
  ],
  [
    "Sadima\u0027?s?Touch",
    "uniques.txt",
    "name\u003dSadima\u0027s Touch"
  ],
  [
    "Valako\u0027?s?Sign",
    "uniques.txt",
    "name\u003dValako\u0027s Sign"
  ],
  [
    "LightningCoil",
    "uniques.txt",
    "name\u003dLightning Coil"
  ],
  [
    "BloodSacrifice",
    "uniques.txt",
    "name\u003dBlood Sacrifice"
  ],
  [
    "Chill(of)?Corruption",
    "uniques.txt",
    "name\u003dChill of Corruption"
  ],
  [
    "Rise(of)?(the)?Phoenix",
    "uniques.txt",
    "name\u003dRise of the Phoenix"
  ],
  [
    "Call(of)?(the)?Brotherhood",
    "uniques.txt",
    "name\u003dCall of the Brotherhood"
  ],
  [
    "Doryani\u0027?s?Invitation",
    "uniques.txt",
    "name\u003dDoryani\u0027s Invitation"
  ],
  [
    "Geofri\u0027?s?Baptism",
    "uniques.txt",
    "name\u003dGeofri\u0027s Baptism"
  ],
  [
    "Doomfletch",
    "uniques.txt",
    "name\u003dDoomfletch"
  ],
  [
    "Kaom\u0027?s?Roots",
    "uniques.txt",
    "name\u003dKaom\u0027s Roots"
  ],
  [
    "Death\u0027?s?Oath",
    "uniques.txt",
    "name\u003dDeath\u0027s Oath"
  ],
  [
    "Prismweave",
    "uniques.txt",
    "name\u003dPrismweave"
  ],
  [
    "Goredrill",
    "uniques.txt",
    "name\u003dGoredrill"
  ],
  [
    "(The)?GoddessScorned",
    "uniques.txt",
    "name\u003dThe Goddess Scorned"
  ],
  [
    "Kaltenhalt",
    "uniques.txt",
    "name\u003dKaltenhalt"
  ],
  [
    "Pyre",
    "uniques.txt",
    "name\u003dPyre"
  ],
  [
    "EldritchKnowledge",
    "uniques.txt",
    "name\u003dEldritch Knowledge"
  ],
  [
    "Maloney\u0027?s?Nightfall",
    "uniques.txt",
    "name\u003dMaloney\u0027s Nightfall"
  ],
  [
    "Jack,(the)?Axe",
    "uniques.txt",
    "name\u003dJack, the Axe"
  ],
  [
    "(The)?Stormheart",
    "uniques.txt",
    "name\u003dThe Stormheart"
  ],
  [
    "Drillneck",
    "uniques.txt",
    "name\u003dDrillneck"
  ],
  [
    "Rigvald\u0027?s?Charge",
    "uniques.txt",
    "name\u003dRigvald\u0027s Charge"
  ],
  [
    "Demigod\u0027?s?Triumph",
    "uniques.txt",
    "name\u003dDemigod\u0027s Triumph"
  ],
  [
    "GreatOldOne\u0027?s?Ward",
    "uniques.txt",
    "name\u003dGreat Old One\u0027s Ward"
  ],
  [
    "(The)?Anvil",
    "uniques.txt",
    "name\u003dThe Anvil"
  ],
  [
    "VoltaxicRift",
    "uniques.txt",
    "name\u003dVoltaxic Rift"
  ],
  [
    "Blackgleam",
    "uniques.txt",
    "name\u003dBlackgleam"
  ],
  [
    "Malachai\u0027?s?Simula",
    "uniques.txt",
    "name\u003dMalachai\u0027s Simula"
  ],
  [
    "Kaom\u0027?s?Sign",
    "uniques.txt",
    "name\u003dKaom\u0027s Sign"
  ],
  [
    "Warlord\u0027?s?Reach",
    "uniques.txt",
    "name\u003dWarlord\u0027s Reach"
  ],
  [
    "Kaom\u0027?s?Primacy",
    "uniques.txt",
    "name\u003dKaom\u0027s Primacy"
  ],
  [
    "Deerstalker",
    "uniques.txt",
    "name\u003dDeerstalker"
  ],
  [
    "(The)?Princess",
    "uniques.txt",
    "name\u003dThe Princess"
  ],
  [
    "SacrificialHarvest",
    "uniques.txt",
    "name\u003dSacrificial Harvest"
  ],
  [
    "Wheel(of)?(the)?Stormsail",
    "uniques.txt",
    "name\u003dWheel of the Stormsail"
  ],
  [
    "FleshandSpirit",
    "uniques.txt",
    "name\u003dFlesh and Spirit"
  ],
  [
    "Icetomb",
    "uniques.txt",
    "name\u003dIcetomb"
  ],
  [
    "Skullhead",
    "uniques.txt",
    "name\u003dSkullhead"
  ],
  [
    "Divinarius",
    "uniques.txt",
    "name\u003dDivinarius"
  ],
  [
    "(The)?GoddessBound",
    "uniques.txt",
    "name\u003dThe Goddess Bound"
  ],
  [
    "Blackheart",
    "uniques.txt",
    "name\u003dBlackheart"
  ],
  [
    "Tear(of)?Purity",
    "uniques.txt",
    "name\u003dTear of Purity"
  ],
  [
    "Bones(of)?Ullr",
    "uniques.txt",
    "name\u003dBones of Ullr"
  ],
  [
    "Alberon\u0027?s?Warpath",
    "uniques.txt",
    "name\u003dAlberon\u0027s Warpath"
  ],
  [
    "BatedBreath",
    "uniques.txt",
    "name\u003dBated Breath"
  ],
  [
    "CarnageHeart",
    "uniques.txt",
    "name\u003dCarnage Heart"
  ],
  [
    "Starkonja\u0027?s?Head",
    "uniques.txt",
    "name\u003dStarkonja\u0027s Head"
  ],
  [
    "Bronn\u0027?s?Lithe",
    "uniques.txt",
    "name\u003dBronn\u0027s Lithe"
  ],
  [
    "ForbiddenTaste",
    "uniques.txt",
    "name\u003dForbidden Taste"
  ],
  [
    "Lioneye\u0027?s?Glare",
    "uniques.txt",
    "name\u003dLioneye\u0027s Glare"
  ],
  [
    "(The)?BloodDance",
    "uniques.txt",
    "name\u003dThe Blood Dance"
  ],
  [
    "EzomytePeak",
    "uniques.txt",
    "name\u003dEzomyte Peak"
  ],
  [
    "EnergisedArmour",
    "uniques.txt",
    "name\u003dEnergised Armour"
  ],
  [
    "(The)?RestlessWard",
    "uniques.txt",
    "name\u003dThe Restless Ward"
  ],
  [
    "Taryn\u0027?s?Shiver",
    "uniques.txt",
    "name\u003dTaryn\u0027s Shiver"
  ],
  [
    "(The)?ScreamingEagle",
    "uniques.txt",
    "name\u003dThe Screaming Eagle"
  ],
  [
    "Thunderfist",
    "uniques.txt",
    "name\u003dThunderfist"
  ],
  [
    "Cybil\u0027?s?Paw",
    "uniques.txt",
    "name\u003dCybil\u0027s Paw"
  ],
  [
    "WarpedTimepiece",
    "uniques.txt",
    "name\u003dWarped Timepiece"
  ],
  [
    "Snakebite",
    "uniques.txt",
    "name\u003dSnakebite"
  ],
  [
    "Demigod\u0027?s?Presence",
    "uniques.txt",
    "name\u003dDemigod\u0027s Presence"
  ],
  [
    "Vaults(of)?Atziri",
    "uniques.txt",
    "name\u003dVaults of Atziri"
  ],
  [
    "Doedre\u0027?s?Elixir",
    "uniques.txt",
    "name\u003dDoedre\u0027s Elixir"
  ],
  [
    "Asenath\u0027?s?Mark",
    "uniques.txt",
    "name\u003dAsenath\u0027s Mark"
  ],
  [
    "Quecholli",
    "uniques.txt",
    "name\u003dQuecholli"
  ],
  [
    "Self-?Flagellation",
    "uniques.txt",
    "name\u003dSelf-Flagellation"
  ],
  [
    "Kingmaker",
    "uniques.txt",
    "name\u003dKingmaker"
  ],
  [
    "BrinerotMark",
    "uniques.txt",
    "name\u003dBrinerot Mark"
  ],
  [
    "Voll\u0027?s?Protector",
    "uniques.txt",
    "name\u003dVoll\u0027s Protector"
  ],
  [
    "ImmortalFlesh",
    "uniques.txt",
    "name\u003dImmortal Flesh"
  ],
  [
    "Shackles(of)?(the)?Wretched",
    "uniques.txt",
    "name\u003dShackles of the Wretched"
  ],
  [
    "ThousandTeethTemu",
    "uniques.txt",
    "name\u003dThousand Teeth Temu"
  ],
  [
    "DarkrayVectors",
    "uniques.txt",
    "name\u003dDarkray Vectors"
  ],
  [
    "Dusktoe",
    "uniques.txt",
    "name\u003dDusktoe"
  ],
  [
    "Twyzel",
    "uniques.txt",
    "name\u003dTwyzel"
  ],
  [
    "Titucius\u0027?Span",
    "uniques.txt",
    "name\u003dTitucius\u0027 Span"
  ],
  [
    "Hrimsorrow",
    "uniques.txt",
    "name\u003dHrimsorrow"
  ],
  [
    "MutewindWhispersteps",
    "uniques.txt",
    "name\u003dMutewind Whispersteps"
  ],
  [
    "Voidhome",
    "uniques.txt",
    "name\u003dVoidhome"
  ],
  [
    "Jorrhast\u0027?s?Blacksteel",
    "uniques.txt",
    "name\u003dJorrhast\u0027s Blacksteel"
  ],
  [
    "Rashkaldor\u0027?s?Patience",
    "uniques.txt",
    "name\u003dRashkaldor\u0027s Patience"
  ],
  [
    "PrismaticEclipse",
    "uniques.txt",
    "name\u003dPrismatic Eclipse"
  ],
  [
    "Lori\u0027?s?Lantern",
    "uniques.txt",
    "name\u003dLori\u0027s Lantern"
  ],
  [
    "LeHeup(of)?All",
    "uniques.txt",
    "name\u003dLe Heup of All"
  ],
  [
    "Victario\u0027?s?Flight",
    "uniques.txt",
    "name\u003dVictario\u0027s Flight"
  ],
  [
    "Dyadus",
    "uniques.txt",
    "name\u003dDyadus"
  ],
  [
    "Poorjoy\u0027?s?Asylum",
    "uniques.txt",
    "name\u003dPoorjoy\u0027s Asylum"
  ],
  [
    "Darkscorn",
    "uniques.txt",
    "name\u003dDarkscorn"
  ],
  [
    "Ashrend",
    "uniques.txt",
    "name\u003dAshrend"
  ],
  [
    "Shavronne\u0027?s?Revelation",
    "uniques.txt",
    "name\u003dShavronne\u0027s Revelation"
  ],
  [
    "Conqueror\u0027?s?Efficiency",
    "uniques.txt",
    "name\u003dConqueror\u0027s Efficiency"
  ],
  [
    "VaalCaress",
    "uniques.txt",
    "name\u003dVaal Caress"
  ],
  [
    "Ornament(of)?(the)?East",
    "uniques.txt",
    "name\u003dOrnament of the East"
  ],
  [
    "RathpithGlobe",
    "uniques.txt",
    "name\u003dRathpith Globe"
  ],
  [
    "Mon\u0027?tregul\u0027?s?Grasp",
    "uniques.txt",
    "name\u003dMon\u0027tregul\u0027s Grasp"
  ],
  [
    "Crest(of)?Perandus",
    "uniques.txt",
    "name\u003dCrest of Perandus"
  ],
  [
    "ThousandRibbons",
    "uniques.txt",
    "name\u003dThousand Ribbons"
  ],
  [
    "Chalice(of)?Horrors",
    "uniques.txt",
    "name\u003dChalice of Horrors"
  ],
  [
    "Eye(of)?Chayula",
    "uniques.txt",
    "name\u003dEye of Chayula"
  ],
  [
    "Springleaf",
    "uniques.txt",
    "name\u003dSpringleaf"
  ],
  [
    "Dreamfeather",
    "uniques.txt",
    "name\u003dDreamfeather"
  ],
  [
    "(The)?Covenant",
    "uniques.txt",
    "name\u003dThe Covenant"
  ],
  [
    "SurvivalInstincts",
    "uniques.txt",
    "name\u003dSurvival Instincts"
  ],
  [
    "Honourhome",
    "uniques.txt",
    "name\u003dHonourhome"
  ],
  [
    "Wake(of)?Destruction",
    "uniques.txt",
    "name\u003dWake of Destruction"
  ],
  [
    "MarohiErqi",
    "uniques.txt",
    "name\u003dMarohi Erqi"
  ],
  [
    "Cameria\u0027?s?Maul",
    "uniques.txt",
    "name\u003dCameria\u0027s Maul"
  ],
  [
    "Apep\u0027?s?Rage",
    "uniques.txt",
    "name\u003dApep\u0027s Rage"
  ],
  [
    "StormCloud",
    "uniques.txt",
    "name\u003dStorm Cloud"
  ],
  [
    "HeartboundLoop",
    "uniques.txt",
    "name\u003dHeartbound Loop"
  ],
  [
    "Daresso\u0027?s?Courage",
    "uniques.txt",
    "name\u003dDaresso\u0027s Courage"
  ],
  [
    "Wildslash",
    "uniques.txt",
    "name\u003dWildslash"
  ],
  [
    "Atziri\u0027?s?Mirror",
    "uniques.txt",
    "name\u003dAtziri\u0027s Mirror"
  ],
  [
    "NullandVoid",
    "uniques.txt",
    "name\u003dNull and Void"
  ],
  [
    "TremorRod",
    "uniques.txt",
    "name\u003dTremor Rod"
  ],
  [
    "Slitherpinch",
    "uniques.txt",
    "name\u003dSlitherpinch"
  ],
  [
    "(The)?Vertex",
    "uniques.txt",
    "name\u003dThe Vertex"
  ],
  [
    "Voideye",
    "uniques.txt",
    "name\u003dVoideye"
  ],
  [
    "Rat\u0027?s?Nest",
    "uniques.txt",
    "name\u003dRat\u0027s Nest"
  ],
  [
    "Acton\u0027?s?Nightmare",
    "uniques.txt",
    "name\u003dActon\u0027s Nightmare"
  ],
  [
    "LastResort",
    "uniques.txt",
    "name\u003dLast Resort"
  ],
  [
    "InfernalMantle",
    "uniques.txt",
    "name\u003dInfernal Mantle"
  ],
  [
    "Crown(of)?(the)?PaleKing",
    "uniques.txt",
    "name\u003dCrown of the Pale King"
  ],
  [
    "SolarisLorica",
    "uniques.txt",
    "name\u003dSolaris Lorica"
  ],
  [
    "Mantra(of)?Flames",
    "uniques.txt",
    "name\u003dMantra of Flames"
  ],
  [
    "Brawn",
    "uniques.txt",
    "name\u003dBrawn"
  ],
  [
    "RelentlessFury",
    "uniques.txt",
    "name\u003dRelentless Fury"
  ],
  [
    "Ambu\u0027?s?Charge",
    "uniques.txt",
    "name\u003dAmbu\u0027s Charge"
  ],
  [
    "SoulStrike",
    "uniques.txt",
    "name\u003dSoul Strike"
  ],
  [
    "SurvivalSkills",
    "uniques.txt",
    "name\u003dSurvival Skills"
  ],
  [
    "Hegemony\u0027?s?Era",
    "uniques.txt",
    "name\u003dHegemony\u0027s Era"
  ],
  [
    "EfficientTraining",
    "uniques.txt",
    "name\u003dEfficient Training"
  ],
  [
    "ChinSol",
    "uniques.txt",
    "name\u003dChin Sol"
  ],
  [
    "Realmshaper",
    "uniques.txt",
    "name\u003dRealmshaper"
  ],
  [
    "AlDhih",
    "uniques.txt",
    "name\u003dAl Dhih"
  ],
  [
    "IntuitiveLeap",
    "uniques.txt",
    "name\u003dIntuitive Leap"
  ],
  [
    "Lavianga\u0027?s?Spirit",
    "uniques.txt",
    "name\u003dLavianga\u0027s Spirit"
  ],
  [
    "Rebuke(of)?(the)?Vaal",
    "uniques.txt",
    "name\u003dRebuke of the Vaal"
  ],
  [
    "Cherrubim\u0027?s?Maleficence",
    "uniques.txt",
    "name\u003dCherrubim\u0027s Maleficence"
  ],
  [
    "(The)?Taming",
    "uniques.txt",
    "name\u003dThe Taming"
  ],
  [
    "Timeclasp",
    "uniques.txt",
    "name\u003dTimeclasp"
  ],
  [
    "Hall(of)?Grandmasters",
    "uniques.txt",
    "name\u003dHall of Grandmasters"
  ],
  [
    "Mj[öo]lner",
    "uniques.txt",
    "name\u003dMjölner"
  ],
  [
    "Headhunter",
    "uniques.txt",
    "name\u003dHeadhunter"
  ],
  [
    "WhakawairuaTuahu",
    "uniques.txt",
    "name\u003dWhakawairua Tuahu"
  ],
  [
    "HiddenPotential",
    "uniques.txt",
    "name\u003dHidden Potential"
  ],
  [
    "Maelstr[öo]m(of)?Chaos",
    "uniques.txt",
    "name\u003dMaelström of Chaos"
  ],
  [
    "Nycta\u0027?s?Lantern",
    "uniques.txt",
    "name\u003dNycta\u0027s Lantern"
  ],
  [
    "RedbladeBand",
    "uniques.txt",
    "name\u003dRedblade Band"
  ],
  [
    "DyadianDawn",
    "uniques.txt",
    "name\u003dDyadian Dawn"
  ],
  [
    "Blood(of)?Corruption",
    "uniques.txt",
    "name\u003dBlood of Corruption"
  ],
  [
    "KaruiWard",
    "uniques.txt",
    "name\u003dKarui Ward"
  ],
  [
    "Kingsguard",
    "uniques.txt",
    "name\u003dKingsguard"
  ],
  [
    "Wanderlust",
    "uniques.txt",
    "name\u003dWanderlust"
  ],
  [
    "Reaper\u0027?s?Pursuit",
    "uniques.txt",
    "name\u003dReaper\u0027s Pursuit"
  ],
  [
    "ChoberChaber",
    "uniques.txt",
    "name\u003dChober Chaber"
  ],
  [
    "(The)?WhisperingIce",
    "uniques.txt",
    "name\u003dThe Whispering Ice"
  ],
  [
    "Aurseize",
    "uniques.txt",
    "name\u003dAurseize"
  ],
  [
    "Bloodplay",
    "uniques.txt",
    "name\u003dBloodplay"
  ],
  [
    "(The)?Coward\u0027?s?Trial",
    "uniques.txt",
    "name\u003dThe Coward\u0027s Trial"
  ],
  [
    "(The)?ConsumingDark",
    "uniques.txt",
    "name\u003dThe Consuming Dark"
  ],
  [
    "FeveredMind",
    "uniques.txt",
    "name\u003dFevered Mind"
  ],
  [
    "(The)?Ignomon",
    "uniques.txt",
    "name\u003dThe Ignomon"
  ],
  [
    "Wondertrap",
    "uniques.txt",
    "name\u003dWondertrap"
  ],
  [
    "Piscator\u0027?s?Vigil",
    "uniques.txt",
    "name\u003dPiscator\u0027s Vigil"
  ],
  [
    "Ming\u0027?s?Heart",
    "uniques.txt",
    "name\u003dMing\u0027s Heart"
  ],
  [
    "Asphyxia\u0027?s?Wrath",
    "uniques.txt",
    "name\u003dAsphyxia\u0027s Wrath"
  ],
  [
    "DivinationDistillate",
    "uniques.txt",
    "name\u003dDivination Distillate"
  ],
  [
    "Pugilist",
    "uniques.txt",
    "name\u003dPugilist"
  ],
  [
    "ClearMind",
    "uniques.txt",
    "name\u003dClear Mind"
  ],
  [
    "(The)?SupremeTruth",
    "uniques.txt",
    "name\u003dThe Supreme Truth"
  ],
  [
    "Doedre\u0027?s?Tenure",
    "uniques.txt",
    "name\u003dDoedre\u0027s Tenure"
  ],
  [
    "RedbladeTramplers",
    "uniques.txt",
    "name\u003dRedblade Tramplers"
  ],
  [
    "Repentance",
    "uniques.txt",
    "name\u003dRepentance"
  ],
  [
    "BrittleBarrier",
    "uniques.txt",
    "name\u003dBrittle Barrier"
  ],
  [
    "Lavianga\u0027?s?Wisdom",
    "uniques.txt",
    "name\u003dLavianga\u0027s Wisdom"
  ],
  [
    "Rainbowstride",
    "uniques.txt",
    "name\u003dRainbowstride"
  ],
  [
    "Bloodgrip",
    "uniques.txt",
    "name\u003dBloodgrip"
  ],
  [
    "Ventor\u0027?s?Gamble",
    "uniques.txt",
    "name\u003dVentor\u0027s Gamble"
  ],
  [
    "(The)?Peregrine",
    "uniques.txt",
    "name\u003dThe Peregrine"
  ],
  [
    "InspiredLearning",
    "uniques.txt",
    "name\u003dInspired Learning"
  ],
  [
    "Jaws(of)?Agony",
    "uniques.txt",
    "name\u003dJaws of Agony"
  ],
  [
    "Hrimnor\u0027?s?Hymn",
    "uniques.txt",
    "name\u003dHrimnor\u0027s Hymn"
  ],
  [
    "Lioneye\u0027?s?Fall",
    "uniques.txt",
    "name\u003dLioneye\u0027s Fall"
  ],
  [
    "Chitus\u0027?Apex",
    "uniques.txt",
    "name\u003dChitus\u0027 Apex"
  ],
  [
    "Sunblast",
    "uniques.txt",
    "name\u003dSunblast"
  ],
  [
    "MortemMorsu",
    "uniques.txt",
    "name\u003dMortem Morsu"
  ],
  [
    "DyingBreath",
    "uniques.txt",
    "name\u003dDying Breath"
  ],
  [
    "HungryAbyss",
    "uniques.txt",
    "name\u003dHungry Abyss"
  ],
  [
    "Empire\u0027?s?Grasp",
    "uniques.txt",
    "name\u003dEmpire\u0027s Grasp"
  ],
  [
    "TerminusEst",
    "uniques.txt",
    "name\u003dTerminus Est"
  ],
  [
    "(The)?Bringer(of)?Rain",
    "uniques.txt",
    "name\u003dThe Bringer of Rain"
  ],
  [
    "Nomic\u0027?s?Storm",
    "uniques.txt",
    "name\u003dNomic\u0027s Storm"
  ],
  [
    "CarcassJack",
    "uniques.txt",
    "name\u003dCarcass Jack"
  ],
  [
    "Null\u0027?s?Inclination",
    "uniques.txt",
    "name\u003dNull\u0027s Inclination"
  ],
  [
    "Hyrri\u0027?s?Ire",
    "uniques.txt",
    "name\u003dHyrri\u0027s Ire"
  ],
  [
    "FragileBloom",
    "uniques.txt",
    "name\u003dFragile Bloom"
  ],
  [
    "Doedre\u0027?s?Scorn",
    "uniques.txt",
    "name\u003dDoedre\u0027s Scorn"
  ],
  [
    "Heartbreaker",
    "uniques.txt",
    "name\u003dHeartbreaker"
  ],
  [
    "Fairgraves\u0027?Tricorne",
    "uniques.txt",
    "name\u003dFairgraves\u0027 Tricorne"
  ],
  [
    "Atziri\u0027?s?Promise",
    "uniques.txt",
    "name\u003dAtziri\u0027s Promise"
  ],
  [
    "tabula|TabulaRasa",
    "uniques.txt",
    "name\u003dTabula Rasa"
  ],
  [
    "AgnerodSouth",
    "uniques.txt",
    "name\u003dAgnerod South"
  ],
  [
    "Goldrim",
    "uniques.txt",
    "name\u003dGoldrim"
  ],
  [
    "Fencoil",
    "uniques.txt",
    "name\u003dFencoil"
  ],
  [
    "Bino\u0027?s?KitchenKnife",
    "uniques.txt",
    "name\u003dBino\u0027s Kitchen Knife"
  ],
  [
    "Cloak(of)?Defiance",
    "uniques.txt",
    "name\u003dCloak of Defiance"
  ],
  [
    "BlackSunCrest",
    "uniques.txt",
    "name\u003dBlack Sun Crest"
  ],
  [
    "Deidbell",
    "uniques.txt",
    "name\u003dDeidbell"
  ],
  [
    "Mark(of)?(the)?DoubtingKnight",
    "uniques.txt",
    "name\u003dMark of the Doubting Knight"
  ]
]