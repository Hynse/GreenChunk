GreenChunk is a plugin designed to help players easily identify and locate slime chunks within the game world. Slime chunks are specific areas where slimes can spawn, making them valuable for players who want to collect slime balls or create slime farms.

# Key Features:

1.  In-game command to check if you're in a slime chunk: By typing `/slime`, players can instantly find out whether they are currently standing in a slime chunk or not. The plugin will display a message in green or red to indicate the presence or absence of a slime chunk at the player's location.
    
2.  Slime map item: Players can use a configurable item (default is a slime ball) to open a graphical user interface (GUI) that displays a slime chunk map. This map shows the surrounding area and highlights the slime chunks, making it easier for players to locate and navigate to them.
    
3.  Permission-based access: Server administrators can control which players have access to the plugin's features by configuring permissions.
    
4.  Configurable messages and settings: The plugin allows server administrators to customize messages, item used for opening the slime chunk map, and enabling or disabling the slime map item feature.
    
5.  Multi-language support: GreenChunk has built-in support for different languages, making it accessible to a global audience. New languages can be added by creating a pull request on the plugin's GitHub repository.
    
> In summary, GreenChunk is a user-friendly Minecraft plugin that assists players in locating slime chunks with ease. By providing a simple command and a convenient GUI-based map, players can save time and effort while searching for these valuable resources.

# Image In-Game
### Slime chunk Map GUI
![SlimeChunk Map Gui](https://cdn.discordapp.com/attachments/1069509162989523015/1101220991801237545/image.png)
### Slime chunk Chat
![SlimeChunk Chat](https://media.discordapp.net/attachments/1069509162989523015/1101221387005337610/image.png)


# Permissions & Commands
| Command | Description  | Permission | Default |
|--|--|--|--|
| `/slime` | Check slime at current position | `greenchunk.check` | `true`|
| `/slimemap` | Open slimechunk map around current position | `greenchunk.map` | `true`|
| `/greenchunkreload` | Reload the GreenChunk config | `greenchunk.reload` | `false`|
| `/slimereload` | Reload the GreenChunk config | `greenchunk.reload` | `false`|
|  | Allows players to use the slime map item | `greenchunk.map.item` | `true`|
|  | Allows players to use check slime chunk with item. | `greenchunk.item` | `true`|

# Config.yml
```yml
# If you can translate or create new languages please create a Pull Request
# https://github.com/Hynse/GreenChunk


# If you want to change lang just uncomment that section

### English ------------------------------------------------------
# Configuration for the SlimeCommand
slime-command:
  messages:
    not-in-chunk: "&cYou are not in a slime chunk (&e%d&7,&e%d&c)!"
    in-chunk: "&aYou are in a slime chunk (&e%d&7,&e%d&a)!"
    no-permission: '&cYou do not have permission to use this command!'
    not-player: '&cYou must be a player to use this command!'
# Configuration for the ReloadCommand
reload-command:
  messages:
    reload-config: '&eGreenChunk config reloaded.'
    no-permission: '&cYou do not have permission to use this command!'
    error-reload-config: '&cSomething went wrong reloading GreenChunk config, see the console for more.'
# Configuration for the Gui Map
gui:
  messages:
    title: 'Slime Maps'
    no-permission: '&cYou do not have permission to use this command!'
    not-player: '&cYou must be a player to use this command!'
    not-slimechunk: 'Not Slime Chunk'
    is-slimechunk: 'Is Slime Chunk'
    item: 'You do not have permission to use item!'
# Slimemap Settings
enableSlimeMapItem: true
slimeMapItem: SLIME_BALL
### ---------------------------------------------------------------


### Chinese Simplified --------------------------------------------
##slime-command:
#  messages:
#    not-in-chunk: '&c你不在X:&e%d&c, Z:&e%d&c的史莱姆区块内！'
#    in-chunk: '&a你在X:&e%d&a, Z:&e%d&a的史莱姆区块内！'
#    no-permission: '&c&c你没有权限使用这个指令！'
#    not-player: '&c你必须是玩家才能使用该命令！'
#reload-command:
#  messages:
#    reload-config: '&eGreenChunk配置已重新加载'
#    no-permission: '&c你没有权限使用这个指令！'
#    error-reload-config: '&c重新加载GreenChunk配置出现了问题，请查看控制台获取更多信息'
#gui:
#  messages:
#    title: 'Slime Maps'
#    no-permission: '&cYou do not have permission to use this command!'
#    not-player: '&cYou must be a player to use this command!'
#    not-slimechunk: 'Not Slime Chunk'
#    is-slimechunk: 'Is Slime Chunk'
#    item: '&cYou do not have permission to use item!'
#enableSlimeMapItem: true
#slimeMapItem: SLIME_BALL
### ---------------------------------------------------------------


### Thailand --------------------------------------------
#slime-command:
#  messages:
#    not-in-chunk: "&cคุณไม่ได้อยู่ในสลามชักค์ (&e%d&7,&e%d)&c!"
#    in-chunk: "&aคุณอยู่ในสลามชักค์ (&e%d&7,&e%d)&a!"
#    no-permission: "&cคุณไม่มีสิทธิ์ในการใช้คำสั่งนี้!"
#    not-player: "&cคุณต้องเป็นผู้เล่นเท่านั้นที่จะใช้คำสั่งนี้!"
#reload-command:
#  messages:
#    reload-config: "&eGreenChunk รีโหลดคอนฟิกแล้ว"
#    no-permission: "&cคุณไม่มีสิทธิ์ในการใช้คำสั่งนี้!"
#    error-reload-config: "&cเกิดข้อผิดพลาดขณะโหลดคอนฟิก GreenChunk โปรดดูที่คอนโซลสำหรับข้อมูลเพิ่มเติม"
#gui:
#  messages:
#    title: 'Slime Maps'
#    no-permission: '&cYou do not have permission to use this command!'
#    not-player: '&cYou must be a player to use this command!'
#    not-slimechunk: 'Not Slime Chunk'
#    is-slimechunk: 'Is Slime Chunk'
#    item: 'You do not have permission to use item!'
#enableSlimeMapItem: true
#slimeMapItem: SLIME_BALL
### ---------------------------------------------------------------```
```
## Contributing

If you would like to contribute to FoliaFlow, feel free to submit a pull request with your changes. All contributions are welcome and appreciated.

## License

This plugin is licensed under the MIT license. See [LICENSE.md](https://github.com/Hynse/GreenChunk/blob/master/LICENSE.md) for more information.
