/*
 * Forgotten Mods
 * 
 * 2016-01-01 - Initial Version
 */

 load('script/basetypes.js');
 load('script/affixes.js');
 
 var logger
 
/*
 * items  - a list of SearchResultItem objects
 * _logger - logger, an instance of org.slf4j.Logger
 */
function process(_logger, items) {
	logger = _logger
	for (idx in items) {
		item = items[idx]
		//logger.info('name: ' + item.name + " rarity: " + item.rarity.name())
		if (item.rarity.name() == "magic" || item.rarity.name() == "rare") 
			processExplicitMods(item)
		
		setupWtbMessage(item)
	}
	return 'success'
}

function setupWtbMessage(item) {
	// Look at class SearchResultItem in file SearchPageScraper.java
	// for more variable to use
	// vanilla js doesn't have format function :(
	if(item.buyout ==  ""){
		var wtbTemplate = '@%s Hi, I would like to buy your %s listed in %s'
		var name = item.name;
		if(item.level != "" && (item.name.indexOf("Map") == -1)){
			name = java.lang.String.format(
			"%s, quality %s, level %s",
			item.name,
			item.quality,
			item.level) 
		}
		var wtb = java.lang.String.format(
			wtbTemplate,
			item.ign, 
			name,
			item.league
		)
	}else{
		var wtbTemplate = '@%s Hi, I would like to buy your %s listed for %s in %s'
		var buyout = item.buyout
		if (item.guildItem()) {
			buyout = java.lang.String.format(
				"%s (less %s guildmate discount)",
				item.buyout,
				item.guildDiscount()) 
		}
		var name = item.name;
		if(item.level != "" && (item.name.indexOf("Map") == -1)){
			name = java.lang.String.format(
			"%s, quality %s, level %s",
			item.name,
			item.quality,
			item.level) 
		}
		var wtb = java.lang.String.format(
			wtbTemplate,
			item.ign, 
			name,
			buyout,
			item.league
		)
	}
	item.wtb(wtb)
}



function processExplicitMods(item) {
	name = item.name
	explicitMods = item.explicitMods

	baseType = determineBaseType(name)
	//logger.info('baseType: ' + baseType)
	if(!baseType) return

	for(idx in explicitMods) {
		var mod = explicitMods[idx]
		var affix = affixesLookup(baseType, mod.name, mod.value)
		var maxTier = affixMaxLookUp(baseType, mod.name)
		if(affix) {
			//logger.info('affix found:' + affix.mod + ' tier: ' + affix.tier + ' maxTier: ' + maxTier)
			affixLabel = affix.affix == 'Prefix' ? '[prefix]' : '[suffix]'
			tierLabel = '[T' + affix.tier + '/T' + maxTier +']'
			valueLabel = mod.value
			modNameLabel = mod.name
			if(modNameLabel.startsWith('#')) modNameLabel = modNameLabel.substring(1)
			mod.forgottenMod = affixLabel + tierLabel + ' ' + valueLabel + ' ' + modNameLabel
		} else {
			//logger.info('explicit mod: ' + mod.name + ' mod value: ' + mod.value)
		}
	}
}

function affixesLookup(baseType, modName, modValue) {
	// extra # at the beginning
	if(modName.startsWith('#')) modName = modName.substring(1)
	for(idx in affixes) {
		affix = affixes[idx]
		baseTypeFlag = affix[baseType]
		// print(baseTypeFlag + ':' + modName + ' = ' + affix.mod)
		if(baseTypeFlag && baseTypeFlag.indexOf('Yes') != -1 && affix.mod == modName) {
			// logger.info('modValue:' + modValue + ' affix.minvalue: ' + affix.minvalue + ' affix.maxvalue: ' + affix.maxvalue)
			if(affix.minvalue <= modValue && affix.maxvalue >= modValue)
			  return affix 
		}
	}
	return null
}
function affixMaxLookUp(baseType, modName) {
	// extra # at the beginning
	if(modName.startsWith('#')) modName = modName.substring(1)
	var maxTier = 0;
	for(idx in affixes) {
		affix = affixes[idx]
		baseTypeFlag = affix[baseType]
		// print(baseTypeFlag + ':' + modName + ' = ' + affix.mod)
		if(baseTypeFlag && baseTypeFlag.indexOf('Yes') != -1 && affix.mod == modName) {			
			if(maxTier < affix.tier) maxTier = affix.tier;
		}else{
			if(maxTier > 0)
				return maxTier;
		}
	}
	return maxTier;
}

function determineBaseType(name) {
	for(base in baseTypes) {
		if(name.indexOf(base) != -1)
		  return baseTypes[base]
	}
	return null
}

