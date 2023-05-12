# HynseBackup

HynseBackup is an easy-to-use auto backup plugin for Minecraft servers.

## Features

- Automatic backups: Enable automatic backups at a specified interval. Backups can be scheduled to occur periodically after the server starts.
- Whitelisted worlds: Specify a list of worlds to include in the backups.
- Maximum backup count: Limit the number of backups to keep. Older backups will be automatically deleted when the maximum count is reached.
- Compression options: Choose between different compression modes - zip, zstd, or zstd_experimental. Adjust compression levels and enable progress display using a boss bar.
- Backup management: Use commands to manually start backups for specific worlds or list existing backups.
- Permissions support: Configure permissions to control who can start backups and list backups.

## Installation

1. Download the HynseBackup.jar file from the [modrinth](https://modrinth.com/plugin/hynsebackup).
2. Place the HynseBackup.jar file in your server's `plugins` folder.
3. Start your server. The plugin will automatically generate the default configuration file.

## Configuration

The HynseBackup plugin can be configured through the `config.yml` file located in the plugin's folder. Below is an example configuration:

```yaml
auto:
  # Enable automatic backups
  enabled: true
  # Backup interval in seconds (72000 seconds = 20 hours)
  interval: 72000
  # Delay before the first backup after server start, in seconds
  delay: 60

whitelist_world:
  # List of worlds to be included in backups
  - world
  - world_nether
  - world_the_end

max_backup:
  # Enable limiting the number of backups
  enabled: true
  # Maximum number of backups to keep
  count: 3

compression:
  # Compression mode: zip, zstd, zstd_experimental
  mode: zstd

  zstd:
    # Zstd compression level (1-22)
    level: 3
    # Parallelism level for zstd_experimental compression
    # Note: zstd_experimental mode is experimental and may cause performance issues.
    # It increases the server's memory when using zstd_experimental mode.
    parallelism: 4

  zip:
    # Zip compression level ((-1)-9)
    level: -1

  # Enable progress display using a boss bar
  bossbar: true
```
## Commands
- `/backup start <world>`: Starts a backup for the specified world.
- `/backup list`: Lists existing backups.
## Permissions
- `hynsebackup.start`: Allows the player to start a backup.
- `hynsebackup.list`: Allows the player to list backups.
## Contributing
Contributions to the HynseBackup plugin are welcome! If you would like to contribute
## License
This plugin is licensed under the MIT license. See LICENSE.md for more information.

