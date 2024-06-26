package net.tiagofar78.prisonescape.managers;

import net.tiagofar78.prisonescape.PrisonEscapeResources;
import net.tiagofar78.prisonescape.dataobjects.ItemProbability;
import net.tiagofar78.prisonescape.game.prisonbuilding.PrisonEscapeLocation;
import net.tiagofar78.prisonescape.game.prisonbuilding.regions.SquaredRegion;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

public class ConfigManager {

    private static ConfigManager instance = new ConfigManager();

    public static ConfigManager getInstance() {
        return instance;
    }

    private double _prisionerRatio;
    private double _officerRatio;
    private int _minimumPlayers;
    private int _maxPlayers;
    private int _secondsInSolitary;
    private int _daysAmount;
    private int _dayDuration;
    private int _nightDuration;
    private int _waitingPhaseDuration;
    private int _fullLobbyWaitDuration;
    private int _finishedPhaseDuration;
    private int _delayBetweenAnnouncements;
    private int _speedDuration;
    private int _speedLevel;
    private int _startingBalance;
    private int _trapLimit;
    private int _sensorLimit;
    private int _radarLimit;
    private int _energyDrinkLimit;
    private int _cameraLimit;
    private int _trapPrice;
    private int _sensorPrice;
    private int _radarPrice;
    private int _energyDrinkPrice;
    private int _cameraPrice;
    private int _helicopterSpawnDelay;
    private int _helicopterDepartureDelay;
    private int _radarDuration;
    private double _soundDetectorRange;

    private List<String> _availableLanguages;
    private String _defaultLanguage;

    private String _teamChatPrefix;

    private String _worldName;
    private PrisonEscapeLocation _referenceBlock;
    private PrisonEscapeLocation _leavingLocation;
    private PrisonEscapeLocation _waitingLocation;
    private PrisonEscapeLocation _prisonUpperCornerLocation;
    private PrisonEscapeLocation _prisonLowerCornerLocation;
    private List<SquaredRegion> _regions;
    private List<PrisonEscapeLocation> _prisionersSpawnLocation;
    private List<PrisonEscapeLocation> _policeSpawnLocation;
    private PrisonEscapeLocation _solitaryLocation;
    private PrisonEscapeLocation _solitaryExitLocation;
    private PrisonEscapeLocation _helicopterExitLocation;
    private PrisonEscapeLocation _helicopterJoinLocation;
    private PrisonEscapeLocation _helicopterUpperLocation;
    private PrisonEscapeLocation _helicopterLowerLocation;
    private Hashtable<PrisonEscapeLocation, PrisonEscapeLocation> _prisionersSecretPassageLocations;
    private Hashtable<PrisonEscapeLocation, PrisonEscapeLocation> _policeSecretPassageLocations;
    private List<PrisonEscapeLocation> _vaultsLocations;
    private String _vaultsDirection;
    private List<PrisonEscapeLocation> _chestsLocations;
    private List<PrisonEscapeLocation> _goldenDoorsLocations;
    private List<PrisonEscapeLocation> _grayDoorsLocations;
    private List<PrisonEscapeLocation> _codeDoorsLocations;
    private List<PrisonEscapeLocation> _wallCornersLocations;
    private List<List<String>> _wallCrackFormats;
    private List<String> _mazeFormat;
    private PrisonEscapeLocation _mazeUpperCornerLocation;
    private List<List<PrisonEscapeLocation>> _fencesLocations;
    private List<PrisonEscapeLocation> _ventsLocations;

    private Hashtable<String, List<ItemProbability>> _regionsChestContents;

    private double _commonItemsProbability;
    private double _rareItemsProbability;

    private int _chestSize;

    public ConfigManager() {
        YamlConfiguration config = PrisonEscapeResources.getYamlConfiguration();

        _prisionerRatio = config.getDouble("PrisionersRatio");
        _officerRatio = config.getDouble("PoliceRatio");
        _minimumPlayers = config.getInt("MinPlayers");
        _maxPlayers = config.getInt("MaxPlayers");
        _waitingPhaseDuration = config.getInt("WaitingPhaseDuration");
        _fullLobbyWaitDuration = config.getInt("FullLobbyWaitDuration");
        _finishedPhaseDuration = config.getInt("FinishedPhaseDuration");
        _delayBetweenAnnouncements = config.getInt("DelayBetweenAnnouncements");
        _secondsInSolitary = config.getInt("SecondsInSolitary");
        _daysAmount = config.getInt("DaysAmount");
        _dayDuration = config.getInt("DayDuration");
        _nightDuration = config.getInt("NightDuration");
        _speedDuration = config.getInt("SpeedDuration");
        _speedLevel = config.getInt("SpeedLevel");
        _startingBalance = config.getInt("StartingBalance");
        _trapLimit = config.getInt("TrapLimit");
        _sensorLimit = config.getInt("SensorLimit");
        _radarLimit = config.getInt("RadarLimit");
        _energyDrinkLimit = config.getInt("EnergyDrinkLimit");
        _cameraLimit = config.getInt("CameraLimit");
        _trapPrice = config.getInt("TrapPrice");
        _sensorPrice = config.getInt("SensorPrice");
        _radarPrice = config.getInt("RadarPrice");
        _energyDrinkPrice = config.getInt("EnergyDrinkPrice");
        _cameraPrice = config.getInt("CameraPrice");
        _helicopterSpawnDelay = config.getInt("HelicopterSpawnDelay");
        _helicopterDepartureDelay = config.getInt("HelicopterDepartureDelay");
        _radarDuration = config.getInt("RadarDuration");
        _soundDetectorRange = config.getDouble("SoundDetectorRange");

        _availableLanguages = config.getStringList("AvailableLanguages");
        _defaultLanguage = config.getString("DefaultLanguage");

        _teamChatPrefix = config.getString("TeamChatPrefix");

        _worldName = config.getString("WorldName");
        _referenceBlock = createLocation(config, "ReferenceBlock");
        _leavingLocation = createLocation(config, "LeavingLocation");
        _waitingLocation = createLocation(config, "WaitingLocation");
        _prisonUpperCornerLocation = createLocation(config, "PrisonTopLeftCornerLocation");
        _prisonLowerCornerLocation = createLocation(config, "PrisonBottomRightCornerLocation");
        _regions = createRegionsList(config);
        _prisionersSpawnLocation = createLocationList(config, "PrisionersSpawnLocations");
        _policeSpawnLocation = createLocationList(config, "PoliceSpawnLocations");
        _solitaryLocation = createLocation(config, "SolitaryLocation");
        _solitaryExitLocation = createLocation(config, "SolitaryExitLocation");
        _helicopterExitLocation = createLocation(config, "Helicopter.ExitLocation");
        _helicopterJoinLocation = createLocation(config, "Helicopter.JoinLocation");
        _helicopterUpperLocation = createLocation(config, "Helicopter.UpperLocation");
        _helicopterLowerLocation = createLocation(config, "Helicopter.LowerLocation");
        _prisionersSecretPassageLocations = createLocationsMap(config, "PrisionersSecretPassagesLocation");
        _policeSecretPassageLocations = createLocationsMap(config, "PoliceSecretPassagesLocation");
        _vaultsLocations = createLocationList(config, "VaultsLocations");
        _vaultsDirection = config.getString("VaultsDirection");
        _chestsLocations = createLocationList(config, "ChestsLocations");
        _goldenDoorsLocations = createLocationList(config, "GoldenDoorsLocations");
        _grayDoorsLocations = createLocationList(config, "GrayDoorsLocations");
        _codeDoorsLocations = createLocationList(config, "CodeDoorsLocations");
        _wallCornersLocations = createLocationList(config, "WallCorners");
        _wallCrackFormats = createStringListList(config, "WallCrackFormats");
        _mazeFormat = config.getStringList("Maze.Format");
        _mazeUpperCornerLocation = createLocation(config, "Maze.UpperCornerLocation");
        _fencesLocations = createLocationPairList(config, "Fences");
        _ventsLocations = createLocationList(config, "Vents");

        _regionsChestContents = createRegionsChestContentsMap(config);

        _commonItemsProbability = config.getDouble("CommonItemsProbability");
        _rareItemsProbability = config.getDouble("RareItemsProbability");

        _chestSize = config.getInt("ChestSize");
    }

    private PrisonEscapeLocation createLocation(YamlConfiguration config, String path) {
        int x = config.getInt(path + ".X");
        int y = config.getInt(path + ".Y");
        int z = config.getInt(path + ".Z");

        return new PrisonEscapeLocation(x, y, z);
    }

    private List<PrisonEscapeLocation> createLocationList(YamlConfiguration config, String path) {
        List<PrisonEscapeLocation> list = new ArrayList<>();

        List<String> filteredKeys = config.getKeys(true).stream().filter(
                key -> key.startsWith(path) && key.lastIndexOf(".") == path.length()
        ).toList();

        for (String key : filteredKeys) {
            list.add(createLocation(config, key));
        }

        return list;
    }

    private List<List<PrisonEscapeLocation>> createLocationPairList(YamlConfiguration config, String path) {
        List<List<PrisonEscapeLocation>> list = new ArrayList<>();

        List<String> filteredKeys = config.getKeys(true).stream().filter(
                key -> key.startsWith(path) && key.lastIndexOf(".") == path.length()
        ).toList();

        for (String key : filteredKeys) {
            List<PrisonEscapeLocation> pair = new ArrayList<>();
            pair.add(createLocation(config, key + ".UpperCornerLocation"));
            pair.add(createLocation(config, key + ".LowerCornerLocation"));
            list.add(pair);
        }

        return list;
    }

    private List<List<String>> createStringListList(YamlConfiguration config, String path) {
        List<List<String>> list = new ArrayList<>();

        List<String> filteredKeys = config.getKeys(true).stream().filter(
                key -> key.startsWith(path) && key.lastIndexOf(".") == path.length()
        ).toList();

        for (String key : filteredKeys) {
            list.add(config.getStringList(key));
        }

        return list;
    }

    private Hashtable<PrisonEscapeLocation, PrisonEscapeLocation> createLocationsMap(
            YamlConfiguration config,
            String path
    ) {
        Hashtable<PrisonEscapeLocation, PrisonEscapeLocation> map = new Hashtable<>();

        List<String> filteredKeys = config.getKeys(true).stream().filter(
                key -> key.startsWith(path) && key.lastIndexOf(".") == path.length()
        ).toList();

        for (String key : filteredKeys) {
            map.put(createLocation(config, key + ".Key"), createLocation(config, key + ".Value"));
        }

        return map;
    }

    private List<SquaredRegion> createRegionsList(YamlConfiguration config) {
        List<SquaredRegion> list = new ArrayList<>();

        String regionsPath = "Regions";

        List<String> regionsNamesPaths = config.getKeys(true).stream().filter(
                key -> key.startsWith(regionsPath) && key.lastIndexOf(".") == regionsPath.length()
        ).toList();

        for (String regionNamePath : regionsNamesPaths) {
            String name = regionNamePath.substring(regionsPath.length() + 1);
            boolean isRestricted = config.getBoolean(regionNamePath + ".IsRestricted");
            boolean cutCellPhoneCalls = config.getBoolean(regionNamePath + ".CutCellPhoneCalls");

            List<String> regionsPaths = config.getKeys(true).stream().filter(
                    key -> key.startsWith(regionNamePath + ".") && key.lastIndexOf(".") == regionNamePath.length() &&
                            !key.contains("IsRestricted") && !key.contains("HasCellPhoneCoverage")
            ).toList();

            for (String regionPath : regionsPaths) {
                PrisonEscapeLocation upperCornerLocation = createLocation(config, regionPath + ".UpperCorner");
                PrisonEscapeLocation lowerCornerLocation = createLocation(config, regionPath + ".LowerCorner");

                list.add(
                        new SquaredRegion(
                                name,
                                isRestricted,
                                !cutCellPhoneCalls,
                                upperCornerLocation,
                                lowerCornerLocation
                        )
                );
            }
        }

        return list;
    }

    private Hashtable<String, List<ItemProbability>> createRegionsChestContentsMap(YamlConfiguration config) {
        Hashtable<String, List<ItemProbability>> map = new Hashtable<>();

        String chestsContentsPath = "ChestsContents";

        List<String> paths = config.getKeys(true).stream().filter(
                key -> key.startsWith(chestsContentsPath + ".") && key.lastIndexOf(".") != chestsContentsPath.length()
        ).toList();
        for (String path : paths) {
            int lastIndexOfDot = path.lastIndexOf(".");
            String regionName = path.substring(chestsContentsPath.length() + 1, lastIndexOfDot);

            List<ItemProbability> itemsProbabilities = map.get(regionName);
            if (itemsProbabilities == null) {
                itemsProbabilities = new ArrayList<>();
                map.put(regionName, itemsProbabilities);
            }

            String itemName = path.substring(lastIndexOfDot + 1);
            double probability = config.getDouble(path);
            itemsProbabilities.add(new ItemProbability(itemName, probability));
        }

        return map;
    }

    public Double getPrisionerRatio() {
        return _prisionerRatio;
    }

    public Double getOfficerRatio() {
        return _officerRatio;
    }

    public int getMinimumPlayers() {
        return _minimumPlayers;
    }

    public int getMaxPlayers() {
        return _maxPlayers;
    }

    public int getSecondsInSolitary() {
        return _secondsInSolitary;
    }

    public int getDaysAmount() {
        return _daysAmount;
    }

    public int getDayDuration() {
        return _dayDuration;
    }

    public int getNightDuration() {
        return _nightDuration;
    }

    public int getWaitingPhaseDuration() {
        return _waitingPhaseDuration;
    }

    public int getFullLobbyWaitDuration() {
        return _fullLobbyWaitDuration;
    }

    public int getFinishedPhaseDuration() {
        return _finishedPhaseDuration;
    }

    public int getDelayBetweenAnnouncements() {
        return _delayBetweenAnnouncements;
    }

    public int getSpeedDuration() {
        return _speedDuration;
    }

    public int getSpeedLevel() {
        return _speedLevel;
    }

    public int getStartingBalance() {
        return _startingBalance;
    }

    public int getTrapLimit() {
        return _trapLimit;
    }

    public int getSensorLimit() {
        return _sensorLimit;
    }

    public int getRadarLimit() {
        return _radarLimit;
    }

    public int getEnergyDrinkLimit() {
        return _energyDrinkLimit;
    }

    public int getCameraLimit() {
        return _cameraLimit;
    }

    public int getTrapPrice() {
        return _trapPrice;
    }

    public int getSensorPrice() {
        return _sensorPrice;
    }

    public int getRadarPrice() {
        return _radarPrice;
    }

    public int getEnergyDrinkPrice() {
        return _energyDrinkPrice;
    }

    public int getCameraPrice() {
        return _cameraPrice;
    }

    public int getHelicopterSpawnDelay() {
        return _helicopterSpawnDelay;
    }

    public int getHelicopterDepartureDelay() {
        return _helicopterDepartureDelay;
    }

    public double getSoundDetectorRange() {
        return _soundDetectorRange;
    }

    public int getRadarDuration() {
        return _radarDuration;
    }

    public List<String> getAvailableLanguages() {
        return new ArrayList<>(_availableLanguages);
    }

    public String getDefaultLanguage() {
        return _defaultLanguage;
    }

    public String getTeamChatPrefix() {
        return _teamChatPrefix;
    }

    public String getWorldName() {
        return _worldName;
    }

    @Deprecated
    public PrisonEscapeLocation getReferenceBlock() {
        return createLocationCopy(_referenceBlock);
    }

    public PrisonEscapeLocation getLeavingLocation() {
        return createLocationCopy(_leavingLocation);
    }

    public PrisonEscapeLocation getWaitingLobbyLocation() {
        return createLocationCopy(_waitingLocation);
    }

    public PrisonEscapeLocation getPrisonUpperCornerLocation() {
        return createLocationCopy(_prisonUpperCornerLocation);
    }

    public PrisonEscapeLocation getPrisonLowerCornerLocation() {
        return createLocationCopy(_prisonLowerCornerLocation);
    }

    public List<SquaredRegion> getRegions() {
        return createRegionsListCopy(_regions);
    }

    public List<PrisonEscapeLocation> getPrisionersSpawnLocations() {
        return createLocationsListCopy(_prisionersSpawnLocation);
    }

    public List<PrisonEscapeLocation> getPoliceSpawnLocations() {
        return createLocationsListCopy(_policeSpawnLocation);
    }

    public PrisonEscapeLocation getSolitaryLocation() {
        return createLocationCopy(_solitaryLocation);
    }

    public PrisonEscapeLocation getSolitaryExitLocation() {
        return createLocationCopy(_solitaryExitLocation);
    }

    public PrisonEscapeLocation getHelicopterExitLocation() {
        return createLocationCopy(_helicopterExitLocation);
    }

    public PrisonEscapeLocation getHelicopterJoinLocation() {
        return createLocationCopy(_helicopterJoinLocation);
    }

    public PrisonEscapeLocation getHelicopterUpperLocation() {
        return createLocationCopy(_helicopterUpperLocation);
    }

    public PrisonEscapeLocation getHelicopterLowerLocation() {
        return createLocationCopy(_helicopterLowerLocation);
    }

    public Hashtable<PrisonEscapeLocation, PrisonEscapeLocation> getPrisionersSecretPassageLocations() {
        return createLocationsMapCopy(_prisionersSecretPassageLocations);
    }

    public Hashtable<PrisonEscapeLocation, PrisonEscapeLocation> getPoliceSecretPassageLocations() {
        return createLocationsMapCopy(_policeSecretPassageLocations);
    }

    public List<PrisonEscapeLocation> getVaultsLocations() {
        return createLocationsListCopy(_vaultsLocations);
    }

    public String getVaultsDirection() {
        return _vaultsDirection;
    }

    public List<PrisonEscapeLocation> getChestsLocations() {
        return createLocationsListCopy(_chestsLocations);
    }

    public List<PrisonEscapeLocation> getGoldenDoorsLocations() {
        return createLocationsListCopy(_goldenDoorsLocations);
    }

    public List<PrisonEscapeLocation> getGrayDoorsLocations() {
        return createLocationsListCopy(_grayDoorsLocations);
    }

    public List<PrisonEscapeLocation> getCodeDoorsLocations() {
        return createLocationsListCopy(_codeDoorsLocations);
    }

    public List<PrisonEscapeLocation> getWallCornersLocations() {
        return createLocationsListCopy(_wallCornersLocations);
    }

    public List<List<String>> getWallCrackFormats() {
        return createStringListListCopy(_wallCrackFormats);
    }

    public List<String> getMazeFormat() {
        return new ArrayList<>(_mazeFormat);
    }

    public PrisonEscapeLocation getMazeUpperCornerLocation() {
        return createLocationCopy(_mazeUpperCornerLocation);
    }

    public List<List<PrisonEscapeLocation>> getFencesLocations() {
        return createLocationsPairListCopy(_fencesLocations);
    }

    public List<PrisonEscapeLocation> getVentsLocations() {
        return createLocationsListCopy(_ventsLocations);
    }

    public List<ItemProbability> getChestContents(String regionName) {
        if (!_regionsChestContents.containsKey(regionName)) {
            return null;
        }

        return createItemProbabilityListCopy(_regionsChestContents.get(regionName));
    }

    public double getCommonItemsProbability() {
        return _commonItemsProbability;
    }

    public double getRareItemsProbability() {
        return _rareItemsProbability;
    }

    public int getChestSize() {
        return _chestSize;
    }

//  ########################################
//  #                 Copy                 #
//  ########################################

    private PrisonEscapeLocation createLocationCopy(PrisonEscapeLocation location) {
        return new PrisonEscapeLocation(location);
    }

    private List<PrisonEscapeLocation> createLocationsListCopy(List<PrisonEscapeLocation> locations) {
        List<PrisonEscapeLocation> list = new ArrayList<>();

        for (PrisonEscapeLocation location : locations) {
            list.add(createLocationCopy(location));
        }

        return list;
    }

    private List<List<PrisonEscapeLocation>> createLocationsPairListCopy(
            List<List<PrisonEscapeLocation>> locationPairs
    ) {
        List<List<PrisonEscapeLocation>> list = new ArrayList<>();

        for (List<PrisonEscapeLocation> locationPair : locationPairs) {
            list.add(createLocationsListCopy(locationPair));
        }

        return list;
    }

    private Hashtable<PrisonEscapeLocation, PrisonEscapeLocation> createLocationsMapCopy(
            Hashtable<PrisonEscapeLocation, PrisonEscapeLocation> locations
    ) {
        Hashtable<PrisonEscapeLocation, PrisonEscapeLocation> map = new Hashtable<>();

        for (Entry<PrisonEscapeLocation, PrisonEscapeLocation> entry : locations.entrySet()) {
            map.put(createLocationCopy(entry.getKey()), createLocationCopy(entry.getValue()));
        }

        return map;
    }

    private List<SquaredRegion> createRegionsListCopy(List<SquaredRegion> regions) {
        List<SquaredRegion> list = new ArrayList<>();

        for (SquaredRegion region : regions) {
            list.add(new SquaredRegion(region));
        }

        return list;
    }

    private List<ItemProbability> createItemProbabilityListCopy(List<ItemProbability> itemsProbabilities) {
        List<ItemProbability> list = new ArrayList<>();

        for (ItemProbability itemProbability : itemsProbabilities) {
            list.add(new ItemProbability(itemProbability.getItemName(), itemProbability.getProbability()));
        }

        return list;
    }

    private List<List<String>> createStringListListCopy(List<List<String>> strings) {
        List<List<String>> list = new ArrayList<>();

        for (List<String> s : strings) {
            list.add(new ArrayList<String>(s));
        }

        return list;
    }

}
