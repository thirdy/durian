/*
 * Forgotten Mods
 * 
 * 2016-01-01 - Initial Version
 */

 load('script/basetypes.js');
 load('script/affixes.js');

/*
 * items - a list of SearchResultItem objects
 */
function process(items) {	
	for (idx in items) {
		item = items[idx]
		print('name: ' + item.name)
		processExplicitMods(item)
	}
	return 'success'
}

function processExplicitMods(item) {
	name = item.name
	explicitMods = item.explicitMods

	baseType = determineBaseType(name)
	print('baseType: ' + baseType)
	if(!baseType) return

	// isUnique = true TODO, do we handle uniques?
	
	for(idx in explicitMods) {
		var mod = explicitMods[idx]
		var affix = affixesLookup(baseType, mod.name, mod.value)
		if(affix) {
			print('affix found:' + affix.mod + ' tier: ' + affix.tier)
			affixLabel = affix.affix == 'Prefix' ? '[P]' : '[S]'
			tierLabel = '[T' + affix.tier + ']'
			valueLabel = mod.value
			modNameLabel = mod.name
			if(modNameLabel.startsWith('#')) modNameLabel = modNameLabel.substring(1)
			mod.forgottenMod = affixLabel + tierLabel + ' ' + valueLabel + ' ' + modNameLabel
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
		if(baseTypeFlag && baseTypeFlag == 'Yes' && affix.mod == modName) {
			print('modValue:' + modValue + ' affix.minvalue: ' + affix.minvalue + ' affix.maxvalue: ' + affix.maxvalue)
			if(affix.minvalue <= modValue && affix.maxvalue >= modValue)
			  return affix 
		}
	}
	return null
}

function determineBaseType(name) {
	for(base in baseTypes) {
		if(name.indexOf(base) != -1)
		  return baseTypes[base]
	}
	return null
}

