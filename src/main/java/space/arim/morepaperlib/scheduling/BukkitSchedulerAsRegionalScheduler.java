/*
 * MorePaperLib
 * Copyright © 2023 Anand Beh
 *
 * MorePaperLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MorePaperLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MorePaperLib. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Lesser General Public License.
 */

package space.arim.morepaperlib.scheduling;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import space.arim.morepaperlib.MorePaperLib;

import java.util.function.Consumer;

@SuppressWarnings("deprecation")
final class BukkitSchedulerAsRegionalScheduler implements RegionalScheduler {

	private final BukkitScheduler scheduler;
	private final Plugin plugin;

	private final boolean hasMethodRunTask;
	private final boolean hasMethodRunTaskLater;
	private final boolean hasMethodRunTaskTimer;

	BukkitSchedulerAsRegionalScheduler(MorePaperLib morePaperLib) {
		Plugin plugin = morePaperLib.getPlugin();
		this.scheduler = plugin.getServer().getScheduler();
		this.plugin = plugin;
		hasMethodRunTask = morePaperLib.methodExists(
				BukkitScheduler.class, "runTask", Plugin.class, Consumer.class
		);
		hasMethodRunTaskLater = morePaperLib.methodExists(
				BukkitScheduler.class, "runTaskLater", Plugin.class, Consumer.class, long.class
		);
		hasMethodRunTaskTimer = morePaperLib.methodExists(
				BukkitScheduler.class, "runTaskTimer", Plugin.class, Consumer.class, long.class, long.class
		);
	}

	void cancelTasks() {
		scheduler.cancelTasks(plugin);
	}

	@Override
	public ScheduledTask run(Runnable command) {
		return new PaperTask(
				scheduler.runTask(plugin, command)
		);
	}

	@Override
	public void run(Consumer<ScheduledTask> command) {
		if (hasMethodRunTask) {
			scheduler.runTask(plugin, (bukkitTask) -> command.accept(new PaperTask(bukkitTask)));
			return;
		}
		BukkitTaskConsumerToRunnable.setup(
				command, (runnable) -> scheduler.runTask(plugin, runnable)
		);
	}

	@Override
	public ScheduledTask runDelayed(Runnable command, long delay) {
		return new PaperTask(
				scheduler.runTaskLater(plugin, command, delay)
		);
	}

	@Override
	public void runDelayed(Consumer<ScheduledTask> command, long delay) {
		if (hasMethodRunTaskLater) {
			scheduler.runTaskLater(plugin, (bukkitTask) -> command.accept(new PaperTask(bukkitTask)), delay);
			return;
		}
		BukkitTaskConsumerToRunnable.setup(
				command, (runnable) -> scheduler.runTaskLater(plugin, runnable, delay)
		);
	}

	@Override
	public ScheduledTask runAtFixedRate(Runnable command, long initialDelay, long period) {
		return new PaperTask(
				scheduler.runTaskTimer(plugin, command, initialDelay, period)
		);
	}

	@Override
	public void runAtFixedRate(Consumer<ScheduledTask> command, long initialDelay, long period) {
		if (hasMethodRunTaskTimer) {
			scheduler.runTaskTimer(plugin, (bukkitTask) -> command.accept(new PaperTask(bukkitTask)), initialDelay, period);
			return;
		}
		BukkitTaskConsumerToRunnable.setup(
				command, (runnable) -> scheduler.runTaskTimer(plugin, runnable, initialDelay, period)
		);
	}

}
