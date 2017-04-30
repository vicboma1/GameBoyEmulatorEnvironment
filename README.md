# [GameBoy Emulator](https://github.com/vicboma1/emulators/tree/master/gameboyclassic) Environment - GBEE  [v.0.2.2](https://github.com/vicboma1/GameBoyEmulatorEnvironment/blob/master/Versioning.md#version-022----wip)

Front-End developed with Kotlin for my [GameBoy Emulator](https://github.com/vicboma1/emulators/tree/master/gameboyclassic)

WIP

![](https://github.com/vicboma1/GameBoyEmulatorEnvironment/blob/master/src/main/resources/version/GBEE_v.0.2.2-1.gif)

#### version 0.2.2  - news
```
Add MenuItemsView Grid by scale { 1x, 2x, 3x, 4x}
Create Images in memory
Asynchronous GridView
```

# GridView

## Rom Name

![](https://github.com/vicboma1/GameBoyEmulatorEnvironment/blob/master/src/main/resources/version/GBEE_v.0.2.2-2.png)

# Resources

Folders 
```
app
    |______ main.png
    
cartridge 
    |______ _bg.png
    |______ addamsf.png
    |______ {romName}.png
    
cover
    |______ _bg.png
    |______ _gbNotFound.png
    |______ addamsf.png
    |______ {romName}.png

font
    |______ _fontGrid.png 
    
list
    |______ listGame.json
    
rom
    |______ ace_stri.gb
    |______ addamsf.gb
    |______ addamsf2.gb
    |______ *.gb
    
snapshot
    |______ _bg.png
    |______ addamsf.png
    |______ {romName}.png
```

where :

```
"main.png" is the icon
"{romName}.png" is the Game image
"_bg.png" is the pattern that contains the others images inside it.
"_gbNotFound.png" is the pattern that represents the image not found. 
"*.gb" is the specific name that contains the game inside the rom
"listGame.json" is a Array<Array<Any>> than contains the column and rows
"_fontGrid.png " is the font that represent the game cover
```


###
# Game List
### 

This is a list of 1049 games released for the Game Boy handheld video game system, excluding any cancelled and unlicensed games campatible with GameBoy Emulator Environment - GBEE

```kotlin
" ---  CADA  --- "          "BURAI FIGHTER DE"            "ELITE SOCCER    "           "HATRIS          "
"ACE STRIKER     "          "BURAI FIGHTER DX"            "EMPIRE STRIKES  "           "HEAD ON         "
"ADDAMS FAMILY   "          "BURGER TIME     "            "ESPARKS         "           "HEIANKYO ALIEN  "
"ADDAMS FAMILY 2 "          "BURNING PAPER   "            "F-1 HEROGB      "           "HEIANKYO ALIEN  "
"ADVENTURE ISLAND"          "BZONE SBREAKOUT "            "F-1HERO GB92    "           "HERCULES EIKOU  "
"ADVENTUREISLAND2"          "CADILLAC2       "            "F1 BOY          "           "HIGH STAKES     "
"AEROSTAR        "          "CAESARS PALACE  "            "F1 SPIRIT       "           "HIRYU GAIDEN    "
"AFTER BURST     "          "CAESARS PALACE  "            "F1 SUPER DRIVING"           "HOME ALONE      "
"ALADDIN         "          "CAPCOM QUIZ     "            "F15 STRIKE EAGLE"           "HOME ALONE2     "
"ALFRED CHICKEN  "          "CAPTAIN TUBASA J"            "F1RACE          "           "HONG KONG       "
"ALIEN           "          "CARD GAME       "            "FACEBALL 2000   "           "HOOK            "
"ALIEN OLYMPICS  "          "CASINO FUNPAK   "            "FAMILYJOCKEY    "           "HOOK            "
"ALIEN V PREDATOR"          "CASTELIAN       "            "FASTEST LAP     "           "HOSHINOKA-BI    "
"ALLEY WAY       "          "CASTLEVANIA ADVE"            "FELIX THE CAT   "           "HUDSON HAWK     "
"ALLSTARCHALLENGE"          "CASTLEVANIA ADVE"            "FERRARI         "           "HUDSON HAWK     "
"ALSTARCHALLENGE2"          "CASTLEVANIA2 BEL"            "FIFA SOCCER     "           "HUGO            "
"ALSTARCHALLENGE2"          "CAVE NOIRE      "            "FIFA SOCCER 96  "           "HYPER BLACKBASS "
"ALTERED SPACE   "          "CENTIPEDE       "            "FIGHBIRD  GB    "           "HYPER DUNK      "
"ALTERED SPACE   "          "CHACHAMARUPANIC "            "FIGHTING SOCCER "           "HYPERLODERUNNER "
"AMAZING-TATER   "          "CHASE HQ        "            "FINAL REVERSE   "           "IKARI NO YOUSAI "
"AMIDA           "          "CHASE HQ        "            "FIRE FIGHTER    "           "IN YOUR FACE    "
"ANIMANIACS      "          "CHIBIMARUKO     "            "FISH DUDE       "           "INDIANA JONES   "
"ANOTHER BIBLE   "          "CHIKI RACE GB   "            "FLAPPY SPECIAL  "           "INDIANA JONES 3 "
"ARETHA          "          "CHOPLIFTER 2    "            "FLINTSTONES     "           "IREM FIGHTER    "
"ARETHA3         "          "CHOPLIFTER 2    "            "FLIPULL         "           "ISHIDO JPN VER. "
"ASMIK2          "          "CHOPLIFTER III  "            "FLYING WARRIORS "           "ISHIDO USA VER. "
"ASTERIX         "          "CLEMENS BASEBALL"            "FOOTBALL INT'L  "           "ITCHY & SCRATCHY"
"ASTEROIDS       "          "CLIFFHANGER     "            "FOREMAN FOR REAL"           "J CONNOR TENNIS "
"ASTEROIDS/MISCMD"          "CONTRA          "            "FORTIFIED ZONE  "           "J.CUP SOCCER    "
"ASTRO RABBY     "          "CONTRA SPIRITS  "            "FORTIFIED ZONE2 "           "JAMES POND      "
"ATOMIC PUNK     "          "COOL SPOT       "            "FRANK THOMAS BB "           "JANKENMAN       "
"AVENGING SPIRIT "          "COOL WORLD      "            "FUNNY FIELD     "           "JANSHIRO        "
"AYAKASI NO SIRO "          "COSMO TANK      "            "FUNPAK 4IN1     "           "JANTAKUBOY      "
"B.C.KID2        "          "CRASH DUMMIES   "            "FUNPAK 4IN1 - V2"           "JEEP JAMBOREE   "
"BALLOON KID     "          "CRYSTAL QUEST   "            "FUSHIGINA BULOBI"           "JELLY BOY       "
"BANISHING RACER "          "CUTTHROAT ISLAND"            "G&W GALLERY     "           "JEOPARDY        "
"BARBIE GAME GIRL"          "CYBER FORMULA   "            "GALAGA&GALAXIAN "           "JINSEI          "
"BASE BALL KIDS  "          "CYRAID          "            "GAME&WATCH      "           "JOE AND MAC     "
"BASEBALL        "          "DAEDALIAN OPUS  "            "GAMERA DAIKAIJUU"           "JOE AND MAC     "
"BASES LOADED GB "          "DAFFY DUCK      "            "GANBARE GOEMON  "           "JOE AND MAC     "
"BATMAN          "          "DAFFY DUCK      "            "GARFIELD        "           "JORDAN ONEONONE "
"BATMAN ANIMATED "          "DAISENRYAKU HIRO"            "GARGOYLE'S      "           "JORDAN VS BIRD  "
"BATMAN FOREVER  "          "DARK WING DUCK  "            "GAUNTLET II     "           "JUDGE DREDD     "
"BATMAN ROJ      "          "DARKMAN         "            "GB 3TAMA        "           "JUNGLE BOOK     "
"BATTLE BULL     "          "DARUMAN BUSTERS "            "GB BASKETBALL   "           "JUNGLE STRIKE   "
"BATTLE CRUSHER  "          "DAYS OF THUNDER "            "GB BEACHVOLLEY  "           "JUNGLE WARS     "
"BATTLE OFKINGDOM"          "DEADHEATSCRAMBLE"            "GB DBZ GOKOU    "           "JURASSIC PARK   "
"BATTLE PINGPONG "          "DEADHEATSCRAMBLE"            "GB DRUAGA 1     "           "JURASSIC PARK II"
"BATTLE SHIP     "          "DEFENDER/JOUST  "            "GB GARMS 1      "           "KAERUNOTAMENI   "
"BATTLE SHIP     "          "DENNIS          "            "GB GENJIN       "           "KAGUYAHIME      "
"BATTLE SHIP     "          "DESERT STRIKE   "            "GB GENJIN LAND  "           "KATTOBI ROAD    "
"BATTLE SHIP     "          "DEXTERITY       "            "GB GODZILLA     "           "KESAMARU        "
"BATTLECITY      "          "DICK TRACY      "            "GB IRON LEAGUER1"           "KID ICARUS      "
"BATTLETOADS     "          "DIG DUG         "            "GB JIDAIGEKI    "           "KID NIKI        "
"BATTLEUNIT ZEOTH"          "DLAIR LEGEND UK0"            "GB KINNIKUMAN DM"           "KILLER TOMATOES "
"BEACH,VOLLEYBALL"          "DLAIR THE LEGEND"            "GB KUREYON SHIN "           "KILLERINSTINCT95"
"BEACH_VOLLEY    "          "DMG FOOTBALL    "            "GB KUREYON SHIN4"           "KINGDOM CRUSADE "
"BEETHOVEN       "          "DMG-ASE         "            "GB LACROANHERO 1"           "KINGOFTHEZOO    "
"BEETLEJUICE     "          "DMG-ASJ         "            "GB MASKRIDER SD "           "KINTARO ACG     "
"BEST OF THE BEST"          "DMG-KMJ         "            "GB MUSASHIROAD 1"           "KIRBY BLOCKBALL "
"BIKKURI NEKKETU "          "DMG-SJJ         "            "GB MUSASHIROAD 1"           "KIRBY DREAM LAND"
"BILL AND TED    "          "DMG-UQJ         "            "GB NEW SD GUNDAM"           "KIRBY'S PINBALL "
"BIONIC BATTLER  "          "DODGE BALL      "            "GB OTOKOJYUKU 1 "           "KIRBY'S PINBALL "
"BIONIC-COMMANDO "          "DODGE BOY       "            "GB OTOKOJYUKU 1 "           "KIRBY2          "
"BIONIC-COMMANDO "          "DODGEDANPEI     "            "GB OTOKOJYUKU 1 "           "KITCHIN PANIC   "
"BISHOJYOSENSI SE"          "DONKEY KONG     "            "GB POWER MOVIE  "           "KITERETU ZYURAKI"
"BLADES OF STEEL "          "DONKEYKONGLAND 2"            "GB SAILORMOON R "           "KLAX            "
"BLASTER MASTER B"          "DONKEYKONGLAND 3"            "GB SAILORMOON R "           "KNIGHT QUEST    "
"BLODIA          "          "DONKEYKONGLAND95"            "GB SLAM DUNK    "           "KOI WA KAKEHIKI "
"BLUES BROTHERS  "          "DORAEMON        "            "GB SLAM DUNK 2  "           "KONAMI GOLF     "
"BO JACKSON      "          "DORAEMON        "            "GB SUPERBIKKURI1"           "KONAMIC SPORTS  "
"BOKU DRACURA KUN"          "DORAEMON2       "            "GB TAACHIYAN    "           "KORODICE        "
"BOMB JACK       "          "DORAKYURADENSETU"            "GB TAMAGOTCH 1  "           "KRUSTY          "
"BOMBER BOY      "          "DORASUREGAIDEN  "            "GB TAMAGOTCHI   "           "KUNIO           "
"BOMBER KING S2  "          "DOUBLE DRAGON   "            "GB TARURU-TO 1  "           "KWIRK           "
"BOMBER MAN GB   "          "DOUBLE DRAGON 2 "            "GB TARURU-TO 2  "           "KYORCHANLANDHIRO"
"BOMBERMAN GB    "          "DOUBLE DRAGON 3 "            "GB TEKKAMANBLADE"           "LAMBORGHINI     "
"BONK'S ADVENTURE"          "DOUBLE DRIBBLE  "            "GB ULTRAMANCLUB1"           "LANG TRNSLTR FRN"
"BOOBY BOYS      "          "DOUBLEYAKUMAN2  "            "GB UNDOUKAI     "           "LANG TRNSLTR SPN"
"BOUKENJIMA2     "          "DR FRANKEN      "            "GBGENJIN2       "           "LAST ACTION HERO"
"BOULDER DASH    "          "DR FRANKEN      "            "GBWARS          "           "LAST BIBLE      "
"BOULDER DASH    "          "DR FRANKEN 2    "            "GEMGEM          "           "LAST BIBLE2     "
"BOXING          "          "DR.MARIO        "            "GENERATIONS     "           "LAWNMOWER MAN   "
"BOXXLE          "          "DRACULA         "            "GENSAN          "           "LAZLOS LEAP     "
"BOXXLE2         "          "DRACURA2        "            "GENSAN          "           "LEGEND          "
"BOY AND BLOB GB1"          "DRAGON SLAYER 1 "            "GENSAN2         "           "LEGEND OF ZERD  "
"BOY AND BLOB GB2"          "DRAGONHEART     "            "GEORGE F. BOXING"           "LEMMINGS        "
"BOY AND BLOB GB2"          "DRAGONHEART     "            "GHOSTBUSTERS 2  "           "LEMMINGS 2      "
"BRAINBENDER     "          "DRAGONS LAIR    "            "GHOSTBUSTERS II "           "LETHAL WEAPON   "
"BREAKTHRU!      "          "DRAGONTAILBIT2  "            "GINGA           "           "LITTLE MASTER   "
"BT2RAGNAROKWORLD"          "DROPZONE        "            "GO GO TANK      "           "LITTLE MASTER 2 "
"BUBBLE BOBBLE   "          "DUCK TALES      "            "GOAL!           "           "LITTLE MERMAID  "
"BUBBLE BOBBLE   "          "DUCK TALES      "            "GOD MEDICINE    "           "LOCK'N CHASE    "
"BUBBLE GHOST    "          "DUCK TALES 2    "            "GODZILLA        "           "LOLO            "
"BUBBLE GHOST    "          "DUCK TALES 2    "            "GOLF            "           "LOONEY TUNES    "
"BUBBLEBOBBLE2   "          "DUNGEONLAND     "            "GOLF CLASSIC    "           "LOOPZ           "
"BUBBLEBOBBLEJR  "          "DYNABLASTER     "            "GOURMET PARADISE"           "LUCKY MONKEY    "
"BUBSY II        "          "EARTHWORM JIM   "            "GRADIUS         "           "LUCLE           "
"BUGS BUNNY      "          "ELEVATOR ACTION "            "GREMLINS 2      "           "LUNA LANDER     "
"BUGSCRAZYCASTLE2"          "ELEVATOR ACTION "            "HARIMANADA      "           "MADDEN 95       "



"MADDEN 96       "          "OLYMPUS         "            "ROAD RASH       "           "SQ DSV DMG      "
"MAGNETIC SOCCER "          "ON THE TILES    "            "ROBIN HOOD      "           "SQUARE DEAL     "
"MAHOUJINGURUGURU"          "ONI             "            "ROBOCOP         "           "SRJ DMG         "
"MAKAIMURAGAIDEN "          "ONI 5           "            "ROBOCOP         "           "STAR SAVER      "
"MANSELL         "          "ONI-PACHINKOTEN "            "ROBOCOP2        "           "STAR STACKER    "
"MARBLE MADNESS  "          "OPERATION C     "            "ROCK'N! MONSTER!"           "STAR TREK       "
"MARIO & YOSHI   "          "OTHELLO         "            "ROCKMAN WORLD   "           "STAR TREK       "
"MARIO'S PICROSS "          "OUT BURST       "            "ROCKMAN WORLD2  "           "STAR WARS       "
"MARIOLAND2      "          "OUT OF GAS      "            "ROCKMANWORLD3   "           "STARGATE        "
"MARU'S MISSION  "          "PAC-IN-TIME     "            "ROCKMANWORLD4   "           "STREET FIGHTER 2"
"MASTER KARATEKA "          "PAC-MAN         "            "ROCKMANWORLD5   "           "SUMO FIGHTER    "
"MCDONALDLAND    "          "PAC-MAN         "            "ROCKY BULLWINKLE"           "SUMOU FIGHTER   "
"MEGA MAN 2      "          "PAC-MAN         "            "RODLAND         "           "SUPER B DAMAN   "
"MEGA MAN 2      "          "PAC-PANIC       "            "RODLAND         "           "SUPER BLUES     "
"MEGA MAN 2      "          "PACHINKO TIME   "            "ROGER RABBIT    "           "SUPER BOMBLISS  "
"MEGALIT         "          "PAINTERMOMOPIE  "            "ROLAN'S CURSE 2 "           "SUPER CHINESE 2 "
"MEGAMAN         "          "PALAMEDES       "            "ROLANS CURSE    "           "SUPER HUNCHBACK "
"MEGAMAN3        "          "PANG            "            "RTYPE           "           "SUPER HUNCHBACK "
"MEGAMAN3        "          "PAPERBOY        "            "RTYPE           "           "SUPER KICK OFF  "
"MEGAMAN3        "          "PAPUWAKUNN      "            "RTYPE 2         "           "SUPER MARIOLAND "
"MEGAMAN4        "          "PARASOL STARS   "            "RTYPE 2         "           "SUPER MARIOLAND "
"MERCENARY FORCE "          "PARASORU HEMBEI "            "RUBBLE SAVER 2  "           "SUPER MARIOLAND "
"METALJACK       "          "PARODIUS        "            "RUBBLESA        "           "SUPER MARIOLAND "
"METROID2        "          "PARODIUS DA!    "            "RUNES OF VIRTUE "           "SUPER OFF ROAD  "
"MICKEY 4        "          "PEETAN          "            "RUNES OF VIRTUE2"           "SUPER RC PRO-AM "
"MICKEY 5        "          "PENGUIN BOY     "            "RVT             "           "SUPER ROBOT WAR "
"MICKEY MOUSE    "          "PENGUIN LAND    "            "S S BASKET BALL2"           "SUPERCHINESELAND"
"MICKEY MOUSE    "          "PENGUIN WARS    "            "SAGA            "           "SUPERMARIOLAND3 "
"MICKEY MOUSE    "          "PENGUINKUNWARSVS"            "SAGA            "           "SUPERROBOTWAR 2G"
"MICKEY MOUSE 2  "          "PENTADRAGON     "            "SAGA2           "           "SUPERWWF        "
"MICKEYS CHASE   "          "PERSONAL ORGANIZ"            "SAGA2           "           "SUPERWWF        "
"MICRO MACHINES  "          "PGA EURO TOUR   "            "SAGA3           "           "SWAMP THING     "
"MICRO MACHINES 2"          "PGA TOUR 96     "            "SAGA3           "           "TAIKYOKU RENJU  "
"MIGRAIN ACCLAIM "          "PHANTASM        "            "SAGAIA          "           "TAIL GATOR      "
"MIKENEKO HOLMES "          "PINBALL         "            "SAMURAI SHODOWN "           "TALE SPIN       "
"MILLI/CENTI/PEDE"          "PINBALL         "            "SANGOKUSHI      "           "TARZAN          "
"MILON CASTLE    "          "PINBALL         "            "SANRIO CARNIVAL "           "TASMANIA STORY  "
"MINER 2049ER    "          "PINBALL         "            "Saved game      "           "TASMANIA STORY  "
"MINESWEEPER     "          "PINBALL DELUXE  "            "SCOTLANDYARD    "           "TAZ-MANIA       "
"MINI PUTT       "          "PINBALL DREAMS  "            "SD HIRYUNOKEN   "           "TECMO BOWL      "
"MISSILE COMMAND "          "PINBALL FANTASIE"            "SD LUPIN THE 3RD"           "TEKKYUFIGHT!    "
"MM ULT CHALLENGE"          "PINBALL MANIA   "            "SD SEINTPARADA01"           "TENCHIWOKURAU   "
"MOGURA DE PON!  "          "PINBALL PARTY   "            "SDGUNDAMKUNITORI"           "TENJIN KAISEN 2 "
"MOGURANYA       "          "PIPE DREAM      "            "SDSENGOKUDEN2   "           "TENJINKAISEN    "
"MOMOTARO DENGEKI"          "PIT FIGHTER     "            "SDSENGOKUDEN3   "           "TENNIS          "
"MOMOTARODENGEKI2"          "PITMAN          "            "SEASIDE VOLLEY  "           "TENSAIBAKABON   "
"MOMOTETU        "          "POCAHONTAS      "            "SEIKEN DENSETSU "           "TERMINATOR 2    "
"MONOPOLY        "          "POCKET BATTLE   "            "SEIKEN DENSETSU "           "TERMINATOR 2 TAG"
"MONOPOLY        "          "POCKET STADIUM  "            "SELECTION       "           "TESSERAE        "
"MONSTER MAKER J "          "POKONYAN!       "            "SENGOKUNINJAKUN "           "TETRIS          "
"MONSTER MAKER J2"          "POP UP          "            "SENSIBLE SOCCER "           "TETRIS ATTACK   "
"MONSTER MAX     "          "POPEYE          "            "SERPENT         "           "TETRIS BLAST    "
"MONSTERTRUCK    "          "POPEYE 2        "            "SHADOW WARRIORS "           "TETRIS FLASH    "
"MORTAL KOMBAT   "          "POPEYE 2        "            "SHANGHAI        "           "TETRIS PLUS     "
"MORTAL KOMBAT 3 "          "POPULOUS        "            "SHANGHAI        "           "TETRIS2         "
"MORTAL KOMBAT II"          "POWER MISSION   "            "SHIKINJYO       "           "THE CHESSMASTER "
"MOTOCROSSMANIACS"          "POWER MISSION   "            "SHIPPO DE BUN   "           "THE FIDGETTS    "
"MOUSE TRAP HOTEL"          "POWER RACER     "            "SHISENSYO       "           "THE FIDGETTS    "
"MR NUTZ         "          "POWER RANGERS   "            "SHOOT           "           "THE FLASH       "
"MR.DO!          "          "PREHISTORIK MAN "            "SHOOT           "           "THE FLINTSTONES "
"MS.PAC-MAN      "          "PRIMAL RAGE     "            "SHOUGI          "           "THE GETAWAY     "
"MUHAMMAD ALI    "          "PRINCE OF PERSIA"            "SI KIDS         "           "THE GETAWAY     "
"MYSTERIUM       "          "PRINCE OF PERSIA"            "SIDE POCKET     "           "THE HUMANS      "
"MYSTIC QUEST    "          "PRIPRI          "            "SIMPSON ESCAPE  "           "THE JETSONS     "
"MYSTIC QUEST    "          "PRO SOCCER      "            "SIMPSONS 2      "           "THE LION KING   "
"MYSTIC QUEST    "          "PROBOTECTOR     "            "SIMPSONS3       "           "THE PAGEMASTER  "
"NAIL N SCALE    "          "PROBOTECTOR 2   "            "SKATE OR DIE BAD"           "THE PUNISHER    "
"NAMCOCLASSIC    "          "PROWRES         "            "SMURFS          "           "THE SWORD OFHOPE"
"NASCARFASTTRACKS"          "PROWRES         "            "SNEAKY SNAKES   "           "THE SWORD OFHOPE"
"NAVY BLUE       "          "PURO STADIUM '91"            "SNOOPY          "           "THEGAMEOFHARMONY"
"NAVY BLUE 90    "          "PUYOPUYO        "            "SNOOPY          "           "THUNDERBIRDS    "
"NAVY SEALS      "          "PUZZLE BOY      "            "SNOW BROS.JR    "           "TINTIN AU TIBET "
"NBA JAM         "          "PUZZLE BOY2     "            "SOCCER          "           "TINY TOON 2     "
"NBA JAM TE      "          "PUZZLE ROAD     "            "SOCCER BOY      "           "TINY TOON 3     "
"NEKOJARA        "          "PUZZNIC         "            "SOCCER MANIA    "           "TINY TOON ADVENT"
"NEMESIS         "          "PYRAMIDS OF RA  "            "SOLARSTRIKER    "           "TINY TOON ADVENT"
"NEMESIS 2       "          "Q*BERT II       "            "SOLDAM GB       "           "TINY TOON WACKY "
"NETTOU KOF 95   "          "Q*BERT II       "            "SOLITAIRE FUNPAK"           "TITUS THE FOX   "
"NETTOU SAMURAI  "          "QBILLION        "            "SOLOMON'S CLUB  "           "TMHT BACK FROM S"
"NETTOU TOSHINDEN"          "QIX             "            "SOLOMON'S CLUB  "           "TMNT            "
"NETTOU WH2JET   "          "QUARTH          "            "SOUKOBAN        "           "TMNT 2          "
"NETTOU WH2JET   "          "QUARTH          "            "SOUKOBAN2       "           "TMNT BACK FROM S"
"NETTOUGAROU2    "          "RACE DAYS       "            "SPACE INVADERS  "           "TMNT FOOT CLAN  "
"NEW CHESSMASTER "          "RACE DRIVIN'    "            "SPACE INVADERS  "           "TMNT3           "
"NFL FOOTBALL    "          "RACING DAMASHII "            "SPANKY'S QUEST  "           "TMNT3           "
"NFL QB CLUB 96  "          "RADARMISSION    "            "SPARTAN X       "           "TOM AND JERRY   "
"NHL HOCKEY 95   "          "RADARMISSION    "            "SPARTAN X       "           "TOM AND JERRY   "
"NINJA BOY       "          "RAGING FIGHTER  "            "SPEED BALL 2    "           "TOM AND JERRY   "
"NINJA BOY 2     "          "RAIJINOU        "            "SPEEDY GONZALES "           "TOPGUN 2        "
"NINJA GAIDEN    "          "RAMPART_GB      "            "SPEEDY GONZALES "           "TOPRANKTENNIS   "
"NINJA GAIDEN    "          "RANMA 1/2       "            "SPELL CHECKER   "           "TORPEDO RANGE   "
"NINJA RYUKENDEN "          "RANMA1/2 PART2  "            "SPIDER-MAN      "           "TOTAL CARNAGE   "
"NINJA SPIRIT    "          "RANMA1/2KAKUGEKI"            "SPIDER-MAN 2    "           "TOTSUGEKI TANK  "
"NINJATARO       "          "RAY THUNDER     "            "SPIDER-MAN 3 DMG"           "TOUR DE THRASH  "
"NOBUNAGA        "          "REAL GHOSTBUSTER"            "SPIDERMAN XMEN  "           "TOXIC CRUSADERS "
"NOBUNAGA        "          "RED ARREMER     "            "SPIROU          "           "TOY STORY       "
"NORTH STAR      "          "RED OCTOBER     "            "SPOT            "           "TRACK MEET      "
"OBELIX          "          "RED OCTOBER     "            "SPOT            "           "TRACK MEET      "
"OIRA JAJAMARU   "          "RIDDICK BOWE    "            "SPOT COOL ADV   "           "TRACK MEET HIRO "
"OLYMPIC GAMES   "          "RING RAGE       "            "SPUDS ADVENTURE "           "TRACK&FIELD     "



"TRAPPERS TENGOKU"          "VEGAS STAKES    "           "WORLD BOWLING   "            "YUUYUU-2        "
"TRAX            "          "VEGAS STAKES    "           "WORLD BOWLING   "            "YUUYUU-3        "
"TRIP WORLD      "          "VELIOUS 2       "           "WORLD CUP       "            "YUUYUU-4        "
"TRSI RULEZ      "          "VERSUS HERO     "           "WORLD CUP       "            "ZELDA           "
"TRUE LIES       "          "VIRTUAL WARS    "           "WORLD CUP USA 94"            "ZELDA           "
"TSUMEGO 01      "          "VITAMINA KINGDOM"           "WORLD CUP USA 94"            "ZELDA           "
"TUBASA VS       "          "VOLLEY FIRE     "           "WORLD ICE HOKKEY"            "ZELDA           "
"TUMBLE POP      "          "VS BATTLER      "           "WORMS           "            "ZEN             "
"TUMBLE POP      "          "W CIRCUIT SERIES"           "WWF RAW         "            "ZEOTH           "
"TURN AND BURN   "          "WARIO BLAST     "           "WWF SUPERSTARS  "            "ZOIDS J1        "
"TURRICAN        "          "WARRIOR         "           "WWF SUPERSTARS  "            "ZOOL            "
"TWIN            "          "WATER WORLD     "           "WWFKOR          "            "ZOOP            "
"TWIN-BEE DA!!   "          "WAVERACE        "           "WWFKOR          "
"ULTRA GOLF      "          "WAYNE'S WORLD   "           "X               "
"ULTRAMAN        "          "WEREBACK BC     "           "XENON 2         "
"ULTRAMAN BALL   "          "WH2JET          "           "XENON 2         "
"ULTRAMAN GEKIDEN"          "WHEEL OF FORTUNE"           "XERD!!          "
"UNDER COVER COPS"          "WILDSNAKE       "           "YAIBA           "
"UNIVERSALSOLDIER"          "WINNING GOAL    "           "YAKUMAN         "
"UNO2SMALL WORLD "          "WINTER GOLD     "           "YAMATO          "
"UOOZU           "          "WINTER OLYMPICS "           "YOGIS GOLDRUSH  "
"URUSEIYATSURA   "          "WIZARDRY SOQ    "           "YOSHI'S COOKIE  "
"VALATIONS       "          "WIZARDS WARRIORS"           "YOSSY NO TAMAGO "
"VATTLE GIUCE    "          "WORDTRIS        "           "YOSSY NO TAMAGO "
"VEEDIOTS        "          "WORDZAP         "           "YUUYUU-1        "
```
###
# Add new games ??
###

Now, you can put new games with the possibility of creating a Json with the list of games that you want.

You only need to generate a list of items list.
The index 0 corresponds to the columns and the rest of the elements to the rows.

Automaticly will be generated the columns and rows of game list


```kotlin
[
   ["Load",   "ROM Game",      "Game Name",        "ROM Type",    "Size (in bytes)"],
   [false,    "YYYY.GB    "," ---  YYYY  --- ",    "XXX+ZZZZ  ",       65.536],
   [ true,    "XXXX.GB    "," ---  XXXX  --- ",    "YYY+ZZZZ  ",       65.536],
   [ ....,    ".....GB    "," ---  ....  --- ",    "  ......  ",       ......]
]
```

@Author : Victor Bolinches Marin

@Author : vicboma1

# Note: This homebrew project does not contain ROMs
