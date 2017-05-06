Semantic Versioning
=========

```
Given a version number MAJOR.MINOR.PATCH, increment the:

MAJOR version when you make incompatible API changes,
MINOR version when you add functionality in a backwards-compatible manner, and
PATCH version when you make backwards-compatible bug fixes.
Additional labels for pre-release and build metadata are available as extensions to the MAJOR.MINOR.PATCH format.
```

#### version 0.2.3  - WIP
```
MenuBar Options
    MenuItemView { Delay load Async - Permits } / Dynamic configuration with sliderBar
Custom Sempahore
Add MenuItemsView Grid by Rom Name
Processing with Coroutine + async
```
## Averaging Time  - 12 executions [ version 0.2.3 ]

|                    	|  Coroutine  	|  Coroutine  	|     Coroutine     	|     Coroutine     	|
|:------------------:	|:-----------:	|:-----------:	|:-----------------:	|:-----------------:	|
|     RunBlocking    	|     True    	|     True    	|        True       	|        True       	|
|                    	| launch<Job> 	| launch<Job> 	|  async<Deferred>  	|  async<Deferred>  	|
|       Context      	|  CommonPool 	|  CommonPool 	|     CommonPool    	|     CommonPool    	|
| Mutex Optimization 	|    False    	|     True    	|       False       	|        True       	|
|      Time [ms]     	|   70 - 90   	|   70 - 80   	|      65 - 90      	|      50 - 60      	|
|         Job        	|     wait    	|     wait    	|                   	|                   	|
|      Time [ms]     	| 70 - 110    	| 70 - 95     	|                   	|                   	|
|      Deffered      	|             	|             	| CompletableFuture 	| CompletableFuture 	|
|      Time [ms]     	|             	|             	|       65-80       	|       45-50       	|


#### version 0.2.2 
```
Add MenuItemsView Grid by scale { 1x, 2x, 3x, 4x}
Create Images in memory
Asynchronous GridView
```

#### version 0.2.1
```
Add MenutItemView { Grid - List }
Grid {View - Model - Scroll} based in Table with multiple rows cached
Model { Image - NameRom }
```
## Averaging Time  - 12 executions [ version 0.2.1 ]

|                        	|  ThreadPool  	|  ThreadPool 	|  ThreadPool 	|  ThreadPoolExecutor 	|       Thread      	|       Thread      	|
|:----------------------:	|:------------:	|:-----------:	|:-----------:	|:-------------------:	|:-----------------:	|:-----------------:	|
|        Executor        	|    Cached    	|  Fixed(1)   	|  Fixed(4)   	|      Fixed(4)       	| ForkJoinPool(4)   	| ForkJoinPool(1)   	|
|     Concurrent  EDA    	|  LinkedDeque 	| LinkedDeque 	| LinkedDeque 	| LinkedBlockingDeque 	|    LinkedQueue    	|    LinkedQueue    	|
| Parallelism            	| False        	| False       	|    False    	|         True        	|        True       	| True              	|
| wait-free Optimization 	|     False    	|    False    	|    False    	|         True        	|       False       	|       False       	|
| Time [ms]              	| 1970 - 2250  	| 2100 - 2450 	| 1700 - 1900 	|     1200 - 1300     	|    1200 - 1340    	| 1900 - 2100       	|
|    CompletableFuture   	|   Supplier   	|   Supplier  	|   Supplier  	|       Supplier      	|      Supplier     	|      Supplier     	|
|  Time [ms]             	| 1500 - 1950  	| 1500 - 2000 	| 1420 - 1700 	| 1100 - 1350         	| 700 - 1240        	| 1500 - 1700       	|


#### version 0.2.0
```
External dependencies of the list of games with json protocol (DDD)
MessageDialog to show the absolutePath of the rom when press enter key
Asynchronous window minimization
Optimize tab selection
```

#### version 0.1.1
```
Fixed row boolean table sort
Optimization 
    Repaint table with daemon
    Circular navigation tab

```

#### version 0.1.0
```
Table List
   Listerner InputKey - [ Left - Right ]
   Initialize sort column by game
   
Circular navigation for tab pane child

```

#### version 0.0.2
```
Fixed navigation table list Up - Down limits
Fixed status bar with static text
Fixed selected row

Table List
   Enable row
   Listerner InputKey - [ Up - Down - Enter ]

Tab Pane
   Cartridge, snapshot Component
   Main image
   Background image

Rom, cartridge, snapshot folders
```

#### version 0.0.1
```
Status Bar
  * Animation text
  
Split Pane
  * Table List
       ColumnHeader - [ load - rom - game - type - size ]
       RowHeader
       Game list
       Disable row
       AutoScroll - [ H - V ] 
       Listerner - [ ColumnHeader - RowHeader ]
       Transparent delimiter row

  * Tab Pane
       Cover Component
       Image
       
Cover folder
```
